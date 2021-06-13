package com.h5190041.mobiett.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.h5190041.mobiett.data.model.Otobusler
import com.h5190041.mobiett.ui.category.OtobusListAdapter
import com.h5190041.mobiett.ui.login.LoginActivity
import kotlin.system.exitProcess

class AlertDialogUtil() {

    fun alertDialog(activity: Activity, context: Context?, alertTitle: String?, alertMessage: String?, alertPositiveButton: String?, alertNegativeButton: String?, ekran: String?) {
        AlertDialog.Builder(activity).apply {
            setTitle(alertTitle)
            setMessage(alertMessage)
            setPositiveButton(alertPositiveButton) { _, _ ->
                if (ekran == Constants.SPLASH) {
                    val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
                    activity.startActivity(intent);
                } else {
                    val intent = Intent(context, LoginActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
            }
            setNegativeButton(alertNegativeButton) { _, _ ->
                if (ekran == Constants.CATEGORY) {

                } else {
                    exitProcess( Constants.DEGER_SIFIR)

                }

            }
        }.create().show()

    }

    fun alertSeciniz(
        otobusList: List<Otobusler>?,
        activity: Activity,
        alertArtan: String?,
        alertAzalan: String?,
        adapter: OtobusListAdapter

    ) {
        AlertDialog.Builder(activity).apply {
            val builder = AlertDialog.Builder(activity)
            val degerler = arrayOf(alertArtan, alertAzalan)
            builder.setItems(degerler) { dialog, pozisyon ->
                when (pozisyon) {
                    Constants.DEGER_SIFIR -> {
                        val otobusAzalan = otobusList?.sortedBy { it.otobusAdi }
                        adapter.setNewData(otobusAzalan)
                        adapter.notifyDataSetChanged()
                    }
                    Constants.DEGER_BIR -> {
                        val otobusArtan =  otobusList?.sortedByDescending { it.otobusAdi }
                        adapter.setNewData(otobusArtan)
                        adapter.notifyDataSetChanged()
                    }

                }


            }.create().show()
        }
    }
}