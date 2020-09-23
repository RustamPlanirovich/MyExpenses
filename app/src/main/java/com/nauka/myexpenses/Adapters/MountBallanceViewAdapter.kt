package com.nauka.myexpenses.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauka.myexpenses.AllEntity.MountBallance
import com.nauka.myexpenses.R
//import kotlinx.android.synthetic.main.cash_item.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
//import kotlinx.android.synthetic.main.setting_fragment.view.*

class MountBallanceViewAdapter(
    val today: List<MountBallance>,
    private val onCardClick: (MountBallance) -> Unit
): RecyclerView.Adapter<MountBallanceViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MountBallanceViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MountBallanceViewAdapter.ViewHolder, position: Int) {

        //holder.txtTitle.setText(today[position].mountballance)

    }

    override fun getItemCount(): Int {
        return today.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val txtTitle = itemView.ballanceSumm
    }
}