package br.com.luishenrique.alarmedevoz.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.entity.Alarm

class AlarmListAdapter (
    adapterListener: AlarmAdapterListener
): RecyclerView.Adapter<AlarmListAdapter.ViewHolder>() {

    var mItems: MutableList<Alarm> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val mAdapterListener: AlarmAdapterListener = adapterListener

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(alarm: Alarm) {
            view.setOnClickListener {
                mAdapterListener.onClick(alarm)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount() = mItems.size
}

interface AlarmAdapterListener {
    fun onClick(alarm: Alarm)
}