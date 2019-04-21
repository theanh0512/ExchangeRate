package pham.honestbee.exchangerate.vo

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("rates")
                    var rates: Map<String, Float>? = null,
                    var base: String, var date: String)