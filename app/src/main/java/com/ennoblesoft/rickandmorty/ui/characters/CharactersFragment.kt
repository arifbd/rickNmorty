package com.ennoblesoft.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ennoblesoft.rickandmorty.R
import com.ennoblesoft.rickandmorty.databinding.FragmentCharactersBinding
import com.ennoblesoft.rickandmorty.ui.listener.ItemClickListener
import com.ennoblesoft.rickandmorty.utils.Resource
import com.ennoblesoft.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), ItemClickListener {

    private var binding: FragmentCharactersBinding by autoCleared()
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeToObservers()
    }

    private fun setRecyclerView() {
        adapter = CharacterAdapter(this)
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacters.adapter = adapter
    }

    private fun subscribeToObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pbCharacters.visibility = View.GONE
                    if (!it.data!!.results.isNullOrEmpty()) adapter.setItems(ArrayList(it.data.results))
                }
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> binding.pbCharacters.visibility = View.VISIBLE
            }
        })
    }

    override fun onItemClicked(vararg data: Any?) {
        val id = data[0]
        if (id != null && id is Int)
            findNavController().navigate(R.id.action_charactersFragment_to_characterFragment, bundleOf("id" to id))
    }
}