package com.example.routetask.Repository

import com.example.newsapp.API.RetrofitObject
import com.example.routetask.Model.Api.ProductsItem

class BringDataFromApiImp:BringDataFromApi {
    override suspend fun bringProductFromApi():List<ProductsItem?> {
        return try {
            val productResponse = RetrofitObject.apiInterfaceImplementationByRetrofit().getProducts()
            productResponse.products ?: emptyList()
        } catch (e: Exception) {

            emptyList()
        }

    }
}