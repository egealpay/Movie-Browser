package com.alpaye.moviebrowser.ui.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import butterknife.BindView
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseFragment

abstract class DashboardListFragment : BaseFragment() {

    @BindView(R.id.dashboardlist_fragment_recyclerview)
    lateinit var dashboardRecyclerView: RecyclerView

    override fun getResourceLayoutId() = R.layout.fragment_dashboard_list

    override fun initUserInterface(inflater: LayoutInflater, rootView: View) {
        super.initUserInterface(inflater, rootView)
    }

}
