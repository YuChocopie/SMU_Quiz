package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.dataclass.Quiz
import io.reactivex.Observable
import retrofit2.http.GET

interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/request?subject=Database")
    fun test(): Observable <List<Quiz>>

}
