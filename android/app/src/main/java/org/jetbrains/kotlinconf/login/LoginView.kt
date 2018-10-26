package org.jetbrains.kotlinconf.login

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.Button

class LoginView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), LoginInteractor.LoginPresenter {

    override fun nextButtonClick(callback: (String) -> Unit) {
        findViewWithTag<Button>("").setOnClickListener {
            callback.invoke("phone")
        }
    }

    override fun hideSoftKeyboard() {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!isInEditMode) {
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

}