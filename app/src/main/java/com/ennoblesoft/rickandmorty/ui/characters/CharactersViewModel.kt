package com.ennoblesoft.rickandmorty.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ennoblesoft.rickandmorty.data.dto.CharacterList
import com.ennoblesoft.rickandmorty.data.repository.CharacterRepository
import com.ennoblesoft.rickandmorty.utils.Resource

class CharactersViewModel @ViewModelInject constructor(repository: CharacterRepository) :
    ViewModel() {
    val characters: LiveData<Resource<CharacterList>> = repository.getCharacters()
}