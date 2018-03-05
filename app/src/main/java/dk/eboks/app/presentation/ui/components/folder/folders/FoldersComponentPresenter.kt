package dk.eboks.app.presentation.ui.components.folder.folders

import dk.eboks.app.domain.interactors.folder.GetFoldersInteractor
import dk.eboks.app.domain.interactors.folder.OpenFolderInteractor
import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.Folder
import dk.eboks.app.domain.models.FolderType
import dk.nodes.arch.presentation.base.BasePresenterImpl
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class FoldersComponentPresenter @Inject constructor(val appState: AppStateManager, val getFoldersInteractor: GetFoldersInteractor, val openFolderInteractor: OpenFolderInteractor) :
        FoldersComponentContract.Presenter,
        BasePresenterImpl<FoldersComponentContract.View>(),
        GetFoldersInteractor.Output,
        OpenFolderInteractor.Output {


    init {
        openFolderInteractor.output = this
        getFoldersInteractor.output = this
        runAction { v -> v.showProgress(true) }
        refresh()
    }

    override fun refresh() {
        getFoldersInteractor.input = GetFoldersInteractor.Input(false)
        getFoldersInteractor.run()
    }

    override fun openFolder(folder: Folder) {
        openFolderInteractor.input = OpenFolderInteractor.Input(folder)
        openFolderInteractor.run()
    }

    override fun onGetFolders(folders: List<Folder>) {
        runAction { v ->
            v.showProgress(false)
            v.showUserFolders(folders)
            v.showRefreshProgress(false)
        }

    }

    override fun onGetSystemFolders(folders: List<Folder>) {
        //Timber.e("system folders $folders")
        var proccessedList = processFolders(folders)
        runAction { v -> v.showSystemFolders(proccessedList) }
    }

    private fun processFolders(folders: List<Folder>): List<Folder> {
            // only showing the following folders:
            // "inbox","Archive","Draft","Sent","Deleted"
        val proccessedList: MutableList<Folder> = mutableListOf<Folder>(Folder(), Folder(), Folder(), Folder(), Folder())
        for (folder in folders) {
            if (folder.type.equals(FolderType.INBOX)) {
                proccessedList.removeAt(0)
                proccessedList.add(0, folder)
            }
            if (folder.type.equals(FolderType.ARCHIVE)) {
                proccessedList.removeAt(1)
                proccessedList.add(1, folder)
            }
            if (folder.type.equals(FolderType.DRAFTS)) {
                proccessedList.removeAt(2)
                proccessedList.add(2, folder)
            }
            if (folder.type.equals(FolderType.SENT)) {
                proccessedList.removeAt(3)
                proccessedList.add(3, folder)
            }
            if (folder.type.equals(FolderType.DELETED)) {
                proccessedList.removeAt(4)
                proccessedList.add(4, folder)
            }
        }
        return proccessedList
    }

    override fun onGetFoldersError(msg: String) {
        Timber.e(msg)
        runAction { v ->
            v.showProgress(false)
            v.showRefreshProgress(false)
        }

    }

    override fun onOpenFolderDone() {

    }

    override fun onOpenFolderError(msg: String) {
        Timber.e(msg)
    }
}