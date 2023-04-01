package com.example.projecthearthstone.presentation.insider

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.projecthearthstone.R
import com.example.projecthearthstone.databinding.LayoutImageCardBinding
import com.example.projecthearthstone.domain.model.FigureCards
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class FigureAdapter(
    private val context: Context
): RecyclerView.Adapter<FigureAdapter.FigureViewHolder>() {

    inner class FigureViewHolder(var viewBinding: LayoutImageCardBinding): RecyclerView.ViewHolder(viewBinding.root)

    private var figures = mutableListOf<FigureCards>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureViewHolder {
        val view = LayoutImageCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return FigureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FigureViewHolder, position: Int) {
        val image = holder.viewBinding.itemImageFigure
        val url = figures[position].img
        loadFigure(image, url)
    }

    fun updatesFigures(figures: List<FigureCards>) {
        this.figures.clear()
        this.figures.addAll(figures)
        notifyDataSetChanged()
    }

    private fun loadFigure(imageView: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.animation)
            .into(imageView)
    }

    override fun getItemCount(): Int = figures.size

}