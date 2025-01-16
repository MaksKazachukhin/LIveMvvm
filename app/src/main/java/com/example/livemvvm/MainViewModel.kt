import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _counterData = MutableLiveData<Int>(0)
    val counterData: LiveData<Int> get() = _counterData

    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> get() = _toastMessage

    private val _textColor = MutableLiveData<Int>()
    val textColor: LiveData<Int> get() = _textColor

    init {
        _textColor.value = android.graphics.Color.BLACK
    }

    fun incrementCounter() {
        val newValue = (_counterData.value ?: 0) + 1
        _counterData.value = newValue

        when (newValue) {
            10 -> _toastMessage.value = "Поздравляем"
            15 -> _textColor.value = android.graphics.Color.GREEN
            else -> _textColor.value = android.graphics.Color.BLACK
        }
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }
}
