package com.example.gsonlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bag = BagOfPrimitives()
        val gson = Gson()
        val input = gson.toJson(bag)
        println("!!! $input")
        val output = gson.fromJson(input, BagOfPrimitives::class.java)
        println("!!! ${output.value1}")

        val data = Data("Moon", "moon@moon.org", "Moonson", 10, "Moony")
        val gson2 = Gson()
        val input2 = gson2.toJson(data)
        println("!!! $input2")
        val output2 = gson2.toJson(input2)
        println("!!! $output2")
    }
}

internal class BagOfPrimitives {
    val value1 = 1
    private val value2 = "abc"

    @Transient
    private val value3 = 3
}

data class Data(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
)