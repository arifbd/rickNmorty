package com.ennoblesoft.rickandmorty.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(private val characterService: CharacterService) :
    BaseDataSource() {

    suspend fun getCharacters() = getResult { characterService.getCharacters() }
    suspend fun getCharacter(id: Int) = getResult { characterService.getCharacter(id) }

}