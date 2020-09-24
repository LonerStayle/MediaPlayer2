package com.example.a16mediaplayer.view.dest

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.a16mediaplayer.R
import com.example.a16mediaplayer.base.BaseFragment
import com.example.a16mediaplayer.databinding.FragmentPlayListBinding
import com.example.a16mediaplayer.view.adapter.PlayListItemAdapter
import com.example.a16mediaplayer.viewmodel.PlayListViewModel
import com.example.a16mediaplayer.viewmodel.PlayListViewModelFactory

class PlayListFragment : BaseFragment<FragmentPlayListBinding>(R.layout.fragment_play_list) {
    private val viewModel by viewModels<PlayListViewModel> { PlayListViewModelFactory(requireContext()) }
    private val playListAdapter = PlayListItemAdapter()

    override fun FragmentPlayListBinding.bindingViewData() {
        rvPlayList.adapter = playListAdapter

        viewModel.playList.observe(viewLifecycleOwner, Observer {
            playListAdapter.playList = it
        })

        viewModel.getPlayList(arguments?.getString("root") ?: "")
    }

    override fun FragmentPlayListBinding.setEventListener() {

    }

}