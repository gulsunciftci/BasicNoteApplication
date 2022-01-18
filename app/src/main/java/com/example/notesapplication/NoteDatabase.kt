package com.example.notesapplication


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {
        // Singleton, aynı nesnenin her bir istek için bellekte yeniden oluşturulmasını önler.
        // Mesela veritabanı bağlantısı açmak için sürekli nesne yaratmak kaynak tüketimini artırır. Bu da sistemde yavaşlama ve aksamalara sebep olur.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // INSTANCE boş değilse onu döndürür, boşsa veritabanını oluşturur.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    //Context, uygulama hakkında genel bilgilere ulaşan diğer componentlerle iletişim sağlayabilen bir arayüzdür.
                    //applicationContext uygulama prosesinde çalışan single instance'tır. Uygulama yaşadığı sürece yaşamına devam eder.
                    //Farklı yerlerden erişim sağlansa bile aynı instance elde edilir.
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}