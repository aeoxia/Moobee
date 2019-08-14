package com.ausom.moobee.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<TBinding : ViewDataBinding, TViewModel : BaseViewModel> : AppCompatActivity() {
    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val viewModel: TViewModel

    protected abstract fun onBindingCreated(binding: TBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        DataBindingUtil.setContentView<TBinding>(this, layoutRes).let { binding ->
            binding.lifecycleOwner = this
            onBindingCreated(binding)
            setContentView(binding.root)
        }
    }
}