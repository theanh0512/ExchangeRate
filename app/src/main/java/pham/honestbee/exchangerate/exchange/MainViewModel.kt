package pham.honestbee.exchangerate.exchange

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.PropertyChangeRegistry
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import pham.honestbee.exchangerate.repository.ExchangeRateRepository
import pham.honestbee.exchangerate.vo.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(val exchangeRateRepository: ExchangeRateRepository) : ViewModel(), Observable {
    val loading = ObservableBoolean(false)
    val loadSuccess = ObservableBoolean(false)
    val currentRateMap = ObservableField<HashMap<String, Float>>()
    val baseRateNames = ObservableField<List<String>>()
    val currentBase = ObservableField<String>("SGD")
    val currentTarget = ObservableField<String>("USD")
    val currentRate = ObservableField<String>("1")
    val currentAmount = ObservableField<String>("1")
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val compositeDisposable = CompositeDisposable()

    init {
        loadRates(currentBase.get() ?: "SGD")
    }

    // we use these here because the databinding lib is still having a timing bug
    // where calling notifyChanged() or notifyPropertyChanged(int) results in no action taking place
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadRates(base: String) {
        loading.set(true)
        compositeDisposable.add(exchangeRateRepository.getExchangeRate(base)
                .subscribe({ response ->
                    loading.set(false)
                    loadSuccess.set(true)
                    populateRates(response)
                    Log.d("Rates", currentTarget.get())
                }, { throwable ->
                    throwable.printStackTrace()
                    loading.set(false)
                    loadSuccess.set(false)
                })
                { Log.d("Rates", "Completed") })
    }

    private fun populateRates(response: Response) {
        val rateMap = HashMap<String, Float>()
        val bases = ArrayList<String>()
        bases.add(currentBase.get() ?: "SGD")
        response.rates?.keys?.forEach {
            rateMap[it] = response.rates!!.getValue(it)
            bases.add(it)
        }
        currentRateMap.set(rateMap)
        baseRateNames.set(bases)
        val currentAmountFloat = currentAmount.get()?.toFloat() ?: 0.0f
        val result = currentRateMap.get()!![currentTarget.get()]!! * currentAmountFloat
        currentRate.set(result.toString())
    }
}
