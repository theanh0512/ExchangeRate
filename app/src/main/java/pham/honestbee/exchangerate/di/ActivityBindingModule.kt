package pham.honestbee.exchangerate.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pham.honestbee.exchangerate.exchange.MainActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}