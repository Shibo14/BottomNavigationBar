package uz.khan.bottomnavigationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import uz.khan.bottomnavigationbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.home -> HomeFragment()
                R.id.search -> SearchFragment()
                else -> AccountFragment()

            }
            loadFragment(fragment)
            true

        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment).commit()
    }
}