package com.example.routetask.ProductRepository

import com.example.routetask.Model.Api.ProductsItem

interface ProductFromApiRepository {
    suspend fun getProduct(): List<ProductsItem?>


}