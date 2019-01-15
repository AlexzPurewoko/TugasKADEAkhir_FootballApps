package com.apwdevs.apps.football.activities.detailMatchs.presenter

import com.apwdevs.apps.football.activities.detailMatchs.dataController.DetailMatchDataClass
import com.apwdevs.apps.football.activities.detailMatchs.dataController.DetailMatchResponse
import com.apwdevs.apps.football.activities.detailMatchs.dataController.TeamPropData

object DetailMatchPresenterImpl {

    private val listMatch: MutableList<DetailMatchDataCls> = mutableListOf()
    private val listTeamInLeague: MutableList<TeamPropData> = mutableListOf()

    init {
        listMatch.clear()
        listTeamInLeague.clear()
        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133613",
                    strTeam = "Manchester City",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/vwpvry1467462651.png"
                ),
                TeamPropData(
                    idTeam = "133602",
                    strTeam = "Liverpool",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/uvxuqq1448813372.png"
                )
            )

        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "576675",
                data = DetailMatchDataClass(
                    dateEvent = "2019-01-03",
                    timeEvent = "20:00:00+00:00",
                    //// id team
                    idHomeTeam = "133613",
                    idAwayTeam = "133602",
                    //// goals
                    intHomeScore = "2",
                    intAwayScore = "1",
                    //// shots
                    intHomeShots = "4",
                    intAwayShots = "5",
                    //// team_name
                    strHomeTeam = "Man City",
                    strAwayTeam = "Liverpool",
                    //// Goal Details
                    strHomeGoalDetails = "41':Sergio Aguero;72':Leroy Sane;",
                    strAwayGoalDetails = "64':Roberto Firmino;",
                    //// Red Cards
                    strHomeRedCards = "",
                    strAwayRedCards = "",
                    //// Yellow Cards
                    strHomeYellowCards = "31':Vincent Kompany;45':Aymeric Laporte;89':Bernardo Silva;90':Ederson Moraes;",
                    strAwayYellowCards = "20':Dejan Lovren;38':Georginio Wijnaldum;",

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = "Ederson Moraes; ",
                    strAwayLineupGoalkeeper = "Alisson Becker; ",
                    //// Defense
                    strHomeLineupDefense = "Kyle Walker; John Stones; Vincent Kompany; Aymeric Laporte; ",
                    strAwayLineupDefense = "Trent Alexander-Arnold; Dejan Lovren; Virgil van Dijk; Andrew Robertson; ",
                    //// Mildfield
                    strHomeLineupMidfield = "Bernardo Silva; Fernandinho; David Silva; ",
                    strAwayLineupMidfield = "Georginio Wijnaldum; Jordan Henderson; Naby Keita; ",
                    //// Forward
                    strHomeLineupForward = "Raheem Sterling; Sergio Aguero; Leroy Sane; ",
                    strAwayLineupForward = "Mohamed Salah; Roberto Firmino; Sadio Mane; ",
                    //// Subtitues
                    strHomeLineupSubstitutes = "Arijanet Muric; Kyle Walker; Ilkay Guendogan; Kevin De Bruyne; Riyad Mahrez; Nicolas Otamendi; Gabriel Jesus; ",
                    strAwayLineupSubstitutes = "Simon Mignolet; Fabinho; Naby Keita; Alberto Moreno; Daniel Sturridge; Adam Lallana; Xherdan Shaqiri; "
                )
            )
        )

        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133636",
                    strTeam = "West Ham",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/yutyxs1467459956.png"
                ),
                TeamPropData(
                    idTeam = "133619",
                    strTeam = "Brighton",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/ywypts1448810904.png"
                )
            )

        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "576679",
                data = DetailMatchDataClass(
                    dateEvent = "2019-01-02",
                    timeEvent = "19:45:00+00:00",
                    //// id team
                    idHomeTeam = "133636",
                    idAwayTeam = "133619",
                    //// goals
                    intHomeScore = "2",
                    intAwayScore = "0",
                    //// shots
                    intHomeShots = "6",
                    intAwayShots = "6",
                    //// team_name
                    strHomeTeam = "West Ham",
                    strAwayTeam = "Brighton",
                    //// Goal Details
                    strHomeGoalDetails = "65':Marko Arnautovic;68':Marko Arnautovic;",
                    strAwayGoalDetails = "56':Dale Stephens;58':Shane Duffy;",
                    //// Red Cards
                    strHomeRedCards = "",
                    strAwayRedCards = "",
                    //// Yellow Cards
                    strHomeYellowCards = "31':Vincent Kompany;45':Aymeric Laporte;89':Bernardo Silva;90':Ederson Moraes;",
                    strAwayYellowCards = "80':Solly March;",

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = "Lukasz Fabianski; ",
                    strAwayLineupGoalkeeper = "David Button; ",
                    //// Defense
                    strHomeLineupDefense = "Michail Antonio; Angelo Ogbonna; Issa Diop; Aaron Cresswell; ",
                    strAwayLineupDefense = "Martin Montoya; Lewis Dunk; Shane Duffy; Bernardo; ",
                    //// Mildfield
                    strHomeLineupMidfield = "Bernardo Silva; Fernandinho; David Silva; ",
                    strAwayLineupMidfield = "Georginio Wijnaldum; Jordan Henderson; Naby Keita; ",
                    //// Forward
                    strHomeLineupForward = "Lucas Perez; Marko Arnautovic; ",
                    strAwayLineupForward = "Glenn Murray; ",
                    //// Subtitues
                    strHomeLineupSubstitutes = "Adrian; Arthur Masuaku; Mark Noble; Samir Nasri; Grady Diangana; Lucas Perez; Michail Antonio; ",
                    strAwayLineupSubstitutes = "Jason Steele; Gaetan Bong; Leon Balogun; Beram Kayal; Yves Bissouma; Anthony Knockaert; Florin Andone; "

                )
            )
        )

        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133626",
                    strTeam = "Leicester",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xtxwtu1448813356.png"
                ),
                TeamPropData(
                    idTeam = "134778",
                    strTeam = "Southampton",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/qusxss1448813481.png"
                )
            )
        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "576681",
                data = DetailMatchDataClass(
                    dateEvent = "2019-01-02",
                    timeEvent = "15:00:00+00:00",
                    //// id team
                    idHomeTeam = "133626",
                    idAwayTeam = "134778",
                    //// goals
                    intHomeScore = null,
                    intAwayScore = null,
                    //// shots
                    intHomeShots = null,
                    intAwayShots = null,
                    //// team_name
                    strHomeTeam = "Leicester",
                    strAwayTeam = "Southampton",
                    //// Goal Details
                    strHomeGoalDetails = null,
                    strAwayGoalDetails = null,
                    //// Red Cards
                    strHomeRedCards = null,
                    strAwayRedCards = null,
                    //// Yellow Cards
                    strHomeYellowCards = null,
                    strAwayYellowCards = null,

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = null,
                    strAwayLineupGoalkeeper = null,
                    //// Defense
                    strHomeLineupDefense = null,
                    strAwayLineupDefense = null,
                    //// Mildfield
                    strHomeLineupMidfield = null,
                    strAwayLineupMidfield = null,
                    //// Forward
                    strHomeLineupForward = null,
                    strAwayLineupForward = null,
                    //// Subtitues
                    strHomeLineupSubstitutes = null,
                    strAwayLineupSubstitutes = null

                )
            )
        )

        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133623",
                    strTeam = "Burnley",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/sqrttx1448811003.png"
                ),
                TeamPropData(
                    idTeam = "133600",
                    strTeam = "Fulham",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xwwvyt1448811086.png"
                )
            )
        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "576684",
                data = DetailMatchDataClass(
                    dateEvent = "2019-01-02",
                    timeEvent = "15:00:00+00:00",
                    //// id team
                    idHomeTeam = "133623",
                    idAwayTeam = "133600",
                    //// goals
                    intHomeScore = null,
                    intAwayScore = null,
                    //// shots
                    intHomeShots = null,
                    intAwayShots = null,
                    //// team_name
                    strHomeTeam = "Burnley",
                    strAwayTeam = "Fulham",
                    //// Goal Details
                    strHomeGoalDetails = null,
                    strAwayGoalDetails = null,
                    //// Red Cards
                    strHomeRedCards = null,
                    strAwayRedCards = null,
                    //// Yellow Cards
                    strHomeYellowCards = null,
                    strAwayYellowCards = null,

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = null,
                    strAwayLineupGoalkeeper = null,
                    //// Defense
                    strHomeLineupDefense = null,
                    strAwayLineupDefense = null,
                    //// Mildfield
                    strHomeLineupMidfield = null,
                    strAwayLineupMidfield = null,
                    //// Forward
                    strHomeLineupForward = null,
                    strAwayLineupForward = null,
                    //// Subtitues
                    strHomeLineupSubstitutes = null,
                    strAwayLineupSubstitutes = null

                )
            )
        )

        // italian serie a
        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133695",
                    strTeam = "Empoli",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/uuwtyx1448806449.png"
                ),
                TeamPropData(
                    idTeam = "133681",
                    strTeam = "Inter",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xqyvuy1448806439.png"
                )
            )
        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "582235",
                data = DetailMatchDataClass(
                    dateEvent = "2018-12-29",
                    timeEvent = "14:00:00+00:00",
                    //// id team
                    idHomeTeam = "133695",
                    idAwayTeam = "133681",
                    //// goals
                    intHomeScore = "0",
                    intAwayScore = "1",
                    //// shots
                    intHomeShots = "3",
                    intAwayShots = "6",
                    //// team_name
                    strHomeTeam = "Empoli",
                    strAwayTeam = "Inter",
                    //// Goal Details
                    strHomeGoalDetails = "",
                    strAwayGoalDetails = "",
                    //// Red Cards
                    strHomeRedCards = "",
                    strAwayRedCards = "",
                    //// Yellow Cards
                    strHomeYellowCards = "74':Ismael Bennacer;",
                    strAwayYellowCards = "68':Lautaro Martinez;",

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = "Ivan Provedel; ",
                    strAwayLineupGoalkeeper = "Samir Handanovic; ",
                    //// Defense
                    strHomeLineupDefense = "Frederic Veseli; Matias Silvestre; Jacob Rasmussen; Manuel Pasqual; ",
                    strAwayLineupDefense = "Sime Vrsaljko; Milan Skriniar; Stefan de Vrij; Kwadwo Asamoah; ",
                    //// Mildfield
                    strHomeLineupMidfield = "Afriyie Acquah; Ismael Bennacer; Hamed Traore; Miha Zajc; ",
                    strAwayLineupMidfield = "Matias Vecino; Joao Mario; Matteo Politano; Radja Nainggolan; Ivan Perisic; ",
                    //// Forward
                    strHomeLineupForward = "Francesco Caputo; Antonino La Gumina; ",
                    strAwayLineupForward = "Mauro Icardi; ",
                    //// Subtitues
                    strHomeLineupSubstitutes = "Pietro Terracciano; Andrea Fulignati; Matteo Brighi; Miha Zajc; Levan Mchedlidze; Alejandro Rodriguez; Luca Antonelli; Arnel Jakupovic; Leonardo Capezzi; Michal Marcjanik; ",
                    strAwayLineupSubstitutes = "Daniele Padelli; Roberto Gagliardini; Lautaro Martinez; Andrea Ranocchia; Radja Nainggolan; Matteo Politano; Miranda; Dalbert; Danilo D'Ambrosio; Antonio Candreva; "

                )
            )
        )

        listTeamInLeague.addAll(
            mutableListOf(
                TeamPropData(
                    idTeam = "133682",
                    strTeam = "Roma",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xqpqpq1448806641.png"
                ),
                TeamPropData(
                    idTeam = "133687",
                    strTeam = "Torino",
                    strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xxprty1448806802.png"
                )
            )
        )
        listMatch.add(
            DetailMatchDataCls(
                eventId = "582239",
                data = DetailMatchDataClass(
                    dateEvent = "2019-01-19",
                    timeEvent = "14:00:00+00:00",
                    //// id team
                    idHomeTeam = "133682",
                    idAwayTeam = "133687",
                    //// goals
                    intHomeScore = null,
                    intAwayScore = null,
                    //// shots
                    intHomeShots = null,
                    intAwayShots = null,
                    //// team_name
                    strHomeTeam = "Roma",
                    strAwayTeam = "Torino",
                    //// Goal Details
                    strHomeGoalDetails = null,
                    strAwayGoalDetails = null,
                    //// Red Cards
                    strHomeRedCards = null,
                    strAwayRedCards = null,
                    //// Yellow Cards
                    strHomeYellowCards = null,
                    strAwayYellowCards = null,

                    /////////////
                    // lineups
                    //// Goalkeeper
                    strHomeLineupGoalkeeper = null,
                    strAwayLineupGoalkeeper = null,
                    //// Defense
                    strHomeLineupDefense = null,
                    strAwayLineupDefense = null,
                    //// Mildfield
                    strHomeLineupMidfield = null,
                    strAwayLineupMidfield = null,
                    //// Forward
                    strHomeLineupForward = null,
                    strAwayLineupForward = null,
                    //// Subtitues
                    strHomeLineupSubstitutes = null,
                    strAwayLineupSubstitutes = null

                )
            )
        )
    }

    fun getTeamDataInMatch(matchResponse: DetailMatchResponse): MutableList<TeamPropData> {
        val match = matchResponse.events[0]
        val homeTeamId = match.idHomeTeam
        val awayTeamId = match.idAwayTeam

        var homeData: TeamPropData? = null
        for (team in listTeamInLeague) {
            if (team.idTeam.equals(homeTeamId))
                homeData = team
        }

        var awayData: TeamPropData? = null
        for (team in listTeamInLeague) {
            if (team.idTeam.equals(awayTeamId))
                awayData = team
        }
        return mutableListOf(
            homeData!!,
            awayData!!
        )
    }

    fun getData(eventId: String): DetailMatchResponse? {
        for (match in listMatch) {
            if (match.eventId.equals(eventId)) {
                return DetailMatchResponse(listOf(match.data))
            }
        }
        return null
    }
}

data class DetailMatchDataCls(
    val eventId: String,
    val data: DetailMatchDataClass
)