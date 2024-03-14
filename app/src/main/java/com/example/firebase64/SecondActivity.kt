package com.example.firebase64

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase64.databinding.ActivitySecondBinding
import com.example.firebase64.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class SecondActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//       Initialize Firestore
//        firestore = Firebase.firestore
        firestore = FirebaseFirestore.getInstance()

        binding.button.setOnClickListener {
            val user = User(
                "Umarov", "123", "abduhalilvohidjonov@gmail.com", "https://www.google.com"
            )

            firestore.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
        binding.readData.setOnClickListener {
            firestore.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val user = document.toObject(User::class.java)
                        binding.textView.text = binding.textView.text.toString() + user.toString()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(
                        this,
                        "Error getting documents: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }
}