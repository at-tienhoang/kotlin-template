package dk.eboks.app.presentation.ui.screens.senders.overview

import dk.eboks.app.domain.managers.AppStateManager
import dk.nodes.arch.presentation.base.BasePresenterImpl

/**
 * Created by bison on 20-05-2017.
 */
class SendersOverviewPresenter(val appStateManager: AppStateManager) : SendersOverviewContract.Presenter, BasePresenterImpl<SendersOverviewContract.View>() {
    init {
    }

}