/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.common.extensions

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ali.task.R
import com.ali.task.main.data.models.NetworkState
import com.ali.task.main.ui.LoadingDialog
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast


fun Any.getClassName(): String {
    return this::class.java.simpleName
}


fun FragmentActivity.handleResultSharedFlow(
    userFlow: SharedFlow<NetworkState>,
    onShowProgress: (() -> Unit)? = null,
    onHideProgress: (() -> Unit)? = null,
    onSuccess: (data: Any) -> Unit,
    onError: ((th: Throwable) -> Unit)? = null
) {
    this.lifecycleScope.launch {
        val loadingDialog = LoadingDialog(this@handleResultSharedFlow)
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            userFlow.collect { networkState ->
                when (networkState) {
                    is NetworkState.Success<*> -> {
                        onSuccess(networkState.data!!)
                    }

                    is NetworkState.Loading -> {
                        if (onShowProgress != null)
                            onShowProgress()
                        else {
                            loadingDialog.show()
                        }
                    }

                    is NetworkState.StopLoading -> {
                        if (onHideProgress != null)
                            onHideProgress()
                        else {
                            loadingDialog.dismiss()
                        }
                    }

                    is NetworkState.Error -> {
                        if (onError == null)
//                            handleErrorGeneral(networkState.throwable)
                        else
                            onError(networkState.throwable)
                    }

                    else -> {}
                }
            }
        }
    }
}


private var progressDialog : LoadingDialog? = null
private var loadingDone = false
private val handler = Handler(Looper.getMainLooper())
fun Activity.showLoadingDialog() {
    // Delay the showing of the loading dialog by 500 milliseconds
    loadingDone = false
    handler.postDelayed({
        if(!loadingDone){
            if(progressDialog == null){
                progressDialog = LoadingDialog(this)
                progressDialog?.show()
            }else{
                if(!progressDialog?.isShowing!!)
                    progressDialog?.show()
            }
        }
    }, 500)

}

fun Activity.dismissLoadingDialog() {
    if(loadingDone)
        return
    if (progressDialog!=null){
        progressDialog?.dismissLoadingDialog()
        loadingDone = true
    }
}


fun <T> FragmentActivity.startActivity(_class: Class<T>) {
    startActivity(Intent(this, _class))
}


fun FragmentActivity.showMsg(msg: String, type: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        type,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

fun FragmentActivity.showSuccessMessage(msg: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

fun Activity.emptyListChecker(list: List<*>, view: TextView){
    if(list.isEmpty()){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

fun FragmentActivity.showErrorMessage(msg: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

//fun Activity.showConfirmationDialog(title : String, message : String, positiveBtnTxt :String, negativeBtnTxt : String,onConfirm:(Boolean)->Unit) {
//    var confirmationDialog = ConfirmationDialog(this,title,message,positiveBtnTxt,negativeBtnTxt,onConfirm)
//    confirmationDialog.show()
//}



