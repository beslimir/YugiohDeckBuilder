package com.example.yugiohdeckbuilder.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_rows")
    val currentRows: Int,
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("next_page_offset")
    val nextPageOffset: Int,
    @SerializedName("pages_remaining")
    val pagesRemaining: Int,
    @SerializedName("rows_remaining")
    val rowsRemaining: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_rows")
    val totalRows: Int
)