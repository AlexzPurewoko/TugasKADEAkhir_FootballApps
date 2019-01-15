package com.apwdevs.apps.football.activities.detailPlayer.presenter

import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayerDetailsDataResponse
import com.apwdevs.apps.football.activities.detailPlayer.dataController.PlayersDetailData

object PlayerTeamsPresenterImpl {
    private val listPlayerDetails: MutableList<PlayersDetailData> = mutableListOf(
        // Arsenal
        PlayersDetailData(
            playerId = "34145411",
            playerName = "Aaron Ramsey",
            nationality = "Wales",
            playerTeam = "Arsenal",
            playerBorn = "1990-12-26",
            birthLocation = "Caerphilly, Wales",
            playerDescription = "Aaron James Ramsey /ˈræmzi/ (born 26 December 1990) is a Welsh footballer who plays as a midfielder for Arsenal and the Wales national football team. Ramsey mainly plays as a box-to-box midfielder, but has also been deployed on the left and right wings. He played as a schoolboy for Cardiff City, where he spent eight years in youth football, became the club's youngest ever first team player, and made 22 appearances for the senior team – including the 2008 FA Cup Final.\r\n\r\nRamsey moved to Arsenal in 2008 in a £5 million deal, where he quickly gained first team experience. However, his career stalled significantly after he suffered a broken leg in a match against Stoke City in February 2010. After two loan spells away from Arsenal, he returned to full fitness and re-established himself as a regular starter during the 2011–12 season. Ramsey was a key player for Arsenal in the 2013–14 season campaign, scoring 16 goals in all competitions, including the winner in the 2014 FA Cup Final.",
            playerGender = "Male",
            playerPosition = "Centre Midfielder",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/lm76w01544985585.jpg",
            playerHeight = "1.78",
            playerWeight = "69.84",


            facebook = "t.co/XIUOJkg9ch",
            website = "",
            twitter = "twitter.com/aaronramsey",
            instagram = "t.co/tgzBQFVzl4",
            youtube = "",

            strFanart1 = "https://www.thesportsdb.com/images/media/player/fanart/r0va621514551152.jpg",
            strFanart2 = "https://www.thesportsdb.com/images/media/player/fanart/sqwxxv1426214175.jpg",
            strFanart3 = "https://www.thesportsdb.com/images/media/player/fanart/v5mc061514551216.jpg",
            strFanart4 = "https://www.thesportsdb.com/images/media/player/fanart/hlsq281514551207.jpg"
        ),
        PlayersDetailData(
            playerId = "34145444",
            playerName = "Ainsley Maitland-Niles",
            nationality = "England",
            playerTeam = "Arsenal",
            playerBorn = "1997-08-29",
            birthLocation = "Goodmayes",
            playerDescription = "Ainsley Cory Maitland-Niles (born 29 August 1997) is an English professional footballer who plays as a winger, midfielder or right-back for Arsenal.",
            playerGender = "Male",
            playerPosition = "Midfielder",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/3775d71510853007.jpg",
            playerHeight = "1.77",
            playerWeight = "0",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = "https://www.thesportsdb.com/images/media/player/fanart/vrpsvw1432691066.jpg",
            strFanart2 = null,
            strFanart3 = null,
            strFanart4 = null
        ),
        // Chelsea
        PlayersDetailData(
            playerId = "34160535",
            playerName = "Álvaro Morata",
            nationality = "Spain",
            playerTeam = "Chelsea",
            playerBorn = "1992-10-23",
            birthLocation = "Madrid",
            playerDescription = "Álvaro Borja Morata Martín (Spanish pronunciation: ; born 23 October 1992) is a Spanish professional footballer who plays as a striker for Chelsea and the Spain national team.\r\n\r\nHe began his career at Real Madrid, making his debut with the senior team in late 2010 and going on to appear in 52 official games (11 goals), notably winning the 2014 Champions League. He moved to Juventus for €20 million in 2014, winning the domestic double of Serie A and Coppa Italia in both of his seasons before being bought back for €30 million.\r\n\r\nMorata earned 34 caps for Spain at youth level, helping the country win the 2013 European Under-21 Championship. He made his senior debut in 2014, representing the nation at the Euro 2016.",
            playerGender = "Male",
            playerPosition = "Forward",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/64gwb11510591004.jpg",
            playerHeight = "1.87",
            playerWeight = "82 Kg",


            facebook = "www.facebook.com/alvaromorata/",
            website = "",
            twitter = "twitter.com/AlvaroMorata",
            instagram = "www.instagram.com/alvaromorata",
            youtube = "",

            strFanart1 = "https://www.thesportsdb.com/images/media/player/fanart/vrpsvw1432691066.jpg",
            strFanart2 = "https://www.thesportsdb.com/images/media/player/fanart/sssyus1472681281.jpg",
            strFanart3 = "https://www.thesportsdb.com/images/media/player/fanart/vrvysq1472681198.jpg",
            strFanart4 = "https://www.thesportsdb.com/images/media/player/fanart/qspsty1472680857.jpg"
        ),
        PlayersDetailData(
            playerId = "34145498",
            playerName = "Cesar Azpilicueta",
            nationality = "Spain",
            playerTeam = "Chelsea",
            playerBorn = "1989-08-28",
            birthLocation = "Pamplona",
            playerDescription = "César Azpilicueta Tanco (Spanish pronunciation: ; born 28 August 1989) is a Spanish professional footballer who plays for English club Chelsea and the Spain national team primarily as a full-back but also as a centre-back.\r\n\r\nA youth product of Osasuna, he spent three seasons in La Liga before switching to Marseille, winning four major honours with the French club. In the summer of 2012 he moved to Chelsea, winning the Europa League in his first season and a domestic double two years later.\r\n\r\nAzpilicueta gained 55 caps for Spain at youth level in all age groups, and represented the under-21s in two European Championships, winning the 2011 edition. He made his first appearance with the full side in 2013, and was selected for the 2014 World Cup and Euro 2016.",
            playerGender = "Male",
            playerPosition = "Defender",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/x1lq5y1510590378.jpg",
            playerHeight = "1.73",
            playerWeight = "69.84",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = "https://www.thesportsdb.com/images/media/player/fanart/ypqrxw1431624860.jpg",
            strFanart2 = "https://www.thesportsdb.com/images/media/player/fanart/rqrtwr1431624872.jpg",
            strFanart3 = "https://www.thesportsdb.com/images/media/player/fanart/yvpvtq1431624887.jpg",
            strFanart4 = "https://www.thesportsdb.com/images/media/player/fanart/wsvpxp1431624902.jpg"
        ),
        //Atalanta
        PlayersDetailData(
            playerId = "34148374",
            playerName = "Andrea Masiello",
            nationality = "Italy",
            playerTeam = "Atalanta",
            playerBorn = "1986-02-05",
            birthLocation = "Viareggio, Italy",
            playerDescription = "Andrea Masiello (born 5 February 1986) is an Italian footballer, who plays as a right-back for Atalanta.",
            playerGender = "Male",
            playerPosition = "Defender",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/yxqzgr1546032328.jpg",
            playerHeight = "1.83",
            playerWeight = "73.47",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = null,
            strFanart2 = null,
            strFanart3 = null,
            strFanart4 = null
        ),
        PlayersDetailData(
            playerId = "34148386",
            playerName = "Alejandro Gomez",
            nationality = "Argentina",
            playerTeam = "Atalanta",
            playerBorn = "1988-02-15",
            birthLocation = "",
            playerDescription = "Alejandro Darío Gómez born 15 February 1988), nicknamed Papu, is an Italian-Argentine footballer who plays as a forward, winger or as an attacking midfielder for Italian club Atalanta and the Argentina national football team. Gómez is a dual citizen of Argentina and Italy, having gained Italian citizenship on 14 May 2016, as his wife is an Italian citizen.",
            playerGender = "Male",
            playerPosition = "Midfielder",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/iwhk4p1546032300.jpg",
            playerHeight = "1.6",
            playerWeight = "58.96",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = null,
            strFanart2 = null,
            strFanart3 = null,
            strFanart4 = null
        ),
        // Juventus
        PlayersDetailData(
            playerId = "34146585",
            playerName = "Alex Sandro",
            nationality = "Brazil",
            playerTeam = "Juventus",
            playerBorn = "1991-01-26",
            birthLocation = "Catanduva, Brazil",
            playerDescription = "Alex Sandro Lobo Silva, or simply Alex Sandro (born 26 January 1991), is a Brazilian professional footballer who plays as a left-back for Italian club Juventus and the Brazil national team. A quick, energetic and offensive minded defender who is also a strong tackler and a good reader of the game, Alex Sandro is capable of playing anywhere along the left flank; he has also been used as a wing-back and as a wide midfielder. He has been described as a player who is a \"powerful runner, can beat opponents one-on-one and is an excellent crosser of the ball\". His playing position, athleticism, and playing style have drawn comparisons with compatriot and 2002 FIFA World Cup-winner Roberto Carlos.\r\n\r\nAt club level, Alex Sandro began his career with Atletico Paranaense, and later also played for Santos on loan. In 2011, he joined Porto for €9.6 million, alongside teammate and countryman Danilo, who plays as a right back. He joined Juventus in 2015, winning the domestic double in his first two seasons.\r\n\r\nAt international level, Alex Sandro also plays for the Brazil national football team, for which he has gained 7 caps so far. At youth level, he also represented the Brazil national under-20 football team, winning both the South American Youth Championship and the FIFA U-20 World Cup in 2011, as well as the Brazil national under-23 football team, with which he won a silver medal at the 2012 Summer Olympics.",
            playerGender = "Male",
            playerPosition = "Defender",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/kyc4rv1515955255.jpg",
            playerHeight = "1.80",
            playerWeight = "66 kg",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = null,
            strFanart2 = null,
            strFanart3 = null,
            strFanart4 = null
        ),
        PlayersDetailData(
            playerId = "34162105",
            playerName = "Andrea Barzagli",
            nationality = "Argentina",
            playerTeam = "Italy",
            playerBorn = "1981-05-08",
            birthLocation = "Fiesole, Italy",
            playerDescription = "Andrea Barzagli, Ufficiale OMRI (Italian pronunciation: ; born 8 May 1981) is an Italian professional footballer who plays as a centre-back for Italian club Juventus and formerly the Italian national team.\r\n\r\nA four-time member of the Serie A Team of the Year, Barzagli is regarded as one of the best and most consistent defenders of his generation. After playing for several smaller Italian clubs in the lower divisions of Italian football in his early career, he made his Serie A debut with Chievo in 2003, and eventually came to prominence while playing for Palermo. In 2008, he was signed by German side VfL Wolfsburg, where he remained for two and a half seasons, winning a Bundesliga title in 2009. In 2011, he returned to Italy, joining Juventus, where he later won six consecutive Serie A titles between 2012 and 2017, among other trophies.\r\n\r\nAt international level, he represented the Italian national football team on 73 occasions between 2004 and 2017, taking part at the 2004 Summer Olympic Games – winning a bronze medal –, at two FIFA World Cups (2006 and 2014), three UEFA European Championships (2008, 2012, and 2016), and at the 2013 FIFA Confederations Cup, where he also won a bronze medal. He was most notably a member of the Italian 2006 World Cup winning squad, as well as a starting member of the Italian squad that reached the UEFA Euro 2012 final.",
            playerGender = "Male",
            playerPosition = "Defender",
            playerPhotos = "https://www.thesportsdb.com/images/media/player/thumb/4h89dl1515955588.jpg",
            playerHeight = "1.87",
            playerWeight = "",


            facebook = "",
            website = "",
            twitter = "",
            instagram = "",
            youtube = "",

            strFanart1 = null,
            strFanart2 = null,
            strFanart3 = null,
            strFanart4 = null
        )


    )

    fun getDetails(playerId: String): PlayerDetailsDataResponse? {
        for (playerDetails in listPlayerDetails) {
            if (playerDetails.playerId.equals(playerId))
                return PlayerDetailsDataResponse(listOf(playerDetails))
        }
        return null
    }
}