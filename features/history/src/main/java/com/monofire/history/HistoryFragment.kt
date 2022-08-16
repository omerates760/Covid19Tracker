package com.monofire.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.monofire.domain.model.HistoryUiData
import com.monofire.history.adapter.HistoryAdapter
import com.monofire.history.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding

    private val viewModel: HistoryViewModel by viewModels()
    private val fragmentArgs: HistoryFragmentArgs by navArgs()

    private val adapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = viewLifecycleOwner
        viewModel.getHistory(fragmentArgs.countryName,fragmentArgs.day)
        setUpAdapter()
        subscribeViewModel()
    }

    private fun setUpAdapter() {
        binding?.historyRecyclerview?.adapter = adapter
    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.histories.collect(::userCollector)
            }
        }
    }

    private fun userCollector(users: List<HistoryUiData>) {
        if (users.isEmpty()) return
        adapter.submitList(users)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}