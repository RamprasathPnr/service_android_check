package com.ram.service_android_check

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        fetchDataFromApi()
        return START_STICKY
    }

    // Calling API
    private fun fetchDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.fetchData()
            if (response.isSuccessful) {
                response.body()?.let {
                    val message = it.support?.text
                    val email = it.data?.email

                    /*
                     * Sending data to update our UI from Service
                     */
                    val dataIntent = Intent("com.ram.app.DATA_RECEIVED")
                    dataIntent.putExtra(Constants.EMAIL, email)
                    dataIntent.putExtra(Constants.DATA, message)
                    sendBroadcast(dataIntent)
                }
            } else {
                val dataIntent = Intent("com.ram.app.DATA_RECEIVED")
                dataIntent.putExtra(Constants.EMAIL, "unable to fetch email")
                dataIntent.putExtra(Constants.DATA, "Unable to fetch Data")
                sendBroadcast(dataIntent)
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
