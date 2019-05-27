package com.example.smu_quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_stop.*

class MockTestStart : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop)


        //xml 초기 화면 설정
        tvStop.setText("원하는 것을 선택하세요")
        tvStopYes.setText("이어풀기")
        tvStopNo.setText("새로풀기")


        //이어풀기
        tvStopYes.setOnClickListener {
            if(intent.hasExtra("already_pr")){
                var already = intent.getIntExtra("already_pr",0)
            }else{
                Toast.makeText(this,"이어푸시던 문제가 없습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        //새로풀기
        tvStopNo.setOnClickListener {
            startActivity(intent)

        }

    }
}