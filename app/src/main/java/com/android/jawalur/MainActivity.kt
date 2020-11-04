package com.android.jawalur

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.jawalur.ui.dashboard.DashboardFragment
import com.android.jawalur.ui.favorite.FavoriteFragment
import com.android.jawalur.ui.kuis.KuisFragment
import com.android.jawalur.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    val WEB_CLIENT = "564803799642-hd0oai62858tehgclcjmm1kjh8vo3lnj.apps.googleusercontent.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(DashboardFragment())
        setBottomNavigationView()

    }

    private fun setBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bn_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.getItemId()) {
            R.id.dashboard_menu -> fragment = DashboardFragment()
            R.id.favorite_menu -> fragment = FavoriteFragment()
            R.id.profile_menu -> fragment = KuisFragment()
        }
        return loadFragment(fragment)
    }

}