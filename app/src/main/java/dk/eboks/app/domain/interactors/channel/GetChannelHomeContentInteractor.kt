package dk.eboks.app.domain.interactors.channel

import dk.eboks.app.domain.models.channel.Channel
import dk.eboks.app.domain.models.home.HomeContent
import dk.eboks.app.domain.models.local.ViewError
import dk.nodes.arch.domain.interactor.Interactor

/**
 * Created by bison on 01/02/18.
 */
interface GetChannelHomeContentInteractor : Interactor {
    var output : Output?
    var input : Input?

    data class Input(val cached: Boolean = true)

    interface Output {
        fun onGetInstalledChannelList(channels : MutableList<Channel>)
        fun onGetInstalledChannelListError(error : ViewError)
        fun onGetChannelHomeContent(channel : Channel, content : HomeContent)
        fun onGetChannelHomeContentError(channel : Channel)
        fun onGetChannelHomeContentEmpty(channel : Channel)
        fun onGetChannelHomeContentDone()
        fun continueGetChannelHomeContent(): Boolean
    }
}