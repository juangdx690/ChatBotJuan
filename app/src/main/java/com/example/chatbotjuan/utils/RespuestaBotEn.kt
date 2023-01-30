package com.example.chatbotjuan.utils

import com.example.chatbotjuan.utils.Constantes.ABRIR_BUSCADOR
import com.example.chatbotjuan.utils.Constantes.ABRIR_GOOGLE

object RespuestaBotEn {

    fun respuestBasica(_mensaje: String): String {


        val random = (0..2).random()
        val mensaje = _mensaje.toString()

        return when {
            //hola

            mensaje.uppercase().contains("HELLO") || mensaje.uppercase().contains("GOOD MORNING")
                    || mensaje.uppercase().contains("GOOD AFTERNOON") -> {

                when (random) {

                    0 -> "Hello, how are you?"
                    1 -> "Hello there!"
                    2 -> "Good morning/afternoon!"
                    else -> "error"

                }

            }

            //thanks
            mensaje.uppercase().contains("THANKS") -> {

                when (random) {

                    0 -> "You're welcome, it was a pleasure to help you!"
                    1 -> "You're welcome!"
                    2 -> "No problem."
                    else -> "error"

                }

            }


            //Where are you from
            mensaje.uppercase().contains("WHERE ARE YOU FROM") -> {

                "I am a computer program, I do not have a physical location."

            }

            //Tell me a joke
            mensaje.uppercase().contains("TELL ME A JOKE") -> {

                when (random) {

                    0 -> "Why do birds fly south in winter? Because it's too far to walk."
                    1 -> "What does one watermelon say to the other watermelon? You're very ripe!"
                    2 -> "Why can't fish play basketball? Because they don't have arms."
                    else -> "error"

                }

            }

            mensaje.uppercase().contains("HOW ARE YOU") || mensaje.uppercase()
                .contains("HOW ARE YOU DOING") -> {

                when (random) {

                    0 -> "I'm good, thanks for asking."
                    1 -> "I'm tired!"
                    2 -> "I'm great! How about you?"
                    else -> "error"

                }

            }


            (mensaje.uppercase().contains("FLIP") || mensaje.uppercase()
                .contains("TOSS")) && mensaje.uppercase().contains("COIN") -> {

                var r = (0..1).random()

                val result = if (r == 0) "heads" else "tails"

                "I have flipped a coin and it has landed on $result"

            }


            mensaje.uppercase().contains("SOLVE") -> {

                val operation: String? = mensaje.uppercase().substringAfter("SOLVE")

                return try {

                    val answer = Operaciones.resolver(operation ?: "0")
                    answer.toString()

                } catch (e: Exception) {

                    "Sorry I can't solve that."
                }

            }


            mensaje.uppercase().contains("TIME") -> {

                Tiempo.Time.timeStamp()

            }


            mensaje.uppercase().contains("DATE") && mensaje.uppercase().contains("TODAY") -> {

                Fecha.Date.date()

            }


            mensaje.uppercase().contains("DAY") && mensaje.uppercase().contains("TODAY") -> {

                Tiempo.Time.timeStamp()

            }

            mensaje.uppercase().contains("OPEN") && mensaje.uppercase().contains("GOOGLE") -> {

                ABRIR_GOOGLE

            }

            mensaje.uppercase().contains("SEARCH") -> {

                ABRIR_BUSCADOR

            }

            // Weather
            mensaje.uppercase().contains("WEATHER") || mensaje.uppercase()
                .contains("TEMPERATURE") -> {
                "I'm sorry, I don't have updated information on the weather."
            }

// Who are you?
            mensaje.uppercase().contains("WHO ARE YOU") -> {
                "I am a chatbot created by JUANITO, how can I help you?"
            }

// What is your purpose?
            mensaje.uppercase().contains("PURPOSE") -> {
                "My purpose is to help you with any questions or problems you may have."
            }

// Can you tell me a story?
            mensaje.uppercase().contains("STORY") -> {

                when (random) {
                    0 -> "Once upon a time there was a king who ruled a far-off kingdom. One day, a wizard gave him a challenge: if the king could guess what the wizard was thinking, the wizard would reward him with a wish. The king accepted the challenge and, after a while of concentration, he guessed what the wizard was thinking. The wizard fulfilled his promise and gave the king a wish. What did the king wish for? That the wizard would think of something more interesting!"
                    1 -> "Once upon a time there was a young prince who lived in a castle. One day, he decided to explore the forest beyond his lands and met a beautiful princess in an enchanted palace. They fell in love but, when they tried to escape together, the evil wizard of the palace caught them. How will they escape and live happily ever after?"
                    2 -> "Once upon a time there was a fisherman who lived in a small village by the sea. One day, he caught a magic fish that granted him three wishes. What will the fisherman wish for and how will he make sure his life changes forever?"
                    else -> "Error: invalid random number."
                }

            }

            mensaje.uppercase().contains("FAVORITE") && mensaje.uppercase().contains("FOOD") -> {
                when (random) {
                    0 -> "Rice, chicken, and creatine."
                    1 -> "Pizza"
                    2 -> "You're silly, I'm a bot how am I going to eat"
                    else -> "error"
                }
            }

            mensaje.uppercase().contains("WHAT CAN I DO") || mensaje.uppercase()
                .contains("WHAT DO I DO") -> {
                "You can do many things, here are some ideas: read a book, take a walk, cook something new, watch a movie, call a friend, learn something new, listen to music, play a game, do sports, etc."
            }

            mensaje.uppercase().contains("FAVORITE") && mensaje.uppercase().contains("COLOR") -> {
                when (random) {
                    0 -> "Sky blue"
                    1 -> "Pink"
                    2 -> "Red"
                    else -> "error"
                }
            }


            else ->
                when (random) {

                    0 -> "Idk"
                    1 -> "Shh dolbaiob"
                    2 -> "Suge piszde"
                    else -> "error"

                }

        }


    }

}