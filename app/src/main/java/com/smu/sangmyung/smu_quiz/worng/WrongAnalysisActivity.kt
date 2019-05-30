package com.smu.sangmyung.smu_quiz.worng

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.SmuQuizAIP
import com.smu.sangmyung.smu_quiz.SmuQuizInterface
import kotlinx.android.synthetic.main.activity_wrong_analysis.*
import kotlinx.android.synthetic.main.item_global_title.*

class WrongAnalysisActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")

    private var smuQuizAIP = SmuQuizAIP()
    private var smuQuizRetrofit = smuQuizAIP.smuQuizInfoRetrofit()
    private var smuQuizInterface = smuQuizRetrofit.create(SmuQuizInterface::class.java)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_analysis)
        tvGlobalTitle.text = "오답분석"

        val points = intArrayOf(5, 3, 7, 8, 0, 0, 3)

        LineGraphView?.setPoints(points, 1.0, 0, 10)
        LineGraphView?.drawForBeforeDrawView()


        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val infoview = circleGraph as LinearLayout
        val sampleView = CircleGraphView(this)
        infoview.addView(sampleView)

    }


}