package com.smu.sangmyung.smu_quiz

import com.smu.sangmyung.smu_quiz.model.Quiz
import com.smu.sangmyung.smu_quiz.model.User
import com.smu.sangmyung.smu_quiz.model.Wrong
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.DELETE




interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/mocktest?subject=Database&subject=operation_system")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest")
    fun getMocktest(@QueryMap option: Map<String, String>): Observable<List<Quiz>>

    //    fun groupList(@Path("id") groupId: Int, @QueryMap options: Map<String, String>): Call<List<User>>
    @GET("/register/wrong")
    fun getWorngNum(@Query("email") email: String): Observable<List<Wrong>>

    @GET("/register/detail")
    fun getWorngDetail(@Query("pr_id") wrongNum: Int): Observable<List<Quiz>>

    @GET("/quiz/request")
    fun getDailyQuiz(@Query("subject") subject: String): Observable<List<Quiz>>

    //    @FormUrlEncoded
    @POST("/register/wrong")
    fun setWrongQuiz(@Body value: Wrong): Flowable<Wrong>

    @POST("/register/bookmark")
    fun setBookMark(@Body value: Wrong): Flowable<Wrong>

    @POST("/register")
    fun setUser(@Body value: User): Flowable<User>

    @DELETE("/register/wrong/{id}")
    fun deleteWrong(@Path("id") id: String, @Body value: User): Flowable<User>

    @DELETE("/register/bookmark/{id}")
    fun deleteFavoirty(@Path("id") id: String, @Body value: User): Flowable<User>


}
