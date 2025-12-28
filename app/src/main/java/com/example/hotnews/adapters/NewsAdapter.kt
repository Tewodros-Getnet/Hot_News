package com.example.hotnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotnews.R
import com.example.hotnews.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // DiffUtil callback should be inside the adapter class
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize views here, not with lateinit
        val articleImageView: ImageView = itemView.findViewById(R.id.articleImage)
        val articleTitle: TextView = itemView.findViewById(R.id.articleTitle)
        val articleDescription: TextView = itemView.findViewById(R.id.articleDescription)
        val articleSource: TextView = itemView.findViewById(R.id.articleSource)
        val articleDateTime: TextView = itemView.findViewById(R.id.articleDateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        // Set click listener on the itemView
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(article)
        }

        // Load image with Glide
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.articleImageView)
        holder.articleTitle.text = article.title
        holder.articleDescription.text = article.description
        holder.articleSource.text = article.source?.name ?: "Unknown"
        holder.articleDateTime.text = article.publishedAt
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}