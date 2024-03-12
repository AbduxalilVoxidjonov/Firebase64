package com.example.firebase64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase64.adapter.MessageAdapter
import com.example.firebase64.databinding.ActivityMessageBinding
import com.example.firebase64.models.Message
import com.example.firebase64.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date

class MessageActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMessageBinding.inflate(layoutInflater) }
    private lateinit var user: User
    private lateinit var currentUserUid: String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        currentUserUid = intent.getStringExtra("uid").toString() ?: ""
        user = intent.getSerializableExtra("user") as User

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        messageList = ArrayList()
        messageAdapter = MessageAdapter(messageList, currentUserUid)
        binding.messageRv.adapter = messageAdapter



        binding.sendButton.setOnClickListener {
            val text = binding.edit.text.toString()
            val message =
                Message(text, currentUserUid, user.uid, getData())
            val key = reference.push().key

            reference.child(user.uid ?: "").child("messages").child(currentUserUid)
                .child(key ?: "").setValue(message)

            reference.child(currentUserUid).child("messages").child(user.uid ?: "")
                .child(key ?: "").setValue(message)

            binding.edit.setText("")
        }

        reference.child(currentUserUid).child("messages").child(user.uid ?: "")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    val children = snapshot.children
                    children.forEach {
                        val message = it.getValue(Message::class.java)
                        message?.let { it1 -> messageList.add(it1) }
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getData(): String {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return simpleDateFormat.format(date)
    }
}