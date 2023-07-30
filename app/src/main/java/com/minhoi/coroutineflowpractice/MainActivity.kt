package com.minhoi.coroutineflowpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minhoi.coroutineflowpractice.utils.textChangesToFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private var searchJob: Job? = null
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : RecipeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = RecipeListAdapter()

        val input = findViewById<EditText>(R.id.inputRecipeName)
        val rv = findViewById<RecyclerView>(R.id.recipeRv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)


        input.textChangesToFlow()
            .debounce(300L)
            .onEach { query ->
                // 새로운 입력이 있을 때마다 이전 검색 Job을 취소하고 새로운 Job 시작
                searchJob?.cancel()
                searchJob = lifecycleScope.launch(Dispatchers.IO) {
                    val list = viewModel.getRecipeName(query.toString())
                    withContext(Dispatchers.Main){
                        Log.d(TAG, list.toString())
                        adapter.setList(list)
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

}