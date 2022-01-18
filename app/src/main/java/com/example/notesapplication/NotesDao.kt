package com.example.notesapplication

import androidx.lifecycle.LiveData
import androidx.room.*

//DAO(veri erişim nesnesi) interface olarak kullanılır ve bütün database
// işlemleri (ekleme, silme, güncelleme, sorgu) bu interface içinde yapılır.

@Dao    //Room kütüphanesi veri tabanı ile ilgili metodları oluştursun diye Dao ekliyoruz.
interface NotesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select*from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    suspend fun  update(note: Note)
}