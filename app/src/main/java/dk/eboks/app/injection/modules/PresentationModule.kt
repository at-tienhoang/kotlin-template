package dk.eboks.app.injection.modules

import dagger.Module
import dagger.Provides
import dk.eboks.app.domain.interactors.*
import dk.eboks.app.domain.interactors.channel.GetChannelsInteractor
import dk.eboks.app.domain.interactors.folder.GetFoldersInteractor
import dk.eboks.app.domain.interactors.folder.OpenFolderInteractor
import dk.eboks.app.domain.interactors.message.GetMessagesInteractor
import dk.eboks.app.domain.interactors.message.OpenAttachmentInteractor
import dk.eboks.app.domain.interactors.message.OpenMessageInteractor
import dk.eboks.app.domain.interactors.message.SaveAttachmentInteractor
import dk.eboks.app.domain.interactors.sender.GetSendersInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.presentation.ui.components.folder.folders.FoldersComponentContract
import dk.eboks.app.presentation.ui.components.folder.folders.FoldersComponentPresenter
import dk.eboks.app.presentation.ui.components.mail.foldershortcuts.FolderShortcutsComponentContract
import dk.eboks.app.presentation.ui.components.mail.foldershortcuts.FolderShortcutsComponentPresenter
import dk.eboks.app.presentation.ui.components.mail.maillist.MailListComponentContract
import dk.eboks.app.presentation.ui.components.mail.maillist.MailListComponentPresenter
import dk.eboks.app.presentation.ui.components.mail.sendercarousel.SenderCarouselComponentContract
import dk.eboks.app.presentation.ui.components.mail.sendercarousel.SenderCarouselComponentPresenter
import dk.eboks.app.presentation.ui.screens.mail.folder.FolderContract
import dk.eboks.app.presentation.ui.screens.mail.folder.FolderPresenter
import dk.eboks.app.presentation.ui.screens.mail.list.MailListContract
import dk.eboks.app.presentation.ui.screens.mail.list.MailListPresenter
import dk.eboks.app.presentation.ui.screens.mail.overview.MailOverviewContract
import dk.eboks.app.presentation.ui.screens.mail.overview.MailOverviewPresenter
import dk.eboks.app.pasta.activity.PastaContract
import dk.eboks.app.pasta.activity.PastaPresenter
import dk.eboks.app.presentation.ui.components.channels.list.*
import dk.eboks.app.presentation.ui.screens.message.MessageContract
import dk.eboks.app.presentation.ui.screens.message.MessagePresenter
import dk.eboks.app.presentation.ui.screens.message.embedded.MessageEmbeddedContract
import dk.eboks.app.presentation.ui.screens.message.embedded.MessageEmbeddedPresenter
import dk.eboks.app.presentation.ui.components.message.attachments.AttachmentsComponentContract
import dk.eboks.app.presentation.ui.components.message.attachments.AttachmentsComponentPresenter
import dk.eboks.app.presentation.ui.components.message.document.DocumentComponentContract
import dk.eboks.app.presentation.ui.components.message.document.DocumentComponentPresenter
import dk.eboks.app.presentation.ui.components.message.folderinfo.FolderInfoComponentContract
import dk.eboks.app.presentation.ui.components.message.folderinfo.FolderInfoComponentPresenter
import dk.eboks.app.presentation.ui.components.message.header.HeaderComponentContract
import dk.eboks.app.presentation.ui.components.message.header.HeaderComponentPresenter
import dk.eboks.app.presentation.ui.components.message.locked.LockedMessageComponentContract
import dk.eboks.app.presentation.ui.components.message.locked.LockedMessageComponentPresenter
import dk.eboks.app.presentation.ui.components.message.notes.NotesComponentContract
import dk.eboks.app.presentation.ui.components.message.notes.NotesComponentPresenter
import dk.eboks.app.presentation.ui.components.message.privatesender.PrivateSenderWarningComponentContract
import dk.eboks.app.presentation.ui.components.message.privatesender.PrivateSenderWarningComponentPresenter
import dk.eboks.app.presentation.ui.components.message.protectedmessage.ProtectedMessageComponentContract
import dk.eboks.app.presentation.ui.components.message.protectedmessage.ProtectedMessageComponentPresenter
import dk.eboks.app.presentation.ui.components.message.share.ShareComponentContract
import dk.eboks.app.presentation.ui.components.message.share.ShareComponentPresenter
import dk.eboks.app.presentation.ui.components.message.viewers.html.HtmlViewComponentContract
import dk.eboks.app.presentation.ui.components.message.viewers.html.HtmlViewComponentPresenter
import dk.eboks.app.presentation.ui.components.message.viewers.image.ImageViewComponentContract
import dk.eboks.app.presentation.ui.components.message.viewers.image.ImageViewComponentPresenter
import dk.eboks.app.presentation.ui.components.message.viewers.pdf.PdfViewComponentContract
import dk.eboks.app.presentation.ui.components.message.viewers.pdf.PdfViewComponentPresenter
import dk.eboks.app.presentation.ui.components.message.viewers.text.TextViewComponentContract
import dk.eboks.app.presentation.ui.components.message.viewers.text.TextViewComponentPresenter
import dk.eboks.app.presentation.ui.components.navigation.NavBarComponentContract
import dk.eboks.app.presentation.ui.components.navigation.NavBarComponentPresenter
import dk.eboks.app.presentation.ui.components.senders.SenderListComponentContract
import dk.eboks.app.presentation.ui.components.senders.SenderListComponentPresenter
import dk.eboks.app.presentation.ui.screens.channels.ChannelsContract
import dk.eboks.app.presentation.ui.screens.channels.ChannelsPresenter
import dk.eboks.app.presentation.ui.screens.message.opening.MessageOpeningContract
import dk.eboks.app.presentation.ui.screens.message.opening.MessageOpeningPresenter
import dk.eboks.app.presentation.ui.screens.senders.overview.SendersOverviewContract
import dk.eboks.app.presentation.ui.screens.senders.overview.SendersOverviewPresenter
import dk.eboks.app.presentation.ui.screens.splash.SplashContract
import dk.eboks.app.presentation.ui.screens.splash.SplashPresenter
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.injection.scopes.ActivityScope

/**
 * Created by bison on 07/12/17.
 */

@Module
class PresentationModule {
    @ActivityScope
    @Provides
    fun providePastaPresenter(appState: AppStateManager) : PastaContract.Presenter {
        return PastaPresenter(appState)
    }

    @ActivityScope
    @Provides
    fun provideSplashPresenter(bootstrapInteractor: BootstrapInteractor, loginInteractor: LoginInteractor) : SplashContract.Presenter {
        return SplashPresenter(bootstrapInteractor, loginInteractor)
    }

    @ActivityScope
    @Provides
    fun provideMailOverviewPresenter(stateManager: AppStateManager) : MailOverviewContract.Presenter {
        return MailOverviewPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideMailListPresenter(appState: AppStateManager) : MailListContract.Presenter {
        return MailListPresenter(appState)
    }

    @ActivityScope
    @Provides
    fun provideFolderPresenter(appState: AppStateManager) : FolderContract.Presenter {
        return FolderPresenter(appState)
    }

    @ActivityScope
    @Provides
    fun provideMessagePresenter(appState: AppStateManager) : MessageContract.Presenter {
        return MessagePresenter(appState)
    }


    @ActivityScope
    @Provides
    fun provideChannelsPresenter(stateManager: AppStateManager) : ChannelsContract.Presenter {
        return ChannelsPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideMessageSheetPresenter(stateManager: AppStateManager) : MessageEmbeddedContract.Presenter {
        return MessageEmbeddedPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideMessageOpeningPresenter(stateManager: AppStateManager, executor: Executor) : MessageOpeningContract.Presenter {
        return MessageOpeningPresenter(stateManager, executor)
    }

    @ActivityScope
    @Provides
    fun provideHeaderComponentPresenter(stateManager: AppStateManager) : HeaderComponentContract.Presenter {
        return HeaderComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideNotesComponentPresenter(stateManager: AppStateManager) : NotesComponentContract.Presenter {
        return NotesComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideAttachmentsComponentPresenter(stateManager: AppStateManager, openAttachmentInteractor: OpenAttachmentInteractor, saveAttachmentInteractor: SaveAttachmentInteractor) : AttachmentsComponentContract.Presenter {
        return AttachmentsComponentPresenter(stateManager, openAttachmentInteractor, saveAttachmentInteractor)
    }

    @ActivityScope
    @Provides
    fun provideFolderInfoComponentPresenter(stateManager: AppStateManager) : FolderInfoComponentContract.Presenter {
        return FolderInfoComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideDocumentComponentPresenter(stateManager: AppStateManager) : DocumentComponentContract.Presenter {
        return DocumentComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun providePdfPreviewComponentPresenter(stateManager: AppStateManager) : PdfViewComponentContract.Presenter {
        return PdfViewComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideFoldersComponentPresenter(stateManager: AppStateManager, getFoldersInteractor: GetFoldersInteractor, openFolderInteractor: OpenFolderInteractor) : FoldersComponentContract.Presenter {
        return FoldersComponentPresenter(stateManager, getFoldersInteractor, openFolderInteractor)
    }

    @ActivityScope
    @Provides
    fun provideFolderShortcutsComponentPresenter(stateManager: AppStateManager, getCategoriesInteractor: GetCategoriesInteractor, openFolderInteractor: OpenFolderInteractor) : FolderShortcutsComponentContract.Presenter {
        return FolderShortcutsComponentPresenter(stateManager, getCategoriesInteractor, openFolderInteractor)
    }

    @ActivityScope
    @Provides
    fun provideSenderCarouselComponentPresenter(stateManager: AppStateManager, sendersInteractor: GetSendersInteractor) : SenderCarouselComponentContract.Presenter {
        return SenderCarouselComponentPresenter(stateManager, sendersInteractor)
    }

    @ActivityScope
    @Provides
    fun provideMailListComponentPresenter(stateManager: AppStateManager, getMessagesInteractor: GetMessagesInteractor, openMessageInteractor: OpenMessageInteractor) : MailListComponentContract.Presenter {
        return MailListComponentPresenter(stateManager, getMessagesInteractor, openMessageInteractor)
    }

    @ActivityScope
    @Provides
    fun provideNavBarComponentPresenter(stateManager: AppStateManager) : NavBarComponentContract.Presenter {
        return NavBarComponentPresenter(stateManager)
    }


    @ActivityScope
    @Provides
    fun provideHtmlViewComponentPresenter(stateManager: AppStateManager) : HtmlViewComponentContract.Presenter {
        return HtmlViewComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideImageViewComponentPresenter(stateManager: AppStateManager) : ImageViewComponentContract.Presenter {
        return ImageViewComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideTextViewComponentPresenter(stateManager: AppStateManager) : TextViewComponentContract.Presenter {
        return TextViewComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideLockedMessageComponentPresenter(stateManager: AppStateManager) : LockedMessageComponentContract.Presenter {
        return LockedMessageComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideProtectedMessageComponentPresenter(stateManager: AppStateManager) : ProtectedMessageComponentContract.Presenter {
        return ProtectedMessageComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun providePrivateSenderWarningComponentPresenter(stateManager: AppStateManager, executor: Executor) : PrivateSenderWarningComponentContract.Presenter {
        return PrivateSenderWarningComponentPresenter(stateManager, executor)
    }

    @ActivityScope
    @Provides
    fun provideChannelListComponentPresenter(stateManager: AppStateManager, getChannelsInteractor: GetChannelsInteractor) : ChannelListComponentContract.Presenter {
        return ChannelListComponentPresenter(stateManager, getChannelsInteractor)
    }

    @ActivityScope
    @Provides
    fun provideChannelDetailComponentPresenter(stateManager: AppStateManager) : ChannelDetailComponentContract.Presenter {
        return ChannelDetailComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideChannelSettingsPopUpComponentPresenter(stateManager: AppStateManager) : ChannelSettingsPopUpComponentContract.Presenter {
        return ChannelSettingsPopUpComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideShareComponentPresenter(stateManager: AppStateManager) : ShareComponentContract.Presenter {
        return ShareComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideSenderListComponentPresenter(stateManager: AppStateManager) : SenderListComponentContract.Presenter {
        return SenderListComponentPresenter(stateManager)
    }

    @ActivityScope
    @Provides
    fun provideSendersOverviewPresenter(stateManager: AppStateManager) : SendersOverviewContract.Presenter {
        return SendersOverviewPresenter(stateManager)
    }


    /* Pasta
    @ActivityScope
    @Provides
    fun provideComponentPresenter(stateManager: AppStateManager) : ComponentContract.Presenter {
        return ComponentPresenter(stateManager)
    }
    */
}