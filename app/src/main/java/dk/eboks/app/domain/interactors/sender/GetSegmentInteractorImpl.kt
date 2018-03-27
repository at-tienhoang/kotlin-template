package dk.eboks.app.domain.interactors.sender

import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor

/**
 * Created by bison on 01/02/18.
 * @author   bison
 * @since    01/02/18.
 */
class GetSegmentInteractorImpl(executor: Executor, val api: Api) : BaseInteractor(executor), GetSegmentInteractor {

    override var output: GetSegmentInteractor.Output? = null
    override var input: GetSegmentInteractor.Input? = null

    override fun execute() {
        input?.id?.let {
            try {
                val result = api.getSegmentDetail(it).execute()
                result?.body()?.let {
                    runOnUIThread {
                        output?.onGetSegment(it)
                    }
                    return
                }
            } catch (t: Throwable) {
                runOnUIThread {
                    output?.onError(exceptionToViewError(t))
                }
            }
        }
        runOnUIThread {
            output?.onError(exceptionToViewError(Throwable("Unknown")))
        }
    }
}