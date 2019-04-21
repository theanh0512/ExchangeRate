package pham.honestbee.exchangerate.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pham.honestbee.exchangerate.api.ExchangeRateService
import pham.honestbee.exchangerate.vo.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRateRepository @Inject constructor(val exchangeRateService: ExchangeRateService) {
    fun getExchangeRate(base: String): Observable<Response> {
        return exchangeRateService.getRates(base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    companion object {
        private val TAG = "ExchangeRate"
    }
}