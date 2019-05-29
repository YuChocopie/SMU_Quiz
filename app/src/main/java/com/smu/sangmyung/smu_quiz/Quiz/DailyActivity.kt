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

    open fun isQuestionResult(view: TextView, position: Int, num: String){
        if(questionList[position].answer == num){
            ivAnswerCorrect.visibility = VISIBLE
            view.setTextColor(Color.BLUE)
        }else{
            ivAnswerWrong.visibility = VISIBLE
            view.setTextColor(Color.RED)
        }
        tvChoice1.isClickable =false
        tvChoice2.isClickable =false
        tvChoice3.isClickable =false
        tvChoice4.isClickable =false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var i = 0
        var j = 0
        var pr_num =1 //문제 수
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        tvMainTopTitle.setText("Daily_${questionList[0].subject}")
        tvMainQuestionContent.setText(questionList[0].problem)
        tvMainQuestionNum.text="Question ${pr_num}"
        tvChoice1.text = questionList[0].choice1
        tvChoice2.text = questionList[0].choice2
        tvChoice3.text = questionList[0].choice3
        tvChoice4.text = questionList[0].choice4



        tvChoice1.setOnClickListener {
            isQuestionResult(tvChoice1, j, "1")
        }
        tvChoice2.setOnClickListener {
            isQuestionResult(tvChoice2, j,"2")
        }
        tvChoice3.setOnClickListener {
            isQuestionResult(tvChoice3, j,"3")
        }
        tvChoice4.setOnClickListener {
            isQuestionResult(tvChoice4, j,"4")
        }


        tvStop.setOnClickListener{
            val intent = Intent(this,StopActivity::class.java)
            startActivity(intent)


        }


        tvNext.setOnClickListener {
            j += 1
            pr_num += 1
            if(j < questionList.size){

                tvMainTopTitle.setText("Daily_${questionList[j].subject}")
                tvMainQuestionContent.setText(questionList[j].problem)
                tvMainQuestionNum.text="Question ${pr_num}"
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                //rvMainMultipleChoice.adapter = mAdapter
                ivMainLike.setImageResource(R.drawable.like_empty)

                tvChoice1.setText(questionList[j].choice1)
                tvChoice2.setText(questionList[j].choice2)
                tvChoice3.setText(questionList[j].choice3)
                tvChoice4.setText(questionList[j].choice4)
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


