package com.beraterdem2.tarifkupu2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beraterdem2.tarifkupu2.databinding.MealRowBinding
import com.beraterdem2.tarifkupu2.modul.Meals
import com.beraterdem2.tarifkupu2.view.DetailsActivity

class DessertAdapter(val arrayList: ArrayList<Meals>):RecyclerView.Adapter<DessertAdapter.DesserHolder>() {

    class DesserHolder(val binding: MealRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesserHolder {
        val mealRowBinding=MealRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DesserHolder(mealRowBinding)
    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: DesserHolder, position: Int) {
        holder.binding.imageRow.setImageResource(arrayList.get(position).mealImage)
        holder.binding.txtRow.text=arrayList.get(position).mealName
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("pushDessert",arrayList.get(position))
            holder.itemView.context.startActivity(intent)

        }

    }
}