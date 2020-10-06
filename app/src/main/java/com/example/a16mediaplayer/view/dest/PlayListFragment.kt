package com.example.a16mediaplayer.view.dest

import android.Manifest
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.a16mediaplayer.R
import com.example.a16mediaplayer.base.BaseFragment
import com.example.a16mediaplayer.databinding.FragmentPlayListBinding
import com.example.a16mediaplayer.view.adapter.PlayListItemAdapter
import com.example.a16mediaplayer.viewmodel.PlayListViewModel
import com.example.a16mediaplayer.viewmodel.PlayListViewModelFactory
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.RuntimePermissions


@RuntimePermissions
class PlayListFragment : BaseFragment<FragmentPlayListBinding>(R.layout.fragment_play_list) {
    private val viewModel by viewModels<PlayListViewModel> { PlayListViewModelFactory(requireContext()) }
    private val playListAdapter = PlayListItemAdapter()

    override fun FragmentPlayListBinding.bindingViewData() {
        rvPlayList.adapter = playListAdapter

        viewModel.playList.observe(viewLifecycleOwner, Observer {
            playListAdapter.playList = it
        })

        val root = arguments?.getString("root") ?: ""
        if (root == "ringtone") viewModel.getPlayList(root)
        else fetchPlayList(root)

        viewModel.getPlayList(arguments?.getString("root") ?: "")
    }

    override fun FragmentPlayListBinding.setEventListener() {

    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun fetchPlayList(root: String) {
        viewModel.getPlayList(root)
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun onMediaNaverAskAgain() {
        Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

}