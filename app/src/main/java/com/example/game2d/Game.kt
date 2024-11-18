import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow


class Game(val scope: CoroutineScope) {

    var counter = 0
    val state = MutableStateFlow(0)

    fun Play(){
        scope.launch {
            counter = 0
            while (counter<5000) {
                delay(40)
                counter++
                state.emit(counter)

            }
        }
    }
}
