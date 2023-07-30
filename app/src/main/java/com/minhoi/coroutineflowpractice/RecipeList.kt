package com.minhoi.coroutineflowpractice

import com.minhoi.coroutineflowpractice.RecipeDto

data class RecipeList(
    var row : List<RecipeDto> = arrayListOf()
)
