package com.monofire.country.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.monofire.domain.model.CountryDetailUiData
import com.monofire.country.R
import com.monofire.country.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryDetailViewModel by viewModels()

    private val fragmentArgs: CountryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountryDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountryDetail(fragmentArgs.countryName)
        subscribeViewModel()
        setUpToolbar()
        initUiClickListeners()
    }

    private fun initUiClickListeners() {
        binding.historyButton.setOnClickListener {
            val request = NavDeepLinkRequest
                .Builder
                .fromUri("$HISTORY_FRAGMENT_DEEPLINK${binding.country?.countryName}/${binding.country?.day}".toUri())
                .build()
            findNavController().navigate(request)
        }
    }

    private fun setUpToolbar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.share_menu -> {
                    shareCountryStatistics()
                    true
                }
                else -> {
                    false
                }
            }

        }
    }


    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.country.collect(::countryDetailCollector)
            }
        }

    }

    private fun countryDetailCollector(countryDetailUiData: CountryDetailUiData?) {
        countryDetailUiData?.let {
            binding.country = it
        }
    }

    private fun shareCountryStatistics() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TITLE, binding.country?.countryName)
        shareIntent.putExtra(Intent.EXTRA_TEXT, binding.country.toString());
        startActivity(Intent.createChooser(shareIntent, null))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val HISTORY_FRAGMENT_DEEPLINK =
            "android-app://com.monofire.covid19tracker/history_fragment/"
    }

}