package com.ennoblesoft.rickandmorty.ui.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ennoblesoft.rickandmorty.data.entities.Character
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.pbCharacter.visibility = View.GONE
                    binding.clCharacter.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> binding.pbCharacter.visibility = View.VISIBLE
            }
        })
    }

    private fun bindCharacter(character: Character) {
        binding.tvName.text = character.name
        binding.tvSpecies.text = character.species
        binding.tvStatus.text = character.status
        binding.tvGender.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}