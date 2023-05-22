package uz.khan.bottomnavigationbar

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import uz.khan.bottomnavigationbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawableLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.navView.setNavigationItemSelectedListener(this)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        loadFragment(HomeFragment())
        initBottomNav()
        val toggle =
            ActionBarDrawerToggle(this, binding.constraint, R.string.app_name, R.string.app_name)
        binding.constraint.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment()).commit()
            binding.navView.setCheckedItem(R.id.home)
        }


    }


    private fun initBottomNav() {
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
            .replace(R.id.container, fragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment()).commit()
            R.id.search -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment()).commit()
            R.id.account -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, AccountFragment()).commit()
            R.id.info -> Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show()


        }
        binding.constraint.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java", ReplaceWith("onBackPressedDispatcher.onBackPressed()"))
    override fun onBackPressed() {
        if (binding.constraint.isDrawerOpen(GravityCompat.START)) {
            binding.constraint.isDrawerOpen(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}