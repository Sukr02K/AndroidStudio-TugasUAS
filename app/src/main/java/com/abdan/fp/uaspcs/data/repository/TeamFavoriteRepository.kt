package com.abdan.fp.uaspcs.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.abdan.fp.uaspcs.data.db.dao.TeamFavoriteDatabase
import com.abdan.fp.uaspcs.data.db.entity.convertToTeamFavoriteModel
import com.abdan.fp.uaspcs.model.TeamFavoriteModel
import com.abdan.fp.uaspcs.model.TeamModel
import com.abdan.fp.uaspcs.utils.Helper.convertTeamFavoriteModelToTeamFavoriteEntity
import com.abdan.fp.uaspcs.utils.Helper.convertTeamModelToTeamFavoriteModel

class TeamFavoriteRepository(private val database: TeamFavoriteDatabase) {

    companion object {
        private val TAG = TeamFavoriteRepository::class.java.simpleName
    }

    val favoriteTeamList: LiveData<List<TeamFavoriteModel>>? =
        Transformations.map(database.favoriteTeamDao.getTeamFavoriteList()) {
            it.convertToTeamFavoriteModel()
        }

    fun validateTeam(idTeam: Int): Int {
        return database.favoriteTeamDao.validateTeamFavorite(idTeam)
    }

    fun removeTeamFavorite(idTeam: Int) {
        database.favoriteTeamDao.deleteTeamFavorite(idTeam)
        Log.d(TAG, "Successfully removed team with id : $idTeam from favorite")
    }

    fun addToFavoriteTeam(team: TeamModel) {
        val favoriteTeam = convertTeamModelToTeamFavoriteModel(team)
        val data = convertTeamFavoriteModelToTeamFavoriteEntity(favoriteTeam)

        database.favoriteTeamDao.insertTeamFavorite(data)

        Log.d(TAG, "Successfully added team ${data.idTeam} to favorite")
    }

}