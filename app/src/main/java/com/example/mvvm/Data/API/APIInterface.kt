package com.example.mvvm.Data.API

import com.example.mvvm.Data.Model.Images
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("Photos")
    fun getUserDetails(): Call<MutableList<Images?>?>?
}