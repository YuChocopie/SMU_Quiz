package com.smu.sangmyung.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.smu.sangmyung.quiz.mainquiz.DailyActivity
import com.smu.sangmyung.quiz.mainquiz.MockTestStart
import com.google.firebase.auth.FirebaseAuth
import com.smu.sangmyung.quiz.login.GoogleSignInActivity
import com.smu.sangmyung.quiz.login.SubjectActivity
import com.smu.sangmyung.quiz.model.User
import com.smu.sangmyung.quiz.worng.FavoriteActivity
import com.smu.sangmyung.quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.quiz.worng.WrongNoteActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth


    var Algorithme: Boolean? = null
    var Database: Boolean? = null
    var Software_Engineering: Boolean? = null
    var operation_system: Boolean? = null
    var computer_network: Boolean? = null
    var computer_structure: Boolean? = null
    var Data_Structure: Boolean? = null
    var selected_subject: String? = null


    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //로그인 되있으면 이메일 확인후 네비바에 삽입 & 서버에 유저이메일 보내기
        checkCurrentUser()
        tvGlobalTitle.text = "ALL QUIZ"

        setToggle()
        setBtn()
    }


    private fun setBtn() {
        btnGoDaily.setOnClickListener {
            val intent = Intent(this, DailyActivity::class.java)
            intent.putExtra("subject", subjectSet())
            startActivity(intent)
        }
        btnGoMock.setOnClickListener {
            val intent = Intent(this, MockTestStart::class.java)
            intent.putExtra("subject", subjectSet())
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
        var subject_list = arrayListOf<String>()
        subject_list.clear()

        Algorithme = false
        Database = false
        Software_Engineering = false
        operation_system = false
        computer_network = false
        computer_structure = false
        Data_Structure = false

        var subjectall: String = loadSubjectlist()
        if (subjectall != null) {
            var subject_token = StringTokenizer(subjectall, "&")
            subject_list.clear()
            while (subject_token.hasMoreTokens()) {
                var token: String = subject_token.nextToken().toString()
                if (token == "Algorithme") {
                    Algorithme = true
                    subject_list.add("Algorithme")
                }
                if (token == "Database") {
                    Database = true
                    subject_list.add("Database")
                }
                if (token == "Software_Engineering") {
                    Software_Engineering = true
                    subject_list.add("Software_Engineering")
                }
                if (token == "operation_system") {
                    operation_system = true
                    subject_list.add("operation_system")
                }
                if (token == "computer_network") {
                    computer_network = true
                    subject_list.add("computer_network")
                }
                if (token == "computer_structure") {
                    computer_structure = true
                    subject_list.add("computer_structure")
                }
                if (token == "Data_Structure") {
                    Data_Structure = true
                    subject_list.add("Data_Structure")
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
                val intent = Intent(applicationContext, SubjectActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.favorite -> {
                val intent = Intent(applicationContext, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.wrong_ques -> {
                val intent = Intent(applicationContext, WrongNoteActivity::class.java)
                startActivity(intent)
            }
            R.id.wrong_graph -> {
                val intent = Intent(applicationContext, WrongAnalysisActivity::class.java)
                startActivity(intent)
            }
            R.id.gotomain -> {

            }
            R.id.community_ques -> {
                Toast.makeText(applicationContext, "준비중입니다 ^^", Toast.LENGTH_SHORT).show()
            }
            R.id.community_free -> {
                Toast.makeText(applicationContext, "준비중입니다 ^^", Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                val intent = Intent(applicationContext, GoogleSignInActivity::class.java)
                startActivity(intent)
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        drawer_layout_main.closeDrawer(GravityCompat.START)
        return true
    }

    fun <String> ArrayList<String>.radnom(): String {
        var random = Random().nextInt(size)
        return get(random)
    }

    @SuppressLint("CheckResult")
    fun checkCurrentUser() {
        if (getUserEmail() != null) {
            val email = getUserEmail()
            val navheaderview = nav_view_main.getHeaderView(0)
            navheaderview.tv_nvheader_email?.text = email

            val userId = User(email)

            Log.d("Result", "123123:$userId")
            smuQuizInterface.setUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ wrong ->
                    Log.d("Result", "123123:setUser:$wrong")
                }, { error ->
                    error.printStackTrace()
                    Log.d("Result", "error::setUser$error")
                }, { Log.d("Result", "complete::setUser") })


        } else {

        }
    }

}


