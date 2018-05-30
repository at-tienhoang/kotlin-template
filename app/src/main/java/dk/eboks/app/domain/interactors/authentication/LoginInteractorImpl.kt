package dk.eboks.app.domain.interactors.authentication

import dk.eboks.app.BuildConfig
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.managers.UserManager
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor

/**
 * Created by bison on 24-06-2017.
 */
class LoginInteractorImpl(executor: Executor, val api: Api, val appStateManager: AppStateManager, val userManager: UserManager) : BaseInteractor(executor), LoginInteractor {
    override var output: LoginInteractor.Output? = null
    override var input: LoginInteractor.Input? = null

    override fun execute() {
        try {
            input?.let {
                var map = mapOf(
                        Pair("grant_type", "password"),
                        Pair("scope", "mobileapi offline_access"),
                        Pair("client_id", BuildConfig.OAUTH_LONG_ID),
                        Pair("client_secret", BuildConfig.OAUTH_LONG_SECRET)
                )
                it.loginState.userName?.let {
                    map = map.plus(Pair("username", it))
                }
                it.loginState.userPassWord?.let {
                    map = map.plus(Pair("password", it))
                }
                it.loginState.activationCode?.let {
                    map = map.plus(Pair("acr_values", "activationcode:$it nationality:DK"))
                }

                val tokenResult = api.getToken(map).execute()

                if (tokenResult.isSuccessful) {

                    tokenResult?.body()?.let { token ->
                        appStateManager.state?.loginState?.token = token

                        val userResult = api.getUserProfile().execute()
                        userResult?.body()?.let {
                            userManager.add(it)
                            appStateManager.state?.currentUser = it
                        }
                        appStateManager.save()

                        runOnUIThread {
                            output?.onLoginSuccess(token)
                        }
                    }
                } else if (tokenResult.code() == 400) {
                    runOnUIThread {
                        output?.onLoginActivationCodeRequired()
                    }
                } else {
                    runOnUIThread {
                        output?.onLoginDenied(ViewError(title = Translation.error.genericTitle, message = Translation.error.genericMessage, shouldCloseView = true)) // TODO better error
                    }
                }
            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onLoginError(exceptionToViewError(t))
            }
        }
    }
}