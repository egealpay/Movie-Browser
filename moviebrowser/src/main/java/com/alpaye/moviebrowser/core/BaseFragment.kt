package com.alpaye.moviebrowser.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import butterknife.ButterKnife
import com.monitise.mea.android.core.fragments.MTSNetworkFragment
import com.monitise.mea.android.core.utils.MTSCallerIdProvider
import com.monitise.mea.android.network.bus.NetworkBus
import com.monitise.mea.android.network.bus.NetworkSubscriber

abstract class BaseFragment : MTSNetworkFragment(), NetworkSubscriber {

    private val callerIdProvider = MTSCallerIdProvider()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callerIdProvider.createOrUpdate(savedInstanceState)
        NetworkBus.getInstance().register(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        callerIdProvider.save(outState)
    }

    override fun onStop() {
        NetworkBus.getInstance().unregister(this, false)
        super.onStop()
    }

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        ButterKnife.bind(this, rootView)
    }

    override fun getCallerId(): Int {
        return callerIdProvider.callerId
    }

}