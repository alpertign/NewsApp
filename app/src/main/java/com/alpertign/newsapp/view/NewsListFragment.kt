package com.alpertign.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpertign.newsapp.R
import com.alpertign.newsapp.adapter.ArticleListAdapter
import com.alpertign.newsapp.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*


class NewsListFragment : Fragment() {

    private lateinit var  viewModel : NewsListViewModel
    private val articleAdapter = ArticleListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)
        viewModel.refreshData()

        newsList.layoutManager = LinearLayoutManager(context)
        newsList.adapter = articleAdapter

        swipeRefreshLayout.setOnRefreshListener {
            newsList.visibility = View.GONE
            newsError.visibility = View.GONE
            newsLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                newsList.visibility = View.VISIBLE
                articleAdapter.updateArticleList(articles)
            }

        })

        viewModel.articleError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    newsError.visibility = View.VISIBLE
                } else {
                    newsError.visibility = View.GONE
                }
            }
        })

        viewModel.articleLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    newsLoading.visibility = View.VISIBLE
                    newsList.visibility = View.GONE
                    newsError.visibility = View.GONE
                }else{
                    newsLoading.visibility = View.GONE
                }
            }
        })
    }


}