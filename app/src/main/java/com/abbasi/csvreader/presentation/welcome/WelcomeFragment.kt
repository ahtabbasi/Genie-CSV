package com.abbasi.csvreader.presentation.welcome

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abbasi.csvreader.R
import com.abbasi.csvreader.commons.utils.EventObserver
import com.abbasi.csvreader.databinding.FragmentWelcomeBinding
import com.google.android.material.snackbar.Snackbar

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWelcomeBinding.inflate(inflater, container, false).also {
        binding = it
        setupUI()
    }.root

    private fun setupUI() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.selectFileClicked.observe(viewLifecycleOwner, EventObserver {
            pickCsv()
        })
    }


    /**
     * Initiates the interface for file picker from device
     * and returns success with the file if it has a csv extension
     */
    private fun pickCsv() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/comma-separated-values"
        }

        csvPicker.launch(intent)
    }


    private var csvPicker = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            result.data?.data.let {
                if (it != null) navigateToLoadingScreen(it)
                else showError(getString(R.string.unable_to_access))
            }
        }
    }


    private fun showError(errorMsg: String) {
        Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_SHORT).show()
    }


    private fun navigateToLoadingScreen(csvUri: Uri) {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToPreviewFragment(
            csvUri = csvUri.toString()
        )
        findNavController().navigate(action)
    }

}