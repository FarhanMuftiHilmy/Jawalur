package com.android.jawalur.ui.register

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.jawalur.MainActivity
import com.android.jawalur.R
import com.android.jawalur.ui.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btn_daftar.setOnClickListener {
            registerUser()
        }

        btn_masuk.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    private fun registerUser() {
        val email = edt_email_register.text.toString()
        val password = edt_password_register.text.toString()
        val name = edt_name_register.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this
            ) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error registering, try again later :(",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }

    }


}