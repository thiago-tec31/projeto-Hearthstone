package com.example.projecthearthstone.core.components.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewEmptySuport @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): RecyclerView(context, attrs, defStyleAttr) {

    private var emptyView: View? = null

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(emptyObserver)
    }

    private val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && emptyView != null) {
                if (adapter.itemCount == 0) {
                    emptyView?.let {
                        it.visibility = View.VISIBLE
                        this@MyRecyclerViewEmptySuport.visibility = View.GONE
                    }
                } else {
                    emptyView?.let {
                        it.visibility = View.GONE
                        this@MyRecyclerViewEmptySuport.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}