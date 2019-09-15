package com.smu.sangmyung.quiz

import com.smu.sangmyung.quiz.model.Quiz
import com.smu.sangmyung.quiz.model.User
import com.smu.sangmyung.quiz.model.Wrong
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.HTTP
import retrofit2.http.FormUrlEncoded




interface SmuQuizInterface {

    /** Get StationResponse with TM Position **/
    @GET("/quiz/mocktest?subject=Database&subject=operation_system")
    fun test(): Observable<List<Quiz>>

    @GET("/quiz/mocktest")
    fun getMocktest(@QueryMap option: Map<String, String>): Observable<List<Quiz>>

    @GET("/register/wrong")
    fun getWorngNum(@Query("email") email: String): Observable<List<Wrong>>

    @GET("/register/bookmark")
    fun getBookMarkNum(@Query("email") email: String): Observable<List<Wrong>>

    @GET("/register/detail")
    fun getWorngDetail(@Query("pr_id") wrongNum: Int): Observable<List<Quiz>>

    @GET("/quiz/request")
    fun getDailyQuiz(@Query("subject") subject: String): Observable<List<Quiz>>

    @POST("/register/wrong")
    fun setWrongQuiz(@Body value: Wrong): Flowable<Wrong>

    @POST("/register/bookmark")
    fun setBookMark(@Body value: Wrong): Flowable<Wrong>

    @POST("/register/user_list")
    fun setUser(@Body value: User): Flowable<User>

    @FormUrlEncoded
    @HTTP(method = "DELETE",path = "{/register/bookmark/{id}}",hasBody = true)
    fun deleteFavoirty(@Path("id") id: String, @Body value: User): Flowable<User>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/register/wrong/{id}", hasBody = true)
    fun deleteWrong(@Path("id") id: String,@Body value: User): Flowable<User>

}
