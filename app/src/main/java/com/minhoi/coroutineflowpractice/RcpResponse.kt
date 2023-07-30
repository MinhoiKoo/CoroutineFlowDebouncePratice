package com.minhoi.coroutineflowpractice
import com.google.gson.annotations.SerializedName

data class RcpResponse(
    @SerializedName("COOKRCP01")
    var COOKRCP01 : RecipeList?
)