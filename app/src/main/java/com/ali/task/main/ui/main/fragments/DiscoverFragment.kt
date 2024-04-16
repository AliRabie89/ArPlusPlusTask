package com.ali.task.main.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.*
import androidx.fragment.app.viewModels
import com.ali.task.R
import com.ali.task.databinding.FragmentDiscoverBinding
import com.ali.task.main.base.BaseFragment
import com.ali.task.main.base.CustomLoadOnScrollAdapterCustom
import com.ali.task.main.common.extensions.addDebouncedTextChangedListener
import com.ali.task.main.common.extensions.handleResultSharedFlow
import com.ali.task.main.common.extensions.openUrl
import com.ali.task.main.common.extensions.showSuccessMessage
import com.ali.task.main.data.models.Article
import com.ali.task.main.data.models.EndPointResult
import com.ali.task.main.ui.main.adapters.ArticlesAdapter
import com.ali.task.main.ui.main.viewModels.ArticlesViewModel


class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {
    lateinit var binding : FragmentDiscoverBinding
    private val articlesViewModel : ArticlesViewModel by viewModels()
    private lateinit var articlesAdapter: ArticlesAdapter
    private var pageNumber = 1
    private var searchKey = ""
    override fun setUpLayoutView() {
        binding = FragmentDiscoverBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchUi()
        initListUi()
        initData()
    }

    private fun initSearchUi() {
        binding.searchView.addDebouncedTextChangedListener {
            searchKey = it
            pageNumber = 1
            if(searchKey.isEmpty()){
                articlesAdapter.setInitialData(emptyList())
                binding.emptyFlag.visibility = View.VISIBLE
            }else{
                articlesViewModel.callArticles(searchKey,pageNumber)
            }
        }
    }

    private fun initData() {
        handleResultSharedFlow(articlesViewModel.articlesFlow, onSuccess = {
            if(it is EndPointResult<*>){
                if(articlesAdapter.itemCount == 0){
                    binding.emptyFlag.visibility = View.VISIBLE
                }else{
                    binding.emptyFlag.visibility = View.GONE
                }
                if(it.status.equals("ok")){
                    val articles = it.articles as List<Article>
                    if(articles.isNotEmpty()){
                        articlesAdapter.setLoading()
                    }else{
                        articlesAdapter.setLoaded()
                    }
                    articlesAdapter.addItems(articles)
                }
            }
        }, onShowProgress = {
            binding.articlesList.showShimmer()
        }, onHideProgress = {
            binding.articlesList.hideShimmer()
        })
    }

    private fun initListUi() {
        articlesAdapter = ArticlesAdapter(
            onItemUrlClicked = {
                            requireContext().openUrl(it)
        }, onAddToFavoriteClicked = {
            articlesViewModel.addArticleToFavorite(it)
                showSuccessMessage(getString(R.string.added_to_favorite))
        })
        articlesAdapter.setLoadMoreListener(object : CustomLoadOnScrollAdapterCustom.OnLoadMoreListener{
                override fun onLoadMore() {
                    if(articlesAdapter.isLoading()){
                        val nextPage = pageNumber+1
                        articlesViewModel.callArticles(searchKey,nextPage)
                    }
                }
        })

        binding.articlesList.apply {
            adapter = articlesAdapter
        }
    }
}