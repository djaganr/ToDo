package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityPeopleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class People : AppCompatActivity() {
    private lateinit var binding: ActivityPeopleBinding
    var data = ArrayList<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        getAllProducts()
    }

    private fun getAllProducts() {
        val retrofit = ServiceBuilder.buildService(ServiceInterface::class.java)

        retrofit.getAllUsers().enqueue(object : Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                try {
                    val responseBody = response.body()!!
                    data = responseBody.users
                    var adapter = UserAdapter(data)
                    binding.recyclerview.adapter = adapter


                }
                catch (ex: java.lang.Exception){
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Failed", "Api Failed"+t.message)
            }

        })

    }
}