package com.h5190041.mobiett.ui.login

import android.content.Intent
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.h5190041.mobiett.R

import com.h5190041.mobiett.databinding.ActivityLoginBinding
import com.h5190041.mobiett.ui.category.CategoryActivity

import com.h5190041.mobiett.util.Constants
import com.h5190041.mobiett.util.TextWatcherUtil


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var edtLoginEmail: String = ""
    var edtLoginSifre: String = ""
    var emailGelenSifre: String? = ""
    val kullanicilar = hashMapOf<String?, String?>()

    var loginViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        loginEdtTextKontrol()
        initViewModel()
        loginIslemi()

    }

    fun btnRenkKontrolu(){
        binding.apply {
            if (!btnDevam.isEnabled) {
                btnDevam.background = ContextCompat.getDrawable(applicationContext , R.drawable.button_login_valid  )
            } else {
                btnDevam.background = ContextCompat.getDrawable(applicationContext , R.drawable.button_login_kirmizi_valid  )
            }
        }
    }

    fun loginEdtTextKontrol() {
        binding.apply {
            btnDevam.isEnabled = false
            val textWatcherUtil = TextWatcherUtil(
                onChanged = { _, _, _, _ ->
                    edtLoginEmail = edtEmail?.text.toString().trim()
                    edtLoginSifre = edtSifte?.text.toString().trim()
                    btnDevam.isEnabled = (!edtLoginEmail.isEmpty() && !edtLoginSifre.isEmpty())
                    btnRenkKontrolu()
                },
                afterChanged = { _ ->
                },
                beforeChanged = { _, _, _, _ ->
                }
            )
            edtEmail.addTextChangedListener(textWatcherUtil);
            edtSifte.addTextChangedListener(textWatcherUtil);
        }
    }

    fun loginIslemi(){
        binding.apply {
            btnDevam.setOnClickListener {
                 emailGelenSifre  = kullanicilar[edtLoginEmail]
                if(emailGelenSifre.equals(edtLoginSifre) && kullanicilar.contains(edtLoginEmail))  {
                    startActivity(Intent(this@LoginActivity, CategoryActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        R.string.yanlistir,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    fun hashMapOfEkle(email : String? , parola : String?){
        kullanicilar.put(email,parola)
    }

    fun initViewModel() {
        loginViewModel = LoginViewModel()

        loginViewModel?.apply {
            loginLiveData?.observe(this@LoginActivity, Observer {
                it.run {
                   for (index in 0..it.size+Constants.EKLE) {
                       if(index==size){
                           break
                       }
                       hashMapOfEkle(it[index].email,it[index].parola)
                        continue
                    }
                }
            })
            error?.observe(this@LoginActivity, Observer {
                it.run {
                }
            })
            loading?.observe(this@LoginActivity, Observer {
            })
        }
    }
}



