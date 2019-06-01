package com.smu.sangmyung.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {
    var login: Runnable = Runnable {
        val intent = Intent(applicationContext, GoogleSignInActivity::class.java)
        startActivity(intent)
        finish()
    }
    var main: Runnable = Runnable {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val anim = AnimationUtils.loadAnimation(applicationContext,R.anim.splash)
        ivSplash.startAnimation(anim)


        val useremail = loadCurrentUserEmail()

        var handler: Handler = Handler()

        if (useremail!="false") {
                handler.postDelayed(main,4000)
            }
        else{
            handler.postDelayed(login, 3000)

        }
    }
}
