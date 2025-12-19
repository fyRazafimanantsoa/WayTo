package com.example.wayto.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.wayto.model.Project
import com.example.wayto.model.RoadmapStep
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProjectDataManager(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("wayto_projects", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val PROJECTS_KEY = "projects_list"
    }

    fun saveProjects(projects: List<Project>) {
        val json = gson.toJson(projects)
        sharedPreferences.edit().putString(PROJECTS_KEY, json).apply()
    }

    fun loadProjects(): List<Project> {
        val json = sharedPreferences.getString(PROJECTS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Project>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun addProject(project: Project): List<Project> {
        val projects = loadProjects().toMutableList()
        projects.add(project)
        saveProjects(projects)
        return projects
    }

    fun updateProject(updatedProject: Project): List<Project> {
        val projects = loadProjects().toMutableList()
        val index = projects.indexOfFirst { it.id == updatedProject.id }
        if (index != -1) {
            projects[index] = updatedProject
            saveProjects(projects)
        }
        return projects
    }

    fun deleteProject(projectId: String): List<Project> {
        val projects = loadProjects().toMutableList()
        projects.removeAll { it.id == projectId }
        saveProjects(projects)
        return projects
    }
}