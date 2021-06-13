package com.h5190041.mobiett.ui.category

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.h5190041.mobiett.R
import com.h5190041.mobiett.data.model.CategoryResponseItem
import com.h5190041.mobiett.data.model.Otobusler
import com.h5190041.mobiett.databinding.ActivityCategoryBinding
import com.h5190041.mobiett.ui.otobuslist.OtobusListActivity
import com.h5190041.mobiett.util.*


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    var otobusler: List<Otobusler>? = null
    var categoryViewModel: CategoryViewModel? = null
    var categoryList: List<CategoryResponseItem>? = null
    val progressBar = ProgressDialogUtil(this@CategoryActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        initViewModel()
        progressDialogBasla()
        binding.apply {
            searchViewCategory.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean
                {

                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    categoryFiltreleme(newText)
                    return false
                }})
        }

    }

    fun categoryFiltreleme(arananCategory : String?){
        arananCategory?.let {
            categoryList?.let {
                var filterCategoryList = it.filter { it.durakAdi!!.contains(arananCategory) }
                categoryAdapter.setNewData(filterCategoryList)
                categoryAdapter.notifyDataSetChanged()
                initRecycleView(filterCategoryList)
            }
        }
    }

    fun initRecycleView(categoryList: List<CategoryResponseItem>) {
        binding.apply {
            categoryAdapter = CategoryAdapter(categoryList, object :
                ItemClickListener {
                override fun onItemClick(position: Int) {
                    otobusler = categoryList.get(position).otobusler
                    otobusListSayfasiVeriGonderme(otobusler)
                }
            })
            rcvOtolar.adapter = categoryAdapter
            rcvOtolar.layoutManager =
                StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    fun otobusListSayfasiVeriGonderme(otobusList: List<Otobusler>?) {
        val otobusListActivity = Intent(applicationContext, OtobusListActivity::class.java)
        var objectUtil = ObjectUtil();
        var clickOtobus = objectUtil.ObjeToJsonString(otobusList)
        otobusListActivity.putExtra(Constants.ISTENILEN_OTOBUS, clickOtobus)
        startActivity(otobusListActivity)
    }

    fun initViewModel() {
        categoryViewModel = CategoryViewModel()
        categoryViewModel?.apply {
            categoryLiveData?.observe(this@CategoryActivity, Observer {
                it.run {
                    categoryList = it
                    initRecycleView(it)
                    progressDialogBitir(it)

                }
            })
            error?.observe(this@CategoryActivity, Observer {
                it.run {
                }
            })
            loading?.observe(this@CategoryActivity, Observer {
            })
        }
    }

    override fun onBackPressed() {
        var alertDialog = AlertDialogUtil()
        alertDialog.alertDialog(
            this@CategoryActivity,
            applicationContext,
            getString(R.string.cikisTitle),
            getString(R.string.cikisMessage),
            getString(R.string.positiveButton),
            getString(R.string.negativeButton),
            Constants.CATEGORY
        )
    }

    fun progressDialogBasla() {
        progressBar.progressDialogBasla()
    }

    fun progressDialogBitir(categoryList: List<CategoryResponseItem>) {
        for (index in Constants.DEGER_SIFIR..categoryList.size) {
            if (index == categoryList.size) {
                progressBar.progressDialogBitir()
            }
            continue
        }
    }


}