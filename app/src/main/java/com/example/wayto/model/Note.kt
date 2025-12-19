package com.example.wayto.model

import java.util.*

data class Note(
    val id: String = UUID.randomUUID().toString(),
    var projectId: String = "",
    var stepId: String = "", // Empty if note is for project
    var title: String = "",
    var content: String = "",
    var type: NoteType = NoteType.TEXT,
    var filePath: String = "", // Path to voice note or mind map file
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis()
)

enum class NoteType {
    TEXT,
    VOICE,
    MIND_MAP
}