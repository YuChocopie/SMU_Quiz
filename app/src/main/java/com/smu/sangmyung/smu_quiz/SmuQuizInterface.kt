package com.smu.sangmyung.smu_quiz

import io.reactivex.Observable
import retrofit2.http.GET
import com.smu.sangmyung.smu_quiz.model.Quiz
import com.smu.sangmyung.smu_quiz.model.Wrong
import retrofit2.http.Query

interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/request?subject=Database")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest?subject=operation_system&&computer_structure")
    fun mocktest(): Observable<List<Quiz>>

    @GET("/register/wrong")
    fun getWorngNum(@Query("email")email : String): Observable <List<Wrong>>

    @GET("/register/detail")
    fun getWorngDetail(@Query("pr_id")wrongNum : Int): Observable <List<Quiz>>

    @GET("/quiz/request")
    fun getDailyQuiz(@Query("subject")subject : String): Observable <List<Quiz>>

}
