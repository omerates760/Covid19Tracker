package com.monofire.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.monofire.country.adapter.CountryAdapter
import com.monofire.domain.model.CountryUiData
import com.monofire.country.databinding.FragmentCountryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryViewModel by viewModels()
    private val adapter = CountryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        subscribeViewModel()
    }

    private fun setUpAdapter() {
        binding.countryRecyclerview.adapter = adapter
        adapter.setOnItemCountryClickListener {
            val action = CountryFragmentDirections.actionCountryFragmentToCountryDetailFragment(
                it.countryName
            )
            findNavController().navigate(action)

        }
    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countries.collect(::countriesCollector)
            }
        }
    }

    private fun countriesCollector(countries: List<CountryUiData>) {
        if (countries.isEmpty()) return
        adapter.submitList(countries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}