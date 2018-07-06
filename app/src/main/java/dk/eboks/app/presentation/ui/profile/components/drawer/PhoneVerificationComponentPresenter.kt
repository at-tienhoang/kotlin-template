package dk.eboks.app.presentation.ui.profile.components.drawer

import dk.eboks.app.domain.interactors.user.ConfirmPhoneInteractor
import dk.eboks.app.domain.interactors.user.VerifyPhoneInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.local.ViewError
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class PhoneVerificationComponentPresenter @Inject constructor(val appState: AppStateManager,
                                                              val verifyPhoneInteractor: VerifyPhoneInteractor,
                                                              val confirmPhoneInteractor: ConfirmPhoneInteractor) :
        PhoneVerificationComponentContract.Presenter,
        BasePresenterImpl<PhoneVerificationComponentContract.View>(),
        VerifyPhoneInteractor.Output,
        ConfirmPhoneInteractor.Output
{

    init {
        verifyPhoneInteractor.output = this
        confirmPhoneInteractor.output = this
    }

    var currentMobile : String? = null

    override fun setup(mobile: String) {
        currentMobile = mobile
        verifyPhoneInteractor.input = VerifyPhoneInteractor.Input(mobile)
        verifyPhoneInteractor.run()
    }

    override fun resendVerificationCode() {
        runAction { v->
            v.showProgress(true)
        }
        currentMobile?.let {
            verifyPhoneInteractor.input = VerifyPhoneInteractor.Input(it)
            verifyPhoneInteractor.run()
        }
    }

    override fun confirmMobile(activationCode: String) {
        currentMobile?.let { mobile->
            confirmPhoneInteractor.input = ConfirmPhoneInteractor.Input(mobile, activationCode)
            confirmPhoneInteractor.run()
        }
    }

    /**
     * VerifyPhoneInteractor callbacks
     */

    override fun onVerifyPhone() {
        runAction { v->
            v.showProgress(false)
        }
    }

    override fun onVerifyPhoneError(error: ViewError) {
        runAction { v->
            v.showProgress(false)
            v.showErrorDialog(error)
        }
    }

    /**
     * ConfirmPhoneInteractor callbacks
     */
    override fun onConfirmPhone() {
        runAction { v->v.finishActivity(null) }
    }

    override fun onConfirmPhoneError(error: ViewError) {
        runAction { v->
            v.showProgress(false)
            v.showErrorDialog(error)
        }
    }
}