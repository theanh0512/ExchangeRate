package pham.honestbee.exchangerate.binding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.widget.Spinner
import pham.honestbee.exchangerate.extensions.SpinnerExtensions
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.getSpinnerValue
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.setSpinnerEntries
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.setSpinnerEntriesFromMap
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.setSpinnerInverseBindingListener
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.setSpinnerItemSelectedListener
import pham.honestbee.exchangerate.extensions.SpinnerExtensions.setSpinnerValue

class SpinnerBindings {

    @BindingAdapter("stringEntries")
    fun Spinner.setStringEntries(entries: List<String>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: HashMap<String, Float>?) {
        setSpinnerEntriesFromMap(entries)
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }

    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(selectedValue: String?) {
        setSpinnerValue(selectedValue)
    }

    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedValue(): Any? {
            return getSpinnerValue()
        }
    }
}