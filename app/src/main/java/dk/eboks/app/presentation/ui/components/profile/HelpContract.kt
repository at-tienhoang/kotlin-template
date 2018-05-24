package dk.eboks.app.presentation.ui.components.profile

import dk.eboks.app.presentation.base.BaseView
import dk.nodes.arch.presentation.base.BasePresenter

/**
 * Created by Christian on 5/23/2018.
 * @author   Christian
 * @since    5/23/2018.
 */
interface HelpContract {
    interface View : BaseView {
        fun loadUrl(urlString: String)
    }

    interface Presenter : BasePresenter<View>
}