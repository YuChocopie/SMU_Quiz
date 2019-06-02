package com.smu.sangmyung.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.smu.sangmyung.smu_quiz.login.GoogleSignInActivity
import com.smu.sangmyung.smu_quiz.login.SubjectActivity
import com.smu.sangmyung.smu_quiz.worng.WrongAnalysisActivity
import com.smu.sangmyung.smu_quiz.worng.WrongNoteActivity
import kotlinx.android.synthetic.main.activity_wrong_note.*
import kotlinx.android.synthetic.main.item_global_title.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class FavoriteActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    val email: String = user!!.email.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_note)

        tvGlobalTitle.text = "즐겨찾기"

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


    val toggle = ActionBarDrawerToggle(
        this, drawer_layout_wrong_note, toolbar,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    )
    drawer_layout_wrong_note.addDrawerListener(toggle)
    toggle.syncState()
    nav_view_wrong_note.setNavigationItemSelectedListener(this)

    val email = user!!.email
    val nav_header_view = nav_view_wrong_note.getHeaderView(0)
    nav_header_view.tv_nvheader_email?.text=email.toString()


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
