package dk.eboks.app.presentation.ui.components.start.login

import dk.eboks.app.domain.managers.AppStateManager
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class LoginComponentPresenter @Inject constructor(val appState: AppStateManager) : LoginComponentContract.Presenter, BasePresenterImpl<LoginComponentContract.View>() {

    init {
    }

}