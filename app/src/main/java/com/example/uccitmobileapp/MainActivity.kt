package com.example.uccitmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up button click listeners
        findViewById<Button>(R.id.btnDirectory).setOnClickListener {
            startActivity(Intent(this, DirectoryActivity::class.java))
        }

        findViewById<Button>(R.id.btnCourses).setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        findViewById<Button>(R.id.btnAdmissions).setOnClickListener {
            startActivity(Intent(this, AdmissionsActivity::class.java))
        }

        findViewById<Button>(R.id.btnSocial).setOnClickListener {
            startActivity(Intent(this, SocialMediaActivity::class.java))
        }

        // Set up FAB for email
        findViewById<FloatingActionButton>(R.id.fabEmail).setOnClickListener {
            sendEmailToHOD()
        }
    }

    private fun sendEmailToHOD() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("ithod@ucc.edu.jm"))
            putExtra(Intent.EXTRA_SUBJECT, "Inquiry from UCC Mobile App")
            putExtra(Intent.EXTRA_TEXT, "Dear Head of Department,\n\n")
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            // Handle case where no email client is installed
        }
    }
}