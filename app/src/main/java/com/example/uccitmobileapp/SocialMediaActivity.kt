package com.example.uccitmobileapp

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SocialMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)

        val facebookButton = findViewById<Button>(R.id.btnFacebook)
        val twitterButton = findViewById<Button>(R.id.btnTwitter)
        val instagramButton = findViewById<Button>(R.id.btnInstagram)
        val homeButton = findViewById<Button>(R.id.btnHome)

        facebookButton.setOnClickListener {
            openSocialMedia("https://m.facebook.com/profile.php?id=uccjamaica")
        }

        twitterButton.setOnClickListener {
            openSocialMedia("https://twitter.com/UCCjamaica")
        }

        instagramButton.setOnClickListener {
            openSocialMedia("https://www.instagram.com/uccjamaica/")
        }

        homeButton.setOnClickListener {
            finish() // Go back to previous screen (MainActivity)
        }
    }

    private fun openSocialMedia(url: String) {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        // Set user-agent to mimic a real browser
        webView.settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.74 Mobile Safari/537.36"

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView, request: WebResourceRequest, error: WebResourceError
            ) {
                Toast.makeText(this@SocialMediaActivity, "Error loading page", Toast.LENGTH_SHORT).show()
            }
        }

        webView.loadUrl(url)
    }
}
