package com.smu.sangmyung.quiz.mainquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smu.sangmyung.quiz.R
import kotlinx.android.synthetic.main.activity_stop.*

class StopActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop)

        val intent = Intent(this, DailyActivity::class.java)

        tvStopYes.setOnClickListener {
            intent.putExtra("finish",true)
            setResult(RESULT_OK,intent)
            finish()
        }

        tvStopNo.setOnClickListener {
            finish()
        }
    }
}