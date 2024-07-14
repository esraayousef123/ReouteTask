package com.example.routetask.API

import com.example.routetask.Model.Api.Response
import retrofit2.http.GET

interface ApiProductServices {
 @GET("/products")
 suspend fun getProducts():Response





}