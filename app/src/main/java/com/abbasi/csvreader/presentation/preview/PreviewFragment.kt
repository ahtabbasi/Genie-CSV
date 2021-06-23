package com.abbasi.csvreader.presentation.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.abbasi.csvreader.commons.mappers.CHMappers
import com.abbasi.csvreader.commons.mappers.CellMappers
import com.abbasi.csvreader.commons.utils.Resource
import com.abbasi.csvreader.databinding.FragmentPreviewBinding
import com.abbasi.csvreader.presentation.preview.adapters.MyTableViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment() {

    private val viewModel: PreviewViewModel by viewModels()
    private lateinit var binding: FragmentPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPreviewBinding.inflate(inflater, container, false).also {
        binding = it
        setupUI()
    }.root


    private fun setupUI() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupTable()
        setupObservers()

        val args: PreviewFragmentArgs by navArgs()
        viewModel.parse(args.csvUri)
    }

    private fun setupTable() {
        binding.mainTable.setAdapter(MyTableViewAdapter())
        binding.mainTable.rowHeaderWidth = 0 //hiding the row header
    }

    private fun setupObservers() {
        viewModel.parsedData.observe(viewLifecycleOwner) {
            if (it is Resource.Valid) {
                (binding.mainTable.adapter as? MyTableViewAdapter)?.setAllItems(
                    CHMappers.listToCHs(it.data.firstOrNull() ?: emptyList()),
                    emptyList(), // not using row header
                    CellMappers.listOfListsToCells(it.data.drop(1))
                )
            }
        }
    }
}