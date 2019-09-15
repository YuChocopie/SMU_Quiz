package com.smu.sangmyung.quiz.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.sangmyung.quiz.model.Quiz
import com.smu.sangmyung.quiz.R
import kotlinx.android.synthetic.main.item_wrong_note_quiz.view.*


class WorngListAdapter(val context: Context, val worngList: List<Quiz>) :
    RecyclerView.Adapter<WorngListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viweType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_wrong_note_quiz, parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return worngList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(worngList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuizNum = itemView.tvWorngNoteQuizNum
        private val tvQuizTexe1 = itemView.tvWorngNoteQuizText1
        private val tvQuizTexe2 = itemView.tvWorngNoteQuizText2
        private val tvQuizTexe3 = itemView.tvWorngNoteQuizText3
        private val tvQuizTexe4 = itemView.tvWorngNoteQuizText4
        private val tvQuizAnswer = itemView.tvWorngNoteQuizAnswer
        private val tvQuizExplain = itemView.tvWorngNoteQuizExplain

        @SuppressLint("SetTextI18n")
        fun bind(mainMenu: Quiz) {
            tvQuizNum.text = String.format(context.getString(R.string.question), mainMenu.title)
            tvQuizTexe1.text = "1. "+mainMenu.choice_1
            tvQuizTexe2.text = "2. "+mainMenu.choice_2
            tvQuizTexe3.text = "3. "+mainMenu.choice_3
            tvQuizTexe4.text = "4. "+mainMenu.choice_4
            tvQuizAnswer.text = String.format(context.getString(R.string.wrong_problem), mainMenu.answer.toString())
            tvQuizExplain.text = "해설 : "+mainMenu.explain
        }
    }
}