package com.example.uccitmobileapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdmissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admissions)

        findViewById<Button>(R.id.btnApplyOnline).setOnClickListener {
            val url = "https://ucc.edu.jm/apply/undergraduate"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnHome).setOnClickListener {
            finish() // Go back to previous screen (MainActivity)
        }
    }
}
