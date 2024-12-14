package com.project.vruddhi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.vruddhi.databinding.ItemPregnantWomanListBinding
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse
import com.project.vruddhi.utils.checkNullAndSet
import com.project.vruddhi.utils.isNotNullOrBlank

class PregnantWomanAdapter(
    private val mPatientInfos: List<PregnantWomanListResponse>,
    private val onPatientClick: (PregnantWomanListResponse) -> Unit
) :
    RecyclerView.Adapter<PregnantWomanAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewPatientBinding =
            ItemPregnantWomanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewPatientBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val patientInfo = mPatientInfos[holder.adapterPosition]
        val patientAge = patientInfo.age
        val patientCity = patientInfo.village

        holder.binding.tvPatientName.checkNullAndSet(patientInfo.womenName)
        holder.binding.tvStatus.checkNullAndSet(patientInfo.status)
        holder.binding.tvPatientCity.checkNullAndSet(patientCity)
        holder.binding.tvDotCity.visibility =
            if (patientCity.isNotNullOrBlank()) View.VISIBLE else View.GONE

        holder.binding.tvPatientAge.text =
            if (patientAge.toString().isNotNullOrBlank()) ("$patientAge years") else ""

        holder.binding.llItemPregnantWoman.setOnClickListener {
            onPatientClick(patientInfo)
        }
    }

    override fun getItemCount(): Int {
        return mPatientInfos.size
    }

    class MyViewHolder(val binding: ItemPregnantWomanListBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}
