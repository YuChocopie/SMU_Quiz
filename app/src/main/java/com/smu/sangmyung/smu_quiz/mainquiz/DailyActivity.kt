package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.DataClass.QuizSubject
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.mainquiz.Choice
import com.smu.sangmyung.smu_quiz.SmuQuizAIP
import com.smu.sangmyung.smu_quiz.SmuQuizInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.item_global_title.*


class DailyActivity : AppCompatActivity() {

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuDailyInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    var subject = mutableListOf<QuizSubject>(
        QuizSubject("Databse"),
        QuizSubject("Algorighme"),
        QuizSubject("operation_system")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        var i = 0 //즐겨찾기 처리할 변수
        var pr_num = 0 //문제 수
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
        var choice = mutableListOf<Choice>(
            Choice(tvChoice1),
            Choice(tvChoice2),
            Choice(tvChoice3),
            Choice(tvChoice4)
        )

        // 답 선택했을 때 맞는지 틀리는지
        fun isResult(answer:Int ,num:Int){
            if(answer == num){
                ivAnswerCorrect.visibility = View.VISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                for (m in 0..3) {
                    if (m != num-1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.BLUE)
                    }
                }

            }else{
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.VISIBLE
                for (m in 0..3) {
                    if (m != num-1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.RED)
                    }
                }
            }
        }

        //문제 불러오기 && xml설정하기
        fun callQuiz(){
            smuDailyInterface.getDailyQuiz("Database")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    // data 를 받아 처리합니다.
                    // 작업 중 오류가 발생하면 이 블록은 호출되지 않습니다
                    tvGlobalTitle.text = result[0].subject
                    tvMainQuestionContent.text = result[0].title
                    tvChoice1.text = "1. ${result[0].choice_1}"
                    tvChoice2.text = "2. ${result[0].choice_2}"
                    tvChoice3.text = "3. ${result[0].choice_3}"
                    tvChoice4.text = "4. ${result[0].choice_4}"
                    val correctAnswer = result[0].answer

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

            ivAnswerCorrect.visibility = View.GONE
            ivAnswerWrong.visibility = View.GONE

            ivMainLike.setImageResource(R.drawable.like_empty)

            for (m in 0..3) {
                choice[m].tvChoice.setTextColor(Color.BLACK)
            }
            tvMainQuestionNum.text = "Question ${pr_num + 1}"
        }

        callQuiz()

        //문제 풀다가 중간에 그만둘 때
        tvStop.setOnClickListener {
            val intent = Intent(this, StopActivity::class.java)
            startActivity(intent)

        }

        val finishable = intent.getBooleanExtra("finish", false)
        if (finishable) {
            finish()
        }
        //다음문제로 넘어가기
        tvNext.setOnClickListener {
            callQuiz()
            pr_num += 1
            callQuiz()
        }

        //즐겨찾기 체크 or 해제
        ivMainLike.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                i = 1 - i
                if (i == 1) {
                    ivMainLike.setImageResource(R.drawable.like_fill)
                } else {
                    ivMainLike.setImageResource(R.drawable.like_empty)
                }
            }
        })
    }
}


