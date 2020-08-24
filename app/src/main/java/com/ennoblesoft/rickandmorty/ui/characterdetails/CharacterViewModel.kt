package com.ennoblesoft.rickandmorty.ui.characterdetails

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ennoblesoft.rickandmorty.data.entities.Character
import com.ennoblesoft.rickandmorty.data.repository.CharacterRepository
import com.ennoblesoft.rickandmorty.utils.Resource

class CharacterViewModel @ViewModelInject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id -> repository.getCharacter(id) }
    val characterData: LiveData<Resource<Character>> = _character
    var character: ObservableField<Character> = ObservableField()
    var isLoading: ObservableBoolean = ObservableBoolean(true)
    var hasData: ObservableBoolean = ObservableBoolean(false)

    fun start(id: Int) {
        hasData.set(false)
        _id.value = id
    }

    fun setData(character: Character? = null) {
        if (character != null)
            this.character.set(character)
        hasData.set(character != null)
        isLoading.set(false)
    }
}