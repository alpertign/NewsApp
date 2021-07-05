package com.alpertign.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alpertign.newsapp.R
import com.alpertign.newsapp.model.Article
import com.alpertign.newsapp.util.downloadFromUrl
import com.alpertign.newsapp.util.placeholderProgressBar
import com.alpertign.newsapp.view.NewsListFragmentDirections
import kotlinx.android.synthetic.main.item_news.view.*


class ArticleListAdapter(val articleList : ArrayList<Article>) : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {
    class ArticleViewHolder (var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.view.title_item.text = articleList[position].title
        holder.view.description_item.text = articleList[position].description
        holder.view.author_item.text = articleList[position].author

        holder.view.setOnClickListener {
            val action = articleList[position].url?.let { it1 ->
                NewsListFragmentDirections.actionNewsListFragmentToDetailsFragment(
                    it1
                )
            }
            action?.let { it1 -> Navigation.findNavController(it).navigate(it1) }
        }

        holder.view.iv_news_list.downloadFromUrl(articleList[position].urlToImage,
            placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticleList(newArticleList : List<Article>){
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
}