package dk.eboks.app.domain.models.message

import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.domain.models.sender.Sender
import dk.eboks.app.domain.models.shared.Link
import dk.eboks.app.domain.models.shared.Status
import java.io.Serializable
import java.util.*

/**
 * Created by bison on 24-06-2017.
 */
data class Message(
        var id : String,
        var subject : String,
        var received : Date,
        var unread : Boolean,
        var sender: Sender? = null,
        var type: MessageType? = MessageType.RECEIVED,
        var recipient: Sender? = null,
        var folder : Folder? = null,
        var folderId : Int = 0,
        var content : Content? = null,
        var attachments : List<Content>? = null,
        var numberOfAttachments: Int = 0,
        var payment: Payment?,
        var sign: Sign?,
        var reply: Status?,
        var link : Link? = null,
        var status: Status? = null,
        var note : String = ""
) : Serializable