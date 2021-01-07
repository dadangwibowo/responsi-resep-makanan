package com.dadang.resepmakanan_dadangwibowo020

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIManager {
    private var apiService: APIService? = null
    init {
        createService()
    }
    val service: APIService get() = apiService!!
    private fun createService() {
        val loggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i("Retrofit", message)
                }
            })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://masak-apa.tomorisakura.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create<APIService>(APIService::class.java)
    }
    companion object {
        private var instance: APIManager? = null
        fun getInstance(): APIManager {
            return instance ?: synchronized(this) {
                APIManager().also { instance = it }
            }
        }
    }
}