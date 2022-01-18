package com.example.notesapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    //Aşağıdaki satırda tüm not listemiz için bir değişken oluşturuyoruz.
    private val allNotes = ArrayList<Note>()

    //ViewHolder ListView kullanımının performans açısından iyileştirilmesini sağlayan bir pattern’dır.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Liste elemanlarımızın görüntüleneceği layout dosyasını tanımladığımız metod.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Bu fonksiyon o anki elemanının pozisyon bilgisini barındırır ve bu sayede o elemana değer atamamıza yardımcı olur.
        //onBindViewHolder metodundaki holder, ViewHolder sınıfımızdan oluşturulan bir nesnedir.
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Last Updated : " + allNotes.get(position).timeStamp)

        holder.deleteIV.setOnClickListener {

            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }


        holder.itemView.setOnClickListener {

            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        //Parametre olarak aldığımız listenin boyutunu döndüren metottur.
        return allNotes.size
    }

    // not listemizi güncellemek için aşağıdaki metodu kullanıyoruz.
    fun updateList(newList: List<Note>) {

        allNotes.clear()

        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {

    fun onNoteClick(note: Note)
}
