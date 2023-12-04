// 서기표 추가
// SharedViewModel.kt
import androidx.lifecycle.ViewModel
import android.net.Uri

class SharedViewModel : ViewModel() {
    var selectedImageUri: Uri? = null
}