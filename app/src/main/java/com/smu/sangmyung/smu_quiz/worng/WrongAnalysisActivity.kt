package com.smu.sangmyung.smu_quiz.worng

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.BaseActivity
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity
import com.smu.sangmyung.smu_quiz.login.SubjectActivity
import kotlinx.android.synthetic.main.activity_wrong_analysis.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


@SuppressLint("ParcelCreator")
class WrongAnalysisActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, Parcelable {
    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    @SuppressLint("WrongViewCast", "SetTextI18n")

    var resultAlgorithme = 0
    var resultDB = 0
    var resultSE = 0
    var resultOS = 0
    var resultCN = 0
    var resultCS = 0
    var resultDS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_analysis)
        setText()
        setGraph()
//        checkGone()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_wrong_analysis, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout_wrong_analysis.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_wrong_anal.setNavigationItemSelectedListener(this)
        val email =loadCurrentUserEmail()

//        val email = user!!.email
        val nav_header_view = nav_view_wrong_anal.getHeaderView(0)
        nav_header_view.tv_nvheader_email?.text = email.toString()

    }

//    private fun checkGone() {
////        if (resultAlgorithme == 0f)
////            clalgo.visibility = GONE
//        if (resultDB == 0f)
//            cldaba.visibility = GONE
//        if (resultSE == 0f)
//            clsoft.visibility = GONE
//        if (resultOS == 0f)
//            cloper.visibility = GONE
//        if (resultCN == 0f)
//            clcomne.visibility = GONE
//        if (resultCS == 0f)
//            clcomstruc.visibility = GONE
//        if (resultDS == 0f)
//            cldatastruc.visibility = GONE
//    }

    private fun setGraph() {
        val points = intArrayOf(1, 1, 1, 1, 1, 1, 1)
        points[0] = loadQuizResult("wr_Algorithme")
        points[1] = loadQuizResult("wr_Database")
        points[2] = loadQuizResult("wr_Software_engineering")
        points[3] = loadQuizResult("wr_operation_system")
        points[4] = loadQuizResult("wr_computer_network")
        points[5] = loadQuizResult("wr_computer_structure")
        points[6] = loadQuizResult("wr_Data_structure")

        LineGraphView?.setPoints(points, 1.0, 0, points.max()!!.toInt() + 5)
        LineGraphView?.drawForBeforeDrawView()


        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val Algoview = circleGraph_Algo as LinearLayout
        val CGAlgo = CircleGraphView(this, resultAlgorithme)
        Algoview.addView(CGAlgo)

        val Dabaview = circleGraph_Daba as LinearLayout
        val CGDaba = CircleGraphView(this, resultDB)
        Dabaview.addView(CGDaba)

        val Softview = circleGraph_Soft as LinearLayout
        val CGSoft = CircleGraphView(this, resultSE)
        Softview.addView(CGSoft)

        val Operview = circleGraph_Oper as LinearLayout
        val CGOper = CircleGraphView(this, resultOS)
        Operview.addView(CGOper)

        val Comneview = circleGraph_Comne as LinearLayout
        val CGComne = CircleGraphView(this, resultCN)
        Comneview.addView(CGComne)

        val ComStrucview = circleGraph_ComStruc as LinearLayout
        val CGComstruc = CircleGraphView(this, resultCS)
        ComStrucview.addView(CGComstruc)

        val DataStrucview = circleGraph_DataStruc as LinearLayout
        val CGDataStruc = CircleGraphView(this, resultDS)
        DataStrucview.addView(CGDataStruc)
    }

    private fun calaulate(wrong: Int, all: Int): Double {
        var tic = all/100 + all % 100 * 0.01
        return (all-wrong) / tic
    }

    @SuppressLint("SetTextI18n")
    private fun setText() {
        if (loadQuizResult("Algorithme") != 0)
            resultAlgorithme = calaulate(loadQuizResult("wr_Algorithme"), loadQuizResult("Algorithme")).toInt()
        if (loadQuizResult("Database") != 0)
            resultDB = calaulate(loadQuizResult("wr_Database"), loadQuizResult("Database")).toInt()
        if (loadQuizResult("Software_engineering") != 0)
            resultSE =
                calaulate(loadQuizResult("wr_Software_engineering"), loadQuizResult("Software_engineering")).toInt()
        if (loadQuizResult("operation_system") != 0)
            resultOS = calaulate(loadQuizResult("wr_operation_system"), loadQuizResult("operation_system")).toInt()
        if (loadQuizResult("computer_network") != 0)
            resultCN = calaulate(loadQuizResult("wr_computer_network"), loadQuizResult("computer_network")).toInt()
        if (loadQuizResult("computer_structure") != 0)
            resultCS = calaulate(loadQuizResult("wr_computer_structure"), loadQuizResult("computer_structure")).toInt()
        if (loadQuizResult("Data_structure") != 0)
            resultDS = calaulate(loadQuizResult("wr_Data_structure"), loadQuizResult("Data_structure")).toInt()

        Log.e("123123123", (resultAlgorithme.toString()))
        Log.e("123123123", (resultDB.toString()))

        tvGlobalTitle.text = "오답분석"
        tvlgAlgo.text = getText(R.string.algorithm_h)
        tvlgDaba.text = getText(R.string.database_h)
        tvlgSoft.text = getText(R.string.softwareEngineering_h)
        tvlgOper.text = getText(R.string.operationSystem_h)
        tvlgComne.text = getText(R.string.computerNetwork_h)
        tvlgComstruc.text = getText(R.string.computerStructure_h)
        tvlgDatastruc.text = getText(R.string.dataStructure_h)

        tvAlgoGraph.text = "$resultAlgorithme%"
        tvdabagraph.text = "$resultDB%"
        tvsoftgraph.text = "$resultSE%"
        tvopergraph.text = "$resultOS%"
        tvcomnegraph.text = "$resultCN%"
        tvcomstrucgraph.text = "$resultCS%"
        tvdatastrucgraph.text = "$resultDS%"


        tvAlgotext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("Algorithme")
        tvWorngDataBaseText.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("Database")
        tvSofttext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("Software_engineering")
        tvOpertext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("operation_system")
        tvComnetext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("computer_network")
        tvComstructext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("computer_structure")
        tvDatatructext.text = "$resultAlgorithme%의 정답률\n총 푼 문제의 수 : " + loadQuizResult("Data_structure")

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
                val intent = Intent(applicationContext, SubjectActivity::class.java)
                startActivity(intent)
            }
            R.id.favorite -> {
                val intent = Intent(applicationContext, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.wrong_ques -> {
                val intent = Intent(applicationContext, WrongNoteActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.wrong_graph -> {
                val intent = Intent(applicationContext, WrongAnalysisActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.gotomain -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
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

        drawer_layout_wrong_analysis.closeDrawer(GravityCompat.START)
        return true
    }
}