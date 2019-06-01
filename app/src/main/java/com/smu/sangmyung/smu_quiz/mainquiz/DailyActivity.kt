package com.example.smu_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.mainquiz.Choice
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.item_global_title.*

class DailyActivity : AppCompatActivity() {

    //데일리 문제 list
    var questionList = mutableListOf<Question>(
        Question("1", "", "Database", "problem1", "choice1-1", "choice1-2", "choice1-3", "choice1-4", "1", ""),
        Question("2", "", "subject2", "problem2", "choice2-1", "choice2-2", "choice2-3", "choice2-4", "2", ""),
        Question("3", "", "subject3", "problem3", "choice3-1", "choice3-2", "choice3-3", "choice3-4", "3", ""),
        Question("4", "", "subject4", "problem4", "choice4-1", "choice4-2", "choice4-3", "choice4-4", "4", ""),
        Question("5", "", "subject5", "problem5", "choice5-1", "choice5-2", "choice5-3", "choice5-4", "1", "")

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

        //Choice 선택했을 때 처리
        fun isQuestionResult(position: Int, num: String) {
            val choicenum = Integer.parseInt(num)
            if (questionList[position].answer == num) {
                ivAnswerCorrect.visibility = VISIBLE
                ivAnswerWrong.visibility = INVISIBLE
                for (m in 0..3) {
                    if (m != choicenum - 1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.BLUE)
                    }
                }
            } else {
                ivAnswerWrong.visibility = VISIBLE
                ivAnswerCorrect.visibility = INVISIBLE
                for (m in 0..3) {
                    if (m != choicenum - 1) {
                        choice[m].tvChoice.setTextColor(Color.BLACK)
                    } else {
                        choice[m].tvChoice.setTextColor(Color.RED)
                    }
                }
            }
        }

        //xml 파일 problem, choice text설정
        fun setting(pr_num: Int) {
            tvGlobalTitle.text=("Daily_${questionList[pr_num].subject}")
            tvMainQuestionContent.setText(questionList[pr_num].title)
            tvMainQuestionNum.text = "Question ${pr_num + 1}"
            tvChoice1.text = "1. " + questionList[pr_num].choice1
            tvChoice2.text = "2. " + questionList[pr_num].choice2
            tvChoice3.text = "3. " + questionList[pr_num].choice3
            tvChoice4.text = "4. " + questionList[pr_num].choice4

            ivAnswerCorrect.visibility = View.GONE
            ivAnswerWrong.visibility = View.GONE

            ivMainLike.setImageResource(R.drawable.like_empty)

            for (m in 0..3) {
                choice[m].tvChoice.setTextColor(Color.BLACK)
            }
        }


        //xml파일 text뷰 내용 설정
        setting(pr_num)

        //1번 선택했을 때
        tvChoice1.setOnClickListener {
            isQuestionResult(pr_num, "1")
        }
        //2번 선택했을 때
        tvChoice2.setOnClickListener {
            isQuestionResult(pr_num, "2")
        }
        //3번 선택했을 때
        tvChoice3.setOnClickListener {
            isQuestionResult(pr_num, "3")
        }
        //4번 선택했을 때
        tvChoice4.setOnClickListener {
            isQuestionResult(pr_num, "4")
        }

        //문제 풀다가 중간에 그만둘 때
        tvStop.setOnClickListener {
            val intent = Intent(this, StopActivity::class.java)
            startActivity(intent)
        }
        val finishable = intent.getBooleanExtra("finish", false)
        if (finishable) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //다음문제로 넘어가기
        tvNext.setOnClickListener {
            pr_num += 1
            if (pr_num < questionList.size) {
                setting(pr_num)

            } else {
                Toast.makeText(this, "더이상 문제가 없습니다.", Toast.LENGTH_SHORT).show()
            }
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


