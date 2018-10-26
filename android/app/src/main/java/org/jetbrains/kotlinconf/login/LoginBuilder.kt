package org.jetbrains.kotlinconf.login

import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater
import org.jetbrains.kotlinconf.R

class LoginBuilder {

    fun build(parentViewGroup: ViewGroup) {
        val loginInteractor = LoginInteractor()
        val context = parentViewGroup.context
        val layoutInflater = context.layoutInflater
        val view = layoutInflater.inflate(R.layout.view_login_home, parentViewGroup, false)
        parentViewGroup.addView(view)
        loginInteractor.didBecomeActive()
    }
}