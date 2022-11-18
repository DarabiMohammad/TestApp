package com.darabi.testapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testapplication.databinding.FragmentHomeBinding
import com.darabi.testapplication.model.Month
import com.darabi.testapplication.repository.ResponseWrapper
import com.darabi.testapplication.ui.home.worker.DelayedWorker
import com.darabi.testapplication.util.fadeIn
import com.darabi.testapplication.util.fadeOut
import com.darabi.testapplication.util.invisible
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val recyclerViewAdapter: MonthRecyclerViewAdapter
) : BaseFragment(), Observer<WorkInfo> {

    private val viewModel: HomeViewModel by viewModels()

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMonths(5).observe(viewLifecycleOwner, this)
    }

    override fun onChanged(workInfo: WorkInfo) {

        when (workInfo.state) {

            WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING -> {
                binding.prgLoading.fadeIn()
                binding.rcvMonths.invisible()
            }

            WorkInfo.State.SUCCEEDED -> {
                initViews(viewModel.fromJson(workInfo.outputData.getString(DelayedWorker.LIST_OF_MONTH)))
            }

            else -> {
                binding.prgLoading.fadeOut()
                binding.rcvMonths.invisible()
            }
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