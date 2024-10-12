package com.project.vruddhi.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.vruddhi.databinding.ItemPregnantWomanListBinding
import com.project.vruddhi.ui.pregnantwoman.model.PregnantWomanListResponse

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
        val patientName = patientInfo.womenName
        val patientAge = patientInfo.age
        val patientCity = patientInfo.village


        holder.binding.tvPatientName.text =
            if (!TextUtils.isEmpty(patientName)) (patientName) else ""

        holder.binding.tvPatientAge.text =
            if (!TextUtils.isEmpty(patientAge.toString())) ("$patientAge years") else ""

        holder.binding.tvPatientCity.text =
            if (!TextUtils.isEmpty(patientCity)) (patientCity) else ""

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