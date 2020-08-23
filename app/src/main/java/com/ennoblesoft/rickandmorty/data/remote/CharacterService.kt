package com.ennoblesoft.rickandmorty.data.remote

import com.ennoblesoft.rickandmorty.data.dto.CharacterList
import com.ennoblesoft.rickandmorty.data.entities.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    suspend fun getCharacters() : Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}