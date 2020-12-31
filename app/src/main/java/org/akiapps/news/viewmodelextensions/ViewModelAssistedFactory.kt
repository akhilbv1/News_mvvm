package org.akiapps.news.viewmodelextensions

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import org.akiapps.news.mainactivity.MainViewModel
import org.akiapps.news.repository.UserRepo
import javax.inject.Inject

interface ViewModelAssistedFactory<T:ViewModel> {
    fun create(handle: SavedStateHandle):T
}

class GenericSavedStateViewModelFactory<out V : ViewModel>(
        private val viewModelFactory: ViewModelAssistedFactory<V>,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
    ): T {
        return viewModelFactory.create(handle) as T
    }
}

