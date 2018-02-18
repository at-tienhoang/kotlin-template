package dk.eboks.app.presentation.ui.screens.message.embedded

import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface MessageEmbeddedContract {
    interface View : BaseView {
        fun addHeaderComponentFragment()
        fun addNotesComponentFragment()
        fun addAttachmentsComponentFragment()
        fun addFolderInfoComponentFragment()
        fun addPdfViewer()
        fun addImageViewer()
        fun addHtmlViewer()
        fun addTextViewer()
        fun showError(msg : String)
    }

    interface Presenter : BasePresenter<MessageEmbeddedContract.View> {
    }
}