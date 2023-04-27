package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var btnDownloadUserData : Button
    private lateinit var btnCount : Button
    private lateinit var tvCount : TextView
    private lateinit var tvUserMessage : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        btnCount = findViewById(R.id.btnCount)
        tvCount = findViewById(R.id.tvCount)
        tvUserMessage = findViewById(R.id.tvUserMessage)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        // Using Async and await
        CoroutineScope(Main).launch {
        Log.d("Cal", "Started the calculation")
            val stock1 = async(IO) {getStock1() }
            val stock2 = async(IO) { getStock2() }
            val stock3 = stock1.await() + stock2.await()
            Log.d("total",stock3.toString())
            Toast.makeText(applicationContext,"Total is ${stock3}.toString()",Toast.LENGTH_LONG).show()
        }
        btnDownloadUserData.setOnClickListener {
           CoroutineScope(Main).launch {
           // downloadUserData()
//               tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
               tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()

           }

        }
    }

    private suspend fun downloadUserData() {
        withContext(Dispatchers.Main){
        for (i in 1..200000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"

            }

        }
    }
    suspend fun getStock1(): Int {
        delay(10000)
        Log.d("MYTAG","Stoc1 returned")
        return 5000
    }
    suspend fun getStock2(): Int {
        delay(10000)
        Log.d("MYTAG","Stoc2 returned")
        return 7000
    }
}