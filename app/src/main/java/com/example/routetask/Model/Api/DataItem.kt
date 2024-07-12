package com.example.routetask.Model.Api

data class DataItem(
	val sold: Int? = null,
	val images: List<String?>? = null,
	val quantity: Int? = null,
	val availableColors: List<Any?>? = null,
	val imageCover: String? = null,
	val description: String? = null,
	val title: String? = null,
	val ratingsQuantity: Int? = null,
	val ratingsAverage: Any? = null,
	val createdAt: String? = null,
	val price: Int? = null,
	val id: String? = null,
	val subcategory: List<SubcategoryItem?>? = null,
	val category: Category? = null,
	val brand: Brand? = null,
	val slug: String? = null,
	val updatedAt: String? = null,
	val priceAfterDiscount: Int? = null
)
