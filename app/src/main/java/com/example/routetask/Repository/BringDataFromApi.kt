package com.example.routetask.Repository

import com.example.routetask.Model.Api.ProductsItem

interface BringDataFromApi {
    suspend fun bringProductFromApi():List<ProductsItem?>
}