package com.example.paleteriadjn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OlvidarActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvidar)

        val txtCambiarMail : TextView = findViewById(R.id.txtCambiarMail)
        val btnCambiar : Button = findViewById(R.id.btnCambiar)

        btnCambiar.setOnClickListener()
        {
            sendPasswordReset(txtCambiarMail.text.toString())
        }

        firebaseAuth= Firebase.auth
    }

    private fun sendPasswordReset(email : String)
    {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task ->
                if (task.isSuccessful)
                {
                    Toast.makeText(baseContext, "Hemos enviado un correo para la restauración de tu contraseña", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext, "ERROR, No se pudo completar el porceso", Toast.LENGTH_SHORT).show()
                }
            }
    }
}