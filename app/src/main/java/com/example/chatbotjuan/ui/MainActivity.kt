package com.codepalace.chatbot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotjuan.R
import com.example.chatbotjuan.databinding.ActivityMainBinding
import com.example.chatbotjuan.datos.Mensaje
import com.example.chatbotjuan.utils.Constantes.ABRIR_BUSCADOR
import com.example.chatbotjuan.utils.Constantes.ABRIR_GOOGLE
import com.example.chatbotjuan.utils.Constantes.ENVIAR_ID
import com.example.chatbotjuan.utils.Constantes.RECIBIR_ID
import com.example.chatbotjuan.utils.Fecha
import com.example.chatbotjuan.utils.RespuestaBotEs
import com.example.chatbotjuan.utils.RespuestaBotEn
import com.example.chatbotjuan.utils.Tiempo

import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    //You can ignore this messageList if you're coming from the tutorial,
    // it was used only for my personal debugging
    var messagesList = mutableListOf<Mensaje>()

    private lateinit var adapter: AdaptadorMensaje
    private val botList = listOf("Galindo", "Dolbaiob", "Isidroro", "Rajoy")
    private lateinit var binding: ActivityMainBinding

    private lateinit var lenguaje:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lenguaje = Locale.getDefault().language

        recyclerView()

        clickEvents()

        enterclick()
        val random = (0..3).random()
        customBotMessage(this.resources.getString(R.string.saludoInicio)+" "+botList[random].toString()+" "+ this.resources.getString(R.string.saludoInicio2))
    }


    private fun enterclick(){

        val mensaje = binding.etMessage

        mensaje.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE){
                sendMessage()
                return@setOnEditorActionListener true
            }else{

                return@setOnEditorActionListener false
            }

        }

    }

    private fun clickEvents() {

        //Send a message
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        findViewById<EditText>(R.id.et_message).setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = AdaptadorMensaje()
        findViewById<RecyclerView>(R.id.rv_messages).adapter = adapter
        findViewById<RecyclerView>(R.id.rv_messages).layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = findViewById<EditText>(R.id.et_message).text.toString()
        val timeStamp = Tiempo.Time.toString()

        val fecha = Fecha.Date.toString()
        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Mensaje(message, ENVIAR_ID, timeStamp,fecha))
            findViewById<EditText>(R.id.et_message).setText("")

            adapter.insertMessage(Mensaje(message, ENVIAR_ID, timeStamp, fecha))
            findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Tiempo.Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                var response = ""

                if (lenguaje.equals("es")){

                    response = RespuestaBotEs.respuestBasica(message)

                }

                if (lenguaje.equals("en")){

                    response = RespuestaBotEn.respuestBasica(message)

                }


                val fecha = Fecha.Date.toString()
                //Adds it to our local list
                messagesList.add(Mensaje(response, RECIBIR_ID, timeStamp,fecha))

                //Inserts our message into the adapter
                adapter.insertMessage(Mensaje(response, RECIBIR_ID, timeStamp, fecha))

                //Scrolls us to the position of the latest message
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response) {
                    ABRIR_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    ABRIR_BUSCADOR -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Tiempo.Time.timeStamp()
                val fecha = Fecha.Date.toString()
                messagesList.add(Mensaje(message, RECIBIR_ID, timeStamp, fecha))
                adapter.insertMessage(Mensaje(message, RECIBIR_ID, timeStamp, fecha))

                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}