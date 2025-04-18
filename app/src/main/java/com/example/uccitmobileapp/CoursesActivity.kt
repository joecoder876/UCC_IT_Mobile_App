package com.example.uccitmobileapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        val courseList = listOf(
            Course("ITT107", "Computer Information Essentials", 3, "None",
                "Fundamental concepts of IT including hardware, software, networks, and their applications."),
            Course("ITT116", "Computer Essential & Troubleshooting I", 3, "ITT107",
                "Computer essentials cover the fundamental knowledge and skills for using computers, including understanding hardware, software, operating systems, and basic networking."),
            Course("ITT103", "Programing Techniques", 3, "ITT107",
                "These are the methods and practices developers use to write code efficiently, reliably, and maintainably."),
            Course("ITT102", "Discrete Mathematics", 3, "None",
                "This is a branch of mathematics that studies finite and countable mathematical structures, focusing on topics like logic, set theory, combinatorics, and graph theory."),
            Course("ITT208", "Internet Authoring", 3, "ITT103",
                "This teaches individuals how to create and manage online content, including web pages and websites. This typically involves learning about website structure, design, and coding languages like HTML and CSS."),
            Course("ITT200", "Object Oriented Programming using C++", 3, "ITT103",
                "This provides a practical introduction to Object-Oriented Programming (OOP) using the C++ language."),
            Course("ITT216", "Computer Essentials & Troubleshooting ", 3, "ITT116",
                "This course builds upon fundamental computer knowledge, focusing on advanced troubleshooting and maintenance of computer systems."),
            Course("ITT201", "Data Communication & Networks I", 3, "IT200",
                "Fundamental concepts of data communication and computer networks."),
            Course("ENG109", "Academic Writing I", 3, "None",
                "This course introduces you to critical reading and writing skills within the conventions of academic writing."),
            Course("ITT215", "Technical Writing For Digital Media", 3, "ENG109",
                "This course prepares students to write effective and engaging digital copy across new and emerging communication technologies.")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvCourses)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CourseAdapter(courseList)

        val homeButton = findViewById<Button>(R.id.btnHome)
        homeButton.setOnClickListener {
            finish()
    }
}

class CourseAdapter(private val courseList: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val code = itemView.findViewById<android.widget.TextView>(R.id.tvCourseCode)
        val name = itemView.findViewById<android.widget.TextView>(R.id.tvCourseName)
        val credits = itemView.findViewById<android.widget.TextView>(R.id.tvCourseCredits)
        val prerequisites = itemView.findViewById<android.widget.TextView>(R.id.tvCoursePrerequisites)
        val description = itemView.findViewById<android.widget.TextView>(R.id.tvCourseDescription)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): CourseViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]

        holder.code.text = course.code
        holder.name.text = course.name
        holder.credits.text = "Credits: ${course.credits}"
        holder.prerequisites.text = "Prerequisites: ${course.prerequisites}"
        holder.description.text = course.description
    }

    override fun getItemCount() = courseList.size
}
}