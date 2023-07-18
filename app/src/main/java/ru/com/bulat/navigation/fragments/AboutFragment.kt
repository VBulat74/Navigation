package ru.com.bulat.navigation.fragments

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.com.bulat.contract.HasCustomTitle
import ru.com.bulat.contract.navigator
import ru.com.bulat.navcomponent.R
import ru.com.bulat.navcomponent.databinding.FragmentAboutBinding


class AboutFragment : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAboutBinding.inflate(inflater, container, false).apply {

        var version = ""
        var version_code = ""
        try {
            val pInfo = requireContext().packageManager.getPackageInfo(
                requireContext().packageName, 0
            )
            version = pInfo.versionName
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                version_code = pInfo.getLongVersionCode().toString()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        versionNameTextView.text = version
        versionCodeTextView.text = version_code
        okButton.setOnClickListener { onOkPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.about

    private fun onOkPressed() {
        navigator().goBack()
    }

}