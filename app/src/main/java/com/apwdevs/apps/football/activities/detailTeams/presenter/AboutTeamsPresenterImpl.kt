package com.apwdevs.apps.football.activities.detailTeams.presenter

import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortData
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamMemberShortDataResponse
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamsAbout
import com.apwdevs.apps.football.activities.detailTeams.dataController.TeamsAboutResponse

object AboutTeamsPresenterImpl {
    private val listDetailTeams: MutableList<TeamsAbout> = mutableListOf(
        TeamsAbout(
            teamId = "133604",
            teamName = "Arsenal",
            formedOnYear = "1892",
            managerTeamName = "Unai Emery",
            stadiumName = "Emirates Stadium",
            stadiumImage = "https://www.thesportsdb.com/images/media/team/stadium/qpuxrr1419371354.jpg",
            stadiumDesc = "The Emirates Stadium (known as Ashburton Grove prior to sponsorship) is a football stadium in Holloway, London, England, and the home of Arsenal Football Club. With a capacity of 60,272, the Emirates is the third-largest football stadium in England after Wembley and Old Trafford.In 1997, Arsenal explored the possibility of relocating to a new stadium, having been denied planning permission by Islington Council to expand its home ground of Highbury. After considering various options (including purchasing Wembley), the club bought an industrial and waste disposal estate in Ashburton Grove in 2000. A year later they won the council's approval to build a stadium on the site; manager Arsène Wenger described this as the \"biggest decision in Arsenal's history\" since the board appointed Herbert Chapman. Relocation began in 2002, but financial difficulties delayed work until February 2004. Emirates Airline was later announced as the main sponsor for the stadium. Work was completed in 2006 at a cost of £390 million.",
            stadiumLocation = "Holloway, London",
            stadiumCapacity = "60338",

            urlWebsite = "www.arsenal.com",
            facebookUrl = "www.facebook.com/Arsenal",
            twitterUrl = "twitter.com/arsenal",
            instagramProfiles = "instagram.com/arsenal",
            clubDescription = "Der FC Arsenal (offiziell: Arsenal Football Club) – auch bekannt als (The) Arsenal, (The) Gunners (deutsche Übersetzung: „Schützen“ oder „Kanoniere“) oder im deutschen Sprachraum auch Arsenal London genannt – ist ein 1886 gegründeter Fußballverein aus dem Ortsteil Holloway des Nordlondoner Bezirks Islington. Mit 13 englischen Meisterschaften und elf FA-Pokalsiegen zählt der Klub zu den erfolgreichsten englischen Fußballvereinen.Erst über 40 Jahre nach der Gründung gewann Arsenal mit fünf Ligatiteln und zwei FA Cups in den 1930er Jahren seine ersten bedeutenden Titel. Der nächste Meilenstein war in der Saison 1970/71 der Gewinn des zweiten englischen „Doubles“ im 20. Jahrhundert. In den vergangenen 20 Jahren etablierte sich Arsenal endgültig als einer der erfolgreichsten englischen Fußballvereine, und beim Gewinn zweier weiterer Doubles zu Beginn des 21. Jahrhunderts blieb die Mannschaft in der Ligasaison 2003/04 als zweite in der englischen Fußballgeschichte ungeschlagen. Zunehmende europäische Ambitionen unterstrich der Verein in der Spielzeit 2005/06, als Arsenal als erster Londoner Verein das Finale der Champions League erreichte.",

            clubGender = "Male",
            clubCountry = "England",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png",
            teamJersey = "https://www.thesportsdb.com/images/media/team/jersey/kzne111510861290.png",
            teamLogo = "https://www.thesportsdb.com/images/media/team/logo/q2mxlz1512644512.png",
            teamFanArt = "https://www.thesportsdb.com/images/media/team/fanart/xyusxr1419347566.jpg",
            teamBanner = "https://www.thesportsdb.com/images/media/team/banner/rtpsrr1419351049.jpg"
        ),
        TeamsAbout(
            teamId = "133610",
            teamName = "Chelsea",
            formedOnYear = "1905",
            managerTeamName = "Maurizio Sarri",
            stadiumName = "Stamford Bridge",
            stadiumImage = "https://www.thesportsdb.com/images/media/team/stadium/vpuxsx1420200157.jpg",
            stadiumDesc = "Stamford Bridge (/ˈstæm.fərd ˈbrɪdʒ/) is a football stadium located in Fulham, London. It is the home ground of Chelsea F.C.. The stadium is located within the Moore Park Estate also known as Walham Green and is often referred to as simply The Bridge. The capacity is 41,798, making it the eighth largest ground in the Premier League.\r\n\r\nOpened in 1877, the stadium was used by the London Athletic Club until 1905, when new owner Gus Mears founded Chelsea Football Club to occupy the ground; Chelsea have played their home games there ever since. It has undergone numerous major changes over the years, most recently in the 1990s when it was renovated into a modern, all-seater stadium.\r\n\r\nStamford Bridge has been used as a venue for England international matches, FA Cup Finals, FA Cup semi-finals and Charity Shield games. It has also hosted numerous other sports, such as cricket, rugby union, speedway, greyhound racing, baseball and American football. The stadium's highest official attendance is 82,905, for a league match between Chelsea and Arsenal on 12 October 1935.",
            stadiumLocation = "Fulham, London",
            stadiumCapacity = "41798",

            urlWebsite = "www.chelseafc.com",
            facebookUrl = "www.facebook.com/ChelseaFC",
            twitterUrl = "twitter.com/chelseafc",
            instagramProfiles = "",
            clubDescription = "Chelsea Football Club /ˈtʃɛlsiː/ are a professional football club based in Fulham, London, who play in the Premier League, the highest level of English football. Founded in 1905, the club have spent most of their history in the top tier of English football. The club's home ground is the 41,837-seat Stamford Bridge stadium, where they have played since their establishment.\r\n\r\nChelsea had their first major success in 1955, winning the league championship, and won various cup competitions during the 1960s, 1970s, 1990s and 2000s. The club have enjoyed their greatest period of success in the past two decades, winning 15 major trophies since 1997. Domestically, Chelsea have won four league titles, seven FA Cups, four League Cups and four FA Community Shields, while in continental competitions they have won two UEFA Cup Winners' Cups, one UEFA Super Cup, one UEFA Europa League and one UEFA Champions League. Chelsea are the only London club to win the UEFA Champions League, and one of four clubs, and the only British club, to have won all three main UEFA club competitions.\r\n\r\nChelsea's regular kit colours are royal blue shirts and shorts with white socks. The club's crest has been changed several times in attempts to re-brand the club and modernise its image. The current crest, featuring a ceremonial lion rampant regardant holding a staff, is a modification of the one introduced in the early 1950s. The club have sustained the fifth highest average all-time attendance in English football. Their average home gate for the 2012–13 season was 41,462, the sixth highest in the Premier League. Since July 2003, Chelsea have been owned by Russian billionaire Roman Abramovich. In April 2013 they were ranked by Forbes Magazine as the seventh most valuable football club in the world, at £588 million ($901 million), an increase of 18% from the previous year.",

            clubGender = "Male",
            clubCountry = "England",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png",
            teamJersey = "https://www.thesportsdb.com/images/media/team/jersey/j4kjvh1510591602.png",
            teamLogo = "https://www.thesportsdb.com/images/media/team/logo/urupss1421777612.png",
            teamFanArt = "https://www.thesportsdb.com/images/media/team/fanart/rppwtt1424447399.jpg",
            teamBanner = "https://www.thesportsdb.com/images/media/team/banner/twxrxv1421778197.jpg"
        ),
        TeamsAbout(
            teamId = "134782",
            teamName = "Atalanta",
            formedOnYear = "1907",
            managerTeamName = "Gian Piero Gasperini",
            stadiumName = "Stadio Atleti Azzurri d",
            stadiumImage = "https://www.thesportsdb.com/images/media/team/stadium/wruwrv1423703902.jpg",
            stadiumDesc = "Atleti Azzurri d'Italia is a stadium in Bergamo, Italy, used by the Atalanta and Albinoleffe football teams. The field is 120 m long, and 70 m wide. The stadium has officially 24,642 recognized seats.\r\n\r\nThe stadium is seen by many to be outdated and not up to standard for Serie A due to poor facilities, poor views from a number of stands and because most of the stadium has no roof cover.\r\n\r\nIn the last decade some projects for a new stadium have been proposed but as of October 2012 no project has been carried out.",
            stadiumLocation = "Bérgamo, Italy",
            stadiumCapacity = "0",

            urlWebsite = "www.atalanta.it",
            facebookUrl = "",
            twitterUrl = "",
            instagramProfiles = "",
            clubDescription = "Atalanta Bergamasca Calcio, commonly known as just Atalanta, Atalanta Bergamo or the abbreviation Atalanta BC, is an Italian football club based in Bergamo, Lombardy. The club currently plays in Serie A, having gained the promotion from Serie B in 2010–11.\r\n\r\nThey are nicknamed the Nerazzurri and the Orobici. Atalanta play in blue-and-black vertically striped shirts, black shorts and black socks. The club stadium is the 26,638 seater Atleti Azzurri d'Italia.\r\n\r\nIn Italy, Atalanta is sometimes called Regina delle provinciali (queen of the provincial clubs) to mark the fact that the club is historically one of the most consistent among the non-metropolitan ones, having played 53 times in Serie A (11th overall for number of participations in the top division), 28 times in Serie B and only once in Serie C1.\r\n\r\nThe club won the Coppa Italia in 1963 and reached the Cup Winners' Cup Semifinal in 1988, when it was still competing in Serie B. This is still the best ever performance by a non-first division club in a major UEFA competition (together with Cardiff City). Atalanta also participated twice in the UEFA Cup, reaching the quarterfinals in 1990–91.",

            clubGender = "Male",
            clubCountry = "Italy",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/lrvxg71534873930.png",
            teamJersey = "https://www.thesportsdb.com/images/media/team/jersey/2018-134782-Jersey.png",
            teamLogo = "https://www.thesportsdb.com/images/media/team/logo/ryysqy1420485357.png",
            teamFanArt = "https://www.thesportsdb.com/images/media/team/fanart/yqqywr1420485511.jpg",
            teamBanner = "https://www.thesportsdb.com/images/media/team/banner/wsxtqu1420653660.jpg"
        ),
        TeamsAbout(
            teamId = "133676",
            teamName = "Juventus",
            formedOnYear = "1897",
            managerTeamName = "Massimiliano Allegri",
            stadiumName = "Juventus",
            stadiumImage = "https://www.thesportsdb.com/images/media/team/stadium/ywrwus1420503051.jpg",
            stadiumDesc = "Juventus Stadium is an all-seater football stadium in the Vallette borough of Turin, Italy, and the home of Serie A club Juventus Football Club. The stadium was built on the site of Juventus's and Torino's former home, the Stadio delle Alpi, and is one of only two club-owned football stadiums in Serie A, alongside Sassuolo's Mapei Stadium. It was opened at the start of the 2011–12 season and has a capacity of 41,000 spectators. The stands are just 7.5 m from the pitch, a major improvement from the Stadio Delle Alpi. The distance between the last row of the grand stand and the pitch is 49 m.\r\n\r\nThe first match played was a friendly against the world's oldest professional football club Notts County on 8 September 2011 where the match ended 1–1. Veteran striker Luca Toni found the net after Fabio Quagliarella failed to convert a penalty. The first official competitive match was Juventus – Parma which was played on 11 September 2011 where Stephan Lichtsteiner scored the first goal in the new stadium in the 17th minute.\r\n\r\nThe stadium hosted the 2014 UEFA Europa League Final.",
            stadiumLocation = "Turin, Italy",
            stadiumCapacity = "0",

            urlWebsite = "www.juventus.com",
            facebookUrl = "",
            twitterUrl = "",
            instagramProfiles = "",
            clubDescription = "Juventus Football Club S.p.A. , commonly referred to as Juventus and colloquially as Juve (pronounced ),are a professional Italian association football club based in Turin, Piedmont. The club is the third oldest of its kind in the country and has spent the majority of its history, with the exception of the 2006–07 season, in the top flight First Division (known as Serie A since 1929).\r\n\r\nFounded in 1897 as Sport Club Juventus by a group of young Torinese students,among them, who was their first president, Eugenio Canfari, and his brother Enrico, author of the company's historical memory; they are managed by the industrial Agnelli family since 1923, which constitutes the oldest sporting partnership in Italy, thus making Juventus the first professional club in the country. Over time, the club has become a symbol of the nation's italianità (\"Italianness\"), due to their tradition of success, some of which have had a significant impact in Italian society, especially in the 1930s and the first post-war decade; and the ideological politics and socio-economic origin of the club's sympathisers. This is reflected, among others, in the club's contribution to the national team, uninterrupted since the second half of the 1920s and recognised as one of the most influential in international football, having performed a decisive role in the World Cup triumphs of 1934, 1982 and 2006.[The club's fan base is larger than any other Italian football club and is one of the largest worldwide. Support for Juventus is widespread throughout the country and abroad, mainly in countries with a significant presence of Italian immigrants.\r\n\r\nJuventus is historically the most successful club in Italian football and one of the most laureated and important globally. Overall, they have won fifty-six official titles on the national and international stage, more than any other Italian club: a record thirty league titles, a record nine Italian cups, a record six national super cups, and, with eleven titles in confederation and inter-confederation competitions (two Intercontinental Cups, two European Champion Clubs' Cup/UEFA Champions Leagues, one European Cup Winners' Cup, a record three UEFA Cups, one UEFA Intertoto Cup and two UEFA Super Cups) the club currently ranks fourth in Europe and eighth in the world with the most trophies won.\r\n\r\nIn 1985, under the management of Giovanni Trapattoni, who led the Torinese team to thirteen official trophies in ten years until 1986, including six league titles and five international titles; Juventus became the first club in the history of European football to have won all three major competitions organised by the Union of European Football Associations: the European Champions' Cup, the (now-defunct) Cup Winners' Cup and the UEFA Cup (the first Italian and Southern European side to win the tournament). After their triumph in the Intercontinental Cup the same year, the club also became the first in football history—and remains the only one at present—to have won all possible official continental competitions and the world title. According to the all-time ranking published in 2009 by the International Federation of Football History and Statistics, an organisation recognised by FIFA, based on clubs' performance in international competitions, Juventus were Italy's best club and second in Europe of the 20th century.",

            clubGender = "Male",
            clubCountry = "Italy",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/dd6d6q1535186317.png",
            teamJersey = "https://www.thesportsdb.com/images/media/team/jersey/yquwvr1474197851.png",
            teamLogo = "https://www.thesportsdb.com/images/media/team/logo/b3cfr71535186225.png",
            teamFanArt = "https://www.thesportsdb.com/images/media/team/fanart/wwtuxx1420504589.jpg",
            teamBanner = "https://www.thesportsdb.com/images/media/team/banner/upuwxp1422399935.jpg"
        )
    )
    private val listMemberDetails: MutableList<TeamMemberShortDataCls> = mutableListOf(
        TeamMemberShortDataCls(
            "133604",
            mutableListOf(
                TeamMemberShortData(
                    playerId = "34145411",
                    playerName = "Aaron Ramsey",
                    playerPosition = "Centre Midfielder",
                    playerPhotosUrl = "https://www.thesportsdb.com/images/media/player/cutout/29000737.png"
                ),
                TeamMemberShortData(
                    playerId = "34145444",
                    playerName = "Ainsley Maitland-Niles",
                    playerPosition = "Midfielder",
                    playerPhotosUrl = "https://www.thesportsdb.com/images/media/player/cutout/twyuww1432691507.png"
                )
            )
        ),
        TeamMemberShortDataCls(
            "133610",
            mutableListOf(
                TeamMemberShortData(
                    playerId = "34160535",
                    playerName = "Álvaro Morata",
                    playerPosition = "Forward",
                    playerPhotosUrl = "https://www.thesportsdb.com/images/media/player/cutout/wqxusp1472656157.png"
                ),
                TeamMemberShortData(
                    playerId = "34145498",
                    playerName = "Cesar Azpilicueta",
                    playerPosition = "Defender",
                    playerPhotosUrl = "https://www.thesportsdb.com/images/media/player/cutout/qyuurv1424827900.png"
                )
            )
        ),
        TeamMemberShortDataCls(
            "134782",
            mutableListOf(
                TeamMemberShortData(
                    playerId = "34148374",
                    playerName = "Andrea Masiello",
                    playerPosition = "Defender",
                    playerPhotosUrl = "https://www.thesportsdb.com/images/media/player/cutout/7984549.png"
                ),
                TeamMemberShortData(
                    playerId = "34148386",
                    playerName = "Alejandro Gomez",
                    playerPosition = "Midfielder",
                    playerPhotosUrl = null
                )
            )
        ),
        TeamMemberShortDataCls(
            "133676",
            mutableListOf(
                TeamMemberShortData(
                    playerId = "34146585",
                    playerName = "Alex Sandro",
                    playerPosition = "Defender",
                    playerPhotosUrl = null
                ),
                TeamMemberShortData(
                    playerId = "34162105",
                    playerName = "Andrea Barzagli",
                    playerPosition = "Defender",
                    playerPhotosUrl = null
                )
            )
        )
    )

    fun getAboutTeams(teamId: String): TeamsAboutResponse? {
        for (team in listDetailTeams) {
            if (team.teamId.equals(teamId))
                return TeamsAboutResponse(listOf(team))
        }
        return null
    }

    fun getPlayerList(teamId: String): TeamMemberShortDataResponse? {
        val listReturnedTeams = mutableListOf<TeamMemberShortData>()
        for (teamMember in listMemberDetails) {
            if (teamMember.teamId.equals(teamId))
                return TeamMemberShortDataResponse(teamMember.dataList)
        }
        return null
    }
}

data class TeamMemberShortDataCls(
    val teamId: String,
    val dataList: MutableList<TeamMemberShortData>
)