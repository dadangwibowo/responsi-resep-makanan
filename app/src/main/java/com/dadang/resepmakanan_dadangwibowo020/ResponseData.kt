package com.dadang.resepmakanan_dadangwibowo020

data class ResponseData<T> (
        val method: String,
        val status: Boolean,
        val results: T
)