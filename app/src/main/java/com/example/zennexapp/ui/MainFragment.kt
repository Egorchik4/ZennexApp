package com.example.zennexapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.zennexapp.databinding.FragmentMainBinding
import com.example.zennexapp.presentation.MainViewModel
import com.example.zennexapp.presentation.state.NewsItemState
import com.example.zennexapp.presentation.state.NewsState
import com.example.zennexapp.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

	lateinit var binding: FragmentMainBinding
	private val viewModel: MainViewModel by viewModels()
	private lateinit var adapter: NewsAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMainBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bindAdapter()
		setListeners()
		setObservers()
	}

	private fun bindAdapter() {
		adapter = NewsAdapter()
		binding.recyclerView.adapter = adapter
	}

	private fun setListeners() {

	}

	private fun setObservers() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: NewsState) {
		when (state) {
			is NewsState.Loading        -> renderLoadingState()
			is NewsState.Error          -> renderError()
			is NewsState.NewsListEntity -> renderContentListAutoState(state.newsItemStateList)
		}
	}

	private fun renderLoadingState() {
		with(binding) {
			progressBar.visibility = View.VISIBLE
		}
	}

	private fun renderError() {
	}

	private fun renderContentListAutoState(state: List<NewsItemState>) {
		adapter.newsList = state
		with(binding) {
			progressBar.visibility = View.GONE
		}
	}

}