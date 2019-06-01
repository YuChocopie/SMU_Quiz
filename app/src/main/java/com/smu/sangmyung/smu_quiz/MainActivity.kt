package com.smu.sangmyung.smu_quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.smu_quiz.DailyActivity
import com.example.smu_quiz.MockTestStart
import com.google.firebase.auth.FirebaseAuth
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity
import com.smu.sangmyung.smu_quiz.login.SubjectActivity
import com.smu.sangmyung.smu_quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.smu_quiz.worng.WrongNoteActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth

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
        //로그인 되있으면 이메일 확인후 네비바에 삽입
        checkCurrentUser()
        tvGlobalTitle.text="ALL QUIZ"

        setToggle()
        setBtn()
    }

    private fun setBtn() {
        btnGoDaily.setOnClickListener{
            val intent = Intent(this, DailyActivity::class.java)
            intent.putExtra("subject",subjectSet())
            startActivity(intent)
        }
        btnGoMock.setOnClickListener {
            val intent = Intent(this, MockTestStart::class.java)
            intent.putExtra("subject",subjectSet())
            startActivity(intent)
        }
        btnChangeProblemType.setOnClickListener {
            val intent = Intent(applicationContext, SubjectActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setToggle() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_main, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout_main.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_main.setNavigationItemSelectedListener(this)
    }

    private fun subjectSet(): ArrayList<String> {
//// subject tokenizer + random
        var subject_list = arrayListOf<String>()

        Algorithm= false
        Database=false
        sofrware_engineering = false
        operation_system =false
        computer_network = false
        computer_structure =false
        data_structure = false

        var subjectall:String = loadSubjectlist()
        if(subjectall!=null) {
            var subject_token = StringTokenizer(subjectall,"&")
            Log.d("ddddd",subjectall)


            while(subject_token.hasMoreTokens()) {
                var token: String = subject_token.nextToken().toString()
                if (token=="algorithm") {
                    Algorithm = true
                    Log.d("ddddd",token)
                    subject_list.add("Algorithme")
                }
                if (token=="database") {
                    Database = true
                    Log.d("ddddd",token)
                    subject_list.add("Database")
                }
                if (token=="sofrware_engineering") {
                    sofrware_engineering = true
                    Log.d("ddddd",token)
                    subject_list.add("Software_Engineering")
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
                    subject_list.add("Data_structure")
                }
            }
            selected_subject = subject_list.random()

        }
            return subject_list
    }


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
            R.id.wrong_graph -> {
                val intent= Intent(applicationContext, WrongAnalysisActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.gotomain -> {
                val intent= Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
           }
            R.id.community_ques -> {
                Toast.makeText(applicationContext,"준비중입니다 ^^",Toast.LENGTH_SHORT).show()
            }
            R.id.community_free -> {
                Toast.makeText(applicationContext,"준비중입니다 ^^",Toast.LENGTH_SHORT).show()
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

    fun checkCurrentUser() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            auth =FirebaseAuth.getInstance()
            var user =auth.currentUser
            val email =user!!.email
            val navheaderview = nav_view_main.getHeaderView(0)
            navheaderview.tv_nvheader_email?.text=email.toString()
        } else {

        }
    }

}


