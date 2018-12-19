package com.example.jasongomez.isschallenge.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jasongomez.isschallenge.R
import com.example.jasongomez.isschallenge.presentation.entities.Pass
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pass_item.*
import java.text.SimpleDateFormat
import java.util.*

class PassAdapter(private val passes: List<Pass>) : RecyclerView.Adapter<PassAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, res: Int): PassAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pass_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = passes.size

    override fun onBindViewHolder(holder: PassAdapter.ViewHolder, position: Int) {
        val tempPass = passes[position]
        holder.bind(riseTime = tempPass.riseTime, duration = tempPass.duration)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(riseTime: String, duration: String) {
            val resources = containerView.resources
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            tvDuration.text = resources.getString(R.string.duration, duration)
            tvRiseTime.text = resources.getString(R.string.risetime, simpleDateFormat.format(Date(riseTime.toLong() * 1000)))
        }
    }
}