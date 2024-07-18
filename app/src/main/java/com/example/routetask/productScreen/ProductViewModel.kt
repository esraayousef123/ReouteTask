package com.example.routetask.productScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routetask.Model.Api.ProductsItem
import com.example.routetask.ProductRepository.ProductFromApiRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductFromApiRepository
) : ViewModel() {
    val textInSearchBar = mutableStateOf("")


    var productList = mutableStateListOf<ProductsItem?>()
    var isLoading = mutableStateOf(false)

    fun getProduct() {
        viewModelScope.launch {
            isLoading.value = true
            productList.clear()
            productList.addAll(repository.getProduct())
            Log.e("ProductViewModel", "Product list size: ${productList.size}")
            isLoading.value = false
        }
    }

    fun priceAfterDiscount(discountPercentage: Any?, originalPrice: Any?): String {
        val original = (originalPrice as? Number)?.toDouble() ?: return "0.00"
        val discount = (discountPercentage as? Number)?.toDouble() ?: return "0.00"
        if (original <= 0 || discount < 0) {
            return "0.00"
        }
        val finalPrice = original - (original * (discount / 100))
        return String.format("%.2f", finalPrice)
    }
}
