package com.monofire.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monofire.domain.model.HistoryUiData
import com.monofire.history.databinding.ListItemHistoryBinding

class HistoryViewHolder(private val binding: ListItemHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createHolder(parent: ViewGroup): HistoryViewHolder {
            val binding =
                ListItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HistoryViewHolder(binding)
        }
    }

    fun bind(country: HistoryUiData, onItemUserClickListener: ((HistoryUiData) -> Unit)?) {
        binding.country = country
        binding.root.setOnClickListener {
            onItemUserClickListener?.invoke(country)
        }
    }
}