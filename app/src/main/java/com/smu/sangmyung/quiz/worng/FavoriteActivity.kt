package com.smu.sangmyung.quiz.worng

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.smu.sangmyung.quiz.*
import com.smu.sangmyung.quiz.adapters.WorngListAdapter
import com.smu.sangmyung.quiz.login.GoogleSignInActivity
import com.smu.sangmyung.quiz.login.SubjectActivity
import com.smu.sangmyung.quiz.model.Quiz
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_wrong_note.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class FavoriteActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var likeListAlgorithm = arrayListOf<Quiz>()
    var likeListDB = arrayListOf<Quiz>()
    var likeListSE = arrayListOf<Quiz>()
    var likeListOS = arrayListOf<Quiz>()
    var likeListCN = arrayListOf<Quiz>()
    var likeListCS = arrayListOf<Quiz>()
    var likeListDS = arrayListOf<Quiz>()
    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    private var algorithm: Boolean = false
    private var database: Boolean = false
    private var softwareEngineering: Boolean = false
    private var operationSystem: Boolean = false
    private var computerNetwork: Boolean = false
    private var computerStructure: Boolean = false
    private var dataStructure: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_note)

        getBookMarkData()
        setText()
        setRecyclerView()
        setRecyclerViewVisible()


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_wrong_note, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout_wrong_note.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_wrong_note.setNavigationItemSelectedListener(this)

        val nav_header_view = nav_view_wrong_note.getHeaderView(0)
        nav_header_view.tv_nvheader_email?.text = getUserEmail()


    }

    private fun setRecyclerViewVisible() {
        val mAdapterA = WorngListAdapter(this, likeListAlgorithm)
        val mAdapterDB = WorngListAdapter(this, likeListDB)
        val mAdapterDS = WorngListAdapter(this, likeListDS)
        val mAdapterCN = WorngListAdapter(this, likeListCN)
        val mAdapterCS = WorngListAdapter(this, likeListCS)
        val mAdapterOS = WorngListAdapter(this, likeListOS)
        val mAdapterSE = WorngListAdapter(this, likeListSE)

        val lm1 = LinearLayoutManager(this)
        val lm2 = LinearLayoutManager(this)
        val lm3 = LinearLayoutManager(this)
        val lm4 = LinearLayoutManager(this)
        val lm5 = LinearLayoutManager(this)
        val lm6 = LinearLayoutManager(this)
        val lm7 = LinearLayoutManager(this)

        rvWorngAlgorithm.adapter = mAdapterA
        rvWorngAlgorithm.layoutManager = lm1
        rvWorngAlgorithm.setHasFixedSize(true)

        rvWorngDatabase.adapter = mAdapterDB
        rvWorngDatabase.layoutManager = lm2
        rvWorngDatabase.setHasFixedSize(true)

        rvWorngDataStructure.adapter = mAdapterDS
        rvWorngDataStructure.layoutManager = lm3
        rvWorngDataStructure.setHasFixedSize(true)

        rvWorngComputerNetwork.adapter = mAdapterCN
        rvWorngComputerNetwork.layoutManager = lm4
        rvWorngComputerNetwork.setHasFixedSize(true)

        rvWorngComputerStructure.adapter = mAdapterCS
        rvWorngComputerStructure.layoutManager = lm5
        rvWorngComputerStructure.setHasFixedSize(true)

        rvWorngOperationSystem.adapter = mAdapterOS
        rvWorngOperationSystem.layoutManager = lm6
        rvWorngOperationSystem.setHasFixedSize(true)

        rvWorngSoftwareEngineering.adapter = mAdapterSE
        rvWorngSoftwareEngineering.layoutManager = lm7
        rvWorngSoftwareEngineering.setHasFixedSize(true)
    }

    private fun setRecyclerView() {
        tvWorngNoteSubjectAlgorithm.setOnClickListener {
            if (algorithm) {
                rvWorngAlgorithm.visibility = View.GONE
                algorithm = false
            } else {
                rvWorngAlgorithm.visibility = View.VISIBLE
                algorithm = true
            }
        }
        tvWorngNoteSubjectDatabase.setOnClickListener {
            if (database) {
                rvWorngDatabase.visibility = View.GONE
                database = false
            } else {
                rvWorngDatabase.visibility = View.VISIBLE
                database = true
            }
        }
        tvWorngNoteSubjectDataStructure.setOnClickListener {
            if (dataStructure) {
                rvWorngDataStructure.visibility = View.GONE
                dataStructure = false
            } else {
                rvWorngDataStructure.visibility = View.VISIBLE
                dataStructure = true
            }
        }
        tvWorngNoteSubjectComputerStructure.setOnClickListener {
            if (computerStructure) {
                rvWorngComputerStructure.visibility = View.GONE
                computerStructure = false
            } else {
                rvWorngComputerStructure.visibility = View.VISIBLE
                computerStructure = true
            }
        }
        tvWorngNoteSubjectComputerNetwork.setOnClickListener {
            if (computerNetwork) {
                rvWorngComputerNetwork.visibility = View.GONE
                computerNetwork = false
            } else {
                rvWorngAlgorithm.visibility = View.VISIBLE
                computerNetwork = true
            }
        }
        tvWorngNoteSubjectOperationSystem.setOnClickListener {
            if (operationSystem) {
                rvWorngOperationSystem.visibility = View.GONE
                operationSystem = false
            } else {
                rvWorngOperationSystem.visibility = View.VISIBLE
                operationSystem = true
            }
        }
        tvWorngNoteSubjectSoftwareEngineering.setOnClickListener {
            if (softwareEngineering) {
                rvWorngSoftwareEngineering.visibility = View.GONE
                softwareEngineering = false
            } else {
                rvWorngSoftwareEngineering.visibility = View.VISIBLE
                softwareEngineering = true
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun getBookMarkData() {
        smuQuizInterface.getBookMarkNum(getUserEmail())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listNum ->
                Log.d("Result", "123123::" + listNum.size.toString())
                for (i in listNum) {
                    when (i.pr_id) {
                        in 1..100 -> getlikeDataDitail(likeListDS, i.pr_id)
                        in 101..200 -> getlikeDataDitail(likeListAlgorithm, i.pr_id)
                        in 201..300 -> getlikeDataDitail(likeListCS, i.pr_id)
                        in 301..400 -> getlikeDataDitail(likeListOS, i.pr_id)
                        in 401..500 -> getlikeDataDitail(likeListCN, i.pr_id)
                        in 501..600 -> getlikeDataDitail(likeListSE, i.pr_id)
                        in 601..700 -> getlikeDataDitail(likeListDB, i.pr_id)
                    }
                }
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr")
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete")
            })
    }

    @SuppressLint("CheckResult")
    private fun getlikeDataDitail(likeListDS: ArrayList<Quiz>, pr_id: Int) {
        smuQuizInterface.getWorngDetail(pr_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrong ->
                likeListDS.add(wrong[0])
                Log.d("Result", "123123:getWrongDataDitail:" + wrong[0].subject)
            }, { error ->
                error.printStackTrace()
                Log.d("Result", "ereerr::getWrongDataDitail")
            }, {
                // 작업이 정상적으로 완료되지 않았을 때 호출됩니다.
                Log.d("Result", "complete::getWrongDataDitail")
            })
    }

    private fun setText() {
        tvGlobalTitle.text = "즐겨찾기"
        tvWorngNoteSubjectAlgorithm.text = "Algorithm"
        tvWorngNoteSubjectDatabase.text = "Database"
        tvWorngNoteSubjectSoftwareEngineering.text = "SoftwareEngineering"
        tvWorngNoteSubjectOperationSystem.text = "OperationSystem"
        tvWorngNoteSubjectComputerNetwork.text ="ComputerNetwork"
        tvWorngNoteSubjectComputerStructure.text ="ComputerStructure"
        tvWorngNoteSubjectDataStructure.text ="DataStructure"


    }


    override fun onBackPressed() {
        if (drawer_layout_wrong_note.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_wrong_note.closeDrawer(GravityCompat.START)
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

        drawer_layout_wrong_note.closeDrawer(GravityCompat.START)
        return true
    }
}
