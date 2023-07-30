package com.minhoi.coroutineflowpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import com.minhoi.coroutineflowpractice.retrofit.Api
import com.minhoi.coroutineflowpractice.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {
    private val retrofitInstance = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getRecipeName(name : String) : ArrayList<String> {

        return suspendCoroutine {
            val call = retrofitInstance.searchRecipe(1,1000, name)
            call.enqueue(object : Callback<RcpResponse> {
                override fun onResponse(call: Call<RcpResponse>, response: Response<RcpResponse>) {

                    Log.d("List", response.body()?.COOKRCP01?.row.toString())
                    val recipeList = response.body()?.COOKRCP01?.row
                    val nameList = arrayListOf<String>()
                    if (recipeList != null) {
                        for(i in recipeList) {
                            nameList.add(i.rcp_NM)
                        }
                    }
                    it.resume(nameList)
                }

                override fun onFailure(call: Call<RcpResponse>, t: Throwable) {
                    t.cause?.let { error -> it.resumeWithException(error) }
                }
            })
        }
    }
}