package com.app.spotfyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotfyclone.Model.Album
import com.app.spotfyclone.Model.Category
import com.app.spotfyclone.R
import com.app.spotfyclone.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class Home : Fragment() {
    private lateinit var categoryAdapter:CategoryAdapter
    private lateinit var album_adapter:albumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private var fragmentsHome: FragmentHomeBinding? = null
    var albums:MutableList<Album> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindigHome= FragmentHomeBinding.bind(view)
        fragmentsHome = bindigHome

        var categorys:MutableList<Category> = ArrayList()

        for(i in 0..4) {
            val category = Category()
            category.title = "title$i"
            categorys.add(category)

            for(a in 0..4) {
                val album = Album()
                album.img = R.drawable.spotify
                albums.add(album)
            }
        }


        categoryAdapter = CategoryAdapter(categorys)
        fragmentsHome!!.recycleCategory.adapter = categoryAdapter
        fragmentsHome!!.recycleCategory.layoutManager = LinearLayoutManager(context)


    }

    private inner class CategoryAdapter(private val category:MutableList<Category>):RecyclerView.Adapter<CategoryHolder>() {// informa o comportamento da recycler
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {// resposavel por inflar o layout que deseja manipular
            return CategoryHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))// layout modelo de cada item
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {// devolve os itens da lista
            val catg= category[position]
            holder.bind(catg)
        }

        override fun getItemCount(): Int = category.size// quantidade de itens da lista

    }

    private inner class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){//ação, itens que serão mudados na lista
        fun bind(category:Category){
            itemView.findViewById<TextView>(R.id.text_title).text = category.title

        // como o recycleView estará dentro de outro recycle, fazemos a instancia aqui dentro
            album_adapter = albumAdapter(albums)
            itemView.findViewById<RecyclerView>(R.id.recycle_album).adapter = album_adapter
            itemView.findViewById<RecyclerView>(R.id.recycle_album).layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
//-----------------------------------------------

    private inner class AlbumHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(album: Album){
            itemView.findViewById<ImageView>(R.id.img_album).setImageResource(album.img)
        }
    }

    private inner class albumAdapter(private val album:MutableList<Album>): RecyclerView.Adapter<AlbumHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
            return AlbumHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
            val alb = album[position]
            holder.bind(alb)
        }

        override fun getItemCount(): Int = album.size

    }


}