package com.smu.sangmyung.smu_quiz.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.R
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*
import kotlin.collections.ArrayList


class AfterLoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var user:FirebaseUser? = FirebaseAuth.getInstance().currentUser

    var Algorithm:Boolean?=null
    var Database :Boolean?=null
    var sofrware_engineering : Boolean ?= null
    var operation_system :Boolean?=null
    var computer_network : Boolean?= null
    var computer_structure :Boolean?=null
    var data_structure : Boolean?= null
    var selected_subject:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        val email =user!!.email

          //네비바
//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, toolbar,
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//        nav_view.setNavigationItemSelectedListener(this)

        val navheaderview = nav_view.getHeaderView(0)
        navheaderview.tv_nvheader_email?.text=email.toString()

        Algorithm= false
        Database=false
        sofrware_engineering = false
        operation_system =false
        computer_network = false
        computer_structure =false
        data_structure = false

        var subjectall:String ?= intent.getStringExtra("subject")
        if(subjectall!=null) {
            var subject_token = StringTokenizer(subjectall,"&")
            Log.d("ddddd",subjectall)
            var subject_list = arrayListOf<String>()

            while(subject_token.hasMoreTokens()) {
                var token: String = subject_token.nextToken().toString()
                if (token=="algorithm") {
                    Algorithm = true
                    Log.d("ddddd",token)
                    subject_list.add("algorithm")
                }
                if (token=="database") {
                    Database = true
                    Log.d("ddddd",token)
                    subject_list.add("Database")
                }
                if (token=="sofrware_engineering") {
                    sofrware_engineering = true
                    Log.d("ddddd",token)
                    subject_list.add("sofrware_engineering")
                }
                if (token=="operation_system") {
                    operation_system = true
                    Log.d("ddddd",token)
                    subject_list.add("operation_system")
                }
                if (token=="computer_network") {
                    computer_network = true
                    Log.d("ddddd",token)
                    subject_list.add("computer_network")
                }
                if (token=="computer_structure") {
                    computer_structure = true
                    Log.d("ddddd",token)
                    subject_list.add("computer_structure")
                }
                if (token=="data_structure") {
                    data_structure = true
                    Log.d("ddddd",token)
                    subject_list.add("data_structure")
                }
                }
            selected_subject = subject_list.random()
        }

        tv_subject.text = selected_subject
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.select_subject -> {
                val intent= Intent(applicationContext, SubjectActivity::class.java)
                startActivity(intent)
            }
            R.id.wrong_ques -> {

            }

            R.id.gotomain -> {
                val intent= Intent(applicationContext, AfterLoginActivity::class.java)
                startActivity(intent)
            }
            R.id.community_ques -> {

            }
            R.id.community_free -> {

            }
            R.id.logout ->{
                val intent= Intent(applicationContext, GoogleSignInActivity::class.java)
                startActivity(intent)
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext,"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show()
                }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun <String>ArrayList<String>.radnom():String{
        var random = Random().nextInt(size)
        return  get(random)
    }

}
