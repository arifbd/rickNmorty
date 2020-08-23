package com.ennoblesoft.rickandmorty.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ennoblesoft.rickandmorty.data.repository.CharacterRepository
import com.ennoblesoft.rickandmorty.data.entities.Character
import com.ennoblesoft.rickandmorty.utils.Resource

class CharactersViewModel @ViewModelInject constructor(private val repository: CharacterRepository) :
    ViewModel() {
    val characters: LiveData<Resource<List<Character>>> = repository.getCharacters()
}