package com.anderson.schedulecake.model

import com.anderson.schedulecake.auth.AuthResponse
import com.anderson.schedulecake.rest.LoginResquest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun registerUser(@Body loginResquest: LoginResquest): Call<UserResponse>


    @POST("auth/login")
    fun login(@Body loginResquest: LoginResquest): Call<AuthResponse>

    @GET("schedule/encomenda")
    fun getEncomendas(@Header("Authorization") token: String): Call<Encomenda>

    @GET("schedule/cliente")
    fun getCliente(@Header("Authorization") token:String): Call<List<Cliente>>

}