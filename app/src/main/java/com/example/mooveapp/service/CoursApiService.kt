package com.example.mooveapp.service

import com.example.mooveapp.model.Cours
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://10.0.2.2:8080/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CoursApiService {
    @GET("api/cours")
    suspend fun getCoursList(): List<Cours>

    @DELETE("api/cours/{id}")
    suspend fun deleteCours(@Path("id") id: Long)

    @PUT("api/cours/{id}")
    suspend fun updateCours(
        @Path("id") id: Long,
        @Body cours: Cours
    ): Cours

    @POST("api/cours")
    suspend fun addCours(@Body cours: Cours): Cours
}

object CoursApi {
    val retrofitService: CoursApiService by lazy {
        retrofit.create(CoursApiService::class.java)
    }
}