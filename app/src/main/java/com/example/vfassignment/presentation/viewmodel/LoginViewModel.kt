package com.example.vfassignment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vfassignment.domain.Model.ImageData
import com.example.vfassignment.domain.Model.ImageResponse
import com.example.vfassignment.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class LoginViewModel : ViewModel() {

    private val _loginImages = MutableLiveData<List<ImageData>>()
    val loginImages: LiveData<List<ImageData>> get() = _loginImages

    fun fetchLoginImages() {
        RetrofitClient.apiService.getLoginImages().enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _loginImages.value = it.data
                    }
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
               Log.e("Failed Call ","Failed to load : $t")
            }
        })
    }
}
