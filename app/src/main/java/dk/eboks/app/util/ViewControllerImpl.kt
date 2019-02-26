package dk.eboks.app.util

import dk.eboks.app.presentation.base.ViewController
import dk.eboks.app.presentation.ui.home.components.channelcontrol.ChannelControlComponentFragment
import dk.eboks.app.presentation.ui.home.components.folderpreview.FolderPreviewComponentFragment
import dk.eboks.app.presentation.ui.home.screens.HomeActivity
import dk.eboks.app.presentation.ui.login.components.verification.VerificationComponentFragment
import dk.eboks.app.presentation.ui.mail.screens.overview.MailOverviewActivity

class ViewControllerImpl : ViewController {

    override var isVerificationSucceeded: Boolean
        get() = VerificationComponentFragment.verificationSucceeded
        set(value) { VerificationComponentFragment.verificationSucceeded = value }

    override fun refreshAllOnResume() {
        HomeActivity.refreshOnResume = true
        MailOverviewActivity.refreshOnResume = true
        ChannelControlComponentFragment.refreshOnResume = true
        FolderPreviewComponentFragment.refreshOnResume = true
    }
}