package com.apwdevs.apps.football.activities.detailPlayer

import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.util.LruCache
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayersDetailData
import com.apwdevs.apps.football.activities.detailPlayer.presenter.PlayerDetailsPresenter
import com.apwdevs.apps.football.activities.detailPlayer.ui.PlayerDetailsModel
import com.apwdevs.apps.football.activities.detailTeams.dataController.DetailRecyclerData
import com.apwdevs.apps.football.activities.detailTeams.fragments.adapter.DetailTeamRA
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.DialogShowHelper
import com.apwdevs.apps.football.utility.ParameterClass
import com.apwdevs.apps.football.utility.gone
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_player_details.*
import kotlinx.android.synthetic.main.content_player_details.*
import org.jetbrains.anko.alert

class PlayerDetails : AppCompatActivity(), PlayerDetailsModel {

    private val TIME_UPDATE_IMAGE_FANART: Long = 6000
    private lateinit var playerImage: ImageView
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerPosition: TextView
    private lateinit var playerDescription: TextView
    private lateinit var loadingFanArtHolder: LinearLayout
    private lateinit var moreDetailsRecycler: RecyclerView
    private lateinit var recyclerAdapter: DetailTeamRA

    private lateinit var leagues: List<TeamLeagueData>
    private lateinit var playerId: String
    private lateinit var teamId: String

    private var memCache: LruCache<Int, Bitmap>? = null
    private var currentPos: Int = 0
    private var isFirstLaunchHandler: Boolean = true
    private lateinit var imageSize: Point

    private val dialog: DialogShowHelper = DialogShowHelper(this)
    private lateinit var presenter: PlayerDetailsPresenter
    private val targetB1 = object : TargetHolder() {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapLoaded(bitmap)
        }
    }
    private val targetB2 = object : TargetHolder() {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapLoaded(bitmap)
        }
    }
    private val targetB3 = object : TargetHolder() {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapLoaded(bitmap)
        }
    }
    private val targetB4 = object : TargetHolder() {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapLoaded(bitmap)
        }
    }

    private var mImageUpdaterHandler: Handler? = null
    private var mImageUpdater: Runnable? = null

    private fun setHandlers() {
        if (mImageUpdaterHandler == null) {
            mImageUpdater = Runnable {
                if (memCache != null && memCache?.size()!! > 0) {
                    playerImage.post {
                        playerImage.setImageBitmap(memCache?.get(currentPos))
                    }
                    if (++currentPos >= memCache?.size()!!)
                        currentPos = 0
                }
                clearHandlers(mImageUpdater!!)
                setHandlers()
            }

            mImageUpdaterHandler = Handler(Looper.getMainLooper())
            mImageUpdaterHandler?.postDelayed(
                mImageUpdater,
                if (isFirstLaunchHandler) {
                    1500
                } else
                    TIME_UPDATE_IMAGE_FANART
            )
        }
    }

    private fun clearHandlers(mRunnable: Runnable) {
        mImageUpdaterHandler?.removeCallbacks(mRunnable)
        mImageUpdaterHandler = null
        mImageUpdater = null
        System.gc()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        setSupportActionBar(player_details_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dialog.buildLoadingLayout()
        initializeMemberLayout()
        val apiRepository = ApiRepository()
        val gson = Gson()

        leagues = intent.getSerializableExtra(ParameterClass.LIST_LEAGUE_DATA) as List<TeamLeagueData>
        teamId = intent.getStringExtra(ParameterClass.ID_SELECTED_TEAMS)
        playerId = intent.getStringExtra(ParameterClass.ID_SELECTED_PLAYERS)

        presenter = PlayerDetailsPresenter(this, apiRepository, this, gson)
        presenter.getPlayerDetails(playerId)
    }

    private fun initializeMemberLayout() {
        playerImage = player_details_imagephotos
        playerHeight = player_details_height
        playerWeight = player_details_weight
        playerPosition = player_details_player_position
        playerDescription = player_details_playerdetails
        moreDetailsRecycler = player_details_adapter
        loadingFanArtHolder = player_details_loadingfanart
    }

    override fun showLoading() {
        dialog.showDialog()
    }

    override fun hideLoading() {
        dialog.stopDialog()
    }

    override fun onDataNotLoaded(msgWhat: String) {
        alert(msgWhat, "Error :(") {
            iconResource = R.drawable.ic_report_problem
            negativeButton("Quit") {
                it.dismiss()
                this@PlayerDetails.finish()
                //val pid = Process.myPid()
                //Handler(Looper.getMainLooper()).postDelayed({ Process.killProcess(pid) }, 1200)
            }
        }.show()
    }

    override fun onDataLoadFinished(playerData: PlayersDetailData, recyclerData: MutableList<DetailRecyclerData>) {
        Picasso.get().load(playerData.strFanart1).into(targetB1)
        Picasso.get().load(playerData.strFanart2).into(targetB2)
        Picasso.get().load(playerData.strFanart3).into(targetB3)
        Picasso.get().load(playerData.strFanart4).into(targetB4)
        playerWeight.text = playerData.playerWeight
        playerHeight.text = playerData.playerHeight
        playerPosition.text = playerData.playerPosition
        playerDescription.text = playerData.playerDescription

        recyclerAdapter = DetailTeamRA(recyclerData)
        moreDetailsRecycler.layoutManager = LinearLayoutManager(this)
        moreDetailsRecycler.adapter = recyclerAdapter
        supportActionBar?.title = playerData.playerName
    }

    override fun onBackPressed() {
        /*startActivity(
            intentFor<AboutTeams>(
                ParameterClass.ID_SELECTED_TEAMS to teamId,
                ParameterClass.LIST_LEAGUE_DATA to leagues
            ).clearTop().clearTask()
        )*/
        finish()
    }

    fun bitmapLoaded(bitmap: Bitmap?) {
        if (bitmap == null) return
        if (memCache == null) {
            val bitmapHeight = bitmap.height
            val bitmapWidth = bitmap.width
            val sizeScreen = Point()
            val imgSize = Point()
            windowManager.defaultDisplay.getSize(sizeScreen)
            val actb = supportActionBar?.height!!

            // building size
            val factor = bitmapWidth.toFloat() / sizeScreen.x.toFloat()
            imgSize.x = sizeScreen.x
            imgSize.y = (bitmapHeight.toFloat() / Math.round(factor)).toInt() + actb
            imageSize = imgSize

            memCache = LruCache(imgSize.x * imgSize.y * 4)
            loadingFanArtHolder.gone()
            setHandlers()
            isFirstLaunchHandler = false
        }

        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageSize.x, imageSize.y, false)
        memCache?.put(
            if (memCache?.size()!! - 1 == -1)
                0
            else
                memCache?.size()!!,
            scaledBitmap
        )

    }

    override fun onDestroy() {
        if (memCache != null) {
            if (mImageUpdater != null) clearHandlers(mImageUpdater!!)
            var i = 0
            while (i < memCache?.size()!!)
                memCache?.get(i++)!!.recycle()
            memCache?.evictAll()
            memCache = null
            System.gc()
        }
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    abstract class TargetHolder : Target {
        override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {

        }

        abstract override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?)

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }
    }
}
