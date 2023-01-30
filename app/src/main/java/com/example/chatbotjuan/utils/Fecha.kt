package com.example.chatbotjuan.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar

object Fecha {

    object Date{

        fun date():String{

            val date = Calendar.getInstance()

            val dia = date.get(Calendar.DAY_OF_MONTH)
            val mes = date.get(Calendar.MONTH)+1
            val anio = date.get(Calendar.YEAR)

            return ""+dia+"/"+mes+"/"+anio
        }

    }

}