package com.abdan.fp.uaspcs.ui.favorite.content.match

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdan.fp.uaspcs.data.db.dao.getMatchFavoriteDatabase
import com.abdan.fp.uaspcs.data.repository.MatchFavoriteRepository

class FavoriteMatchViewModel(application: Application): AndroidViewModel(application){

    private val favoriteMatchFavoriteRepository =
        MatchFavoriteRepository(getMatchFavoriteDatabase(application))

    val favoriteMatch = favoriteMatchFavoriteRepository.favoriteMatchList

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteMatchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoriteMatchViewModel(
                    app
                ) as T
            }
            throw IllegalArgumentException("Cannot create ViewModel")
        }

    }

}