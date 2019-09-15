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
import android.view.View.GONE
import android.view.View.VISIBLE
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


class WrongNoteActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var wrongListAlgorithm = arrayListOf<Quiz>()
    var wrongListDB = arrayListOf<Quiz>()
    var wrongListSE = arrayListOf<Quiz>()
    var wrongListOS = arrayListOf<Quiz>()
    var wrongListCN = arrayListOf<Quiz>()
    var wrongListCS = arrayListOf<Quiz>()
    var wrongListDS = arrayListOf<Quiz>()
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

        getWrongData()
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

    @SuppressLint("CheckResult")
    private fun getWrongData() {
        smuQuizInterface.getWorngNum(getUserEmail())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrongNum ->
                Log.d("Result", "123123::" + wrongNum.size.toString())
                for (i in wrongNum) {
                    when (i.pr_id) {
                        in 1..100 -> getWrongDataDitail(wrongListDS, i.pr_id)
                        in 101..200 -> getWrongDataDitail(wrongListAlgorithm, i.pr_id)
                        in 201..300 -> getWrongDataDitail(wrongListCS, i.pr_id)
                        in 301..400 -> getWrongDataDitail(wrongListOS, i.pr_id)
                        in 401..500 -> getWrongDataDitail(wrongListCN, i.pr_id)
                        in 501..600 -> getWrongDataDitail(wrongListSE, i.pr_id)
                        in 601..700 -> getWrongDataDitail(wrongListDB, i.pr_id)
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
    private fun getWrongDataDitail(subject: ArrayList<Quiz>, wrongNum: Int) {
        smuQuizInterface.getWorngDetail(wrongNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ wrong ->
                subject.add(wrong[0])
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
        tvGlobalTitle.text = "오답노트"
        tvWorngNoteSubjectAlgorithm.text = String.format(this.getString(R.string.wrong_problem), "Algorithm")
        tvWorngNoteSubjectDatabase.text = String.format(this.getString(R.string.wrong_problem), "Database")
        tvWorngNoteSubjectSoftwareEngineering.text =
            String.format(this.getString(R.string.wrong_problem), "SoftwareEngineering")
        tvWorngNoteSubjectOperationSystem.text =
            String.format(this.getString(R.string.wrong_problem), "OperationSystem")
        tvWorngNoteSubjectComputerNetwork.text =
            String.format(this.getString(R.string.wrong_problem), "ComputerNetwork")
        tvWorngNoteSubjectComputerStructure.text =
            String.format(this.getString(R.string.wrong_problem), "ComputerStructure")
        tvWorngNoteSubjectDataStructure.text = String.format(this.getString(R.string.wrong_problem), "DataStructure")
    }

    private fun setRecyclerView() {
        val mAdapterA = WorngListAdapter(this, wrongListAlgorithm)
        val mAdapterDB = WorngListAdapter(this, wrongListDB)
        val mAdapterDS = WorngListAdapter(this, wrongListDS)
        val mAdapterCN = WorngListAdapter(this, wrongListCN)
        val mAdapterCS = WorngListAdapter(this, wrongListCS)
        val mAdapterOS = WorngListAdapter(this, wrongListOS)
        val mAdapterSE = WorngListAdapter(this, wrongListSE)

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


    private fun setRecyclerViewVisible() {
        tvWorngNoteSubjectAlgorithm.setOnClickListener {
            if (algorithm) {
                rvWorngAlgorithm.visibility = GONE
                algorithm = false
            } else {
                rvWorngAlgorithm.visibility = VISIBLE
                algorithm = true
            }
        }
        tvWorngNoteSubjectDatabase.setOnClickListener {
            if (database) {
                rvWorngDatabase.visibility = GONE
                database = false
            } else {
                rvWorngDatabase.visibility = VISIBLE
                database = true
            }
        }
        tvWorngNoteSubjectDataStructure.setOnClickListener {
            if (dataStructure) {
                rvWorngDataStructure.visibility = GONE
                dataStructure = false
            } else {
                rvWorngDataStructure.visibility = VISIBLE
                dataStructure = true
            }
        }
        tvWorngNoteSubjectComputerStructure.setOnClickListener {
            if (computerStructure) {
                rvWorngComputerStructure.visibility = GONE
                computerStructure = false
            } else {
                rvWorngComputerStructure.visibility = VISIBLE
                computerStructure = true
            }
        }
        tvWorngNoteSubjectComputerNetwork.setOnClickListener {
            if (computerNetwork) {
                rvWorngComputerNetwork.visibility = GONE
                computerNetwork = false
            } else {
                rvWorngAlgorithm.visibility = VISIBLE
                computerNetwork = true
            }
        }
        tvWorngNoteSubjectOperationSystem.setOnClickListener {
            if (operationSystem) {
                rvWorngOperationSystem.visibility = GONE
                operationSystem = false
            } else {
                rvWorngOperationSystem.visibility = VISIBLE
                operationSystem = true
            }
        }
        tvWorngNoteSubjectSoftwareEngineering.setOnClickListener {
            if (softwareEngineering) {
                rvWorngSoftwareEngineering.visibility = GONE
                softwareEngineering = false
            } else {
                rvWorngSoftwareEngineering.visibility = VISIBLE
                softwareEngineering = true
            }
        }

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
