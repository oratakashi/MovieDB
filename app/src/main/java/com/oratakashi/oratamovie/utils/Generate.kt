package com.oratakashi.oratamovie.utils

import java.util.*

object Generate {
    fun getTimeMilis() : String{
        return Calendar.getInstance().timeInMillis.toString()
    }
}