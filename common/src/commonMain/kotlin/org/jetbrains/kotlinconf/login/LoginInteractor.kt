package org.jetbrains.kotlinconf.login

class LoginInteractor {

    lateinit var presenter: LoginPresenter

    lateinit var loginService: LoginService

    fun didBecomeActive() {
        setNextButtonClick()
        setCountryCodeClick()
    }

    /**
     * 点击下一步按钮
     */
    private fun setNextButtonClick() {
        presenter.nextButtonClick({

        })
    }

    /**
     * 设置CountryCode点击事件
     */
    private fun setCountryCodeClick() {

    }

    /**
     * 请求用户登录状态
     */
    private fun requestUserLoginState(phoneNumber: String) {

    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface LoginPresenter {

        fun nextButtonClick(callback: (String) -> Unit)

        fun hideSoftKeyboard()

    }

}

enum class LoginPageType {
    PHONE, USERNAME_EMAIL
}