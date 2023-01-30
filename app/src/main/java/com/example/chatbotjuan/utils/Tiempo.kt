package com.example.chatbotjuan.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat

object Tiempo {

    object Time{

        fun timeStamp():String{

            val timeStamp = Timestamp(System.currentTimeMillis())
            val sdf = SimpleDateFormat("HH:mm")
            val time = sdf.format(timeStamp.time)

            return time.toString()

        }

    }

}