package com.ram.service_android_check


import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/users/2")
    suspend fun fetchData(): Response<DataModel>
}