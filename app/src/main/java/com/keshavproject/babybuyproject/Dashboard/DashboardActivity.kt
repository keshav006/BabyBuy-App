package com.keshavproject.babybuyproject.Dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keshavproject.babybuyproject.AppConstants
import com.keshavproject.babybuyproject.Dashboard.Fragment.HomeFragment
import com.keshavproject.babybuyproject.Dashboard.Fragment.MYitemsFragment
import com.keshavproject.babybuyproject.Dashboard.Fragment.ProfileFragment
import com.keshavproject.babybuyproject.Dashboard.Fragment.SuggestionFragment
import com.keshavproject.babybuyproject.R
import com.keshavproject.babybuyproject.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val fragmentManager = supportFragmentManager
    private val homeFragment = HomeFragment()
    private val MYitemsFragment = MYitemsFragment()
    private val suggestionFragment = SuggestionFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRespectiveFragment(homeFragment)
        binding.bottomNav.setOnNavigationItemSelectedListener {


        when (it.itemId) {
            R.id.home -> {
                loadRespectiveFragment(homeFragment)
                true
            }
            R.id.my_items -> {
                loadRespectiveFragment(MYitemsFragment)
                true
            }
            R.id.suggestion -> {
                loadRespectiveFragment(suggestionFragment)
                true
            }
            else -> {
                loadRespectiveFragment(profileFragment)
                true
            }
        }
    }
}
    private fun loadRespectiveFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(
                binding.fragmentContainerView.id,
                fragment,
                null
            )
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}


