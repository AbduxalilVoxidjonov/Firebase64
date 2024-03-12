package com.example.firebase64.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase64.databinding.ItemRvBinding
import com.example.firebase64.models.User
import com.squareup.picasso.Picasso

class UserAdapter(val list: List<User>, val onItemClickListener: (user: User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvName.text = user.displayName
            binding.tvEmail.text = user.email
            Picasso.get().load(user.photoUrl).into(binding.imageView)
            itemView.setOnClickListener {
                onItemClickListener.invoke(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}