package com.example.gsonlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Executors.newSingleThreadExecutor().execute {
            val url = URL("https://api.themoviedb.org/3/movie/550?api_key=${API.key}")
            val connection = url.openConnection() as HttpURLConnection
            val gson = Gson()
            try {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val string = reader.readLine()
                val movie = gson.fromJson(string, Movie::class.java)
                println("!!! ${movie.release_date}")
            } finally {
                connection.disconnect()
            }
        }

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users/2")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseBody = response.body()
                    println("!!! ${responseBody?.string()}")
                } catch (e: Exception) {
                    println(response)
                    e.printStackTrace()
                }
            }

        })
    }
}
