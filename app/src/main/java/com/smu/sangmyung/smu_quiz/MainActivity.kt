package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.smu_quiz.DailyActivity
import com.example.smu_quiz.MockTestStart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.login.SubjectActivity
import com.smu.sangmyung.smu_quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.smu_quiz.worng.WrongNoteActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

   // var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    var Algorithm:Boolean?=null
    var Database :Boolean?=null
    var sofrware_engineering : Boolean ?= null
    var operation_system :Boolean?=null
    var computer_network : Boolean?= null
    var computer_structure :Boolean?=null
    var data_structure : Boolean?= null
    var selected_subject:String?=null

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val email =user!!.email
        tvGlobalTitle.text="ALL QUIZ"

        btnGoDaily.setOnClickListener{
            val intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }
        btnGoMock.setOnClickListener {
            val intent = Intent(this, MockTestStart::class.java)
            startActivity(intent)
        }
//        btn_gotologin.setOnClickListener {
//            val intent = Intent(applicationContext, GoogleSignInActivity::class.java)
//            startActivity(intent)
//        }
        btnChangeProblemType.setOnClickListener {
            val intent = Intent(applicationContext, SubjectActivity::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_main, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout_main.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_main.setNavigationItemSelectedListener(this)

        Log.d("Result", "123123")

//
//        /TODO 나중에 지움
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


        //// subject tokenizer + random

        //val navheaderview = nav_view.getHeaderView(0)
        //navheaderview.tv_nvheader_email?.text=email.toString()


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
    }
    //// 네비바


    override fun onBackPressed() {
        if (drawer_layout_main.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_main.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.select_subject -> {
                val intent= Intent(applicationContext, SubjectActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.wrong_ques -> {
                val intent= Intent(applicationContext, WrongNoteActivity::class.java)
                startActivity(intent)
                finish()
            }
            //R.id.wrong_graph -> {
            //    val intent= Intent(applicationContext, WrongAnalysisActivity::class.java)
            //    startActivity(intent)
             //   finish()
            //}


            R.id.gotomain -> {
                val intent= Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.community_ques -> {

            }
            R.id.community_free -> {

            }
            R.id.logout ->{
                val intent= Intent(applicationContext, GoogleSignInActivity::class.java)
                startActivity(intent)
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext,"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        drawer_layout_main.closeDrawer(GravityCompat.START)
        return true
    }

    fun <String>ArrayList<String>.radnom():String{
        var random = Random().nextInt(size)
        return  get(random)
    }
}


