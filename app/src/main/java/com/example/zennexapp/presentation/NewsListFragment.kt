package com.example.zennexapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.zennexapp.databinding.FragmentNewsListBinding
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.presentation.pagingadapters.DefaultPagingAdapter
import com.example.zennexapp.presentation.pagingadapters.PagingAdapter
import com.example.zennexapp.presentation.pagingadapters.TryAgainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

	lateinit var binding: FragmentNewsListBinding
	private val viewModel: NewsListViewModel by viewModels()
	private lateinit var adapter: PagingAdapter

	companion object {

		const val TEXT_MESSAGE = "Url is Empty"
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentNewsListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bindAdapter()
		setObservers()
		setListeners()
	}

	private fun bindAdapter() {
		adapter = PagingAdapter(::openWebView)
		val tryAgainAction: TryAgainAction = { adapter.retry() }
		val footerAdapter = DefaultPagingAdapter(tryAgainAction)
		val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)
		binding.recyclerView.adapter = adapterWithLoadState
	}

	private fun setObservers() {
		lifecycleScope.launch {
			viewModel.usersFlow.collectLatest {
				adapter.submitData(it)
			}
		}
	}

	private fun setListeners() {
		with(binding) {
			cancelButton.setOnClickListener {
				cancelButton.visibility = View.GONE
				webView.visibility = View.GONE
				webView.goBack()
			}
		}
	}

	private fun openWebView(entity: ArticleEntity) {
		with(binding) {
			cancelButton.visibility = View.VISIBLE
			webView.visibility = View.VISIBLE
			if (entity.url.isNullOrEmpty()) {
				Toast.makeText(requireContext(), TEXT_MESSAGE, Toast.LENGTH_SHORT).show()
			} else {
				webView.webViewClient = WebViewClient()
				webView.loadUrl(entity.url)
			}
		}
	}
}