package com.example.notesapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// entity kullanarak veri tabanındaki tablo ismini belirtiyoruz
@Entity(tableName = "notesTable")

class Note (
    //sütunlarımızı ColumnInfo etiketi ile oluşturuyoruz.
    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String) {
    //PrimaryKey her nesne için birbirinden farklı id oluşturmamızı sağlıyor.
    //Eğer id değişkenimiz sırayla artan bir değerse bu şekilde kullanıyoruz.
    @PrimaryKey(autoGenerate = true)
    var id = 0
}