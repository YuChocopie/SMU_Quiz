package com.smu.sangmyung.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity

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
        val useremail = loadCurrentUserEmail()

        var handler: Handler = Handler()

        if (useremail!="false") {
                handler.postDelayed(main,3000)
            }
        else{
            handler.postDelayed(login, 3000)

        }
    }
}
