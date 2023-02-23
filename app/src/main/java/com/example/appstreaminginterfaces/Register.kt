package com.example.appstreaminginterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appstreaminginterfaces.databinding.ActivityLogin2Binding
import com.example.appstreaminginterfaces.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            auth.createUserWithEmailAndPassword(binding.editTextTextEmailAddressRegister.text.toString(),binding.editTextTextPasswordRegister.text.toString()).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    database = FirebaseDatabase.getInstance().getReference(auth.currentUser?.uid.toString())
                    val intent= Intent(this,Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
}