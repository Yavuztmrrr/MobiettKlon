package com.h5190041.mobiett.ui.otobusdetail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190041.mobiett.R
import com.h5190041.mobiett.data.model.Otobusler


import com.h5190041.mobiett.databinding.ActivityDetailBinding
import com.h5190041.mobiett.util.*


class OtobusDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var otobusDetailAdapter: OtobusDetailAdapter

    var rcvListeDegistir: String = Constants.TERS
    var siraliListe: MutableMap<Int?, String?> = LinkedHashMap()
    var otobusDuraklar: MutableMap<Int?, String?> = HashMap()
    var cagirilanOtobusYonDuz: String? = null
    var cagirilanOtobusYonTers: String? = null
    var cagirilanOtobusAdi: String? = null
    var cagirilanOtobusResim: String? = null
    var otobusAdiYonler: String? = null
    var objectUtil = ObjectUtil()
    var cagirilanOtobusDetay: String? = null
    var otobusDetail: Otobusler? = null

    val progressBar = ProgressDialogUtil(this@OtobusDetailActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    fun init() {
        progressBar.progressDialogBasla()
        listeDegistir()
        initRecycleView(otobusDuraklar)
        initGetOtobusDetay()
        initDetailOtobusBilgileri()
        initOtobusDetayDuraklar()

        binding.apply {
            txtAdiYonu.text = otobusAdiYonler?.toUpperCase()
            txtTaraf.text = cagirilanOtobusYonTers
            GlideUtil().apply {
                imgDetay.resimGetir(cagirilanOtobusResim, imgDetay)
            }

            btnGeriDon.setOnClickListener {
                onBackPressed()
            }
        }
    }

    fun initGetOtobusDetay() {
        cagirilanOtobusDetay = intent.getStringExtra(Constants.ISTENILEN_OTOBUS_DETAIL)
        otobusDetail = objectUtil.jsonStringToObjeOtobus(cagirilanOtobusDetay!!)
    }

    fun initDetailOtobusBilgileri() {


        cagirilanOtobusAdi = otobusDetail?.otobusAdi
        cagirilanOtobusYonDuz = otobusDetail?.yonDuz
        cagirilanOtobusYonTers = otobusDetail?.yonTers
        cagirilanOtobusResim = otobusDetail?.otobusDetay?.otobusDetayResim
        otobusAdiYonler = cagirilanOtobusAdi + " " + cagirilanOtobusYonTers + getString(R.string.tire) + cagirilanOtobusYonDuz


    }

    fun initOtobusDetayDuraklar() {
        var cagirilanOtobusDuraklar = otobusDetail?.otobusDetay?.duraklar
        var count: Int? = Constants.DEGER_SIFIR
        for (duraklar in cagirilanOtobusDuraklar?.split(",")!!) {
            otobusDuraklar.put(count, duraklar)
            count = count!! + Constants.DEGER_BIR
        }
    }

    fun initRecycleView(otobusDetailListe: MutableMap<Int?, String?>) {
        binding.apply {
            otobusDetailAdapter = OtobusDetailAdapter(otobusDetailListe!!, object :
                ItemClickListener {
                override fun onItemClick(position: Int) {
                }
            })
            rcvDetayListe.adapter = otobusDetailAdapter
            rcvDetayListe.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
        progressBar.progressDialogBitir()
    }

    fun listeDegistir() {
        binding.apply {
            btnDegistir.setOnClickListener {
                if (rcvListeDegistir == Constants.TERS) {
                    otobusDuraklar.entries.sortedByDescending { it.key }
                        .forEach { siraliListe[it.key] = it.value }

                    otobusDetailAdapter.setNewData(siraliListe)
                    otobusDetailAdapter.siralamDegistir()

                    rcvListeDegistir =Constants.DUZ
                    txtTaraf.text = cagirilanOtobusYonDuz
                }

                else {
                    otobusDuraklar.entries.sortedBy { it.key }
                        .forEach { siraliListe[it.key] = it.value }

                    otobusDetailAdapter.setNewData(siraliListe)
                    otobusDetailAdapter.siralamDegistir()

                    txtTaraf.text = cagirilanOtobusYonTers
                    rcvListeDegistir = Constants.TERS
                }
                otobusDetailAdapter.notifyDataSetChanged()
            }
        }
    }



}


