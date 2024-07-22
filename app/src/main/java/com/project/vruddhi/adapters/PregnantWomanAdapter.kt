package com.project.vruddhi.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.vruddhi.databinding.ItemPregnantWomanListBinding
import com.project.vruddhi.models.PatientInfo

class PregnantWomanAdapter(
    private val mPatientInfos: List<PatientInfo>,
    private val onPatientClick: (PatientInfo) -> Unit
) :
    RecyclerView.Adapter<PregnantWomanAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewPatientBinding =
            ItemPregnantWomanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewPatientBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val patientInfo = mPatientInfos[holder.adapterPosition]
        val patientName = patientInfo.patient_name
        val patientAge = patientInfo.patient_age
        val patientCity = patientInfo.patient_city


        holder.binding.tvPatientName.text =
            if (!TextUtils.isEmpty(patientName)) (patientName) else ""

        holder.binding.tvPatientAge.text =
            if (!TextUtils.isEmpty(patientAge)) ("$patientAge years") else ""

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