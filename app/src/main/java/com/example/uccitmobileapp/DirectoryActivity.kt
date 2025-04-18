package com.example.uccitmobileapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DirectoryActivity : AppCompatActivity() {
    private val CALL_PERMISSION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directory)

        val staffList = listOf(
            Staff("Mr. Otis Osbourne", "Head of Department", "876906300", "itfaculty@ucc.edu.jm", R.drawable.profile1),
            Staff("Mr. Stefan Watson", "Lecturer", "8764123457", "swatson@ucc.edu.jm", R.drawable.profile2),
            Staff("Mr. Cecil White", "Lecturer", "8764123458", "Cwhite@ucc.edu.jm", R.drawable.profile3),
            Staff("Mr. Matthew Kendley", "Lecturer", "8764123459", "mkendley@ucc.edu.jm", R.drawable.profile4),
            Staff("Mr. Craig wilmot", "Course coordinator", "8769063000", "itprogofficer@ucc.edu.jm", R.drawable.profile5)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvDirectory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StaffAdapter(staffList) { staff, action ->
            when (action) {
                "call" -> makePhoneCall(staff.phone)
                "email" -> sendEmail(staff.email)
            }
        }
        findViewById<Button>(R.id.btnHome).setOnClickListener {
            finish() // Go back to previous screen (MainActivity)
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), CALL_PERMISSION_REQUEST_CODE)
        } else {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        startActivity(Intent.createChooser(intent, "Send email using..."))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted. Please try the call again.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied. Cannot make calls.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

data class Staff(
    val name: String,
    val position: String,
    val phone: String,
    val email: String,
    val photoResId: Int
)

class StaffAdapter(
    private val staffList: List<Staff>,
    private val onItemClick: (Staff, String) -> Unit
) : RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    class StaffViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.findViewById<android.widget.ImageView>(R.id.ivStaffPhoto)
        val name = itemView.findViewById<android.widget.TextView>(R.id.tvStaffName)
        val position = itemView.findViewById<android.widget.TextView>(R.id.tvStaffPosition)
        val callButton = itemView.findViewById<android.widget.ImageButton>(R.id.ibCall)
        val emailButton = itemView.findViewById<android.widget.ImageButton>(R.id.ibEmail)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): StaffViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staff, parent, false)
        return StaffViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val staff = staffList[position]

        holder.photo.setImageResource(staff.photoResId)
        holder.name.text = staff.name
        holder.position.text = staff.position

        holder.callButton.setOnClickListener {
            onItemClick(staff, "call")
        }

        holder.emailButton.setOnClickListener {
            onItemClick(staff, "email")
        }
    }

    override fun getItemCount() = staffList.size
}