package com.smu.sangmyung.quiz.mainquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.smu.sangmyung.quiz.R
import kotlinx.android.synthetic.main.activity_stop.*
import java.util.ArrayList

class MockTestStart : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop)

        //xml 초기 화면 설정
        tvStop.setText(R.string.mock_test_start)
        tvStopYes.setText("이어풀기")
        tvStopNo.setText("새로풀기")

        val subjectSelect: ArrayList<String>? = intent.getStringArrayListExtra("subject")


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
            val intent = Intent(this, MockTestMain::class.java)
            intent.putExtra("subject",subjectSelect)
            startActivity(intent)
            finish()
        }
    }
}