package dk.eboks.app.domain.interactors.message

import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.message.Message
import dk.nodes.arch.domain.interactor.Interactor

interface UpdateMessageInteractor : Interactor {
    var output: Output?
    var input: Input?

    data class Input(var message: Message)

    interface Output {
        fun onUpdateMessageSuccess()
        fun onUpdateMessageError(error: ViewError)
    }
}