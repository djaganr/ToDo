package com.example.todo


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServiceInterface {

    @Headers("Content-Type:application/json")
    @GET("/users")
    fun getAllUsers() : Call<ApiResponse>
}