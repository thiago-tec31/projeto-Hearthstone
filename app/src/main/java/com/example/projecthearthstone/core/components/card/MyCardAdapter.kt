package com.example.projecthearthstone.core.components.card

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.example.projecthearthstone.R
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.ItemCard
import com.example.projecthearthstone.databinding.LayoutItemCardBinding

typealias CardItemClickListener = (String) -> Unit

class MyCardAdapter(
    private val context: Context
): RecyclerView.Adapter<MyCardAdapter.CardsViewHolder>() {

    private var cardsList = mutableListOf<String>()

    private var cardType: CardType? = null

    private var clickItemListener: CardItemClickListener? = null

    inner class CardsViewHolder(var viewBinding: LayoutItemCardBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutItemCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card = cardsList[position]
        holder.viewBinding.itemCardText.text = card
        holder.viewBinding.itemCardViewMain.backgroundTintList = getColorByPosition(position)
        holder.viewBinding.itemCardViewMain.setOnClickListener {
            clickItemListener?.invoke(card)
        }
    }

    fun setOnClickCardListener(clickItemListener: CardItemClickListener){
        this.clickItemListener = clickItemListener
    }

    private fun getColorByPosition(position: Int): ColorStateList  {
        return when(position % 3) {
            0 -> {
                when (cardType) {
                    CardType.CLASSES -> getColorStateList(context, R.color.pink_card)
                    CardType.TYPES -> getColorStateList(context, R.color.purple_card)
                    else -> getColorStateList(context, R.color.beige_card)
                }
            }
            1 -> {
                when (cardType) {
                    CardType.CLASSES -> getColorStateList(context, R.color.green_card)
                    CardType.TYPES ->  getColorStateList(context, R.color.yellow_card)
                    else -> getColorStateList(context, R.color.wine_card)
                }
            }
            else -> {
                when (cardType) {
                    CardType.CLASSES -> getColorStateList(context, R.color.blue_card)
                    CardType.TYPES -> getColorStateList(context, R.color.gray_card)
                    else -> getColorStateList(context, R.color.orange_card)
                }
            }
        }
    }

    fun updateCards(cards: ItemCard) {
        this.cardType = cards.title
        this.cardsList.clear()
        this.cardsList.addAll(cards.names)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cardsList.size
}