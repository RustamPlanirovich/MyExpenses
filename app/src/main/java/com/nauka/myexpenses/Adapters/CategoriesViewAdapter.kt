package com.nauka.myexpenses.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauka.myexpenses.AllEntity.Categories
import com.nauka.myexpenses.R
//import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import kotlinx.android.synthetic.main.recycler_item.view.txtDescription

class CategoriesViewAdapter(
    val today: List<Categories>,
    private val onCardClick: (Categories) -> Unit
): RecyclerView.Adapter<CategoriesViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CategoriesViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoriesViewAdapter.ViewHolder, position: Int) {

        holder.txtDescriptor.text = today[position].categoryName


        //holder.txtOptionDigi.setOnClickListener { onCardClick.invoke(today[position]) }
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
        val txtDescriptor = itemView.txtDescription
        //val txtOptionDigi = itemView.txtOptionDelete
    }
}