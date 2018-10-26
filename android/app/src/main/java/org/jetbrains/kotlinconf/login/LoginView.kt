package org.jetbrains.kotlinconf.login

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import kotlinx.android.synthetic.main.view_login_home.view.*

class LoginView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle), LoginInteractor.LoginPresenter {


    override fun nextButtonClick(callback: (String) -> Unit) {
        nextButton.setOnClickListener {
            callback.invoke("")
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