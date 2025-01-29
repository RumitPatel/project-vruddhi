package com.project.vruddhi.ui.pregnantwoman

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.collection.emptyLongSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.vruddhi.R
import com.project.vruddhi.base.FragmentBase
import com.project.vruddhi.databinding.FragmentPregnanatWomanUpdateCounsellingBinding
import com.project.vruddhi.extensions.setTitle
import com.project.vruddhi.network.ResponseHandler
import com.project.vruddhi.ui.pregnantwoman.model.request.PregnantWomanUpdateAndExitRequest
import com.project.vruddhi.ui.pregnantwoman.viewmodel.PregnantWomanViewModel
import com.project.vruddhi.utils.checkNullAndSet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * Pregnant Woman screening class
 *
 */
class PregnantWomanCounsellingFragment : FragmentBase() {

    private lateinit var _binding: FragmentPregnanatWomanUpdateCounsellingBinding
    private val binding get() = _binding
    private var mView: View? = null

    private val viewModel: PregnantWomanViewModel by activityViewModels()
    private val mCalender: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            _binding =
                FragmentPregnanatWomanUpdateCounsellingBinding.inflate(inflater, container, false)
            mView = binding.root
        }
        return mView
    }

    override fun setupToolbar() {
        setTitle(getString(R.string.pregnant_woman))
    }

    override fun initializeScreenVariables() {
        viewModel.init()
        setScreeningProgressBar()
        setListeners()
        setDataObservers()
        setPatientData()
    }

    override fun makeApiCalls() {

    }

    private fun setPatientData() {
        binding.rbDeliveryOfChildYes.isChecked =
            if (viewModel.mPregnantWomanGetScreeningInfo?.isDelivery == 1) true else false
        binding.etDateOfDelivery.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.dODate)
        binding.etPlaceOfDelivery.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.placeOfDelivery)
        if(viewModel.mPregnantWomanGetScreeningInfo?.modeOfDelivery == "Normal")
            binding.rbMODNormal.isChecked = true;
        if(viewModel.mPregnantWomanGetScreeningInfo?.modeOfDelivery == "Cesarean")
            binding.rbMODCesarean.isChecked = true;
        if(viewModel.mPregnantWomanGetScreeningInfo?.modeOfDelivery == "Instrumentation")
            binding.rbMODInstrumentation.isChecked = true;

        if(viewModel.mPregnantWomanGetScreeningInfo?.durationOfPregnancy == "Preterm")
            binding.rbDuratoinPreTerm.isChecked = true;
        if(viewModel.mPregnantWomanGetScreeningInfo?.durationOfPregnancy == "Fullterm")
            binding.rbDuratoinFulTerm.isChecked = true;

        //binding.cbPreTerm.isChecked = if (viewModel.mPregnantWomanGetScreeningInfo?.ist == 1) true else false

        binding.etBirthWeight.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.birthWeight.toString())
        //binding.rbDeathOfMotherYes.isChecked = if (viewModel.mPregnantWomanGetScreeningInfo.mo)

        if(viewModel.mPregnantWomanGetScreeningInfo?.isMotherComplications == 1)
            binding.rdYesComplicationMother.isChecked = true
        if(viewModel.mPregnantWomanGetScreeningInfo?.isMotherComplications == 0)
            binding.rdNoComplicationMother.isChecked = true

        binding.etComplication.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.motherComplications.toString())


        if(viewModel.mPregnantWomanGetScreeningInfo?.isMigratedArea == 1)
            binding.rdButtonYesMigraionWithin.isChecked = true
        if(viewModel.mPregnantWomanGetScreeningInfo?.isMigratedArea == 0)
            binding.rdButtonNoMigraionWithin.isChecked = true

        binding.etNameOfVillage.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.migratedVillage.toString())

        if(viewModel.mPregnantWomanGetScreeningInfo?.isMiscarriage == 1)
            binding.rdYesMisscarriage.isChecked = true
        if(viewModel.mPregnantWomanGetScreeningInfo?.isMiscarriage == 0)
            binding.rdNoMisscarriage.isChecked = true


        if(viewModel.mPregnantWomanGetScreeningInfo?.isMotherDeath == 1)
            binding.rbDeathOfMotherYes.isChecked = true
        else
            binding.rbDeathOfMotherNo.isChecked = true

        binding.etReasonOfDeath.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.deathReason.toString())


    }

    private fun setScreeningProgressBar() {
        binding.includeProgress.tvScreening.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.black
            )
        )
        binding.includeProgress.ivRoundScreening.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvRegistration.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.black
            )
        )
        binding.includeProgress.ivRoundRegistration.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvServices.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.black
            )
        )
        binding.includeProgress.ivRoundServices.setImageResource(R.drawable.round_green)
        binding.includeProgress.tvExit.setTextColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.colorPrimary
            )
        )
        binding.includeProgress.ivRoundExit.setImageResource(R.drawable.round_blue)
    }

    /**
     * Method to set click listener
     */
    private fun setListeners() {

        binding.etPlaceOfDelivery.setOnClickListener {
            val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            alertDialogBuilder.setTitle(getString(R.string.select_place_of_delivery))
            val types = resources.getStringArray(R.array.DiliverySpace)
            alertDialogBuilder.setItems(
                types
            ) { dialog, which ->
                dialog.dismiss()

                binding.etPlaceOfDelivery.setText(types[which])
            }
            alertDialogBuilder.show()
        }

        binding.etDateOfDelivery.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, month, dayOfMonth ->
                    binding.etDateOfDelivery.setText("$dayOfMonth/${(month + 1)}/$year")

                    mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    mCalender.set(Calendar.MONTH, month)
                    mCalender.set(Calendar.YEAR, year)

                    // Format the date
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Change format as needed
                    val formattedDate = dateFormat.format(mCalender.time)

                    binding.etDateOfDelivery.setText(formattedDate) // Set formatted date
                },
                mCalender.get(Calendar.YEAR),
                mCalender.get(Calendar.MONTH),
                mCalender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
        binding.btnSaveAndNext.setOnClickListener {
            val request = PregnantWomanUpdateAndExitRequest()
            request.isDelivery =
                if (binding.rbDeliveryOfChildYes.isChecked) 1 else if (binding.rbDeliveryOfChildNo.isChecked) 0 else 0
            request.dODate = binding.etDateOfDelivery.text.toString()
            request.placeOfDelivery = binding.etPlaceOfDelivery.text.toString()
            if(binding.rbMODNormal.isChecked == true )
                request.modeOfDelivery = "Normal"
            if(binding.rbMODCesarean.isChecked == true )
                request.modeOfDelivery = "Cesarean"
            if(binding.rbMODInstrumentation.isChecked == true )
                request.modeOfDelivery = "Instrumentation"

            if(binding.rbDuratoinPreTerm.isChecked == true)
                request.durationOfPregnancy = "Preterm"
            if(binding.rbDuratoinFulTerm.isChecked == true)
                request.durationOfPregnancy = "Fullterm"


            request.birthWeight = binding.etBirthWeight.text.toString()
            if(binding.rdYesComplicationMother.isChecked == true)
                request.isMotherComplications  = 1
            if(binding.rdNoComplicationMother.isChecked == true)
                request.isMotherComplications  = 0
            request.motherComplications = binding.etComplication.text.toString()

            if(binding.rdButtonYesMigraionWithin.isChecked == true)
                request.isMigratedArea =1
            else
                request.isMigratedArea =0

            request.migratedVillage = binding.etNameOfVillage.text.toString()

            if(binding.rdYesMisscarriage.isChecked == true)
                request.isMiscarriage = 1
            else
                request.isMiscarriage =0

            if(binding.rbDeathOfMotherYes.isChecked == true)
                request.isMotherDeath = 1
            else
                request.isMotherDeath = 0
            request.deathReason = binding.etReasonOfDeath.text.toString()

            viewModel.mPregnantWomanGetScreeningInfo?.screeningId?.let {
                viewModel.callPregnantWomanUpdateAndExitApi(
                    it,
                    request
                )
            }
        }
    }

    /**
     * Method to set data observers
     */
    private fun setDataObservers() {
        viewModel.apply {
            pregnantWomanUpdateAndExitResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseHandler.Loading -> {
                        showProgressBar()
                    }

                    is ResponseHandler.OnSuccessResponse -> {
                        hideProgressBar()
                        showSnackBar(it?.response?.message)
                        findNavController().navigate(R.id.action_pregnantWomanUpdateCounsellingFragment_to_pregnantWomanListFragment)
                    }

                    is ResponseHandler.OnFailed -> {
                        hideProgressBar()
                        handleError(it.code ?: 0, it.message)
                    }

                    else -> Unit
                }
            }
        }
    }
}
