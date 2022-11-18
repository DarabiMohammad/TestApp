package com.darabi.testapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.darabi.testapplication.databinding.RcvItemMonthBinding
import com.darabi.testapplication.model.Month

class MonthViewHolder constructor(
    private val binding: RcvItemMonthBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModel(month: Month) {
        binding.txtMonthPosition.text = month.position.toString()
        binding.txtMonthName.text = month.name
    }
}