package dk.eboks.app.presentation.ui.components.channels.content.ekey.open

import dk.eboks.app.domain.managers.AppStateManager
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class EkeyOpenItemComponentPresenter @Inject constructor(val appState: AppStateManager) : EkeyOpenItemComponentContract.Presenter, BasePresenterImpl<EkeyOpenItemComponentContract.View>() {

    init {
    }

}