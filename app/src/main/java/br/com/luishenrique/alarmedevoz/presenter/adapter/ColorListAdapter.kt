package br.com.luishenrique.alarmedevoz.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.alarmedevoz.R
import kotlinx.android.synthetic.main.item_color.view.*

class ColorListAdapter(
    context: Context,
    adapterListener: ColorAdapterListener
): RecyclerView.Adapter<ColorListAdapter.ViewHolder>() {

    private val mItems: MutableList<Int> = mutableListOf()
    private var mLastItemSelected: ImageView? = null
    private val mAdapterListener: ColorAdapterListener = adapterListener

    init {
        val typedArray = context.resources.getIntArray(R.array.itemColors)
        for (index in typedArray.indices) {
            mItems.add(typedArray[index])
        }
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(color: Int) {
            var buttonDrawable = view.colorItem.background
            buttonDrawable = DrawableCompat.wrap(buttonDrawable)

            DrawableCompat.setTint(buttonDrawable, color)
            view.colorItem.background = buttonDrawable

            view.colorItem.setOnClickListener {
                mLastItemSelected.let {
                    it?.visibility = View.GONE
                }
                view.selected.visibility = View.VISIBLE
                mLastItemSelected = view.selected
                mAdapterListener.onClick(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount() = mItems.size
}

interface ColorAdapterListener {
    fun onClick(color: Int)
}