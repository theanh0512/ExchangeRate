package pham.honestbee.exchangerate.api

import io.reactivex.Observable
import pham.honestbee.exchangerate.vo.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateService {
    @GET("latest")
    fun getRates(@Query("base") base: String): Observable<Response>
}