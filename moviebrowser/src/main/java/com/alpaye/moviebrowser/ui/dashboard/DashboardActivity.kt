package com.alpaye.moviebrowser.ui.dashboard

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.EditText
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseActivity

class DashboardActivity : BaseActivity() {

    @BindView(R.id.activity_dashboard_viewpager)
    lateinit var viewPagerDashboard: ViewPager

    @BindView(R.id.activity_dashboard_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.drawer_layout)
    lateinit var mDrawerLayout: DrawerLayout

    @BindView(R.id.activity_dashboard_toolbar_searchview)
    lateinit var searchView: SearchView

    @BindView(R.id.activity_dashboard_tablayout)
    lateinit var tabLayout: TabLayout

    override fun getContentResourceId() = R.layout.activity_dashboard

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_24)
        }

        val editText = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
        editText.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.colorWhite))

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerDashboard.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPagerDashboard)

    }

}