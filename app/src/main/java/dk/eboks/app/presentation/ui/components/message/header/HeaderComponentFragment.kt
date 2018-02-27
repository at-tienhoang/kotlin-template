package dk.eboks.app.presentation.ui.components.message.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dk.eboks.app.R
import dk.eboks.app.domain.models.Message
import dk.eboks.app.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_header_component.*
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class HeaderComponentFragment : BaseFragment(), HeaderComponentContract.View {
    @Inject
    lateinit var presenter : HeaderComponentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_header_component, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        arguments?.let { args->
            if(args.getBoolean("show_divider", false))
            dividerV.visibility = View.VISIBLE
        }
    }

    override fun setupTranslations() {
    }

    override fun updateView(message: Message) {
        if(message.recipient != null)
        {

        }
        if(message.recipient != null && message.sender != null)
        {

        }
        senderTv.text = message.sender?.name ?: ""
        titleTv.text = message.name
        message.sender?.logo.let {
            Glide.with(context).load(it).into(senderLogoIv)
        }
    }
}