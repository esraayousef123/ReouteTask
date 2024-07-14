//i will create a retrofit object here which is responsable for implement the interface
//Retrofit adapts a java interface to HTTP calls by using annotations on the declared methods to define how requests are made.
// Create instances using the builder and pass your interface to create to generate an implementation.
package com.example.newsapp.API

import android.util.Log
import com.example.routetask.API.ApiProductServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {


    //logging interceptor
    ;
    val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.e("apiRes", message)
    }
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()





    fun apiInterfaceImplementationByRetrofit(): ApiProductServices { //return object from the interface
        return retrofit.create(ApiProductServices::class.java)

    }


}