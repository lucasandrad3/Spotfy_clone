package com.app.spotfyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.spotfyclone.Model.Secao
import com.app.spotfyclone.R
import com.app.spotfyclone.databinding.FragmentHomeBinding
import com.app.spotfyclone.databinding.FragmentSearchBinding

class Search : Fragment() {

    private lateinit var secao_adapter:SecaoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    private var fragmentsSearch: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindigSearch= FragmentSearchBinding.bind(view)
        fragmentsSearch = bindigSearch

        var Secoes:MutableList<Secao> = mutableListOf(
            // lista sendo criada dentro do onViewCreated para toda vez q a tela for chamada, a cor da seção mudar
            // caso a lista tivesse sido criada dentro do arq Data,kt, isso não seria possivel, ela seria estatica

            Secao(
                choiceImg(),
                "Funk"

            ),

            Secao(
                choiceImg(),
                "Sertanejo"
            ),

            Secao(
                choiceImg(),
                "Eletronica"
            )


        )

        secao_adapter=SecaoAdapter(Secoes)
        fragmentsSearch!!.recycleSearch.adapter = secao_adapter
        fragmentsSearch!!.recycleSearch.layoutManager = GridLayoutManager(context, 2)




    }

    private inner class SecaoAdapter(private val secoes:MutableList<Secao>):RecyclerView.Adapter<SecaoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecaoHolder {
            return SecaoHolder(layoutInflater.inflate(R.layout.secao_item, parent, false))
        }

        override fun onBindViewHolder(holder: SecaoHolder, position: Int) {
            val secao = secoes[position]
            holder.bind(secao)

        }

        override fun getItemCount(): Int = secoes.size
    }

    private inner class SecaoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(secao:Secao){
            itemView.findViewById<TextView>(R.id.title_secao).text = secao.nameSection
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(secao.imgSection)

        }

    }

    fun choiceImg():Int{
        return mutableListOf<Int>(
            R.drawable.secao1,
            R.drawable.secao2,
            R.drawable.secao3,
            R.drawable.secao4
        ).random()

    }

}