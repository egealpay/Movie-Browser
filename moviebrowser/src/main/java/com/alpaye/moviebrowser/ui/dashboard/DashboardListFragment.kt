package com.alpaye.moviebrowser.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment
import com.monitise.mea.android.ui.views.MTSEndlessRecyclerView

abstract class DashboardListFragment : BaseFragment() {

    @BindView(R.id.fragment_dashboard_list_recyclerview)
    lateinit var endlessRecyclerViewDashboard: MTSEndlessRecyclerView

    override fun getResourceLayoutId() = R.layout.fragment_dashboard_list

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
    }

}
