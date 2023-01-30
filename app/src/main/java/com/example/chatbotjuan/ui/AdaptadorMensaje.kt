package com.codepalace.chatbot.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotjuan.R
import com.example.chatbotjuan.datos.Mensaje
import com.example.chatbotjuan.utils.Constantes.ENVIAR_ID
import com.example.chatbotjuan.utils.Constantes.RECIBIR_ID


class AdaptadorMensaje : RecyclerView.Adapter<AdaptadorMensaje.MessageViewHolder>() {

    lateinit var tv_message: TextView
    var messagesList = mutableListOf<Mensaje>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnLongClickListener {

                showPopupMenu(it)
                true

            }
        }


        private fun showPopupMenu(view: View) {
            val popup = PopupMenu(view.context, view)
            popup.inflate(R.menu.menu_contextual)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete -> {
                        messagesList.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.objeto_msg, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            ENVIAR_ID -> {
                holder.itemView.findViewById<TextView>(R.id.tv_message).apply {
                    text = currentMessage.mensaje
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.GONE
            }
            RECIBIR_ID -> {
                holder.itemView.findViewById<TextView>(R.id.tv_bot_message).apply {
                    text = currentMessage.mensaje
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Mensaje) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
        notifyDataSetChanged()
    }

}