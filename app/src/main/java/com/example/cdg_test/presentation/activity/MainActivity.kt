package com.example.cdg_test.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cdg_test.R
import com.example.cdg_test.databinding.ActivityMainBinding
import com.example.cdg_test.presentation.fragment.FavoriteSourcesFragment
import com.example.cdg_test.presentation.fragment.NewsSearchFragment
import com.example.cdg_test.presentation.fragment.SourcesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private val sourcesFragment = SourcesFragment.newInstance()
    private val favoriteSourcesFragment = FavoriteSourcesFragment.newInstance()
    private val searchFragment = NewsSearchFragment.newInstance()
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.apply {
            selectedItemId = R.id.bottom_nav_menu_favorites
            setOnNavigationItemSelectedListener { bottomNavItemClick(it) }
        }
        fragment = favoriteSourcesFragment
        routeToFragment(fragment)
    }

    private fun bottomNavItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId){
            R.id.bottom_nav_menu_sources -> {
                if (fragment is SourcesFragment) return true
                title = "Sources"
                fragment = sourcesFragment
            }
            R.id.bottom_nav_menu_favorites -> {

                if (fragment is FavoriteSourcesFragment && fm.backStackEntryCount == 0) return true
                title = "Favorites"
                fragment = favoriteSourcesFragment
            }
            R.id.bottom_nav_menu_search -> {
                if (fragment is NewsSearchFragment) return true
                title = "Search"
                fragment = searchFragment
            }
        }
        routeToFragment(fragment)
        return true
    }

    private fun routeToFragment(fragment: Fragment) {

        fm.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}
