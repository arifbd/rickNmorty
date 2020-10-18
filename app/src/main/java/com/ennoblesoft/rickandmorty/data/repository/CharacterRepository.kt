package com.ennoblesoft.rickandmorty.data.repository

import com.ennoblesoft.rickandmorty.data.remote.CharacterRemoteDataSource
import com.ennoblesoft.rickandmorty.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) {

    fun getCharacters() = performGetOperation(
        networkCall = { remoteDataSource.getCharacters() }
    )

    fun getCharacter(id: Int) = performGetOperation(
        networkCall = { remoteDataSource.getCharacter(id) }
    )

}