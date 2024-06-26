package com.keshavproject.babybuyproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.keshavproject.babybuyproject.Dashboard.DashboardActivity
import com.keshavproject.babybuyproject.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()



            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, DashboardActivity::class.java)

                        intent.putExtra(AppConstants.KEY_ENTERED_EMAIL, email)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }


            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }

            //Storing in SharedPreferences
            val sharedPreferences = this@SignInActivity.getSharedPreferences(
                "app",
                Context.MODE_PRIVATE
            )
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putBoolean("isLoggedIn", true)
            sharedPrefEditor.apply()



        }


    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}