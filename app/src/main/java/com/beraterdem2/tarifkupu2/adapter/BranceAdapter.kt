package com.beraterdem2.tarifkupu2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beraterdem2.tarifkupu2.databinding.BranceRowBinding
import com.beraterdem2.tarifkupu2.modul.Brance
import com.beraterdem2.tarifkupu2.view.BranceDetailsActivity

class BranceAdapter(val arrayList: ArrayList<Brance>):RecyclerView.Adapter<BranceAdapter.BranceHolder>() {

    class BranceHolder(val binding:BranceRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranceHolder {
        val binding=BranceRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return BranceHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: BranceHolder, position: Int) {
        holder.binding.image2Row.setImageBitmap(arrayList.get(position).branceImage)
        holder.binding.txt2Row.text=arrayList.get(position).branceName
        holder.binding.image2Row.setOnClickListener {

            val intent=Intent(holder.itemView.context, BranceDetailsActivity::class.java)
            intent.putExtra("id",arrayList.get(position).id)
            holder.itemView.context.startActivity(intent)
        }


    }

}