package com.h5190041.mobiett.ui.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.h5190041.mobiett.data.model.Otobusler

import com.h5190041.mobiett.databinding.ListeCardViewBinding
import com.h5190041.mobiett.util.GlideUtil
import com.h5190041.mobiett.util.ItemClickListener


class OtobusListAdapter(
        var otobuslerList : List<Otobusler>?,
        var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<OtobusListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListeCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListeCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return otobuslerList!!.size

    }
    fun setNewData( DataOtobusler : List<Otobusler>?){
        otobuslerList=DataOtobusler
    }
    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        with(viewholder){
            binding.apply {

                txtBaslik.text = otobuslerList?.get(position)?.otobusAdi
                var string : String = otobuslerList?.get(position)?.yonDuz +"-"+ otobuslerList?.get(position)?.yonTers
                txtDetayIcerik.text=string
                GlideUtil().apply {
                    imgListResim.resimGetir(otobuslerList?.get(position)?.otobusResim.toString(),viewholder.binding.imgListResim)
                }




                cardViewListe.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }


            }
        }
    }

}
