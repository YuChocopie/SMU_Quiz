package com.example.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_after_login.view.*
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.item_glibal_title.*

class StartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        tvGlobalTitle.text="ALL QUIZ"

        btnGoDaily.setOnClickListener{
            var intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }
        btnGoMock.setOnClickListener {
            var intent = Intent(this, MockTestStart::class.java)
            startActivity(intent)
        }
    }
}