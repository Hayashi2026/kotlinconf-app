package org.jetbrains.kotlinconf.login

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class LoginView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), LoginInteractor.LoginPresenter {

    override fun nextButtonClick(): () -> String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideSoftKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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