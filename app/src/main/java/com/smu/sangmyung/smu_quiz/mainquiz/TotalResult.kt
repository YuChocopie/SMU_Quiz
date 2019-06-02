package com.example.smu_quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.smu_quiz.worng.WrongNoteActivity
import kotlinx.android.synthetic.main.total_result.*

class TotalResult : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.total_result)


        val totalCorrectPr = intent.getIntExtra("total_correct_num",0)
        val totalPrNum = intent.getIntExtra("total_pr_num",0)

        val intent = Intent(this,MockTestMain::class.java)

        tvTotalResult.text=("${totalPrNum}문제 중에서 ${totalCorrectPr}문제 맞췄습니다.")

        //메인으로 돌아가기
        tvBoxGotoMain.setOnClickListener{
            intent.putExtra("finish",true)
            setResult(RESULT_OK,intent)
            finish()
        }
        //오답 확인하러 가기
        tvBoxWrongAnswer.setOnClickListener {
            intent.putExtra("finish",true)
            setResult(RESULT_OK,intent)
            val intentWrong = Intent(this,WrongNoteActivity::class.java)
            startActivity(intentWrong)
            finish()
        }
    }
}