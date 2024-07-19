package com.ram.service_android_check

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView;
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    private lateinit var textEmail: TextView
    private lateinit var textMessage: TextView


    /**
     * Update UI
     */

    private val dataReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val data: String? = intent?.getStringExtra(Constants.DATA)
            val email: String? = intent?.getStringExtra(Constants.EMAIL)
            textEmail.text = email
            textMessage.text = data

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textEmail = findViewById(R.id.txt_email)
        textMessage = findViewById(R.id.txt_message)


    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        /**
         * Registering BroadcastReceiver and start Service
         */

        registerReceiver(dataReceiver, IntentFilter("com.ram.app.DATA_RECEIVED"),
            RECEIVER_EXPORTED)

        val serviceIntent = Intent(this, DataService::class.java)
        startService(serviceIntent)
    }



    override fun onStop() {
        super.onStop()

        /**
         * Unregistering BroadcastReceiver
         */
        unregisterReceiver(dataReceiver)
    }
}