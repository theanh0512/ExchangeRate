package pham.honestbee.exchangerate

import android.databinding.DataBindingUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pham.honestbee.exchangerate.binding.AppDataBindingComponent
import pham.honestbee.exchangerate.di.DaggerAppComponent

class ExchangeRateApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
