package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.total_result.*
import java.util.prefs.PreferenceChangeEvent

class MockTestMain : AppCompatActivity(){

    var pr_total_correct_num =0 //총 맞춘 문제 개수

    private fun saveData(test_num:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("TEST_NUM",test_num)
            .apply()
    }

    private fun lodaData(textView: TextView){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val test_num = pref.getInt("TEST_NUM",0)
        textView.setText("모의고사 ${test_num+1}")

    }


    //모의고사 문제 리스트
    var mocktestlist = mutableListOf<Question>(
        Question("1","","mock1","problem1","choice1-1","choice1-2","choice1-3","choice1-4", "1"),
        Question("2","","mock2","problem2","choice2-1","choice2-2","choice2-3","choice2-4", "2"),
        Question("3","","mock3","problem3","choice3-1","choice3-2","choice3-3","choice3-4", "3"),
        Question("4","","mock4","problem4","choice4-1","choice4-2","choice4-3","choice4-4", "4"),
        Question("5","","mock5","problem5","choice5-1","choice5-2","choice5-3","choice5-4", "1")
    )


    // 답 선택했을 때 처리
     private fun isQuestionResult(view: TextView, position: Int, num: String, next_or_prev:Int){
        //답 맞췄을 때
        if(mocktestlist[position].answer == num){
            ivAnswerCorrect.visibility = View.VISIBLE
            view.setTextColor(Color.BLUE)
            if(next_or_prev == 1){
                pr_total_correct_num += 1
            }
        }//틀렸을 때
        else{
            ivAnswerWrong.visibility = View.VISIBLE
            view.setTextColor(Color.RED)
        }

        //한 번 답 선택하면 다시 선택할 수 없음
        tvChoice1.isClickable =false
        tvChoice2.isClickable =false
        tvChoice3.isClickable =false
        tvChoice4.isClickable =false
    }

    override fun onCreate(savedInstanceState: Bundle?){
        var i = 0 // 즐겨찾기 처리할 때 사용
        var pr_num:Int = 0 // mocktestlikst[position]에서 position
        var already:String ="empty"
        var next_or_prev = 0 //next(0) prev(1)

        //saveData(1)
        //lodaData(tvMainTopTitle)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        //초기 activity 설정
        tvStop.setText("prev")
        tvMainQuestionContent.setText(mocktestlist[0].problem)
        tvMainQuestionNum.text="Question ${pr_num+1}"
        tvChoice1.text = mocktestlist[0].choice1
        tvChoice2.text = mocktestlist[0].choice2
        tvChoice3.text = mocktestlist[0].choice3
        tvChoice4.text = mocktestlist[0].choice4
        //tvMainTopTitle.setText("모의고사 1회")


        //1번 선택했을 때
        tvChoice1.setOnClickListener {
            isQuestionResult(tvChoice1, pr_num, "1", next_or_prev)
        }
        //2번 선택했을 때
        tvChoice2.setOnClickListener {
            isQuestionResult(tvChoice2, pr_num,"2", next_or_prev)
        }
        //3번 선택했을 때
        tvChoice3.setOnClickListener {
            isQuestionResult(tvChoice3, pr_num,"3", next_or_prev)
        }
        //4번 선택했을 때
        tvChoice4.setOnClickListener {
            isQuestionResult(tvChoice4, pr_num,"4", next_or_prev)
        }
        //다음 문제로 넘어가기

        tvNext.setOnClickListener {
            //문제수 1씩 증가
            next_or_prev = 1
           pr_num += 1
            if(pr_num < mocktestlist.size){


                tvMainQuestionContent.setText(mocktestlist[pr_num].problem)
                tvMainQuestionNum.text="Question ${pr_num+1}"
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                ivMainLike.setImageResource(R.drawable.like_empty)

                tvChoice1.setText(mocktestlist[pr_num].choice1)
                tvChoice2.setText(mocktestlist[pr_num].choice2)
                tvChoice3.setText(mocktestlist[pr_num].choice3)
                tvChoice4.setText(mocktestlist[pr_num].choice4)
                tvChoice1.setTextColor(Color.BLACK)
                tvChoice2.setTextColor(Color.BLACK)
                tvChoice3.setTextColor(Color.BLACK)
                tvChoice4.setTextColor(Color.BLACK)

                tvChoice1.isClickable = true
                tvChoice2.isClickable = true
                tvChoice3.isClickable = true
                tvChoice4.isClickable = true

            }

            //모의고사 다 풀었을 때
            else{
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
            next_or_prev =0
            pr_num -= 1
            if(pr_num >= 0) {

                tvMainQuestionContent.setText(mocktestlist[pr_num].problem)
                tvMainQuestionNum.text = "Question ${pr_num + 1}"
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                ivMainLike.setImageResource(R.drawable.like_empty)

                tvChoice1.setText(mocktestlist[pr_num].choice1)
                tvChoice2.setText(mocktestlist[pr_num].choice2)
                tvChoice3.setText(mocktestlist[pr_num].choice3)
                tvChoice4.setText(mocktestlist[pr_num].choice4)
                tvChoice1.setTextColor(Color.BLACK)
                tvChoice2.setTextColor(Color.BLACK)
                tvChoice3.setTextColor(Color.BLACK)
                tvChoice4.setTextColor(Color.BLACK)

                tvChoice1.isClickable = true
                tvChoice2.isClickable = true
                tvChoice3.isClickable = true
                tvChoice4.isClickable = true
            }else{
                Toast.makeText(this,"이전 문제가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
            }

        }

    }

}

