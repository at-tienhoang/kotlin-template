package dk.nodes.template.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import dk.nodes.template.domain.interactors.PostsInteractor
import dk.nodes.template.presentation.extensions.Fail
import dk.nodes.template.presentation.extensions.Loading
import dk.nodes.template.presentation.extensions.Success
import dk.nodes.template.presentation.extensions.Uninitialized
import dk.nodes.template.presentation.extensions.asLiveData
import dk.nodes.template.presentation.nstack.Translation
import dk.nodes.template.presentation.ui.base.BaseViewModel
import dk.nodes.template.presentation.util.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    postsInteractor: PostsInteractor
) : BaseViewModel() {

    private val postsInteractor = postsInteractor.asLiveData()
    private val _viewState = MediatorLiveData<MainActivityViewState>()
    val viewState: LiveData<MainActivityViewState> = _viewState

    init {
        _viewState.addSource(Transformations.map(this.postsInteractor.liveData) {
            when (it) {
                is Success -> {
                    MainActivityViewState(posts = it.value)
                }
                is Loading -> {
                    MainActivityViewState(isLoading = true)
                }
                is Fail -> {
                    MainActivityViewState(
                        isLoading = false,
                        errorMessage = SingleEvent(Translation.error.unknownError)
                    )
                }
                is Uninitialized -> {
                    MainActivityViewState()
                }
            }
        }) {
            _viewState.postValue(it)
        }
    }

    fun fetchPosts() = scope.launch(Dispatchers.IO) { postsInteractor() }
}