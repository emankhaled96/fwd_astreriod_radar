package com.udacity.asteroidradar

import android.os.Build
import androidx.annotation.RequiresApi
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class DateUtils {
    fun getToday():String{
        val current = DateTime.now()
      return  SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT).format(current.toDate())
    }
    fun getEndDate():String{
        val afterSevenDays = DateTime.now().plusDays(7)
        return  SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT).format(afterSevenDays.toDate())
    }
}