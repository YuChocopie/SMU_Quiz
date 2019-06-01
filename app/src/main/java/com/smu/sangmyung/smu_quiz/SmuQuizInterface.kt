package com.smu.sangmyung.smu_quiz

import io.reactivex.Observable
import com.smu.sangmyung.smu_quiz.model.Quiz
import com.smu.sangmyung.smu_quiz.model.Wrong
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.QueryMap



interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/mocktest?subject=Database&subject=operation_system")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest")
    fun getMocktest(@QueryMap option:Map<String,String>): Observable<List<Quiz>>

//        @QueryMap options: Map<String, String>): Call<List<Quiz>>

//    fun getMocktest(@QueryMap (true) Map<String,String> options): Observable<List<Quiz>>


//    fun groupList(@Path("id") groupId: Int, @QueryMap options: Map<String, String>): Call<List<User>>
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


}
