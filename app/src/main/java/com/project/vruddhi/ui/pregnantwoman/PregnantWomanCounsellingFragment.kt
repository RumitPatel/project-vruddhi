package com.project.vruddhi.ui.pregnantwoman

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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
import java.util.Calendar


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
        binding.etDateOfDelivery.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.dODate)
        binding.etPlaceOfDelivery.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.pODate)
        binding.cbNutritionKitGiven.isChecked =
            if (viewModel.mPregnantWomanGetScreeningInfo?.isNutritionKitGiven == 1) true else false
        binding.cbHandWashingSoapGiven.isChecked =
            if (viewModel.mPregnantWomanGetScreeningInfo?.isSoapGiven == 1) true else false
        binding.cbCounselledAboutDietDuringPregnancy.isChecked =
            if (viewModel.mPregnantWomanGetScreeningInfo?.isDietCounselled == 1) true else false
        //binding.cbPreTerm.isChecked = if (viewModel.mPregnantWomanGetScreeningInfo?.ist == 1) true else false

        binding.etBirthWeight.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.birthWeight.toString())
        binding.etNameOfVillage.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.village.toString())
        binding.etReasonOfDeath.checkNullAndSet(viewModel.mPregnantWomanGetScreeningInfo?.deathReason.toString())
        //binding.rbDeathOfMotherYes.isChecked = if (viewModel.mPregnantWomanGetScreeningInfo.mo)
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
            val types = arrayOf(
                "Delivery place 1",
                "Delivery place 2",
                "Delivery place 3",
                "Delivery place 4",
                "Delivery place 5",
                "Delivery place 6",
                "Delivery place 7",
                "Delivery place 8",
                "Delivery place 9",
                "Delivery place 10",
                "Delivery place 11",
                "Delivery place 12"
            )
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
            request.dODate = binding.etDateOfDelivery.text.toString()
            request.pODate = binding.etPlaceOfDelivery.text.toString()
            request.birthWeight = binding.etBirthWeight.text.toString()
            request.deathReason = binding.etReasonOfDeath.text.toString()
            request.motherComplications = binding.etComplication.text.toString()
            request.migratedVillage = binding.etNameOfVillage.text.toString()
            request.isDelivery =
                if (binding.rbDeliveryOfChildYes.isChecked) 1 else if (binding.rbDeliveryOfChildNo.isChecked) 0 else 0

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
