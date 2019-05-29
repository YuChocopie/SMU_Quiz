package com.example.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_stop.*

class StopActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop)

        tvStopYes.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }
        tvStopNo.setOnClickListener {
            finish()
        }
    }



}