package com.android.jawalur.ui.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.jawalur.R
import com.android.jawalur.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dashboard.view.*


class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.img_keluar.setOnClickListener { keluar() }
    }

    private fun keluar() {
        val WEB_CLIENT = "564803799642-hd0oai62858tehgclcjmm1kjh8vo3lnj.apps.googleusercontent.com"
        val firebaseAuth = FirebaseAuth.getInstance()
        AlertDialog.Builder(context)
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setNegativeButton("Tidak", null)
            .setNeutralButton("Batal", null)
            .setPositiveButton("Ya") { dialogInterface, i ->
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(WEB_CLIENT)
                    .requestEmail()
                    .build()
                activity?.let { GoogleSignIn.getClient(it, gso).signOut() }
                firebaseAuth.signOut()
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
            .create().show()
    }
}