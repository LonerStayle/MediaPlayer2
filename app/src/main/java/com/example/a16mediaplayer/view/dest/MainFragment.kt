package com.example.a16mediaplayer.view.dest

import com.example.a16mediaplayer.R
import com.example.a16mediaplayer.base.BaseFragment
import com.example.a16mediaplayer.databinding.FragmentMainBinding
import com.example.a16mediaplayer.view.adapter.PlayListPageAdapter
import com.google.android.material.tabs.TabLayoutMediator
//컨트롤 + 알트 O 시 임포트 최적화
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun FragmentMainBinding.bindingViewData() {
        viewPager.adapter = PlayListPageAdapter(this@MainFragment)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ringtone"
                1 -> "App"
                2 -> "Music"
                else -> ""
            }

        }.attach()
    }

    override fun FragmentMainBinding.setEventListener() {

    }

}