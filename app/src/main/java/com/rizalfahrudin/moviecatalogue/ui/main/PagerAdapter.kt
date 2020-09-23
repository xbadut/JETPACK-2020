package com.rizalfahrudin.moviecatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizalfahrudin.moviecatalogue.ui.main.content.MovieTvFragment
import com.rizalfahrudin.moviecatalogue.ui.main.content.MovieTvFragment.Companion.PAGE
import com.rizalfahrudin.moviecatalogue.ui.main.content.MovieTvFragment.Companion.POSITION_TAB

class PagerAdapter(activity: AppCompatActivity, private val page: Int) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieTvFragment()
        fragment.arguments = Bundle().apply {
            putInt(POSITION_TAB, position)
            putInt(PAGE, page)
        }
        return fragment
    }
}
