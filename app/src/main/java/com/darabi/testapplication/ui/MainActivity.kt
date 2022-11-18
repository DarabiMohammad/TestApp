package com.darabi.testapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.darabi.testapplication.R
import com.darabi.testapplication.databinding.ActivityMainBinding
import com.darabi.testapplication.ui.base.BaseActivity
import com.darabi.testapplication.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var homeFragment: HomeFragment

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateTo(binding.containerMain.id, homeFragment, shouldReplace = true)
    }

    override fun observeViewModel() {

        mainViewModel.onFragmentBackPressed.observe(this) {

            if (it == firstAddedFragmentTag)
                finish()
            else
                supportFragmentManager.popBackStack(it, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}