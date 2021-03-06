package com.cocktailsppc.cocktails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.cocktailsppc.cocktails.ItemDetails.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tabbed_item.*


class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabbed_item)

        val cocktail = intent.getParcelableExtra<Cocktail>("cocktail")

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, cocktail!!)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = sectionsPagerAdapter.count
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        if(!cocktail.reviewIs) { // display back arrow while not in review mode
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            finish_button_preview.visibility=View.GONE
        }
        finish_button_preview.setOnClickListener {
            finish()
        }

        val activityTitle: TextView = findViewById(R.id.title)
        activityTitle.text = cocktail.name

        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}