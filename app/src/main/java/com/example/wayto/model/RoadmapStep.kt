package com.example.wayto.model

data class RoadmapStep(
    val id: String = "",
    var title: String = "",
    var description: String = "",
    var explanation: String = "", // Why this step is important
    var tips: String = "", // Tips on how to perform this step
    var isCompleted: Boolean = false,
    var order: Int = 0,
    var status: StepStatus = StepStatus.NOT_STARTED, // Added status field
    var notes: String = "", // Personal notes for the step
    var voiceNotePath: String = "", // Path to voice note file
    var mindMapNotePath: String = "" // Path to mind map image
)

enum class StepStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    BLOCKED
}