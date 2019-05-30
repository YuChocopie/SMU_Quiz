package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.quiz.Choice
import com.smu.sangmyung.smu_quiz.quiz.Quiz
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_daily.*

class MockTestMain : AppCompatActivity(){

    var pr_total_correct_num =0 //총 맞춘 문제 개수
    var pr_num:Int = 0 // mocktestlikst[position]에서 position

    //모의고사 30개 리스트 넣어서 처리하기
    var mocktest = mutableListOf<Quiz>(
        Quiz(false),
        Quiz(false),
        Quiz(false),
        Quiz(false),
        Quiz(false)
    )

    //모의고사 문제 리스트
    var mocktestlist = mutableListOf<Question>(
        Question("1","","mock1","problem1","choice1-1","choice1-2","choice1-3","choice1-4", "1",""),
        Question("2","","mock2","problem2","choice2-1","choice2-2","choice2-3","choice2-4", "2",""),
        Question("3","","mock3","problem3","choice3-1","choice3-2","choice3-3","choice3-4", "3",""),
        Question("4","","mock4","problem4","choice4-1","choice4-2","choice4-3","choice4-4", "4",""),
        Question("5","","mock5","problem5","choice5-1","choice5-2","choice5-3","choice5-4", "1","")
    )

    override fun onCreate(savedInstanceState: Bundle?){

        var i = 0 // 즐겨찾기 처리할 때 사용

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        tvMainTopTitle.setText("Mock Test")

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
            for(m in 0..3){
                if(m != choicenum-1){
                    choice[m].tvChoice.setTextColor(Color.BLACK)
                }else{
                    choice[m].tvChoice.setTextColor(Color.BLUE)
                }
            }

            //답 맞췄을 때
            if(mocktestlist[position].answer == num){
                mocktest[position].correct = true
            }else{//답 틀렸을 때
                mocktest[position].correct = false
            }

        }

        //xml 파일 problem, choice text설정
        fun setting(pr_num:Int){
            tvMainQuestionNum.text="Question ${pr_num+1}"
            tvMainQuestionContent.setText(mocktestlist[pr_num].title)
            tvChoice1.text = "1. "+mocktestlist[pr_num].choice1
            tvChoice2.text = "2. "+mocktestlist[pr_num].choice2
            tvChoice3.text = "3. "+mocktestlist[pr_num].choice3
            tvChoice4.text = "4. "+mocktestlist[pr_num].choice4

            for(m in 0..3){
                choice[m].tvChoice.setTextColor(Color.BLACK)
            }
        }

        //초기 activity 설정
        tvStop.setText("prev")
        setting(pr_num)

        //1번 선택했을 때
        tvChoice1.setOnClickListener {
            isQuestionResult(pr_num, "1")
        }
        //2번 선택했을 때
        tvChoice2.setOnClickListener {
            isQuestionResult(pr_num,"2")
        }
        //3번 선택했을 때
        tvChoice3.setOnClickListener {
            isQuestionResult(pr_num,"3")
        }
        //4번 선택했을 때
        tvChoice4.setOnClickListener {
            isQuestionResult(pr_num,"4")

        }

        //다음 문제로 넘어가기
        tvNext.setOnClickListener {
            //문제수 1씩 증가
            pr_num += 1
            if(pr_num < mocktestlist.size){
                ivMainLike.setImageResource(R.drawable.like_empty)
                setting(pr_num)

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
                setting(pr_num)
            }else{
                Toast.makeText(this,"이전 문제가 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
            }

        }

    }

}

