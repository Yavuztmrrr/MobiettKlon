package com.h5190041.mobiett.ui.splash


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.h5190041.mobiett.util.AlertDialogUtil
import com.h5190041.mobiett.R
import com.h5190041.mobiett.ui.login.LoginActivity
import com.h5190041.mobiett.util.Constants
import com.h5190041.mobiett.util.NetworkUtil

class SplashActivity : AppCompatActivity() {
    var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    fun init() {
        initCounterDown()
    }

    fun internetKontrolu() {
        var network = NetworkUtil()
        if (!network.networkControl(applicationContext)) {
            alertDialog()
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish();
        }
    }

    fun alertDialog() {
        var alertDialog = AlertDialogUtil()
        alertDialog.alertDialog(this@SplashActivity, applicationContext, getString(R.string.netKontrol), getString(R.string.netYoktur), getString(R.string.positiveButtonSplash), getString(R.string.negativeButtonSplash), Constants.SPLASH)
    }

    fun initCounterDown() {
        countDownTimer = object : CountDownTimer(Constants.MILLI_SANIYE, Constants.COUNT_DOWN) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                internetKontrolu()
            }
        }.start()
    }

}