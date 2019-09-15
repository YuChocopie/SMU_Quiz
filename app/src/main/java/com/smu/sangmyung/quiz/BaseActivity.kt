package com.smu.sangmyung.quiz

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_subject.*

public open class BaseActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var all :Int=0
    var algo: Int=0
    var comne: Int=0
    var comstruc: Int=0
    var datastruc: Int=0
    var daba: Int=0
    var oper: Int=0
    var soft: Int=0
    var wr_all: Int=0
    var wr_algo: Int=0
    var wr_comne: Int=0
    var wr_comstruc: Int=0
    var wr_datastruc: Int=0
    var wr_daba: Int=0
    var wr_oper: Int=0
    var wr_soft: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun saveSubject(b1: Boolean,b2:Boolean,b3:Boolean,b4:Boolean,b5:Boolean,b6:Boolean,b7:Boolean){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putBoolean("Algorithme",b1)
            .putBoolean("computer_network",b2)
            .putBoolean("computer_structure",b3)
            .putBoolean("Data_Structure",b4)
            .putBoolean("Database",b5)
            .putBoolean("operation_system",b6)
            .putBoolean("Software_Engineering",b7)
            .apply()

        editor.commit()
    }

    fun loadSubject() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val b1 = pref.getBoolean("Algorithme", false)
        val b2 = pref.getBoolean("computer_network", false)
        val b3 = pref.getBoolean("computer_structure", false)
        val b4 = pref.getBoolean("Data_Structure", false)
        val b5 = pref.getBoolean("Database", false)
        val b6 = pref.getBoolean("operation_system", false)
        val b7 = pref.getBoolean("Software_Engineering", false)
        cb_algorithm.isChecked = b1
        cb_computer_network.isChecked = b2
        cb_computer_structure.isChecked = b3
        cb_data_structure.isChecked = b4
        cb_database.isChecked = b5
        cb_operation_system.isChecked = b6
        cb_software_engineering.isChecked = b7

    }
    fun saveSubjectlist(subjectlist : String){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.putString("subjectlist",subjectlist)
            .apply()

        editor.commit()
    }
    fun loadSubjectlist():String{
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val subjectlist = pref.getString("subjectlist","")
        return subjectlist
    }
    //현재 로그인된 유저 이메일 받아오기
    fun getUserEmail():String{
        auth = FirebaseAuth.getInstance()
        return auth.currentUser?.email.toString()
    }


    fun saveQuizResult(subject: String,num:Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        when(subject){
            "all" -> {
                editor.putInt("all",pref.getInt("all",0) +num)
                    .apply()

            }
            "Algorithme" -> {
                editor.putInt("algo", pref.getInt("algo",0) +num)
                    .apply()

            }
            "computer_network" -> {editor.putInt("comne",pref.getInt("comne",0) +num)
            .apply()

            }
            "computer_structure" -> {
                editor.putInt("comstruc", pref.getInt("comstruc",0) +num)
                    .apply()

            }
            "Data_Structure" -> {
                editor.putInt("datastruc", pref.getInt("datastruc",0) +num)
                    .apply()

            }
            "Database" -> {
                editor.putInt("daba", pref.getInt("daba",0) +num)
                    .apply()

            }
            "operation_system" -> {
                editor.putInt("oper", pref.getInt("oper",0) +num)
                    .apply()

            }
            "Software_Engineering" -> {
                editor.putInt("soft", pref.getInt("soft",0) +num)
                    .apply()

            }
            "wr_all" -> {
                editor.putInt("wr_all", pref.getInt("wr_all",0) +num)
                    .apply()

            }
            "wr_Algorithme" -> {
                editor.putInt("wr_algo", pref.getInt("wr_algo",0) +num)
                    .apply()

            }
            "wr_computer_network" -> {
                editor.putInt("wr_comne", pref.getInt("wr_comne",0) +num)
                    .apply()

            }
            "wr_computer_structure" -> {
                editor.putInt("wr_comstruc", pref.getInt("wr_comstruc",0) +num)
                    .apply()

            }
            "wr_Data_Structure" -> {
                editor.putInt("wr_datastruc", pref.getInt("wr_datastruc",0) +num)
                    .apply()

            }
            "wr_Database" -> {
                editor.putInt("wr_daba", pref.getInt("wr_daba",0) +num)
                    .apply()

            }
            "wr_operation_system" -> {
                editor.putInt("wr_oper", pref.getInt("wr_oper",0) +num)
                    .apply()

            }
            "wr_Software_Engineering" -> {
                editor.putInt("wr_soft", pref.getInt("wr_soft",0) +num)
                    .apply()

            }
        }
        editor.commit()
    }
    fun loadQuizResult(subject:String):Int {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        when (subject) {
            "all" -> return pref.getInt("all",0)
            "Algorithme" -> return  pref.getInt("algo", 0)
            "computer_network" -> return pref.getInt("comne", 0)
            "computer_structure" -> return  pref.getInt("comstruc", 0)
            "Data_Structure" -> return  pref.getInt("datastruc", 0)
            "Database" -> return pref.getInt("daba", 0)
            "operation_system" -> return pref.getInt("oper", 0)
            "Software_Engineering" -> return pref.getInt("soft", 0)
            "wr_all" -> return pref.getInt("wr_all", 0)
            "wr_Algorithme" -> return pref.getInt("wr_algo", 0)
            "wr_computer_network" -> return pref.getInt("wr_comne", 0)
            "wr_computer_structure" -> return pref.getInt("wr_comstruc", 0)
            "wr_Data_Structure" -> return pref.getInt("wr_datastruc", 0)
            "wr_Database" -> return pref.getInt("wr_daba", 0)
            "wr_operation_system" -> return  pref.getInt("wr_oper", 0)
            "wr_Software_Engineering" -> return  pref.getInt("wr_soft", 0)
        }
        return 0
    }

}

