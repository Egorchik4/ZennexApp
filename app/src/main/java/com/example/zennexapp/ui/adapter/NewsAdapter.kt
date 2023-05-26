package com.example.zennexapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.zennexapp.databinding.NewsItemBinding
import com.example.zennexapp.presentation.state.NewsItemState

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsListHolder>() {

	var newsList: List<NewsItemState> = emptyList()
		set(newValue) {
			val diffUtil = NewsListDiffUtil(field, newValue)
			val diffResult = DiffUtil.calculateDiff(diffUtil)
			field = newValue
			diffResult.dispatchUpdatesTo(this)
		}

	class NewsListHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(newsState: NewsItemState) {
			with(binding) {
				when (newsState) {
					is NewsItemState.Loading     -> {

					}

					is NewsItemState.Error       -> {

					}

					is NewsItemState.ArticleItem -> {
						Glide.with(itemView)
							.load(newsState.newsItem.urlToImage)
							.diskCacheStrategy(DiskCacheStrategy.NONE)
							.into(imageView)
						titleText.text = newsState.newsItem.title
						textDescription.text = newsState.newsItem.description
						textData.text = newsState.newsItem.publishedAt
					}
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = NewsItemBinding.inflate(inflater, parent, false)
		return NewsListHolder(binding)
	}

	override fun onBindViewHolder(holder: NewsListHolder, position: Int) {
		holder.bind(newsList[position])
	}

	override fun getItemCount() = newsList.size
}