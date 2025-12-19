package com.example.wayto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wayto.R
import com.example.wayto.model.Project

class ProjectsAdapter(
    private val projects: List<Project>,
    private val onItemClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val projectName: TextView = view.findViewById(R.id.projectName)
        val projectDescription: TextView = view.findViewById(R.id.projectDescription)
        val progressText: TextView = view.findViewById(R.id.progressText)
        val miniProgressBar: ProgressBar = view.findViewById(R.id.miniProgressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        
        holder.projectName.text = project.name
        holder.projectDescription.text = project.description
        
        val progress = project.getProgressPercentage()
        holder.progressText.text = "$progress% Complete"
        holder.miniProgressBar.progress = progress
        
        holder.itemView.setOnClickListener {
            onItemClick(project)
        }
    }

    override fun getItemCount(): Int = projects.size
}