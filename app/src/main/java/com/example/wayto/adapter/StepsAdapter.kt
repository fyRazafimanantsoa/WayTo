package com.example.wayto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wayto.R
import com.example.wayto.model.RoadmapStep

class StepsAdapter(
    private val steps: List<RoadmapStep>,
    private val onStepStatusChanged: (RoadmapStep, Boolean) -> Unit
) : RecyclerView.Adapter<StepsAdapter.StepViewHolder>() {

    class StepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepTitle: TextView = view.findViewById(R.id.stepTitle)
        val stepDescription: TextView = view.findViewById(R.id.stepDescription)
        val stepExplanation: TextView = view.findViewById(R.id.stepExplanation)
        val stepTips: TextView = view.findViewById(R.id.stepTips)
        val stepCompleteCheckbox: CheckBox = view.findViewById(R.id.stepCompleteCheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = steps[position]
        
        holder.stepTitle.text = holder.itemView.context.getString(R.string.step_title, position + 1, step.title)
        holder.stepDescription.text = holder.itemView.context.getString(R.string.step_description, step.description)
        holder.stepExplanation.text = holder.itemView.context.getString(R.string.step_explanation, step.explanation)
        holder.stepTips.text = holder.itemView.context.getString(R.string.step_tips, step.tips)
        holder.stepCompleteCheckbox.isChecked = step.isCompleted
        
        holder.stepCompleteCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onStepStatusChanged(step, isChecked)
        }
    }

    override fun getItemCount(): Int = steps.size
}