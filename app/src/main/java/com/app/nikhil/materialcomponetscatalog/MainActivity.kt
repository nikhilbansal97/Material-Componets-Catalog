package com.app.nikhil.materialcomponetscatalog

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.app.nikhil.materialcomponetscatalog.R.drawable
import com.app.nikhil.materialcomponetscatalog.R.id
import com.app.nikhil.materialcomponetscatalog.fragments.BottomAppBarFragment
import com.app.nikhil.materialcomponetscatalog.fragments.BottomNavigationFragment
import com.app.nikhil.materialcomponetscatalog.fragments.ChipFragment
import kotlinx.android.synthetic.main.activity_main.drawerLayout
import kotlinx.android.synthetic.main.activity_main.navigationView

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initToolbar()

    navigationView.setNavigationItemSelectedListener { menuItem ->
      menuItem.isChecked = true
      drawerLayout.closeDrawers()
      openFragment(menuItem)
      true
    }
  }

  private fun initToolbar() {
    val toolbar: Toolbar = findViewById(id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(true)
      setHomeAsUpIndicator(drawable.ic_menu)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        drawerLayout.openDrawer(GravityCompat.START)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun openFragment(menuItem: MenuItem) {
    val fragment = when (menuItem.itemId) {
      R.id.itemBottomAppBar -> BottomAppBarFragment()
      R.id.itemBottomNavigation -> BottomNavigationFragment()
      R.id.chip -> ChipFragment()
      else -> null
    }
    fragment?.let {
      supportFragmentManager.beginTransaction()
          .replace(R.id.fragmentContainer, it)
          .commit()
    }
  }
}
