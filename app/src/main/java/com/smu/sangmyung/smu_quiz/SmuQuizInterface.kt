package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.DataClass.QuizSubject
import com.smu.sangmyung.smu_quiz.dataclass.Quiz
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/request?subject=Database")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest?subject=operation_system&&computer_structure")
    fun mocktest(): Observable<List<Quiz>>
}
