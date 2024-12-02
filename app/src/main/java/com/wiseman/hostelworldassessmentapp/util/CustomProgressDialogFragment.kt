package com.wiseman.hostelworldassessmentapp.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.CustomDialogProgressbarBinding
import javax.inject.Inject
import javax.inject.Singleton

class CustomProgressDialogFragment :
    DialogFragment(R.layout.custom_dialog_progressbar) {
    private var _binding: CustomDialogProgressbarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CustomDialogProgressbarBinding.inflate(inflater, container, false)

        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog?.setCancelable(false)
        }


        return binding.root
    }


    companion object {
        fun newInstance(): CustomProgressDialogFragment =
            CustomProgressDialogFragment()
    }
}