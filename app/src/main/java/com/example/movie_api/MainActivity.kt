package com.example.movie_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_api.Adapter.MovieAdapter
import com.example.movie_api.Client.ApiClient
import com.example.movie_api.Interface.ApiInterface
import com.example.movie_api.Model.MovieModel
import com.example.movie_api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val  TAG = "MainActivity"
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        apiInterface.getData("504c15eb9fd2acefc91b639894d7c295")
            .enqueue(object : Callback<MovieModel>{
                override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                    var data = response.body()
                    binding.rcvMovie.layoutManager = GridLayoutManager(this@MainActivity,2)
                    binding.rcvMovie.adapter = MovieAdapter(data!!.results)
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    Log.e(TAG,"onFailure: === "+ t.message)
                }

            })
    }
}