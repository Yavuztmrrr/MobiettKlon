package com.h5190041.mobiett.data.model
import com.google.gson.annotations.SerializedName




data class CategoryResponseItem(
        @SerializedName("durakAdi")
    val durakAdi: String?,
        @SerializedName("durakResim")
    val durakResim: String?,
        @SerializedName("id")
    val id: Int?,
        @SerializedName("otobusler")
    val otobusler: List<Otobusler>?
)

data class Otobusler(
    @SerializedName("otobusAdi")
    val otobusAdi: String?,
    @SerializedName("otobusDetay")
    val otobusDetay: OtobusDetay?,
    @SerializedName("otobusResim")
    val otobusResim: String?,
    @SerializedName("yonDuz")
    val yonDuz: String?,
    @SerializedName("yonTers")
    val yonTers: String?
)

data class OtobusDetay(
        @SerializedName("duraklar")
    val duraklar: String?,
        @SerializedName("otobusDetayResim")
    val otobusDetayResim: String?

)
