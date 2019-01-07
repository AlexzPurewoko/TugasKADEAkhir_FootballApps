package com.apwdevs.apps.football.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.apwdevs.apps.football.utility.ParameterClass
import org.jetbrains.anko.db.*

class FavoriteDatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, ParameterClass.DB_NAME, null, 1) {

    companion object {
        private var instance: FavoriteDatabaseHelper? = null
        fun getInstance(ctx: Context): FavoriteDatabaseHelper {
            if (instance == null) {
                instance = FavoriteDatabaseHelper(ctx)
            }
            return instance as FavoriteDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        ////// MATCH FAVORITE \\\\\\
        db.createTable(
            MatchFavoriteData.TABLE_MATCH_FAVORITE, true,
            MatchFavoriteData.ID_UNIQUE to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavoriteData.ID_LEAGUE to TEXT,
            MatchFavoriteData.ID_EVENT to TEXT,
            MatchFavoriteData.LEAGUE_NAME to TEXT,
            MatchFavoriteData.DATE_EVENT to TEXT,
            MatchFavoriteData.TIME_EVENT to TEXT,
            MatchFavoriteData.HOME_TEAM to TEXT,
            MatchFavoriteData.HOME_SCORE to INTEGER,
            MatchFavoriteData.AWAY_TEAM to TEXT,
            MatchFavoriteData.AWAY_SCORE to INTEGER
        )

        ////// TEAM FAVORITE \\\\\\
        db.createTable(
            TeamFavoriteData.TABLE_TEAM_FAVORITE, true,
            TeamFavoriteData.ID_UNIQUE to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavoriteData.ID_TEAM to TEXT,
            TeamFavoriteData.TEAM_NAME to TEXT,
            TeamFavoriteData.TEAM_LOGO to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavoriteData.TABLE_MATCH_FAVORITE, true)
        db.dropTable(TeamFavoriteData.TABLE_TEAM_FAVORITE, true)
    }
}

val Context.database: FavoriteDatabaseHelper
    get() = FavoriteDatabaseHelper.getInstance(applicationContext)