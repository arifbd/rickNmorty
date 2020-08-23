package com.ennoblesoft.rickandmorty.data.dto

import com.ennoblesoft.rickandmorty.data.entities.Character

data class CharacterList(
    val info: Info,
    val results: List<Character>
)