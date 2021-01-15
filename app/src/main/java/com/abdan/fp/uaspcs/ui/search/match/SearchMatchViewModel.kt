package com.abdan.fp.uaspcs.ui.search.match

import android.app.Application
import androidx.lifecycle.*
import com.abdan.fp.uaspcs.data.repository.MatchRepository
import com.abdan.fp.uaspcs.model.MatchModel
import com.abdan.fp.uaspcs.utils.EspressoIdlingResource

class SearchMatchViewModel(application: Application) : AndroidViewModel(application) {

    private var _listSearchResultMatch = MutableLiveData<List<MatchModel>>()

    val searchMatchState = MatchRepository.getSearchMatchState()

    fun searchMatch(query: String) {
        EspressoIdlingResource.increment()
        _listSearchResultMatch.postValue(emptyList())
        _listSearchResultMatch = MatchRepository.searchMatchByTeamFromApi(query)
    }

    fun getListSearchMatch(): LiveData<List<MatchModel>>? = _listSearchResultMatch


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchMatchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchMatchViewModel(
                    app
                ) as T
            }
            throw IllegalArgumentException("Cannot create ViewModel")
        }

    }

}