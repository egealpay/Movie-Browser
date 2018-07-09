package com.alpaye.moviebrowser.core

import android.os.Bundle
import android.support.v4.app.Fragment
import butterknife.ButterKnife
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