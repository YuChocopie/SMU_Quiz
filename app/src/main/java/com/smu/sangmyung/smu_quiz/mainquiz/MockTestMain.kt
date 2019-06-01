package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.mainquiz.Choice
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.SmuQuizAIP
import com.smu.sangmyung.smu_quiz.SmuQuizInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.smu.sangmyung.smu_quiz.mainquiz.MockQuiz
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.item_global_title.*

class MockTestMain : AppCompatActivity(){

    var pr_total_correct_num =0 //총 맞춘 문제 개수
    var pr_num:Int = 0 // mocktest[position]에서 position

    //모의고사 30개 리스트 넣어서 처리하기
    var mocktest = mutableListOf<MockQuiz>(
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false),
        MockQuiz(false)
    )


    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuDailyInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?){

        var i = 0 // 즐겨찾기 처리할 때 사용

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        tvStop.text = "prev"

        val finishable = intent.getBooleanExtra("finish", false)
        if (finishable) {
            finish()
        }

        tvGlobalTitle.text=("Mock Test")

        //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
        var choice= mutableListOf<Choice>(
            Choice(tvChoice1),
            Choice(tvChoice2),
            Choice(tvChoice3),
            Choice(tvChoice4)
        )

        // 답 선택했을 때
        fun isResult(answer:Int ,num:Int){
            if(answer == num){
                for (m in 0..3) {
                    if (m != num-1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.BLUE)
                    }
                }

            }else{
                for (m in 0..3) {
                    if (m != num-1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.BLUE)
                    }
                }
            }
        }

        fun callQuiz(pr_num:Int){
            smuDailyInterface.mocktest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    // data 를 받아 처리합니다.
                    // 작업 중 오류가 발생하면 이 블록은 호출되지 않습니다
                    tvGlobalTitle.text = "Mock Test"
                    tvMainQuestionContent.text = result[pr_num].title
                    tvChoice1.text = "1. ${result[pr_num].choice_1}"
                    tvChoice2.text = "2. ${result[pr_num].choice_2}"
                    tvChoice3.text = "3. ${result[pr_num].choice_3}"
                    tvChoice4.text = "4. ${result[pr_num].choice_4}f"
                    val correctAnswer = result[pr_num].answer

                    //1번 선택했을 때
                    tvChoice1.setOnClickListener {
                        isResult(correctAnswer, 1)
                    }
                    //2번 선택했을 때
                    tvChoice2.setOnClickListener {
                        isResult(correctAnswer, 2)
                    }
                    //3번 선택했을 때
                    tvChoice3.setOnClickListener {
                        isResult(correctAnswer, 3)
                    }
                    //4번 선택했을 때
                    tvChoice4.setOnClickListener {
                        isResult(correctAnswer, 4)
                    }

                }, {
                        error ->
                    error.printStackTrace()
                    Toast.makeText(this,"오류",Toast.LENGTH_SHORT).show()
                }, {
                    // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                    Log.d("Result", "complete")
                })

            ivMainLike.setImageResource(R.drawable.like_empty)

            for (m in 0..3) {
                choice[m].tvChoice.setTextColor(Color.BLACK)
            }
            tvMainQuestionNum.text = "Question ${pr_num + 1}"
        }

        callQuiz(pr_num)

        //다음 문제로 넘어가기
        tvNext.setOnClickListener {
            //문제수 1씩 증가
            pr_num += 1
            if(pr_num < mocktest.size){
                ivMainLike.setImageResource(R.drawable.like_empty)
                callQuiz(pr_num)
            }

            //모의고사 다 풀었을 때
            else{
                //맞춘문제 수 세기
                for(m in 0..mocktest.size-1){
                    if(mocktest[m].correct == true){
                        pr_total_correct_num += 1
                    }
                }
                //TotalResult로 총 맞춘문제, 총 문제 수 전달
                val intent = Intent(this, TotalResult::class.java)
                intent.putExtra("total_correct_num",pr_total_correct_num) //총 맞춘문제
                intent.putExtra("total_pr_num",pr_num) // 총 문제 수
                startActivity(intent)
            }
        }


        //즐겨찾기 별 클릭, 해제
        ivMainLike.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View){
                i = 1-i
                if(i == 1){
                    ivMainLike.setImageResource(R.drawable.like_fill)

                }else{
                    ivMainLike.setImageResource(R.drawable.like_empty)
                }
            }
        })

        //이전 문제로 돌아가기
        tvStop.setOnClickListener {
            //문제 수 감소
            pr_num -= 1
            if(pr_num >= 0) {
            }else{
                Toast.makeText(this,"이전 문제가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
            }

        }

    }

}

