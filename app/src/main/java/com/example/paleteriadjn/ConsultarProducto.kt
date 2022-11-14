package com.example.paleteriadjn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ConsultarProducto : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_producto)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.menu_Bucar -> {
                Toast.makeText(baseContext, "Buscar Info", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_salir ->{
                signOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun buscarproducto(view:View)
    {
        val idproducto = findViewById<EditText>(R.id.edit_id)
        val nombrepro = findViewById<TextView>(R.id.nombreproduct)
        val descripcionpro = findViewById<TextView>(R.id.DescripcionProduct)
        val preciopro = findViewById<TextView>(R.id.PrecioProduct)
        val stock = findViewById<TextView>(R.id.Stock)

        //val url:String="http://192.168.3.8:8001/api/producto/" + idproducto.text.toString()
        val url:String="https://jsonplaceholder.typicode.com/posts/" + idproducto.text.toString()
        val rq:RequestQueue= Volley.newRequestQueue(this)
        val jor=JsonObjectRequest(Request.Method.GET,url,null, Response.Listener { response ->
            nombrepro.text=response.getString("userId")
            descripcionpro.text=response.getString("id")
            preciopro.text=response.getString("title")
            stock.text=response.getString("body")
        },Response.ErrorListener { error ->
            nombrepro.text=error.message
        })
        rq.add(jor)
    }

    private  fun signOut(){
        firebaseAuth.signOut()
        Toast.makeText(baseContext, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
        val i=Intent(this, MainActivity::class.java)
        startActivity(i)

    }
}