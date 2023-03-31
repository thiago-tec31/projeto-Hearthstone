package com.example.projecthearthstone.core.components.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecthearthstone.domain.model.ItemCard
import com.example.projecthearthstone.databinding.LayoutMyCardHearthstoneBinding

class MyCardHearthstone @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val homeCardAdapter = MyCardAdapter(context)

    private val binding = LayoutMyCardHearthstoneBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.homeHearthstoneRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = homeCardAdapter
        }
    }

    fun updateCards(cards: ItemCard) {
        homeCardAdapter.updateCards(cards)
    }

    fun setTextTitle(text: String) {
        binding.homeHearthstoneName.text = text
    }

    fun setOnClickCardListener(action: (String) -> Unit){
        homeCardAdapter.setOnClickCardListener(action)
    }

}