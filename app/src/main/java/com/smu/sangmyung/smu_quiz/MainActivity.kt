package com.smu.sangmyung.smu_quiz

import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_gotologin.setOnClickListener {
            val intent = Intent(applicationContext, GoogleSignInActivity::class.java)
            startActivity(intent)
        }
    }
}


