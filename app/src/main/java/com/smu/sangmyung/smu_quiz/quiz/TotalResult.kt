package com.example.smu_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.total_result.*

class TotalResult : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.total_result)


        var totalCorrectPr = intent.getIntExtra("total_correct_num",0)
        var totalPrNum = intent.getIntExtra("total_pr_num",0)

        tvTotalResult.text=("${totalPrNum}문제 중에서 ${totalCorrectPr}문제 맞췄습니다.")



        //메인으로 돌아가기
        tvBoxGotoMain.setOnClickListener{
            var intent = Intent(this,MockTestMain::class.java)
            intent.putExtra("finish",true)
            startActivity(intent)
        }
        //오답 확인하러 가기
        tvBoxWrongAnswer.setOnClickListener {
        }
    }
}