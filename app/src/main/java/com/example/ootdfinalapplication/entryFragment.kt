package com.example.ootdfinalapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ootdfinalapplication.databinding.FragmentEntryBinding
import com.example.ootdfinalapplication.viewmodel.EntryViewModel

class entryFragment : Fragment() {

    private lateinit var detailViewAdapter: DetailViewAdapter
    private var binding: FragmentEntryBinding? = null
    private val viewModel: EntryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerViewEntry?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewEntry?.adapter = DetailViewAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding?.recyclerViewEntry ?: return binding?.root
        detailViewAdapter = DetailViewAdapter(viewModel)
        recyclerView.adapter = detailViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
