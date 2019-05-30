package com.smu.sangmyung.smu_quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_after_login.*
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.activity_subject.drawer_layout
import kotlinx.android.synthetic.main.item_glibal_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class SubjectActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    private var all_subject:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        cb_selectall.setOnClickListener {
            cb_algorithm.toggle()
            cb_computer_network.toggle()
            cb_computer_structure.toggle()
            cb_data_structure.toggle()
            cb_database.toggle()
            cb_operation_system.toggle()
            cb_software_engineering.toggle()
        }

        cb_algorithm.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&algorithm")
            }
        }
        cb_computer_network.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&computer_network")
            }
        }
        cb_data_structure.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&data_structure")
            }
        }
        cb_database.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&database")
            }
        }
        cb_operation_system.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&operation_system")
            }
        }
        cb_software_engineering.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                get_subject("&software_engineering")
            }
        }
        btn_submit.setOnClickListener{
            val intent = Intent(applicationContext,AfterLoginActivity::class.java)
            intent.putExtra("subject",all_subject)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view_sub.setNavigationItemSelectedListener(this)

        val email =user!!.email
        val nav_header_view = nav_view.getHeaderView(0)
        nav_header_view.tv_nvheader_email?.text=email.toString()

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
                val intent= Intent(applicationContext,SubjectActivity::class.java)
                startActivity(intent)
            }
            R.id.wrong_ques -> {

            }

            R.id.gotomain -> {
                val intent= Intent(applicationContext,AfterLoginActivity::class.java)
                startActivity(intent)
            }
            R.id.community_ques -> {

            }
            R.id.community_free -> {

            }
            R.id.logout ->{
                val intent= Intent(applicationContext,GoogleSignInActivity::class.java)
                startActivity(intent)
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(applicationContext,"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
