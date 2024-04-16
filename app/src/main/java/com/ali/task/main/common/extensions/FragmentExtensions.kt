/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ali.task.R
import com.ali.task.main.data.models.NetworkState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

fun Fragment.handleResultSharedFlow(
    userFlow: SharedFlow<NetworkState>,
    onShowProgress: (() -> Unit)? = null,
    onHideProgress: (() -> Unit)? = null,
    onSuccess: (data: Any) -> Unit,
    onError: ((th: Throwable) -> Unit)? = null
) {
    this.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            userFlow.collect { networkState ->
                when (networkState) {
                    is NetworkState.Success<*> -> {
                        onSuccess(networkState.data!!)
                    }
                    is NetworkState.Loading -> {
                        if (onShowProgress != null)
                            onShowProgress()
                    }

                    is NetworkState.StopLoading -> {
                        if (onHideProgress != null)
                            onHideProgress()
                    }
                    is NetworkState.Error -> {
                        if (onError == null)
//                            handleErrorGeneral(networkState.throwable)
                        else
                            onError(networkState.throwable)
                    }

                    else -> {

                    }
                }
            }
        }
    }
}

fun Fragment.showSuccessMessage(msg: String) {
    MotionToast.createColorToast(
        activity!!,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

