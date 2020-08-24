package com.ennoblesoft.rickandmorty.ui.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ennoblesoft.rickandmorty.databinding.FragmentCharacterBinding
import com.ennoblesoft.rickandmorty.utils.Resource
import com.ennoblesoft.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding by autoCleared()
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.characterData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> viewModel.setData(it.data)
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> viewModel.isLoading.set(true)
            }
        })
    }
}