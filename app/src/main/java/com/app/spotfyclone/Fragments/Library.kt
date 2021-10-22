package com.app.spotfyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.spotfyclone.FragmentsTab.Albuns
import com.app.spotfyclone.FragmentsTab.Artistas
import com.app.spotfyclone.FragmentsTab.Playlist
import com.app.spotfyclone.R
import com.app.spotfyclone.databinding.FragmentLibraryBinding
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class Library : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    private var fragmentsLibary:FragmentLibraryBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentsLibary = FragmentLibraryBinding.bind(view)

        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)
            .add("Playlist", Playlist::class.java)
            .add("Artistas", Artistas::class.java)
            .add("Álbums", Albuns::class.java)
            .create())

        fragmentsLibary!!.viewpager.adapter = adapter
        fragmentsLibary!!.viewpagertab.setViewPager(fragmentsLibary!!.viewpager)


    }

}