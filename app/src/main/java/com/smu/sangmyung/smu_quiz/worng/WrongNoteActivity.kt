package com.smu.sangmyung.smu_quiz.worng

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.smu.sangmyung.smu_quiz.R
import com.smu.sangmyung.smu_quiz.adapters.WorngListAdapter
import com.smu.sangmyung.smu_quiz.dataclass.Quiz
import kotlinx.android.synthetic.main.activity_wrong_note.*
import kotlinx.android.synthetic.main.item_global_title.*


class WrongNoteActivity : AppCompatActivity() {

    var wrongList = arrayListOf<Quiz>(
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4"),
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4"),
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4"),
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4"),
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4"),
        Quiz(1, "subject", "", "title", 3, "해설", "1", "2", "3", "4")
    )
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

        setRecyclerView()
        setRecyclerViewVisible()

    }

    private fun setRecyclerView() {
        val mAdapter = WorngListAdapter(this, wrongList)
        val lm = LinearLayoutManager(this)
        val lm2 = LinearLayoutManager(this)
        val lm3 = LinearLayoutManager(this)
        rvWorngAlgorithm.adapter = mAdapter
        rvWorngAlgorithm.layoutManager = lm
//        rvWorngAlgorithm.setHasFixedSize(true)

        rvWorngDatabase.adapter = mAdapter
        rvWorngDatabase.layoutManager = lm2

        rvWorngSoftwareEngineering.adapter = mAdapter
        rvWorngSoftwareEngineering.layoutManager = lm3
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
}
