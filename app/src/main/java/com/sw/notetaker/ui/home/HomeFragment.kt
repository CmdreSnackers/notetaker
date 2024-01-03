package com.sw.notetaker.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.notetaker.databinding.FragmentHomeBinding

import com.sw.notetaker.ui.adapter.WordAdapter
import com.sw.notetaker.ui.base.BaseFragment
import com.sw.notetaker.ui.home.viewModel.HomeViewModelImpl

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: WordAdapter

    private val viewModel: HomeViewModelImpl by viewModels {
        HomeViewModelImpl.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNewNote(it)
        }

        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            navController.navigate(action)
        }

        setupAdapter()

        setFragmentResultListener("from_add_todo_fragment") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.fetchNotes()
            }
        }

        setFragmentResultListener("from_update_todo_fragment") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.fetchNotes()
            }
        }
    }

    fun setupAdapter() {
        adapter = WordAdapter(
            emptyList(),
            {
                val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(it.id)
                navController.navigate(action)
            },
            {
                viewModel.deleteNote(it)
            }
        )
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager
    }
}