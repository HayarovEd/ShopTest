package com.edurda77.data.remote.dto


import com.google.gson.annotations.SerializedName

data class WordsDto(
    @SerializedName("words")
    val words: List<String>
)