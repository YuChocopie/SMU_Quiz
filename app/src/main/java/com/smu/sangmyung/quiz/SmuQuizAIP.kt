package com.smu.sangmyung.quiz

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class SmuQuizAIP {

    fun smuQuizInfoRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sheltered-waters-56326.herokuapp.com/")
            .build()
        return retrofit!!
    }

}