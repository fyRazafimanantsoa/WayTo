package com.example.wayto.model

data class Project(
    val id: String = "",
    var name: String = "",
    var description: String = "",
    var steps: MutableList<RoadmapStep> = mutableListOf(),
    var createdAt: Long = System.currentTimeMillis()
) {
    fun getProgressPercentage(): Int {
        if (steps.isEmpty()) return 0
        val completedSteps = steps.count { it.isCompleted }
        return (completedSteps.toFloat() / steps.size * 100).toInt()
    }
}