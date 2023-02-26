package com.example.appstreaminginterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appstreaminginterfaces.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLogin2Binding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString()).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    //se genera una lista de peliculas vistas y peliculas favoritas para cada usuario
                    pelisUser
                    database = FirebaseDatabase.getInstance().getReference("Usuarios")
                    database.child(auth.currentUser?.uid.toString()).setValue(pelisUser)
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLoginARegistro.setOnClickListener {
            val intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
}