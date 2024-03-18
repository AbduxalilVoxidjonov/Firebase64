package com.example.firebase64

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firebase64.databinding.ActivityThirdBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class ThirdActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThirdBinding.inflate(layoutInflater) }
    private lateinit var storage: FirebaseStorage
    private lateinit var reference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        reference = storage.getReference("images")

        binding.apply {

            button.setOnClickListener {
//                launcher.launch("image/*")
                throw RuntimeException("Test Crash") // Force a crash
            }


        }


    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            reference
                .child("image.png")
                .putFile(uri)
                .addOnSuccessListener {
                    storage.reference
                        .child("images")
                        .child("image.png")
                        .downloadUrl
                        .addOnSuccessListener { ul ->
                            Picasso.get().load(ul).into(binding.imageView)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed to get url", Toast.LENGTH_SHORT).show()
                        }

                }
                .addOnFailureListener {

                }
        }
    }
}