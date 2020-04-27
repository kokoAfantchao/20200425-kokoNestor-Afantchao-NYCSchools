package com.push.nycschools.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.annotation.NonNull
import android.os.Looper
import androidx.annotation.VisibleForTesting



fun Context.isAppOnline(): Boolean {

    val  connectionManager  = this.getSystemService(Context.CONNECTIVITY_SERVICE  ) as ConnectivityManager
    val  activeNetwork : NetworkInfo? = connectionManager.activeNetworkInfo
    return activeNetwork?.isAvailable ?: false

}

