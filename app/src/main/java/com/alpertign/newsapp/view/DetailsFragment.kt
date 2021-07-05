package com.alpertign.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alpertign.newsapp.view.DetailsFragmentArgs
import com.alpertign.newsapp.R
import com.alpertign.newsapp.viewmodel.NewsDetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {


    private lateinit var viewModel : NewsDetailsViewModel
    private var newsUrl = ""
    private lateinit var pageUrl : String
    private var dummyPageUrl : String = "https://www.google.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsDetailsViewModel::class.java)

        arguments?.let {
            newsUrl = DetailsFragmentArgs.fromBundle(it).newsUrl
        }
        observeLiveData()
        webViewSetup(newsUrl)
    }

    private fun observeLiveData(){
        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer { article ->
            article?.let {
                pageUrl = article.url.toString()


                //tekrar tekrar yükleyecek dikkat!
            }
        })
    }

    private fun webViewSetup(url: String) {
        webViewDetails.webViewClient = WebViewClient()

        webViewDetails.apply {
            loadUrl(url)
            settings.javaScriptEnabled
        }

    }




}