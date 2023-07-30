package com.minhoi.coroutineflowpractice.retrofit

import com.minhoi.coroutineflowpractice.RcpResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("COOKRCP01/json/{startidx}/{endidx}")
    fun getRecipe(
        @Path("startidx") startidx: Int,
        @Path("endidx") endidx: Int
    ): Call<RcpResponse>

    @GET("COOKRCP01/json/{startidx}/{endidx}/RCP_NM={rcpName}")
    fun searchRecipe(
        @Path("startidx") startidx: Int,
        @Path("endidx") endidx: Int,
        @Path("rcpName") rcpName : String,
    ) : Call<RcpResponse>

}