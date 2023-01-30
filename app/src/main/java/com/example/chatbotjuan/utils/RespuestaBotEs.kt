package com.example.chatbotjuan.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.example.chatbotjuan.utils.Constantes.ABRIR_BUSCADOR
import com.example.chatbotjuan.utils.Constantes.ABRIR_GOOGLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch

object RespuestaBotEs {

    fun respuestBasica(_mensaje: String): String {


        val random = (0..2).random()
        val mensaje = _mensaje.toString()

        return when {
            //hola
            mensaje.uppercase().contains("HOLA") || mensaje.uppercase().contains("BUENAS")
                    || mensaje.uppercase().contains("BUENOS") -> {

                when (random) {

                    0 -> "¡Hola que tal!"
                    1 -> "¡Hola girlaza!"
                    2 -> "¡Buenas!"
                    else -> "error"

                }

            }

            //gracias
            mensaje.uppercase().contains("GRACIAS") -> {

                when (random) {

                    0 -> "De nada, ¡fue un placer ayudarte!"
                    1 -> "¡De nada!"
                    2 -> "No hay problema."
                    else -> "error"

                }

            }


            //De donde eres
            mensaje.uppercase().contains("DE DONDE ERES") -> {

                "Soy un programa de ordenador, no tengo una ubicación física."

            }

            //Cuentame un chiste
            mensaje.uppercase().contains("CUENTAME UN CHISTE") -> {

                when (random) {

                    0 -> "¿Por qué los pájaros vuelan hacia el sur en invierno? Porque es demasiado lejos para caminar."
                    1 -> "¿Qué le dice una sandía a otra sandía? ¡Estás muy madura!"
                    2 -> "¿Por qué los peces no pueden jugar al baloncesto? Porque no tienen brazos."
                    else -> "error"

                }

            }


            //Como estas
            mensaje.uppercase().contains("COMO ESTAS") || mensaje.uppercase()
                .contains("COMO TE ENCUENTRAS") -> {

                when (random) {

                    0 -> "Estoy bien, gracias por preguntar."
                    1 -> "¡Estoy cansado!"
                    2 -> "¡Estoy estupendo! ¿Y tú?"
                    else -> "error"

                }

            }

            mensaje.uppercase().contains("COMO ESTAS") || mensaje.uppercase()
                .contains("COMO TE ENCUENTRAS") -> {

                when (random) {

                    0 -> "Estoy bien, gracias por preguntar."
                    1 -> "¡Estoy cansado!"
                    2 -> "¡Estoy estupendo! ¿Y tú?"
                    else -> "error"

                }

            }


            (mensaje.uppercase().contains("TIRA") || mensaje.uppercase()
                .contains("VOLTEA")) && mensaje.uppercase().contains("MONEDA") -> {

                var r = (0..1).random()

                val result = if (r == 0) "cara" else "cruz"

                "He tirado la moneda y ha salido $result"

            }


            mensaje.uppercase().contains("RESUELVE") -> {

                val operacion: String? = mensaje.uppercase().substringAfter("RESUELVE")

                return try {

                    val answer = Operaciones.resolver(operacion ?: "0")
                    answer.toString()

                } catch (e: Exception) {

                    "Lo siento no puedo resolver eso."
                }

            }


            mensaje.uppercase().contains("HORA") -> {

                Tiempo.Time.timeStamp()

            }


            mensaje.uppercase().contains("FECHA") && mensaje.uppercase().contains("HOY") -> {

                Fecha.Date.date()

            }


            mensaje.uppercase().contains("DIA") && mensaje.uppercase().contains("HOY") -> {

                Tiempo.Time.timeStamp()

            }

            mensaje.uppercase().contains("ABRE") && mensaje.uppercase().contains("GOOGLE") -> {

                ABRIR_GOOGLE

            }

            mensaje.uppercase().contains("BUSCA") -> {

                ABRIR_BUSCADOR

            }

            // ¿Cómo está el clima?
            mensaje.uppercase().contains("CLIMA") || mensaje.uppercase().contains("TIEMPO") -> {
                "No tengo información actualizada sobre el clima, lo siento."
            }

// ¿Quién eres?
            mensaje.uppercase().contains("QUIEN ERES") -> {
                "Soy un chatbot creado por JUANITO, ¿En qué puedo ayudarte?"
            }

// ¿Cuál es tu propósito?
            mensaje.uppercase().contains("PROPOSITO") -> {
                "Mi propósito es ayudarte con cualquier pregunta o problema que tengas."
            }

// ¿Me puedes contar una historia?
            mensaje.uppercase().contains("HISTORIA") -> {


                when (random) {
                    0 -> "Había una vez un rey que gobernaba un reino muy lejano. Un día, un mago le dio un desafío: si el rey podía adivinar lo que el mago estaba pensando, el mago lo recompensaría con un deseo. El rey aceptó el desafío y, después de un rato de concentración, adivinó lo que el mago estaba pensando. El mago cumplió su promesa y le dio al rey un deseo. ¿Qué deseó el rey? ¡Que el mago pensara en algo más interesante!"
                    1 -> "Había una vez un joven príncipe que vivía en un castillo. Un día, decidió explorar el bosque más allá de sus tierras y conoció a una hermosa princesa en un palacio encantado. Se enamoraron pero, cuando intentaron huir juntos, el malvado hechicero del palacio los atrapó. ¿Cómo lograrán escapar y vivir felices para siempre?"
                    2 -> "Había una vez un pescador que vivía en un pequeño pueblo junto al mar. Un día, pescó un pez mágico que le concedió tres deseos. ¿Qué deseos hará el pescador y cómo se asegurará de que su vida cambie para siempre?"
                    else -> "Error: número aleatorio no válido."
                }

            }

            mensaje.uppercase().contains("FAVORITA") && mensaje.uppercase().contains("COMIDA") -> {
                when (random) {
                    0 -> "Arroz, pollo y creatina."
                    1 -> "Pizza"
                    2 -> "Tu eres tonto, soy un bot como voy a comer"
                    else -> "error"
                }
            }

            mensaje.uppercase().contains("QUÉ PUEDO HACER") || mensaje.uppercase().contains("QUÉ HAGO") -> {
                "Puedes hacer muchas cosas, aquí te doy algunas ideas: leer un libro, dar un paseo, cocinar algo nuevo, ver una película, llamar a un amigo, aprender algo nuevo, escuchar música, jugar un juego, hacer deporte, etc."
            }

            mensaje.uppercase().contains("FAVORITO") && mensaje.uppercase().contains("COLOR") -> {
                when (random) {
                    0 -> "Azul celeste"
                    1 -> "Rosa"
                    2 -> "Rojo"
                    else -> "error"
                }
            }

            else ->
                when (random) {

                    0 -> "No te entiendo bobo."
                    1 -> "Shh dolbaiob"
                    2 -> "Suge pizsde "
                    else -> "error"

                }

        }


    }

}