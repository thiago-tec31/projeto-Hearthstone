package com.example.projecthearthstone.presentation.main

import android.os.Bundle
import com.example.projecthearthstone.core.bases.BaseActivity
import com.example.projecthearthstone.databinding.ActivityHearthstoneBinding

class HearthstoneActivity: BaseActivity<ActivityHearthstoneBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViewBinding(): ActivityHearthstoneBinding =
        ActivityHearthstoneBinding.inflate(layoutInflater)
}