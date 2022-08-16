package com.monofire.country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monofire.domain.model.CountryUiData
import com.monofire.country.databinding.ListItemCountryBinding

class CountryViewHolder(private val binding: ListItemCountryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createHolder(parent: ViewGroup): CountryViewHolder {
            val binding =
                ListItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CountryViewHolder(binding)
        }
    }

    fun bind(country: CountryUiData, onItemUserClickListener: ((CountryUiData) -> Unit)?) {
        binding.country = country
        binding.root.setOnClickListener {
            onItemUserClickListener?.invoke(country)
        }
    }
}