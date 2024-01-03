package com.sw.notetaker.ui.update

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sw.notetaker.R
import com.sw.notetaker.data.model.Type

import com.sw.notetaker.databinding.FragmentUpdateNoteBinding
import com.sw.notetaker.ui.base.BaseFragment
import com.sw.notetaker.ui.update.viewModel.UpdateNoteViewModelImpl


class UpdateNoteFragment : BaseFragment(){
    private lateinit var binding: FragmentUpdateNoteBinding
    private val args: UpdateNoteFragmentArgs by navArgs()

    private val viewModel: UpdateNoteViewModelImpl by viewModels {
        UpdateNoteViewModelImpl.Factory
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentUpdateNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.init(args.noteId)

        viewModel.finished.observe(viewLifecycleOwner) {
            if (it) {
                navigateBack()
                viewModel.finished.value = false
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val snackbar = Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                )

                snackbar.setBackgroundTint(
                    ContextCompat.getColor(
                        requireContext(), R.color.red
                    )
                )
                snackbar.show()
            }
        }

        viewModel.type.observe(viewLifecycleOwner){
            clearSelected()
            val bgDrawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(ContextCompat.getColor(requireContext(), it.colour))
                setStroke(5, ContextCompat.getColor(requireContext(), R.color.black))
            }

            when (it) {
                Type.Yellow -> binding.vYellow
                Type.Green -> binding.vGreen
                else -> binding.vRed
            }.background = bgDrawable

        }
    }

    private fun clearSelected() {
        binding.run {
            vRed.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            vYellow.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            vGreen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    private fun navigateBack() {
        val bundle = Bundle()
        bundle.putBoolean("refresh", true)
        setFragmentResult("from_update_todo_fragment", bundle)
        navController.popBackStack()
    }
}