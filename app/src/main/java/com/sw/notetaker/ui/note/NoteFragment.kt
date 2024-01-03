package com.sw.notetaker.ui.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.sw.notetaker.databinding.FragmentNoteBinding
import com.sw.notetaker.ui.base.BaseFragment
import com.sw.notetaker.ui.note.viewModel.NoteViewModelImpl


class NoteFragment : BaseFragment()
{
    private lateinit var binding: FragmentNoteBinding
    private val args: NoteFragmentArgs by navArgs()

    private val viewModel: NoteViewModelImpl by viewModels {
        NoteViewModelImpl.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.init(args.noteId)

        viewModel.note.observe(viewLifecycleOwner)
        {
            binding.llDetailsBackground.setBackgroundColor(
                ContextCompat.getColor(requireContext(), it.type.colour)
            )
        }

        binding.btnEdit.setOnClickListener {
            val action = NoteFragmentDirections.actionNoteFragmentToUpdateNoteFragment(
                args.noteId
            )
            navController.navigate(action)
        }

    }

}