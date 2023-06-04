package com.example.zennexapp.presentation.pagingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zennexapp.databinding.DefaultPagingItemBinding

typealias TryAgainAction = () -> Unit

class DefaultPagingAdapter(
	private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<DefaultPagingAdapter.DefaultHolder>() {

	class DefaultHolder(
		private val binding: DefaultPagingItemBinding,
		private val tryAgainAction: TryAgainAction
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			binding.tryAgainButton.setOnClickListener { tryAgainAction() }
		}

		fun bind(loadState: LoadState) = with(binding) {
			messageTextView.isVisible = loadState is LoadState.Error
			tryAgainButton.isVisible = loadState is LoadState.Error
			progressBar.isVisible = loadState is LoadState.Loading
		}
	}

	override fun onBindViewHolder(holder: DefaultHolder, loadState: LoadState) {
		holder.bind(loadState)
	}

	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): DefaultHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = DefaultPagingItemBinding.inflate(inflater, parent, false)
		return DefaultHolder(binding, tryAgainAction)
	}
}