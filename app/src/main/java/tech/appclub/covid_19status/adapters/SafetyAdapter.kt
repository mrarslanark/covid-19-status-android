package tech.appclub.covid_19status.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.covid_19status.databinding.SafetyItemViewBinding
import tech.appclub.covid_19status.response.SafetyTips

class SafetyAdapter internal constructor(
    private val safetyTipsList: List<SafetyTips>
) : RecyclerView.Adapter<SafetyAdapter.SafetyTipsViewHolder>() {

    inner class SafetyTipsViewHolder internal constructor(
        private val binding: SafetyItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(safetyTips: SafetyTips) {
            binding.tips = safetyTips
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafetyTipsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = SafetyItemViewBinding.inflate(layoutInflater, parent, false)
        return SafetyTipsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return safetyTipsList.size
    }

    override fun onBindViewHolder(holder: SafetyTipsViewHolder, position: Int) {
        val tip = safetyTipsList[position]
        holder.bind(tip)
    }
}