package org.jetbrains.kotlinconf.login

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.ViewGroup


class LoginActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootViewGroup = findViewById<View>(android.R.id.content) as ViewGroup
        LoginBuilder().build(rootViewGroup)
    }
}