package com.example.routetask.ProductRepository

import com.example.newsapp.API.RetrofitObject
import com.example.routetask.Model.Api.ProductsItem
import javax.inject.Inject

class ProductFromApiRepositoryImp @Inject constructor() : ProductFromApiRepository {
    override suspend fun getProduct(): List<ProductsItem?> {
        return try {
            val productResponse = RetrofitObject.apiInterfaceImplementationByRetrofit().getProducts()
            productResponse.products ?: emptyList()
        } catch (e: Exception) {

            emptyList()
        }
    }

}