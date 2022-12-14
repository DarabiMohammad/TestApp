package com.darabi.testapplication.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testapplication.di.module.InjectingFragmentFactoryEntryPoint
import com.darabi.testapplication.ui.MainViewModel
import dagger.hilt.android.EntryPointAccessors

/**
 * Base class for extending android activity. adds some functionalities for implementing
 * single activity approach using [BaseFragment].
 *
 * @see BaseFragment
 * @see MainViewModel
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val binding: ViewBinding

    /**
     * acts as shared view model between this activity and all hosted in base fragments hosted.
     */
    protected val mainViewModel: MainViewModel by viewModels()

    private val backPressedCallback by lazy {

        object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {

                /**
                 * Lets back button handling be done in appropriate [BaseFragment].
                 * result of handing is sent back to [MainViewModel.onFragmentBackPressed].
                 */
                getBaseFragmentOrNull()?.handleBackButton()
            }
        }
    }

    protected var firstAddedFragmentTag: String? = null
        private set

    /**
     * all [MainViewModel] observables should be observed in this method.
     */
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = EntryPointAccessors.fromActivity(
            this, InjectingFragmentFactoryEntryPoint::class.java
        ).getFragmentFactory()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()

        registerOnBackPressedCallback()
    }

    protected fun navigateTo(
        containerId: Int,
        fragment: BaseFragment,
        addToBackStack: Boolean = false,
        shouldReplace: Boolean = false
    ) {
        if (fragment.isAdded)
            return
        else supportFragmentManager.beginTransaction().also {
            if (shouldReplace) {
                firstAddedFragmentTag = fragment.fragmentTag
                if (addToBackStack)
                    it.addToBackStack(fragment.fragmentTag)
                        .replace(containerId, fragment, fragment.fragmentTag)
                else
                    it.replace(containerId, fragment, fragment.fragmentTag)
            } else {
                if (addToBackStack)
                    it.addToBackStack(fragment.fragmentTag)
                        .add(containerId, fragment, fragment.fragmentTag)
                else
                    it.add(containerId, fragment, fragment.fragmentTag)
            }
        }.commitAllowingStateLoss()
    }

    protected fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun registerOnBackPressedCallback() {

        onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    private fun getBaseFragmentOrNull(): BaseFragment? = (supportFragmentManager.fragments.findLast {
        it is BaseFragment
    } as BaseFragment?)
}