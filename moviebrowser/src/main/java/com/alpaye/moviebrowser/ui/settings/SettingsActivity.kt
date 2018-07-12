package com.alpaye.moviebrowser.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import butterknife.BindView
import butterknife.OnItemSelected
import com.alpaye.moviebrowser.R
import com.alpaye.moviebrowser.core.BaseActivity
import com.alpaye.moviebrowser.ui.dashboard.DashboardActivity
import com.monitise.mea.android.utils.MTSLanguageUtil
import java.util.Locale

class SettingsActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    @BindView(R.id.activity_settings_spinner_language)
    lateinit var spinnerLanguage: Spinner

    private var initialDisplay = false

    override fun getContentResourceId() = R.layout.activity_settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (MTSLanguageUtil.Preferences.getLocale(applicationContext)?.language == MTSLanguageUtil.TURKISH.language) {
            spinnerLanguage.setSelection(1)
        }
    }

    private fun changeLanguageAndRestart(locale: Locale){
        MTSLanguageUtil.setLocale(this, locale)
        startActivity(Intent(this, DashboardActivity::class.java).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    @OnItemSelected(R.id.activity_settings_spinner_language)
    fun spinnerLanguageSelected(spinner: Spinner, position: Int) {

        if (initialDisplay) {
            val choosenLanguage = spinner.getItemAtPosition(position).toString()
            when (choosenLanguage) {
                getString(R.string.settings_language_turkish) -> changeLanguageAndRestart(MTSLanguageUtil.TURKISH)
                getString(R.string.settings_language_english) -> changeLanguageAndRestart(Locale.ENGLISH)
            }
        } else {
            initialDisplay = true
        }

    }

}