package com.example.hotnews.ui.fragments

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hotnews.databinding.FragmentArticleBinding
import com.example.hotnews.ui.NewsActivity
import com.example.hotnews.ui.NewsViewModel
import com.example.hotnews.R

class ArticleFragment : Fragment(R.layout.fragment_article) {
     lateinit var newsViewModel: NewsViewModel
     lateinit var binding: FragmentArticleBinding
     val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        newsViewModel = (activity as NewsActivity).viewModel  // This now matches
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            newsViewModel.addtoFavorites(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}