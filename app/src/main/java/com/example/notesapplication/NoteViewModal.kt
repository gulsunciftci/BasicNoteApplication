package com.example.notesapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal (application: Application) :AndroidViewModel(application) {

    //Aşağıdaki satırda tüm allNotes ve Repository için bir değişken oluşturuyoruz
    val allNotes : LiveData<List<Note>>
    val repository : NoteRepository

    //Aşağıdaki satırda dao, repository ve allNotes'u başlatıyoruz.
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // Aşağıdaki satırda bir notu silmek için yeni bir metod oluşturuyoruz.
    // Burada, notumuzu silmek için repository'den delete metodunu çağırıyoruz.
    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }


    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    // Aşağıdaki satırda veritabanımıza not eklemek için yeni bir metod oluşturuyoruz.
    // Burada not eklemek için repository'den insert metodunu çağırıyoruz.
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}