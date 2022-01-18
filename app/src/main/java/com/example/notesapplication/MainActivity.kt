package com.example.notesapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {


    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)


        notesRV.layoutManager = LinearLayoutManager(this)

        //Adapter, bir veri kaynağıyla, veriye ihtiyacı olan nesneyi birbirine bağlamaya yarayan yapılardır.
        // ListView gibi bir dizi veriyi içinde bulunduran yapılara, bu verileri adapter aracılığıyla veririz.


        // Aşağıdaki satırda adaptör sınıfımızı başlatıyoruz.
        val noteRVAdapter = NoteRVAdapter(this, this, this)


        notesRV.adapter = noteRVAdapter


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        //Aşağıdaki satırda, listedeki değişiklikleri gözlemlemek için view modal sınıfımızdan allNotes metodunu çağırıyoruz.
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                //Aşağıdaki satırda listemizi güncelliyoruz.
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        //yeni bir activity açmak ve beraberinde o activity’ye veri göndermek için bir intent oluşturmamız lazım
        // ve intent’in başlangıç fonksiyonuna 2 adet parametre vermek zorundayız.
        // İlk parametre kaynak activity’nin refreansı, ikinci parametresi ise hedef activitynin class sınıfı türündeki referansıdır.

        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)


        //Veri göndermek için putExtra() fonksiyonundan fayadalanırız.
        // putExtra() fonksiyonu iki tane parametre bekler bizden.
        // Bu parametrelerden ilki göndereceğimiz verinin key’i, ikincisi ise göndereceğimiz verinin value’su.

        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        //Notumuzu silmek için View Modal dan deleteNote metodunu çağırıyoruz.
        viewModal.deleteNote(note)

        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }}