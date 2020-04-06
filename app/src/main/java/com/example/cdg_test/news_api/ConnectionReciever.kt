package com.example.cdg_test.news_api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class ConnectionReciever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (isNetworkAvailable(context)) Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
        else Toast.makeText(context, "Network problems. Please check your internet connection!", Toast.LENGTH_LONG).show()
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork != null
    }
}