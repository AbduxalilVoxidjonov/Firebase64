package com.example.firebase64.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase64.databinding.ItemFromBinding
import com.example.firebase64.databinding.ItemToBinding
import com.example.firebase64.models.Message

class MessageAdapter(val list: List<Message>, val currentUserUid: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TO = 0
    private val FROM = 1

    inner class ToVh(val itemToBinding: ItemToBinding) :
        RecyclerView.ViewHolder(itemToBinding.root) {
        fun onBind(message: Message) {
            itemToBinding.apply {
                messageText.text = message.text
                dateTv.text = message.date
            }
        }
    }

    inner class FromVh(val itemFromBinding: ItemFromBinding) :
        RecyclerView.ViewHolder(itemFromBinding.root) {
        fun onBind(message: Message) {
            itemFromBinding.apply {
                messageText.text = message.text
                dateTv.text = message.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TO) {
            return ToVh(ItemToBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            return FromVh(
                ItemFromBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ToVh) {
            holder.onBind(list[position])
        } else if (holder is FromVh) {
            holder.onBind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].fromUserUid == currentUserUid) {
            return FROM
        }
        return TO
    }
}