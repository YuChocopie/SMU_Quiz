package com.smu.sangmyung.smu_quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_after_login.*
import kotlinx.android.synthetic.main.app_bar_main.*

class AfterLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        var user:FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var email = user!!.email

        textView.text = email

    }
}
