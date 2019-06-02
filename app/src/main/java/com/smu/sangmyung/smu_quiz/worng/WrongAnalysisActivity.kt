package com.smu.sangmyung.smu_quiz.worng

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.*
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity
import com.smu.sangmyung.smu_quiz.login.SubjectActivity
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.activity_wrong_analysis.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class WrongAnalysisActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    @SuppressLint("WrongViewCast")

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_analysis)
        tvGlobalTitle.text = "오답분석"

        val points = intArrayOf(5, 3, 7, 8, 0, 0, 3)
        points[0]=loadQuizResult("wr_Algorithme")
        points[1]=loadQuizResult("wr_Database")
        points[2]=loadQuizResult("wr_Software_engineering")
        points[3]=loadQuizResult("wr_operation_system")
        points[4]=loadQuizResult("wr_computer_network")
        points[5]=loadQuizResult("wr_computer_structure")
        points[6]=loadQuizResult("wr_Data_structure")



        LineGraphView?.setPoints(points, 1.0, 0, points.max()!!.toInt()+2)
        LineGraphView?.drawForBeforeDrawView()


        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val Algoview = circleGraph_Algo as LinearLayout
        val CGAlgo = CircleGraphView(this)
        Algoview.addView(CGAlgo)

        val Dabaview = circleGraph_Daba as LinearLayout
        val CGDaba = CircleGraphView(this)
        Dabaview.addView(CGDaba)

        val Softview = circleGraph_Soft as LinearLayout
        val CGSoft = CircleGraphView(this)
        Softview.addView(CGSoft)

        val Operview = circleGraph_Oper as LinearLayout
        val CGOper = CircleGraphView(this)
        Operview.addView(CGOper)

        val Comneview = circleGraph_Comne as LinearLayout
        val CGComne = CircleGraphView(this)
        Comneview.addView(CGComne)

        val ComStrucview = circleGraph_ComStruc as LinearLayout
        val CGComstruc = CircleGraphView(this)
        ComStrucview.addView(CGComstruc)

        val DataStrucview = circleGraph_DataStruc as LinearLayout
        val CGDataStruc = CircleGraphView(this)
        DataStrucview.addView(CGDataStruc)





        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_wrong_analysis, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout_wrong_analysis.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_wrong_anal.setNavigationItemSelectedListener(this)

        val email =user!!.email
        val nav_header_view = nav_view_wrong_anal.getHeaderView(0)
        nav_header_view.tv_nvheader_email?.text=email.toString()

    }


    override fun onBackPressed() {
        if (drawer_layout_wrong_analysis.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_wrong_analysis.closeDrawer(GravityCompat.START)
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
            R.id.favorite -> {
                val intent= Intent(applicationContext, FavoriteActivity::class.java)
                startActivity(intent)
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
                finish()
            }
            R.id.community_ques -> {
                Toast.makeText(applicationContext,"준비중입니다 ^^", Toast.LENGTH_SHORT).show()
            }
            R.id.community_free -> {
                Toast.makeText(applicationContext,"준비중입니다 ^^", Toast.LENGTH_SHORT).show()
            }
            R.id.logout ->{
                val intent= Intent(applicationContext, GoogleSignInActivity::class.java)
                startActivity(intent)
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext,"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        drawer_layout_wrong_analysis.closeDrawer(GravityCompat.START)
        return true
    }


}