package com.dadang.resepmakanan_dadangwibowo020

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("api/recipes")
    fun listMakanan(): Call<ResponseData<List<ListItemMakanan>>>
}