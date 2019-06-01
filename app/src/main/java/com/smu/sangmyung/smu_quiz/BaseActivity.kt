package com.smu.sangmyung.smu_quiz

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_subject.*

public open class BaseActivity : AppCompatActivity() {

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
}

