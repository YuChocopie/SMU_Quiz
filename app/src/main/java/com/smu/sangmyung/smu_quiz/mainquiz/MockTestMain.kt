package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.BaseActivity
import com.smu.sangmyung.smu_quiz.DataClass.QuizSubject
import com.smu.sangmyung.smu_quiz.mainquiz.Choice
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.SmuQuizAIP
import com.smu.sangmyung.smu_quiz.SmuQuizInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.smu.sangmyung.smu_quiz.mainquiz.MockQuiz
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.item_global_title.*
import java.util.*

class MockTestMain : BaseActivity(){

    var pr_total_correct_num =0 //총 맞춘 문제 개수
    var pr_num:Int = 0 // getMocktest[position]에서 position

    var subject = mutableListOf<QuizSubject>(
        QuizSubject("Database"),
        QuizSubject("Algorighme"),
        QuizSubject("operation_system")
    )
    //모의고사 30개 답 true, false 저장할 리스트
    var isCorrect = mutableListOf(
        false,false,false,false,false,false,false,false,false,false,
        false,false,false,false,false,false,false,false,false,false,
        false,false,false,false,false,false,false,false,false,false
    )

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuDailyInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)


    //답 맞췄는지 틀렸는지
    private fun isResult(answer:Int ,num:Int, choice:MutableList<Choice>){
        if(answer == num){
            isCorrect[pr_num] = true
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


    private fun callQuiz(pr_num:Int,subject: String,choice: MutableList<Choice>){
        smuDailyInterface.getMocktest(subject)
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
                tvChoice4.text = "4. ${result[pr_num].choice_4}"
                val correctAnswer = result[pr_num].answer

                //1번 선택했을 때
                tvChoice1.setOnClickListener {
                    isResult(correctAnswer, 1, choice)
                }
                //2번 선택했을 때
                tvChoice2.setOnClickListener {
                    isResult(correctAnswer, 2, choice)
                }
                //3번 선택했을 때
                tvChoice3.setOnClickListener {
                    isResult(correctAnswer, 3, choice)
                }
                //4번 선택했을 때
                tvChoice4.setOnClickListener {
                    isResult(correctAnswer, 4, choice)
                }

                var i =0
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

            }, {
                    error ->
                error.printStackTrace()
                Toast.makeText(this,"오류",Toast.LENGTH_SHORT).show()
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete")
            })


        //다음문제로 넘어가면 별 무조건 해제
        ivMainLike.setImageResource(R.drawable.like_empty)

        //다음문제로 넘어가면 choice 글씨 색 모두 블랙으로 바꾸기
        for (m in 0..3) {
            choice[m].tvChoice.setTextColor(Color.BLACK)
        }
        //문제 증가한거 표시 Question1, Question2, ...
        tvMainQuestionNum.text = "Question ${pr_num + 1}"

    }

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        tvStop.text = "prev"

        //맞추면 true or 틀리면 false
        //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
        var choice= mutableListOf<Choice>(
            Choice(tvChoice1),
            Choice(tvChoice2),
            Choice(tvChoice3),
            Choice(tvChoice4)
        )

        // 선택한 과목
        val subjectSelect: MutableList<String>? = intent.getStringArrayListExtra("subject")

        Log.d("shivar",subjectSelect.toString())
        //과목 불러오기
        var sss=""
        if(subjectSelect != null){
            val size = subjectSelect.size-1
            for(i in 0..size){
                if(i != subjectSelect.size-1) {
                    sss += "subject=${subjectSelect[i]}&"
                }else{
                    sss += "subject=${subjectSelect[i]}"
                }
            }
        }

        Log.d("gagaga",sss)

<<<<<<< Updated upstream
        //다음 문제로 넘어가기
        tvNext.setOnClickListener {
            //문제수 1씩 증가
            //TODO::과목별 check 더하기
            pr_num += 1
          
            if(pr_num < isCorrect.size){
                //TODO::즐겨찾기 보내기
=======
        callQuiz(pr_num,sss,choice)
>>>>>>> Stashed changes

        /*var size = 0
        var all:String=""
        if(subjectSelect!=null) {
            size = subjectSelect.size

            while (true){
                if (size == 0){
                    break
                }
                var sss = subjectSelect
                all += sss[size-1]
               size--
            }
        }*/

        //다음 문제로 넘어가기
        tvNext.setOnClickListener {
            pr_num+=1
            if(pr_num < 5){
                callQuiz(pr_num,sss,choice)
            }
            //모의고사 다 풀었을 때
            else{
                //맞춘문제 수 세기
                for(m in 0..isCorrect.size-1){
                    if(isCorrect[m] == true){
                        pr_total_correct_num += 1
                    }
                }
                //TotalResult로 총 맞춘문제, 총 문제 수 전달
                val intent = Intent(this, TotalResult::class.java)
                intent.putExtra("total_correct_num",pr_total_correct_num) //총 맞춘문제
                intent.putExtra("total_pr_num",pr_num) // 총 문제 수
                startActivityForResult(intent, 3000)
            }
            //TODO::과목별 check 더하기
            //TODO::즐겨찾기 보내기
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                3000 -> finish()
            }
        }
    }
}


