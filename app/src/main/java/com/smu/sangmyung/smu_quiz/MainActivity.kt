package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){


    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_gotologin.setOnClickListener {
            val intent = Intent(applicationContext, GoogleSignInActivity::class.java)
            startActivity(intent)
        }

//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//        nav_view.setNavigationItemSelectedListener(this)

        Log.d("Result", "123123")


        ///TODO 나중에 지움
        smuQuizInterface.test()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                // data 를 받아 처리합니다.
                // 작업 중 오류가 발생하면 이 블록은 호출되지 않습니다
                Log.d("Result", "123123 ${result}")
            }, {
                    error ->
                error.printStackTrace()
                Log.d("Result", "ereerr")
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete")
            })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}


