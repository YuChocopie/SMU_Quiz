package com.smu.sangmyung.smu_quiz

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_subject.*

public open class BaseActivity : AppCompatActivity() {

    var all :Int =0
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

        editor.putBoolean("algorithm",b1)
            .putBoolean("computer_network",b2)
            .putBoolean("computer_structure",b3)
            .putBoolean("data_structure",b4)
            .putBoolean("database",b5)
            .putBoolean("operation_system",b6)
            .putBoolean("sofrware_engineering",b7)
            .apply()

        editor.commit()
    }
    fun loadSubject() {
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
    fun saveCurrentUserEmail(useremail:String){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putString("useremail",useremail)
            .apply()

        editor.commit()
    }
    fun loadCurrentUserEmail():String{
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val useremail = pref.getString("useremail","false")
        return useremail
    }


    fun saveQuizResult(subject: String,num:Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        when(subject){
            "all" -> {
                editor.putInt("num", all.plus(num))
                    .apply()
                editor.commit()
            }
            "algo" -> {
                editor.putInt("num", algo.plus(num))
                    .apply()
                editor.commit()
            }
            "comne" -> {editor.putInt("num",comne.plus(num))
            .apply()
            editor.commit()
            }
            "comstruc" -> {
                editor.putInt("num", comstruc.plus(num))
                    .apply()
                editor.commit()
            }
            "datastruc" -> {
                editor.putInt("num", datastruc.plus(num))
                    .apply()
                editor.commit()
            }
            "daba" -> {
                editor.putInt("num", daba.plus(num))
                    .apply()
                editor.commit()
            }
            "oper" -> {
                editor.putInt("num", oper.plus(num))
                    .apply()
                editor.commit()
            }
            "soft" -> {
                editor.putInt("num", soft.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_all" -> {
                editor.putInt("num", wr_all.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_algo" -> {
                editor.putInt("num", wr_algo.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_comne" -> {
                editor.putInt("num", wr_comne.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_comstruc" -> {
                editor.putInt("num", wr_comstruc.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_datastruc" -> {
                editor.putInt("num", wr_datastruc.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_daba" -> {
                editor.putInt("num", wr_daba.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_oper" -> {
                editor.putInt("num", wr_oper.plus(num))
                    .apply()
                editor.commit()
            }
            "wr_soft" -> {
                editor.putInt("num", wr_soft.plus(num))
                    .apply()
                editor.commit()
            }
        }
    }
    fun loadQuizResult():Int {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val subject = pref.getString("subject", "")
        var nnum = 0
        when (subject) {
            "all" -> nnum= pref.getInt("num", all)
            "algo" -> nnum=  pref.getInt("num", algo)
            "comne" -> nnum=  pref.getInt("num", comne)
            "comstruc" -> nnum=  pref.getInt("num", comstruc)
            "datastruc" -> nnum=  pref.getInt("num", datastruc)
            "daba" -> nnum=  pref.getInt("num", daba)
            "oper" -> nnum=  pref.getInt("num", oper)
            "soft" -> nnum=  pref.getInt("num", soft)
            "wr_all" -> nnum=  pref.getInt("num", wr_all)
            "wr_algo" -> nnum=  pref.getInt("num", wr_algo)
            "wr_comne" -> nnum=  pref.getInt("num", wr_comne)
            "wr_comstruc" -> nnum=  pref.getInt("num", wr_comstruc)
            "wr_datastruc" -> nnum=  pref.getInt("num", wr_datastruc)
            "wr_daba" -> nnum=  pref.getInt("num", wr_daba)
            "wr_oper" -> nnum=  pref.getInt("num", wr_oper)
            "wr_soft" -> nnum=  pref.getInt("num", wr_soft)
        }
        return nnum

    }

}

