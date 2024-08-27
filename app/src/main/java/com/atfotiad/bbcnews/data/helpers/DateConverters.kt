package com.atfotiad.bbcnews.data.helpers

import android.util.Log
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

fun convertUtcTimestampToDate(utcTimestamp: String): Date? {
    return try {
        val formatter = DateTimeFormatter.ISO_INSTANT
        val instant = Instant.from(formatter.parse(utcTimestamp))
        Date.from(instant)
    } catch (e: Exception) {
        Log.e("Date", "convertUtcTimestampToDate: ",e )
        null // Handle parsing errors as needed
    }
}