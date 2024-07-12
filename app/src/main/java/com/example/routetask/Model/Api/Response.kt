package com.example.routetask.Model.Api

data class Response(
	val metadata: Metadata? = null,
	val data: List<DataItem?>? = null,
	val results: Int? = null
)
