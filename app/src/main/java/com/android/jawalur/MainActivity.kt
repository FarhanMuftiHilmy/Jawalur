package com.android.jawalur

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.jawalur.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val WEB_CLIENT = "564803799642-hd0oai62858tehgclcjmm1kjh8vo3lnj.apps.googleusercontent.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_keluar.setOnClickListener { keluar() }

    }

    private fun keluar() {
        val firebaseAuth = FirebaseAuth.getInstance()
        AlertDialog.Builder(this)
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setNegativeButton("Tidak", null)
            .setNeutralButton("Batal", null)
            .setPositiveButton("Ya") { dialogInterface, i ->
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(WEB_CLIENT)
                    .requestEmail()
                    .build()
                GoogleSignIn.getClient(this, gso).signOut()
                firebaseAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            }
            .create().show()
    }

}