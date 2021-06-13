package com.h5190041.mobiett.ui.otobuslist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190041.mobiett.R
import com.h5190041.mobiett.data.model.CategoryResponseItem
import com.h5190041.mobiett.data.model.Otobusler
import com.h5190041.mobiett.databinding.ActivityListBinding
import com.h5190041.mobiett.util.ItemClickListener
import com.h5190041.mobiett.ui.category.OtobusListAdapter
import com.h5190041.mobiett.ui.otobusdetail.OtobusDetailActivity
import com.h5190041.mobiett.util.*
import kotlinx.coroutines.flow.Flow


class OtobusListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var otobusListAdapter: OtobusListAdapter
    var objectUtil = ObjectUtil()
    var rcvListeLayout: String = Constants.LINEER
    var otobusListe: List<Otobusler>? = null
    val progressBar = ProgressDialogUtil(this@OtobusListActivity)
    var cagirilanOtobus: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init() {
        progressDialogBasla()
        initGetOtobusler()
        layoutlistelemeTuru()
        listelemeDegistirme()
    }

    fun initGetOtobusler() {
        cagirilanOtobus = intent.getStringExtra(Constants.ISTENILEN_OTOBUS)
        otobusListe = objectUtil.jsonStringToObje(cagirilanOtobus!!)
        initRecycleView(otobusListe)
        progressDialogBitir(otobusListe)
    }

    fun initRecycleView(otobusList: List<Otobusler>?) {
        binding.apply {
            otobusListAdapter = OtobusListAdapter(otobusList, object :
                ItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        otobusList?.get(position)?.otobusAdi,
                        Toast.LENGTH_SHORT
                    ).show()
                    otobusDetailSayfasiVeriGonderme(otobusList?.get(position))
                }
            })
            rcvListe.adapter = otobusListAdapter
            rcvListe.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun otobusDetailSayfasiVeriGonderme(otobusdetail: Otobusler?) {
        val otobusDetailActivity = Intent(applicationContext, OtobusDetailActivity::class.java)
        var objectUtil = ObjectUtil();
        var clickOtobusList = objectUtil.ObjeToJsonStringOtobus(otobusdetail)
        otobusDetailActivity.putExtra(Constants.ISTENILEN_OTOBUS_DETAIL, clickOtobusList)
        startActivity(otobusDetailActivity)
    }


    fun layoutlistelemeTuru() {
        binding.apply {
            btnListe.setOnClickListener {
                if (rcvListeLayout == Constants.GRID) {

                    rcvListe.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

                    btnListe.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.grid)

                    rcvListeLayout = Constants.LINEER
                } else {
                    rcvListe.layoutManager =
                        GridLayoutManager(applicationContext, Constants.SPAN_COUNT)
                    rcvListeLayout = Constants.GRID
                    btnListe.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.lineer)
                }
            }
        }
    }

    fun listelemeDegistirme() {
        binding.apply {
            btnSirala.setOnClickListener {
                var alertDialogUtil = AlertDialogUtil()
                alertDialogUtil.alertSeciniz(
                    otobusListe,
                    this@OtobusListActivity,
                    getString(R.string.listeArtan),
                    getString(R.string.listeAzalan),
                    otobusListAdapter
                )
            }
        }
    }

    fun progressDialogBasla() {
        progressBar.progressDialogBasla()
    }

    fun progressDialogBitir(otobusList: List<Otobusler>?) {
        for (index in Constants.DEGER_SIFIR..otobusList!!.size) {
            if (index == otobusList.size) {
                progressBar.progressDialogBitir()
            }
            continue
        }
    }

}
