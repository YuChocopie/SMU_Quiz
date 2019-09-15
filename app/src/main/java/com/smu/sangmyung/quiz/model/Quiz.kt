package com.smu.sangmyung.quiz.model

data class Quiz (
    val pr_id :Int,
    val subject :String,
    val image :String,
    val title :String,
    val answer :Int,
    val explain :String,
    val choice_1 :String,
    val choice_2 :String,
    val choice_3 :String,
    val choice_4 :String
)
