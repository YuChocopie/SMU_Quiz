package com.smu.sangmyung.smu_quiz.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.MainActivity
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.smu_quiz.worng.WrongNoteActivity
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.activity_subject.drawer_layout
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class SubjectActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    private var all_subject:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)
        tvGlobalTitle.text = "문제 유형 설정"

        cb_selectall.setOnClickListener {
            cb_algorithm.toggle()
            cb_computer_network.toggle()
            cb_computer_structure.toggle()
            cb_data_structure.toggle()
            cb_database.toggle()
            cb_operation_system.toggle()
            cb_software_engineering.toggle()
        }

        cb_algorithm.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("&algorithm&")
            }
        }
        cb_computer_network.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("computer_network&")
            }
        }
        cb_computer_structure.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("computer_structure&")
            }
        }
        cb_data_structure.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("data_structure&")
            }
        }
        cb_database.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("database&")
            }
        }
        cb_operation_system.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("operation_system&")
            }
        }
        cb_software_engineering.setOnCheckedChangeListener { _
                                                             , isChecked ->
            if(isChecked){
                get_subject("sofrware_engineering&")
            }
        }
        loadSubject()
        btn_submit.setOnClickListener{
            saveSubject(cb_algorithm.isChecked, cb_computer_network.isChecked, cb_computer_structure.isChecked,
                cb_data_structure.isChecked, cb_database.isChecked,
                cb_operation_system.isChecked, cb_software_engineering.isChecked)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("subject",all_subject)
            startActivity(intent)
            finish()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_sub.setNavigationItemSelectedListener(this)

//       val email =user!!.email
//       val nav_header_view = nav_view_sub.getHeaderView(0)
//       nav_header_view.tv_nvheader_email?.text=email.toString()

    }

    fun get_subject(subject:String){
        all_subject += subject
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

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun saveSubject(b1: Boolean,b2:Boolean,b3:Boolean,b4:Boolean,b5:Boolean,b6:Boolean,b7:Boolean){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putBoolean("algorithm",b1)
            .putBoolean("computer_network",b2)
            .putBoolean("computer_structure",b3)
            .putBoolean("data_structure",b4)
            .putBoolean("database",b5)
            .putBoolean("operation_system",b6)
            .putBoolean("sofrware_engineering",b7)
            .apply()
    }
    private fun loadSubject() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val b1 = pref.getBoolean("algorithm", false)
        val b2 = pref.getBoolean("computer_network", false)
        val b3 = pref.getBoolean("computer_structure", false)
        val b4 = pref.getBoolean("data_structure", false)
        val b5 = pref.getBoolean("database", false)
        val b6 = pref.getBoolean("operation_system", false)
        val b7 = pref.getBoolean("sofrware_engineering", false)
            cb_algorithm.isChecked = b1
            cb_computer_network.isChecked = b2
            cb_computer_structure.isChecked = b3
            cb_data_structure.isChecked = b4
            cb_database.isChecked = b5
            cb_operation_system.isChecked = b6
            cb_software_engineering.isChecked = b7

    }

}

