package com.ennoblesoft.rickandmorty.data.repository

import com.ennoblesoft.rickandmorty.data.local.dao.CharacterDao
import com.ennoblesoft.rickandmorty.data.remote.CharacterRemoteDataSource
import com.ennoblesoft.rickandmorty.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

}