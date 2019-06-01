package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.dataclass.Quiz
import com.smu.sangmyung.smu_quiz.dataclass.Wrong
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/request?subject=Database")
    fun test(): Observable <List<Quiz>>


    @GET("/register/wrong")
    fun getWorngNum(@Query("email")email : String): Observable <List<Wrong>>

    @GET("/register/detail")
    fun getWorngDetail(@Query("pr_id")wrongNum : Int): Observable <List<Quiz>>

}
