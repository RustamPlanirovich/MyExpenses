package com.nauka.myexpenses.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nauka.myexpenses.AllEntity.PurchasesTable
import com.nauka.myexpenses.MainActivity
import com.nauka.myexpenses.R
import kotlinx.android.synthetic.main.recycler_item.view.*
import java.text.SimpleDateFormat


class PurchasesViewAdapter(
    val today: List<PurchasesTable>,
    private val onCardClick: (PurchasesTable) -> Unit,
    val del: Context
) : RecyclerView.Adapter<PurchasesViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PurchasesViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: PurchasesViewAdapter.ViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        holder.txtTitle.text = sdf.format(today[position].purchases)
            //today[position].purchases.toString()
        holder.txtDescriptor.text = today[position].summ.toString()
        holder.categoryNames.text = today[position].category
        holder.cashtype.text = today[position].cashtype


        holder.txtOptionDigit.setOnClickListener {
            //(del as MainActivity).delAlertDialog(it)
            onCardClick.invoke(today[position])
        }



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
        val txtTitle = itemView.txtTitles
        val txtDescriptor = itemView.txtDescription
        val txtOptionDigit = itemView.txtOptionDelet
        val cashtype = itemView.cashType
        val categoryNames = itemView.categoryName
    }
}
