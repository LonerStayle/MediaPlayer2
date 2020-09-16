package com.example.a16mediaplayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

abstract class BaseFragment<VDB:ViewDataBinding>(@LayoutRes private val layoutRes:Int): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(inflater,layoutRes,container,false).run{
        bindingViewData()
        setEventListener()
        root
    }

    protected abstract fun VDB.bindingViewData()
    protected abstract fun VDB.setEventListener()

}