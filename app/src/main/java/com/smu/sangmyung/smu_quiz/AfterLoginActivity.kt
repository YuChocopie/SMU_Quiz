package com.smu.sangmyung.smu_quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_after_login.*

class AfterLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        var user:FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var email = user!!.email

        textView.text = email

    }
}
