package com.example.smu_quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.*
import com.smu.sangmyung.smu_quiz.Quiz.Choice
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_daily.*

class DailyActivity : AppCompatActivity() {

    var questionList = mutableListOf<Question>(

        Question("1","","subject1","problem1","choice1-1","choice1-2","choice1-3","choice1-4", "1",""),
        Question("2","","subject2","problem2","choice2-1","choice2-2","choice2-3","choice2-4", "2",""),
        Question("3","","subject3","problem3","choice3-1","choice3-2","choice3-3","choice3-4", "3",""),
        Question("4","","subject4","problem4","choice4-1","choice4-2","choice4-3","choice4-4", "4",""),
        Question("5","","subject5","problem5","choice5-1","choice5-2","choice5-3","choice5-4", "1","")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        var i = 0 //즐겨찾기 처리할 변수
        var pr_num = 0 //문제 수
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        //choice 4가지 담을 리스트 -> 이걸로 xml파일 설정
        var choice= mutableListOf<Choice>(
            Choice(tvChoice1),
            Choice(tvChoice2),
            Choice(tvChoice3),
            Choice(tvChoice4)
        )

        //Choice 선택했을 때 처리
        fun isQuestionResult(position: Int, num: String){
            val choicenum = Integer.parseInt(num)
            if(questionList[position].answer == num){
                ivAnswerCorrect.visibility = VISIBLE
                ivAnswerWrong.visibility= INVISIBLE
                choice[choicenum-1].tvChoice.setTextColor(Color.BLUE)
            }else{
                ivAnswerWrong.visibility = VISIBLE
                ivAnswerCorrect.visibility= INVISIBLE
                choice[choicenum-1].tvChoice.setTextColor(Color.RED)
            }
        }

        //xml 파일 problem, choice text설정
        fun setting(pr_num:Int){
            tvMainTopTitle.setText("Daily_${questionList[0].subject}")
            tvMainQuestionContent.setText(questionList[0].problem)
            tvMainQuestionNum.text="Question ${pr_num+1}"
            tvChoice1.text = questionList[pr_num].choice1
            tvChoice2.text = questionList[pr_num].choice2
            tvChoice3.text = questionList[pr_num].choice3
            tvChoice4.text = questionList[pr_num].choice4

            for(m in 0..3){
                choice[m].tvChoice.setTextColor(Color.BLACK)
            }
        }


        //xml파일 text뷰 내용 설정
        setting(pr_num)

        tvChoice1.setOnClickListener {
            isQuestionResult(pr_num, "1")
        }
        tvChoice2.setOnClickListener {
            isQuestionResult(pr_num,"2")
        }
        tvChoice3.setOnClickListener {
            isQuestionResult(pr_num,"3")
        }
        tvChoice4.setOnClickListener {
            isQuestionResult(pr_num,"4")
        }


        //문제 풀다가 중간에 그만둘 때
        tvStop.setOnClickListener{
            val intent = Intent(this,StopActivity::class.java)
            startActivity(intent)
        }


        //다음문제로 넘어가기
        tvNext.setOnClickListener {
            pr_num += 1
            if(pr_num < questionList.size){

                tvMainTopTitle.setText("Daily_${questionList[pr_num].subject}")
                tvMainQuestionContent.setText(questionList[pr_num].problem)
                tvMainQuestionNum.text="Question ${pr_num+1}"
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                //rvMainMultipleChoice.adapter = mAdapter
                ivMainLike.setImageResource(R.drawable.like_empty)

                tvChoice1.setText(questionList[pr_num].choice1)
                tvChoice2.setText(questionList[pr_num].choice2)
                tvChoice3.setText(questionList[pr_num].choice3)
                tvChoice4.setText(questionList[pr_num].choice4)
                tvChoice1.setTextColor(Color.BLACK)
                tvChoice2.setTextColor(Color.BLACK)
                tvChoice3.setTextColor(Color.BLACK)
                tvChoice4.setTextColor(Color.BLACK)

                tvChoice1.isClickable = true
                tvChoice2.isClickable = true
                tvChoice3.isClickable = true
                tvChoice4.isClickable = true

            }else{
                Toast.makeText(this,"더이상 문제가 없습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        //즐겨찾기 체크 or 해제
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
    }
}


