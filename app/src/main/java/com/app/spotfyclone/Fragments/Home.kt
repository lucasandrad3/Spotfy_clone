package com.app.spotfyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotfyclone.Model.*
import com.app.spotfyclone.R
import com.app.spotfyclone.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindigHome= FragmentHomeBinding.bind(view)
        fragmentsHome = bindigHome

       val categorys = arrayListOf<Category>()

        categoryAdapter = CategoryAdapter(categorys)
        fragmentsHome!!.recycleCategory.adapter = categoryAdapter
        fragmentsHome!!.recycleCategory.layoutManager = LinearLayoutManager(context)


        retrofit().create(SpotfyApi::class.java)
            .ListCategorys()
            .enqueue(object :Callback<Categorys>{
                override fun onResponse(call: Call<Categorys>, response: Response<Categorys>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            categoryAdapter.categorys.clear()
                            categoryAdapter.categorys.addAll(it.categorys)
                            categoryAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<Categorys>, t: Throwable) {
                    Toast.makeText(context, "Erro!!", Toast.LENGTH_SHORT).show()
                }

            })

    }

    private inner class CategoryAdapter(val categorys:MutableList<Category>):RecyclerView.Adapter<CategoryHolder>() {// informa o comportamento da recycler
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {// resposavel por inflar o layout que deseja manipular
            return CategoryHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))// layout modelo de cada item
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {// devolve os itens da lista
            val catg= categorys[position]
            holder.bind(catg)
        }

        override fun getItemCount(): Int = categorys.size// quantidade de itens da lista

    }

    private inner class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){//ação, itens que serão mudados na lista
        fun bind(category:Category){
            itemView.findViewById<TextView>(R.id.text_title).text = category.title

        // como o recycleView estará dentro de outro recycle, fazemos a instancia aqui dentro
            album_adapter = albumAdapter(category.albuns)
            itemView.findViewById<RecyclerView>(R.id.recycle_album).adapter = album_adapter
            itemView.findViewById<RecyclerView>(R.id.recycle_album).layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
//-----------------------------------------------

    private inner class AlbumHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(album: Album){
            val id_img = itemView.findViewById<ImageView>(R.id.img_album)
            Picasso.get().load(album.img).placeholder(R.drawable.placeholder).fit().into(id_img)
        }
    }

    private inner class albumAdapter(private val album:List<Album>): RecyclerView.Adapter<AlbumHolder>(){
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