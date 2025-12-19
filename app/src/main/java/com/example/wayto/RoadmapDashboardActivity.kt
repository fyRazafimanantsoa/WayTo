package com.example.wayto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wayto.adapter.StepsAdapter
import com.example.wayto.model.Project
import com.example.wayto.model.RoadmapStep
import com.example.wayto.utils.ProjectDataManager

class RoadmapDashboardActivity : AppCompatActivity() {
    
    private lateinit var dashboardTitle: TextView
    private lateinit var projectNameLabel: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var stepsRecyclerView: RecyclerView
    private lateinit var backToMainBtn: Button
    private lateinit var projectDataManager: ProjectDataManager
    private lateinit var stepsAdapter: StepsAdapter
    private var projectId: String = ""
    private var currentProject: Project? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roadmap_dashboard)
        
        projectId = intent.getStringExtra("PROJECT_ID") ?: ""
        
        initViews()
        setupRecyclerView()
        setupClickListeners()
        loadProject()
    }
    
    private fun initViews() {
        dashboardTitle = findViewById(R.id.dashboardTitle)
        projectNameLabel = findViewById(R.id.projectNameLabel)
        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)
        stepsRecyclerView = findViewById(R.id.stepsRecyclerView)
        backToMainBtn = findViewById(R.id.backToMainBtn)
        projectDataManager = ProjectDataManager(this)
    }
    
    private fun setupRecyclerView() {
        stepsRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    
    private fun setupClickListeners() {
        backToMainBtn.setOnClickListener {
            finish()
        }
    }
    
    private fun loadProject() {
        val projects = projectDataManager.loadProjects()
        currentProject = projects.find { it.id == projectId }
        
        currentProject?.let { project ->
            projectNameLabel.text = project.name
            
            val progress = project.getProgressPercentage()
            progressBar.progress = progress
            progressText.text = "$progress% Complete"
            
            stepsAdapter = StepsAdapter(project.steps) { step, isCompleted ->
                // Update step completion status
                step.isCompleted = isCompleted
                currentProject?.let { proj ->
                    // Update the project in data manager
                    projectDataManager.updateProject(proj)
                    
                    // Refresh the UI
                    val newProgress = proj.getProgressPercentage()
                    progressBar.progress = newProgress
                    progressText.text = "$newProgress% Complete"
                }
            }
            
            stepsRecyclerView.adapter = stepsAdapter
        }
    }
}