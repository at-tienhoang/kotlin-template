package dk.eboks.app.domain.repositories

import dk.eboks.app.domain.models.channel.Channel
import dk.eboks.app.domain.models.home.HomeContent

/**
 * Created by bison on 01/02/18.
 */
interface ChannelsRepository {
    fun getChannels(cached: Boolean = false): List<Channel>
    fun getPinnedChannels(cached: Boolean = false): List<Channel>
    fun getInstalledChannels(): List<Channel>
    fun getChannel(id: Int): Channel
    fun getChannelHomeContent(id: Long, cached: Boolean = false): HomeContent
    fun hasCachedChannelList(key: String): Boolean
    fun hasCachedChannelControl(key: Long): Boolean
}