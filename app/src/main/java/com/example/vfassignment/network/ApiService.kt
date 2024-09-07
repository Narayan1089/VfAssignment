package com.example.vfassignment.network

import com.example.vfassignment.domain.Model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/user/get-all-login-images")
    fun getLoginImages(): Call<ImageResponse>
}
