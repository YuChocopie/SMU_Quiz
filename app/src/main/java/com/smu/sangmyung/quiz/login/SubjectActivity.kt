package com.smu.sangmyung.quiz.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.smu.sangmyung.quiz.BaseActivity
import com.smu.sangmyung.quiz.worng.FavoriteActivity
import com.smu.sangmyung.quiz.MainActivity
import com.smu.sangmyung.quiz.R
import com.smu.sangmyung.quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.quiz.worng.WrongNoteActivity
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class SubjectActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener  {

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
                get_subject("Algorithme&")
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
                get_subject("Data_Structure&")
            }
        }
        cb_database.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                get_subject("Database&")
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
                get_subject("Software_Engineering&")
            }
        }
        loadSubject()
        loadSubjectlist()
        btn_submit.setOnClickListener{
            saveSubjectlist(all_subject)

            saveSubject(cb_algorithm.isChecked, cb_computer_network.isChecked, cb_computer_structure.isChecked,
                cb_data_structure.isChecked, cb_database.isChecked,
                cb_operation_system.isChecked, cb_software_engineering.isChecked)
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout_subject, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout_subject.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_sub.setNavigationItemSelectedListener(this)

        val email = getUserEmail()
        val nav_header_view = nav_view_sub.getHeaderView(0)
        nav_header_view.tv_nvheader_email.text=email

    }

    fun get_subject(subject:String){
        all_subject += subject
    }


    override fun onBackPressed() {
        if (drawer_layout_subject.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_subject.closeDrawer(GravityCompat.START)
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

        drawer_layout_subject.closeDrawer(GravityCompat.START)
        return true
    }


}

