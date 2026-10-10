package com.baklol.dialer

import android.app.Activity
import android.os.Bundle
import android.telecom.Call
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CallActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding(32, 32, 32, 32); setBackgroundColor(android.graphics.Color.rgb(15, 13, 23)) }
        val title = TextView(this).apply { text = "BAKLOL DIALER"; textSize = 18f; setTextColor(android.graphics.Color.rgb(231,189,120)); gravity = Gravity.CENTER }
        val state = TextView(this).apply { text = "Call in progress"; textSize = 27f; setTextColor(-1); gravity = Gravity.CENTER; setPadding(0, 32, 0, 32) }
        root.addView(title); root.addView(state)
        fun add(label: String, action: () -> Unit) { root.addView(Button(this).apply { text = label; setOnClickListener { action() } }) }
        add("Answer") { CurrentCall.call?.answer(0) }
        add("Mute / Unmute") { CurrentCall.service?.setMuted(true) }
        add("End call") { CurrentCall.call?.disconnect(); finish() }
        add("Back to app") { finish() }
        setContentView(root)
        val c = CurrentCall.call
        state.text = when (c?.state) { Call.STATE_RINGING -> "Incoming call"; Call.STATE_DIALING, Call.STATE_CONNECTING -> "Calling…"; Call.STATE_ACTIVE -> "Connected"; else -> "Baklol call screen" }
    }
}
