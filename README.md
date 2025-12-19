# WayTo Android App

## Project Overview
WayTo is a powerful, offline-first productivity app designed to help users plan, track, and visualize projects, learning journeys, or skill development. With intuitive tools for structuring goals and actionable steps, WayTo empowers users to turn vague aspirations into clear, manageable pathways. Optional AI-assisted step suggestions enhance planning when connected to the internet—while all core functionality remains fully available without an internet connection.

---

## Features
- **Project Creation:** Create custom projects with names, descriptions, and tags.  
- **Blurry Goal Handling:** Automatically prompts clarifying questions when goals are vague (e.g., “I want to learn coding”), guiding users toward specific, actionable objectives.  
- **Automatic Roadmap & Mind Map Generation:** Instantly generates hierarchical milestones and steps with visual layouts using icons and interactive diagrams.  
- **Progress Tracking:** Color-coded status indicators:  
  - 🟨 Not Started  
  - 🟦 In Progress  
  - ✅ Completed  
  - ⚠️ Blocked  
- **Notes Support:** Add text, voice recordings, and mind map notes directly to any step or milestone.  
- **Reminders / Notifications:** Local push notifications work entirely offline; no cloud dependency required.  
- **Export / Import:** Share or back up your projects in multiple formats: JSON, PDF, and image (PNG/SVG).  
- **Predefined Templates:** Choose from common project types (e.g., Learning Path, Fitness Plan, Career Goals) to auto-populate milestones and steps.  
- **Suggest Next Steps:** Get intelligent, rule-based guidance on what to do next—powered by local logic and templates.  
- **Optional AI Suggestions:** On-demand step generation via AI (requires internet), with results cached locally for future use.  
- **UI/UX Excellence:**  
  - Dark and light mode toggle  
  - Smooth animations and transitions  
  - Drag-and-drop reordering  
  - Zoomable views for detailed exploration  
  - Intuitive, hierarchical navigation  

---

## Installation

### Requirements
- Android Studio (or compatible IDE with Kotlin/Gradle support)  
- Android SDK (API 24+ recommended)  
- JDK 17+  

### Build Instructions
```bash
# Clone the repository
git clone <repository-url>
# Open the project in Android Studio
# Sync the project with Gradle files
# Connect an Android device or start an emulator
# Build APK
Build > Build Bundle(s)/APK(s) > Build APK(s)
# Wait for completion; APK will be located at the output folder
