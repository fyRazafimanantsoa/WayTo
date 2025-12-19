package com.example.wayto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wayto.model.Project
import com.example.wayto.model.RoadmapStep
import com.example.wayto.utils.ProjectDataManager
import java.util.*

class CreateProjectActivity : AppCompatActivity() {
    
    private lateinit var projectNameInput: EditText
    private lateinit var projectDescriptionInput: EditText
    private lateinit var generateRoadmapBtn: Button
    private lateinit var projectDataManager: ProjectDataManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)
        
        initViews()
        setupClickListeners()
    }
    
    private fun initViews() {
        projectNameInput = findViewById(R.id.projectNameInput)
        projectDescriptionInput = findViewById(R.id.projectDescriptionInput)
        generateRoadmapBtn = findViewById(R.id.generateRoadmapBtn)
        projectDataManager = ProjectDataManager(this)
    }
    
    private fun setupClickListeners() {
        generateRoadmapBtn.setOnClickListener {
            createProject()
        }
    }
    
    private fun createProject() {
        val name = projectNameInput.text.toString().trim()
        val description = projectDescriptionInput.text.toString().trim()
        
        if (name.isEmpty()) {
            projectNameInput.error = "Please enter a project name"
            return
        }
        
        if (description.isEmpty()) {
            projectDescriptionInput.error = "Please describe your project goals"
            return
        }
        
        // Generate a unique ID for the project
        val projectId = UUID.randomUUID().toString()
        
        // Create the project with generated steps
        val project = Project(
            id = projectId,
            name = name,
            description = description,
            steps = generateRoadmapSteps(description)
        )
        
        // Save the project
        projectDataManager.addProject(project)
        
        // Navigate to the dashboard
        val intent = Intent(this, RoadmapDashboardActivity::class.java).apply {
            putExtra("PROJECT_ID", projectId)
        }
        startActivity(intent)
        finish()
    }
    
    private fun generateRoadmapSteps(description: String): MutableList<RoadmapStep> {
        // This is a simplified version - in a real app, this could use AI or complex logic
        // For now, we'll generate some generic steps based on the project description
        
        val steps = mutableListOf<RoadmapStep>()
        
        // Sample steps - in a real implementation, these would be generated based on the project description
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Define Project Scope",
            description = "Clearly outline what the project aims to achieve, deliverables, and boundaries.",
            explanation = "Having a well-defined scope prevents scope creep and keeps the project focused.",
            tips = "Write down specific goals, timeline, and constraints.",
            order = 0
        ))
        
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Research & Planning",
            description = "Conduct research, gather requirements, and create a detailed plan.",
            explanation = "Proper planning reduces risks and increases the chances of success.",
            tips = "Create a timeline, assign responsibilities, and identify potential obstacles.",
            order = 1
        ))
        
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Resource Allocation",
            description = "Identify and allocate necessary resources (time, money, personnel).",
            explanation = "Adequate resources ensure the project can be completed successfully.",
            tips = "Create a budget and assign team members to specific tasks.",
            order = 2
        ))
        
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Execute Plan",
            description = "Begin implementation according to the planned schedule.",
            explanation = "Execution is where the actual work happens and deliverables are produced.",
            tips = "Monitor progress regularly and adjust plans as needed.",
            order = 3
        ))
        
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Review & Iterate",
            description = "Review progress, gather feedback, and make necessary adjustments.",
            explanation = "Continuous improvement ensures quality and alignment with goals.",
            tips = "Schedule regular review meetings and document lessons learned.",
            order = 4
        ))
        
        steps.add(RoadmapStep(
            id = UUID.randomUUID().toString(),
            title = "Finalize & Deliver",
            description = "Complete remaining tasks and deliver the final product.",
            explanation = "Proper closure ensures all objectives are met and stakeholders are satisfied.",
            tips = "Conduct a final review, document the process, and celebrate achievements.",
            order = 5
        ))
        
        return steps
    }
}