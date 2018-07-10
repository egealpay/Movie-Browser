package com.alpaye.moviebrowser.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.alpaye.moviebrowser.R
import com.monitise.mea.android.core.activities.MTSBaseActivity

abstract class BaseActivity : MTSBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentResourceId)
        ButterKnife.bind(this)
    }

    override fun getContainedFragment(): Fragment? {
        return null
    }

}