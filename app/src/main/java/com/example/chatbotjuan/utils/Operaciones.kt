package com.example.chatbotjuan.utils

import android.util.Log

object Operaciones {


    fun resolver(equation: String): Int {

        val newEquation = equation.replace(" ", "")
        Log.d("Math", newEquation)

        return when {
            newEquation.contains("+") -> {
                val split = newEquation.split("+")
                val result = split[0].toInt() + split[1].toInt()
                result
            }
            newEquation.contains("-") -> {
                val split = newEquation.split("-")
                val result = split[0].toInt() - split[1].toInt()
                result
            }
            newEquation.contains("*") -> {
                val split = newEquation.split("*")
                val result = split[0].toInt() * split[1].toInt()
                result
            }
            newEquation.contains("/") -> {
                val split = newEquation.split("/")
                val result = split[0].toInt() / split[1].toInt()
                result
            }
            else -> {
                0
            }
        }
    }


}