package com.example.paleteriadjn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var AuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIngresar: Button   = findViewById(R.id.btnIngresar)
        val txtemail:    TextView = findViewById(R.id.edtEmail)
        val txtpass:     TextView = findViewById(R.id.edtPassword)
        val btnCrear_CuentaNueva : TextView = findViewById(R.id.btnCrearCuenta)
        val btnOlvidar: TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth= Firebase.auth
        btnIngresar.setOnClickListener()
        {
            signIn(txtemail.text.toString(),txtpass.text.toString())
        }
        btnCrear_CuentaNueva.setOnClickListener()
        {
            val i = Intent(this, CrearCuentaActivity::class.java )
            startActivity(i)
        }

        btnOlvidar.setOnClickListener()
        {
            val i = Intent(this, OlvidarActivity::class.java)
            startActivity(i)
        }
    }
    private fun signIn(email : String, password : String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                val verificar = user?.isEmailVerified
                if (verificar==true)
                {
                Toast.makeText(baseContext, "Autenticación Exitosa", Toast.LENGTH_SHORT).show()
                //Aqui vamos a ir a la segunda pantalla
                //val i = Intent(this,MainActivity2::class.java)
                val i = Intent(this,ConsultarProducto::class.java)
                startActivity(i)
                }
                else
                {
                    Toast.makeText(baseContext, "No ha verificado su correo electronico", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(baseContext, "Email o contraseña erróneos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}