package com.example.wayto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wayto.adapter.ProjectsAdapter
import com.example.wayto.model.Project
import com.example.wayto.utils.ProjectDataManager
import java.util.*

class MainActivity : AppCompatActivity() {
    
    private lateinit var projectsRecyclerView: RecyclerView
    private lateinit var createNewProjectBtn: Button
    private lateinit var projectDataManager: ProjectDataManager
    private lateinit var projectsAdapter: ProjectsAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupRecyclerView()
        setupClickListeners()
        loadProjects()
    }
    
    private fun initViews() {
        projectsRecyclerView = findViewById(R.id.projectsRecyclerView)
        createNewProjectBtn = findViewById(R.id.createNewProjectBtn)
        projectDataManager = ProjectDataManager(this)
    }
    
    private fun setupRecyclerView() {
        projectsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    
    private fun setupClickListeners() {
        createNewProjectBtn.setOnClickListener {
            val intent = Intent(this, CreateProjectActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun loadProjects() {
        val projects = projectDataManager.loadProjects()
        projectsAdapter = ProjectsAdapter(projects) { project ->
            val intent = Intent(this, RoadmapDashboardActivity::class.java).apply {
                putExtra("PROJECT_ID", project.id)
            }
            startActivity(intent)
        }
        projectsRecyclerView.adapter = projectsAdapter
    }
    
    override fun onResume() {
        super.onResume()
        // Reload projects when returning to main activity to reflect any changes
        loadProjects()
    }
}