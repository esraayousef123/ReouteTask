package com.example.routetask.productScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.API.RetrofitObject
import com.example.routetask.Model.Api.ProductsItem
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    val textInSearchBar =mutableStateOf("")
    var productList = mutableStateListOf<ProductsItem?>()
    var isLoading = mutableStateOf(false)


    fun getProduct(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val productResponse =
                    RetrofitObject.apiInterfaceImplementationByRetrofit().getProducts()
                if (productResponse.products?.isNotEmpty() == true)
                {
                    productList.clear()
                    productList.addAll(productResponse.products)
                    Log.e("ProductViewModel", "Product list size: ${productList.size}")
                    isLoading.value = false


                }

            }
            catch (e: Exception){
                e.printStackTrace()
            }

        }
    }
    fun priceAfterDiscount(discountPercentage : Any? , originalPrice : Any? ) : String{
        val original = (originalPrice as? Number)?.toDouble() ?: return "0.00"
        val discount = (discountPercentage as? Number)?.toDouble() ?: return "0.00"
        if (original <= 0 || discount < 0) {
            return "0.00"
        }
        val finalPrice = original - (original * (discount / 100))
        return String.format("%.2f", finalPrice)



    }



}