package com.abdan.fp.uaspcs.ui.search.team

import android.app.Application
import androidx.lifecycle.*
import com.abdan.fp.uaspcs.data.repository.TeamRepository
import com.abdan.fp.uaspcs.model.TeamModel
import com.abdan.fp.uaspcs.utils.EspressoIdlingResource

class SearchTeamViewModel(application: Application) : AndroidViewModel(application) {

    private var _listSearchResultTeam = MutableLiveData<List<TeamModel>>()

    val searchTeamState = TeamRepository.getSearchTeamState()

    fun searchTeam(query: String) {
        EspressoIdlingResource.increment()
        _listSearchResultTeam.postValue(emptyList())
        _listSearchResultTeam = TeamRepository.searchTeamByTeamFromApi(query)

    }

    fun getListSearchTeam(): LiveData<List<TeamModel>>? = _listSearchResultTeam


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchTeamViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchTeamViewModel(
                    app
                ) as T
            }
            throw IllegalArgumentException("Cannot create ViewModel")
        }

    }

}