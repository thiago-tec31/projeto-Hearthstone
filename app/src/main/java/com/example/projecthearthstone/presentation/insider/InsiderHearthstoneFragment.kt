package com.example.projecthearthstone.presentation.insider

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.databinding.FragmentInsiderHearthstoneBinding

class InsiderHearthstoneFragment: BaseFragment<FragmentInsiderHearthstoneBinding>(FragmentInsiderHearthstoneBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeInsiderLayoutMain.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}