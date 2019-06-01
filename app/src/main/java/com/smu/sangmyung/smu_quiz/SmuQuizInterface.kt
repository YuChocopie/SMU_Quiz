package com.smu.sangmyung.smu_quiz

import io.reactivex.Observable
import com.smu.sangmyung.smu_quiz.model.Quiz
import com.smu.sangmyung.smu_quiz.model.Wrong
import io.reactivex.Flowable
import retrofit2.http.*

interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/mocktest?subject=Database&subject=operation_system")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest")
    fun getMocktest(@Query("subject") subject: String): Observable<List<Quiz>>

    @GET("/register/wrong")
    fun getWorngNum(@Query("email")email : String): Observable <List<Wrong>>

    @GET("/register/detail")
    fun getWorngDetail(@Query("pr_id")wrongNum : Int): Observable <List<Quiz>>

    @GET("/quiz/request")
    fun getDailyQuiz(@Query("subject")subject : String): Observable <List<Quiz>>

//    @FormUrlEncoded
    @POST("/register/wrong")
    fun setWrongQuiz(@Body value: Wrong) : Flowable<Wrong>

   @POST("/register/bookmark")
    fun setBookMark(@Body value: Wrong) : Flowable<Wrong>


//
//    @POST("/posts")
//    Call<ResponseGet> postFirst(@FieldMap HashMap<String, Object> parameters)
//
//    @PUT("/posts/1")
//    Call<ResponseGet> putFirst(@Body RequestPut parameters)


}
