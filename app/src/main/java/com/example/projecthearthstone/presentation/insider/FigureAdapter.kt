package com.example.projecthearthstone.presentation.insider

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.projecthearthstone.R
import com.example.projecthearthstone.databinding.LayoutImageCardBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class FigureAdapter(
    private val context: Context
): RecyclerView.Adapter<FigureAdapter.FigureViewHolder>() {

    inner class FigureViewHolder(var viewBinding: LayoutImageCardBinding): RecyclerView.ViewHolder(viewBinding.root)

    private var figures = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureViewHolder {
        val view = LayoutImageCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return FigureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FigureViewHolder, position: Int) {
        val url = figures[position]
        loadFigure(holder.viewBinding.itemImageFigure, url)
    }

    fun updatesFigures(figures: List<String>) {
        this.figures.clear()
        this.figures.addAll(figures)
    }

    private fun loadFigure(imageView: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.figure_hearthstone)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    Log.e("sucesso", "funcionou")
                }
                override fun onError(e: Exception?) {
                    Log.e("erro", "deu merda " + e?.message)
                }
            })
    }

    override fun getItemCount(): Int = figures.size

}