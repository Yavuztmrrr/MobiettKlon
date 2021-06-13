package com.h5190041.mobiett.ui.otobusdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190041.mobiett.databinding.DetayCardViewBinding
import com.h5190041.mobiett.util.Constants
import com.h5190041.mobiett.util.ItemClickListener


class OtobusDetailAdapter(
    var otobusDetailDuraklar: MutableMap<Int?, String?>,
    var onItemClickListener: ItemClickListener,
    var rcvDegistirme: String = Constants.DUZ

) : RecyclerView.Adapter<OtobusDetailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: DetayCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetayCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return otobusDetailDuraklar.size
    }

    fun setNewData(dataDuraklar: MutableMap<Int?, String?>){
        otobusDetailDuraklar=dataDuraklar
    }

    fun siralamDegistir(){
        if(rcvDegistirme.equals(Constants.DUZ))
            rcvDegistirme=Constants.TERS
        else
            rcvDegistirme=Constants.DUZ
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){

            binding.apply {
                var max=otobusDetailDuraklar.size -Constants.DEGER_BIR
                if(rcvDegistirme.equals(Constants.DUZ)){
                    txtDetayIcerik.text = otobusDetailDuraklar.get(position)
                }
                else{
                    txtDetayIcerik.text = otobusDetailDuraklar.get(max-position)
                }

                cardViewDetay.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }



            }
        }
    }

}
