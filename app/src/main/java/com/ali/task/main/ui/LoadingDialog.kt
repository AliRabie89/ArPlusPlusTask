/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.ali.task.R
import com.ali.task.databinding.DialogLayoutLoadingBinding
import com.ali.task.main.common.extensions.setUpSheetUi

class LoadingDialog(screenContext: Context) :
    Dialog(screenContext, R.style.BottomSheetDialog) {
    var binding: DialogLayoutLoadingBinding =
        DialogLayoutLoadingBinding.inflate(LayoutInflater.from(screenContext))

    init {
        setupBottomSheet()
    }

    //Entry point to this bottom sheet dialog
    private fun setupBottomSheet() {
        setupUI()
        setupListeners()
    }

    // Set up the UI components
    private fun setupUI() {
        // Set up any UI-related configurations here
        setContentView(binding.root)
        setUpSheetUi()
    }

    // Set up event listeners for button clicks and other interactions
    private fun setupListeners() {

    }
}