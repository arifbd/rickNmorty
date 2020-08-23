package com.ennoblesoft.rickandmorty.data.local.dao

import com.ennoblesoft.rickandmorty.data.entities.Character
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("SELECT * FROM character")
    fun getAllCharacters() : LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>

}