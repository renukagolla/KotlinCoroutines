package com.anushka.coroutinesdemo1

import kotlinx.coroutines.*

//Structured concurrency example
class UserDataManager2 {
    var count = 0
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserCount(): Int {
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }
            deferred = async(Dispatchers.IO) {
                return@async 70
            }
        }
        return count + deferred.await()
    }
}