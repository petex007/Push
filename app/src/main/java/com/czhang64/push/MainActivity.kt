package com.czhang64.push

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this,navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when(destination.id){
                    R.id.settingsFragment -> getString(R.string.Settings)
                    else -> getString((R.string.app_name))
                }
            }
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings->{
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                true
            }
//            R.id.action_info->{
//                navHostFragment.navController.navigate(R.id.action_mainFragment_to_infoFragment)
//                true
//            }
//            R.id.action_web->{
//                navHostFragment.navController.navigate(R.id.action_mainFragment_to_webViewFragment)
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        private lateinit var instance: MainActivity
        const val DEFAULT_WEBVIEW_URL = "https://www.self.com/story/fitness-tips-trainers-give-beginner-clients"
    }
}