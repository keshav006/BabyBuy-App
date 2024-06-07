package com.keshavproject.babybuyproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.keshavproject.babybuyproject.Dashboard.DashboardActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //navigating to another activity

        Handler().postDelayed(
            {
                //Fetching Shared Preferences Data
                val sharedPreferences = this@SplashScreen.getSharedPreferences(
                    "app",
                    Context.MODE_PRIVATE
                )


                val isLoggedIn = sharedPreferences.getBoolean(
                    "isLoggedIn",
                    false
                )

                val intent: Intent
                if (isLoggedIn){
                     intent = Intent(this, DashboardActivity::class.java)

                }else {
                   intent = Intent(this, SignInActivity::class.java)

                }

                startActivity(intent)
                finish()

            },
            2000

        )
    }
}