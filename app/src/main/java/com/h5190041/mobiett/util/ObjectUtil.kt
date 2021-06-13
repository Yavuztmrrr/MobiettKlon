package com.h5190041.mobiett.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.h5190041.mobiett.data.model.Otobusler
import java.util.*
import java.util.stream.Collectors


class ObjectUtil {


    fun ObjeToJsonString(otobusListe: List<Otobusler>?): String? {
        val gson = Gson()
        return gson.toJson(otobusListe)
    }

    fun jsonStringToObje(jsonString: String?): List<Otobusler>? {
        val gson = Gson()
        return gson.fromJson(jsonString , Array<Otobusler>::class.java).toList()
    }

    fun ObjeToJsonStringOtobus(otobusListe: Otobusler?): String? {
        val gson = Gson()
        return gson.toJson(otobusListe)
    }

    fun jsonStringToObjeOtobus(jsonString: String?): Otobusler? {
        val gson = Gson()
        return gson.fromJson(jsonString , Otobusler::class.java)
    }

}

