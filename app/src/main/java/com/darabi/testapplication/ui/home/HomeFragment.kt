package com.darabi.testapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testapplication.databinding.FragmentHomeBinding
import com.darabi.testapplication.model.Month
import com.darabi.testapplication.repository.ResponseWrapper
import com.darabi.testapplication.util.fadeIn
import com.darabi.testapplication.util.fadeOut
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val recyclerViewAdapter: MonthRecyclerViewAdapter
) : BaseFragment(), Observer<ResponseWrapper<List<Month>>> {

    private val viewModel: HomeViewModel by viewModels()

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMonths().observe(viewLifecycleOwner, this)
    }

    override fun onChanged(result: ResponseWrapper<List<Month>>) {

        when (result) {
            is ResponseWrapper.Data -> initViews(result.data)
            is ResponseWrapper.Error -> showToast("${result.error.message}")
        }
    }

    private fun initViews(data: List<Month>) {

        binding.rcvMonths.apply {
            adapter = recyclerViewAdapter.apply { setSource(data) }
            fadeIn()
        }

        binding.prgLoading.fadeOut()
    }
}