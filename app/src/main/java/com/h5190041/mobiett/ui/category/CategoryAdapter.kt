package com.h5190041.mobiett.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190041.mobiett.data.model.CategoryResponseItem

import com.h5190041.mobiett.databinding.CategoryCardViewBinding
import com.h5190041.mobiett.util.GlideUtil
import com.h5190041.mobiett.util.ItemClickListener


class CategoryAdapter(
        var kategoriler : List<CategoryResponseItem>,
        var onItemClickListener: ItemClickListener

) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CategoryCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    //Olusturdugumuz adaptorde viewHolder'ı başlatmak için kullanılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    //Listenin eleman sayısını döndürür.
    override fun getItemCount(): Int {
        return kategoriler.size

    }
    //Filtreleme yapıldığı zaman filtredeki listeyi elimzideki listeye eşitlemek için kullanılır.
    fun setNewData( dataKategoriler : List<CategoryResponseItem>){
        kategoriler=dataKategoriler
    }
    //OnCreateViewHolderdan dönen veriyi baglamak için kullanılır
    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        with(viewholder){
            binding.apply {
                txtCategory.text = kategoriler[position].durakAdi
                GlideUtil().apply {
                    imgCategoryResim.resimGetir(kategoriler[position].durakResim.toString(),viewholder.binding.imgCategoryResim)
                }


                OtoCardView.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

}
