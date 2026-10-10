package com.baklol.dialer

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var web: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.webChromeClient = WebChromeClient()
        web.webViewClient = WebViewClient()
        web.addJavascriptInterface(Bridge(this), "BaklolAndroid")
        setContentView(web)
        web.loadUrl("file:///android_asset/index.html")
    }
    override fun onBackPressed() {
        if (::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }
    private class Bridge(private val activity: Activity) {
        @JavascriptInterface fun requestDefaultDialer() {
            activity.runOnUiThread {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val rm = activity.getSystemService(Context.ROLE_SERVICE) as RoleManager
                    if (rm.isRoleAvailable(RoleManager.ROLE_DIALER) && !rm.isRoleHeld(RoleManager.ROLE_DIALER)) {
                        activity.startActivityForResult(rm.createRequestRoleIntent(RoleManager.ROLE_DIALER), 7001)
                    } else Toast.makeText(activity, "Baklol Dialer is already the default, or role unavailable", Toast.LENGTH_LONG).show()
                } else Toast.makeText(activity, "Set default phone app from Android Settings", Toast.LENGTH_LONG).show()
            }
        }
        @JavascriptInterface fun showMessage(message: String) {
            activity.runOnUiThread { Toast.makeText(activity, message.take(120), Toast.LENGTH_SHORT).show() }
        }
    }
}
