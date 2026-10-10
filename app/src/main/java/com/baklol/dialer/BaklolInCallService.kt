package com.baklol.dialer

import android.content.Intent
import android.telecom.Call
import android.telecom.InCallService

class BaklolInCallService : InCallService() {
    override fun onCreate() { super.onCreate(); CurrentCall.service = this }
    override fun onDestroy() { if (CurrentCall.service == this) CurrentCall.service = null; super.onDestroy() }
    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)
        CurrentCall.call = call
        val intent = Intent(this, CallActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        startActivity(intent)
    }
    override fun onCallRemoved(call: Call) {
        if (CurrentCall.call == call) CurrentCall.call = null
        super.onCallRemoved(call)
    }
}

object CurrentCall { @Volatile var call: Call? = null; @Volatile var service: InCallService? = null }
