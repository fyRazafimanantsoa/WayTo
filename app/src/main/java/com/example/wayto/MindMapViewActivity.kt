package com.example.wayto

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.wayto.model.Project
import com.example.wayto.model.RoadmapStep
import com.example.wayto.model.StepStatus
import com.example.wayto.utils.ProjectDataManager

class MindMapViewActivity : AppCompatActivity() {
    
    private lateinit var projectDataManager: ProjectDataManager
    private var projectId: String = ""
    private var currentProject: Project? = null
    
    private lateinit var mindMapContainer: FrameLayout
    private lateinit var backToDashboardBtn: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mind_map_view)
        
        projectId = intent.getStringExtra("PROJECT_ID") ?: ""
        
        initViews()
        loadProject()
        setupClickListeners()
    }
    
    private fun initViews() {
        mindMapContainer = findViewById(R.id.mindMapContainer)
        backToDashboardBtn = findViewById(R.id.backToDashboardBtn)
        projectDataManager = ProjectDataManager(this)
    }
    
    private fun setupClickListeners() {
        backToDashboardBtn.setOnClickListener {
            finish()
        }
    }
    
    private fun loadProject() {
        val projects = projectDataManager.loadProjects()
        currentProject = projects.find { it.id == projectId }
        
        currentProject?.let { project ->
            renderMindMap(project)
        } ?: run {
            Toast.makeText(this, "Project not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    
    private fun renderMindMap(project: Project) {
        // Clear previous views
        mindMapContainer.removeAllViews()
        
        // Create the central node for the project
        val centralNode = createMindMapNode(project.name, getStepStatusColor(StepStatus.NOT_STARTED))
        val layoutParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.apply {
            topMargin = 100
            leftMargin = (mindMapContainer.width / 2) - 100 // Center horizontally
        }
        centralNode.layoutParams = layoutParams
        
        mindMapContainer.addView(centralNode)
        
        // Create nodes for each step
        project.steps.forEachIndexed { index, step ->
            val stepNode = createMindMapNode(step.title, getStepStatusColor(step.status))
            val nodeLayoutParams = FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            
            // Position nodes in a radial pattern around the center
            val angle = (index * 360 / project.steps.size).toDouble()
            val radius = 300 // Distance from center
            
            val x = (mindMapContainer.width / 2) + (radius * Math.cos(Math.toRadians(angle))).toInt() - 100
            val y = 200 + (radius * Math.sin(Math.toRadians(angle))).toInt() - 50
            
            nodeLayoutParams.apply {
                topMargin = y
                leftMargin = x
            }
            stepNode.layoutParams = nodeLayoutParams
            
            // Set click listener to show step details
            stepNode.setOnClickListener {
                showStepDetails(step)
            }
            
            mindMapContainer.addView(stepNode)
            
            // Draw connecting line to central node
            drawConnectionLine(
                (mindMapContainer.width / 2).toFloat(),
                150f,
                (x + 100).toFloat(),
                (y + 50).toFloat()
            )
        }
    }
    
    private fun createMindMapNode(title: String, color: Int): LinearLayout {
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundResource(R.drawable.mind_node_background)
            setPadding(16, 16, 16, 16)
            minimumWidth = 200
            minimumHeight = 100
        }
        
        val textView = TextView(this).apply {
            text = title
            setTextColor(Color.WHITE)
            textSize = 14f
            setSingleLine(false)
            setMaxLines(3)
            ellipsize = android.text.TextUtils.TruncateAt.END
        }
        
        container.addView(textView)
        container.setBackgroundColor(color)
        
        return container
    }
    
    private fun drawConnectionLine(startX: Float, startY: Float, endX: Float, endY: Float) {
        val lineView = View(this).apply {
            setBackgroundColor(Color.GRAY)
            rotation = Math.toDegrees(Math.atan2((endY - startY).toDouble(), (endX - startX).toDouble())).toFloat()
            val length = Math.sqrt(((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY)).toDouble()).toFloat()
            layoutParams = FrameLayout.LayoutParams(
                length.toInt(),
                4
            )
            translationX = startX
            translationY = startY
        }
        mindMapContainer.addView(lineView)
    }
    
    private fun getStepStatusColor(status: StepStatus): Int {
        return when (status) {
            StepStatus.NOT_STARTED -> Color.parseColor("#9E9E9E") // Gray
            StepStatus.IN_PROGRESS -> Color.parseColor("#FF9800") // Orange
            StepStatus.COMPLETED -> Color.parseColor("#4CAF50") // Green
            StepStatus.BLOCKED -> Color.parseColor("#F44336") // Red
        }
    }
    
    private fun showStepDetails(step: RoadmapStep) {
        // Show a dialog or bottom sheet with step details
        val details = StringBuilder()
        details.append("Title: ${step.title}\n")
        details.append("Description: ${step.description}\n")
        details.append("Why Important: ${step.explanation}\n")
        details.append("Tips: ${step.tips}\n")
        details.append("Status: ${step.status}")
        
        Toast.makeText(this, details.toString(), Toast.LENGTH_LONG).show()
    }
}