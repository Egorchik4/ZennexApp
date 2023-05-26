package com.example.zennexapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.zennexapp.presentation.state.NewsItemState

class NewsListDiffUtil(
	private val oldList: List<NewsItemState>,
	private val newList: List<NewsItemState>
) : DiffUtil.Callback() {

	override fun getOldListSize() =
		oldList.size

	override fun getNewListSize() =
		newList.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return if (oldList[oldItemPosition] is NewsItemState.ArticleItem && newList[newItemPosition] is NewsItemState.ArticleItem) {
			val old = oldList[oldItemPosition] as NewsItemState.ArticleItem
			val new = newList[newItemPosition] as NewsItemState.ArticleItem
			old.newsItem.title == new.newsItem.title
		} else {
			true
		}
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition] == newList[newItemPosition]
	}
}