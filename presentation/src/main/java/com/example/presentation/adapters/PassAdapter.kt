package com.example.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.entities.Pass
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pass_item.*
import java.text.SimpleDateFormat
import java.util.*

class PassAdapter(private val passes: List<Pass>) : androidx.recyclerview.widget.RecyclerView.Adapter<PassAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pass_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = passes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempPass = passes[position]
        holder.bind(riseTime = tempPass.riseTime, duration = tempPass.duration)
    }

    class ViewHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(riseTime: String, duration: String) {
            val resources = containerView.resources
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            tvDuration.text = resources.getString(R.string.duration, duration)
            tvRiseTime.text = resources.getString(R.string.risetime, simpleDateFormat.format(Date(riseTime.toLong() * 1000)))
        }
    }
}