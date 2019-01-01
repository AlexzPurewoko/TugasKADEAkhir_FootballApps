package com.apwdevs.apps.football.activities.splash

import android.graphics.Color
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.apwdevs.apps.football.R
import com.apwdevs.apps.football.activities.home.HomeActivity
import com.apwdevs.apps.football.activities.splash.dataController.TeamLeagueData
import com.apwdevs.apps.football.activities.splash.presenter.SplashPresenter
import com.apwdevs.apps.football.activities.splash.ui.SplashModel
import com.apwdevs.apps.football.api.ApiRepository
import com.apwdevs.apps.football.utility.ParameterClass
import com.google.gson.Gson
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

class SplashScreenActivity : AppCompatActivity(), SplashModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val gson = Gson()
        val apiRepo = ApiRepository()
        val adapter = SplashPresenter(this, this, apiRepo, gson)
        adapter.getLeagueList()
        setTransparentColorBar()
    }

    private fun setTransparentColorBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#91000000")
        }
        window.setBackgroundDrawableResource(R.drawable.splash_football_match)
    }

    override fun onLoadingStarted() {

    }

    override fun onLoadFailed(msg: String) {
        alert(msg, "Football") {
            this.negativeButton("Okay") {
                it.dismiss()
                this@SplashScreenActivity.finish()
                val pid = Process.myPid()
                Handler(Looper.getMainLooper()).postDelayed({
                    Process.killProcess(pid)
                }, 2500)
            }
            this.iconResource = R.drawable.ic_report_problem
        }.show()
    }

    override fun onLoadingSuccesfully(leagues: List<TeamLeagueData>) {
        startActivity(intentFor<HomeActivity>(ParameterClass.LIST_LEAGUE_DATA to leagues).clearTop().clearTask())
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}