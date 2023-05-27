package com.example.zennexapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.zennexapp.databinding.FragmentMainBinding
import com.example.zennexapp.presentation.MainViewModel
import com.example.zennexapp.ui.pagingadapters.DefaultPagingAdapter
import com.example.zennexapp.ui.pagingadapters.PagingAdapter
import com.example.zennexapp.ui.pagingadapters.TryAgainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

	lateinit var binding: FragmentMainBinding
	private val viewModel: MainViewModel by viewModels()
	private lateinit var adapter: PagingAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMainBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bindAdapter()
		setObservers()
	}

	private fun bindAdapter() {
		adapter = PagingAdapter()
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
}