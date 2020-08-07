package com.example.nanoleafcolors.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nanoleafcolors.R
import com.example.nanoleafcolors.databinding.ColorCountRowBinding

class ColorsAndCountAdapter(private val selectedColorsWithCounts: List<Pair<String, Pair<Int, Int>>>, private val context: Context) :
    RecyclerView.Adapter<ColorsAndCountAdapter.ColorsAndCountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsAndCountViewHolder {
        val binding =
            ColorCountRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorsAndCountViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedColorsWithCounts.size
    }

    override fun onBindViewHolder(holder: ColorsAndCountViewHolder, position: Int) {
        val currentItem = selectedColorsWithCounts[position]
        holder.binding.txvColorName.text= currentItem.first
        holder.binding.txvColorCount.text=currentItem.second.first.toString()
        holder.binding.llColorNCount.setBackgroundColor(ContextCompat.getColor(context, currentItem.second.second))

        if(currentItem.first.equals("yellow",true)){
            holder.binding.txvColorName.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.binding.txvColorCount.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    class ColorsAndCountViewHolder(val binding: ColorCountRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}