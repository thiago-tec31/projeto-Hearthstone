package com.example.projecthearthstone.core.components.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.projecthearthstone.R
import com.example.projecthearthstone.databinding.LayoutFloatingButtonBinding

class MyFloatingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutFloatingButtonBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.custom_floating_button, 0, 0).apply {
            val srcCompat = getDrawable(R.styleable.custom_floating_button_srcCompat)
            setIcon(srcCompat)
            recycle()
        }
    }

    private fun setIcon(icon: Drawable?) {
        icon?.let {
            binding.image.background = it
        }
    }


}