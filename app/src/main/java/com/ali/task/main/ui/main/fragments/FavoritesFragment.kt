package com.ali.task.main.ui.main.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ali.task.R
import com.ali.task.databinding.FragmentFavoritesBinding
import com.ali.task.main.base.BaseFragment
import com.ali.task.main.common.extensions.handleResultSharedFlow
import com.ali.task.main.common.extensions.openUrl
import com.ali.task.main.common.extensions.showSuccessMessage
import com.ali.task.main.data.models.Article
import com.ali.task.main.ui.main.adapters.FavoritesAdapter
import com.ali.task.main.ui.main.viewModels.ArticlesViewModel

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    lateinit var binding : FragmentFavoritesBinding
    lateinit var favoritesAdapter: FavoritesAdapter
    private val articlesViewModel : ArticlesViewModel by viewModels()
    override fun setUpLayoutView() {
        binding = FragmentFavoritesBinding.bind(requireView())
    }

    override fun onResume() {
        super.onResume()
        articlesViewModel.callArticlesFromFavorites()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListUi()
        initData()
    }

    private fun initData() {
        handleResultSharedFlow(articlesViewModel.favoritesFlow, onSuccess = {
            if(it is List<*>){
                favoritesAdapter.setInitialData(it as List<Article>)
                if(it.isEmpty()){
                    binding.emptyFlag.visibility = View.VISIBLE
                }else{
                    binding.emptyFlag.visibility = View.GONE
                }
            }
        })
        articlesViewModel.callArticlesFromFavorites()
    }


    private fun initListUi() {
        favoritesAdapter = FavoritesAdapter(onItemUrlClicked = {
            requireContext().openUrl(it)
        },
            onRemoveFromFavoriteClicked = {
                articlesViewModel.deleteFavorite(it)
        })
        binding.articlesList.adapter = favoritesAdapter
    }
}