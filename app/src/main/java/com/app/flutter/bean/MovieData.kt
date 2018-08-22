package com.app.flutter.bean

import java.io.Serializable

data class MovieData(val count: Int, val movies: ArrayList<Movie>, val totalCinemaCount: Int, val totalComingMovie: Int, val totalHotMovie: Int) {

    data class Movie(val actorName1: String, val actorName2: String, val btnText: String, val commonSpecial: String, val directorName: String,
                     val img: String, val is3D: Boolean, val isDMAX: Boolean, val isFilter: Boolean, val isHot: Boolean, val isIMAX: Boolean,
                     val isNew: Boolean, val length: Int, val movieId: Int, val nearestShowtime: NearestShowtime, val preferentialFlag: Boolean, val rDay: Int,
                     val rMonth: Int, val rYear: Int, val ratingFinal: Float, val titleCn: String, val titleEn: String,
                     val type: String, val wantedCount: Int) : Serializable {

        data class NearestShowtime(val isTicket: Boolean, val nearestCinemaCount: Int, val nearestShowDay: Long, val nearestShowtimeCount: Int) : Serializable {
            override fun toString(): String {
                return "NearestShowtime(isTicket=$isTicket, nearestCinemaCount=$nearestCinemaCount, nearestShowDay=$nearestShowDay, nearestShowtimeCount=$nearestShowtimeCount)"
            }
        }

        override fun toString(): String {
            return "Movie(actorName1='$actorName1', actorName2='$actorName2', btnText='$btnText', commonSpecial='$commonSpecial', directorName='$directorName', img='$img', is3D=$is3D, isDMAX=$isDMAX, isFilter=$isFilter, isHot=$isHot, isIMAX=$isIMAX, isNew=$isNew, length=$length, movieId=$movieId, nearestShowtime=$nearestShowtime, preferentialFlag=$preferentialFlag, rDay=$rDay, rMonth=$rMonth, rYear=$rYear, ratingFinal=$ratingFinal, titleCn='$titleCn', titleEn='$titleEn', type='$type', wantedCount=$wantedCount)"
        }

    }

    override fun toString(): String {
        return "MovieData(count=$count, movies=$movies, totalCinemaCount=$totalCinemaCount, totalComingMovie=$totalComingMovie, totalHotMovie=$totalHotMovie)"
    }

}