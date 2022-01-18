package com.example.notesapplication

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.concurrent.Flow


//Veri merkezli uygulamalarda veriye erişimin ve yönetimin tek noktaya indirilmesini sağlayan yapıya Repository Pattern denir.
//Kod tekrarı olmadan, saf ve temiz bir şekilde veritabanı işlemlerinin tek bir noktadan kontrol edip, yönetilmesini Repository Pattern ile sağlıyoruz.
//Veritabanı işlemlerinden insert, update ve delete gibi işlemler yaparken her tablo için ayrı ayrı metot ve kod yazmamız gerekebilir.
//Bu da bizim için kod karmaşıklığı ve fazla iş yükü demektir.
//Repository Pattern sayesinde kod karmaşıklığını ortadan kaldırırız,
//iş yükünü azaltırız ve hata payını en aza indirerek daha temiz kod yazabiliriz.


class NoteRepository(private val notesDao: NotesDao) {

    //Aşağıdaki satırda listemiz için bir değişken oluşturuyoruz ve DAO sınıfımızdan tüm notları alıyoruz.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    //Aşağıdaki satırda notu veritabanımıza eklemek için bir ekleme metodu oluşturuyoruz.
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    //Aşağıdaki satırda notumuzu veritabanından silmek için bir silme metodu oluşturuyoruz.
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    //Aşağıdaki satırda, notumuzu veritabanından güncellemek için bir güncelleme metodu oluşturuyoruz.
    suspend fun update(note: Note){
        notesDao.update(note)
    }
}