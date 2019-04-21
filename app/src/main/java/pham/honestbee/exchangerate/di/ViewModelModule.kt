package pham.honestbee.exchangerate.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pham.honestbee.exchangerate.exchange.MainViewModel
import pham.honestbee.exchangerate.viewmodel.ExchangeRateViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ExchangeRateViewModelFactory): ViewModelProvider.Factory
}
