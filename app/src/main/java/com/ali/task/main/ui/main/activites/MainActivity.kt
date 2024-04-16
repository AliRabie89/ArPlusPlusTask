/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.main.activites

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ali.task.R
import com.ali.task.databinding.MainActivityBinding
import com.ali.task.main.base.BaseActivity
import com.ali.task.main.ui.main.adapters.ViewPagerAdapter
import com.ali.task.main.ui.main.fragments.DiscoverFragment
import com.ali.task.main.ui.main.fragments.FavoritesFragment
import com.ali.task.main.ui.main.viewModels.ArticlesViewModel


class MainActivity : BaseActivity() {

    lateinit var binding : MainActivityBinding
    lateinit var pagerAdapter : ViewPagerAdapter
    val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomBar.itemActiveIndex = position
            when(position){
                0->{
                    binding.toolbar.title = getString(R.string.discover_articles)
                }
                1->{
                    binding.toolbar.title = getString(R.string.my_favorites)
                }
            }
        }
    }

    override fun setUpLayoutView(): View {
        binding = MainActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavBarUI()
        initViewPager()
    }

    private fun initViewPager() {
        pagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle,listOf(
            DiscoverFragment(),FavoritesFragment()))
        binding.pager.isSaveEnabled = false
        binding.pager.offscreenPageLimit = 1
        binding.pager.adapter = pagerAdapter
        binding.pager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.pager.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    private fun initBottomNavBarUI() {
        binding.bottomBar.setOnItemSelectedListener {
            binding.pager.currentItem = it
            when(it){
                0->{
                    binding.toolbar.title = getString(R.string.discover_articles)
                }
                1->{
                    binding.toolbar.title = getString(R.string.my_favorites)
                }
            }
        }
    }

}