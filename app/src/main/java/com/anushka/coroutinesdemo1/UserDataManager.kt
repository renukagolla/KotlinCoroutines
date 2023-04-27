package com.anushka.coroutinesdemo1

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class UserDataManager {
    suspend fun getTotalUserCount(): Int {
        var count = 0
        CoroutineScope(IO).launch {
            delay(1000)
            Log.d("Count",count.toString())
            count = 50
        }
        //Unstructured Concurrency Example
        val deferred = CoroutineScope(IO).async {
            delay(1000)
           return@async 70
        }

        return count + deferred.await()
    }
}