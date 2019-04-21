package pham.honestbee.exchangerate.exchange

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import pham.honestbee.exchangerate.R
import pham.honestbee.exchangerate.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var binding: ActivityMainBinding? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModelAndBind()
    }

    private fun initViewModelAndBind() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        viewModel.apply {
            currentBase.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(p0: Observable, p1: Int) {
                    loadRates((p0 as ObservableField<String>).get() ?: "SGD")
                }
            })
            currentTarget.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(p0: Observable, p1: Int) {
                    val currentAmountFloat = currentAmount.get()?.toFloat() ?: 0.0f
                    val result = currentRateMap.get()!![(p0 as ObservableField<String>).get()
                            ?: "EUR"]!! * currentAmountFloat
                    currentRate.set(result.toString())
                }
            })
            currentAmount.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(p0: Observable, p1: Int) {
                    val currentAmountFloat = (p0 as ObservableField<String>).get()!!.toFloatOrNull()
                            ?: 0.0f
                    if (currentRateMap.get() != null && currentRateMap.get()!!.containsKey(currentTarget.get())) {
                        val result = currentRateMap.get()!![currentTarget.get()]!! * currentAmountFloat
                        currentRate.set(result.toString())
                    }
                }
            })
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}