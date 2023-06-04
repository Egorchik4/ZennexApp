package com.example.zennexapp.presentation.pagingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.zennexapp.databinding.NewsItemBinding
import com.example.zennexapp.domain.entity.ArticleEntity

class PagingAdapter(
	private val onItemClickAction: (articleEntity: ArticleEntity) -> Unit
) : PagingDataAdapter<ArticleEntity, PagingAdapter.Holder>(UsersDiffCallback()) {

	class Holder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(articleEntity: ArticleEntity) {
			with(binding) {
				Glide.with(itemView)
					.load(articleEntity.urlToImage)
					.circleCrop()
					.diskCacheStrategy(DiskCacheStrategy.NONE)
					.into(imageView)
				titleText.text = articleEntity.title
				textDescription.text = articleEntity.description
				textData.text = articleEntity.publishedAt
			}
		}
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		val item = getItem(position) ?: return
		holder.bind(item)
		holder.itemView.setOnClickListener {
			onItemClickAction(item)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = NewsItemBinding.inflate(inflater, parent, false)
		return Holder(binding)
	}
}

class UsersDiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {

	override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
		return oldItem.url == newItem.url
	}

	override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
		return oldItem == newItem
	}
}