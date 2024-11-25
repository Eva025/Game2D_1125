import android.content.Context
import android.icu.number.Scale
import android.media.MediaPlayer
import com.example.game2d.Background
import com.example.game2d.R
import com.example.game2d.com.example.game2d.Boy
import com.example.game2d.com.example.game2d.Virus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow


class Game(val scope: CoroutineScope,  screenW:Int, screenH: Int,scale: Float, context: Context) {

    var counter = 0
    val state = MutableStateFlow(0)
    val background = Background(screenW)
    val boy = Boy(screenH,scale)
    val virus= Virus(screenW, screenH, scale)
    var isPlaying = true
    //背景音樂
    var mper1 = MediaPlayer.create(context, R.raw.lastletter)
    //結束音樂
    var mper2 = MediaPlayer.create(context, R.raw.gameover)


    fun Play(){
        scope.launch {
            //counter = 0
            isPlaying = true

            while (isPlaying) {
                mper1.start()  //播放背景音樂

                delay(40)
                background.Roll()
                if (counter % 3 == 0){
                    boy.Walk()
                    virus.Fly()
                    if(boy.getRect().intersect(virus.getRect())) {
                        isPlaying = false
                        //遊戲結束音效
                        mper1.pause()
                        mper2.start()

                    }

                }

                counter++
                state.emit(counter)

            }
        }
    }

    fun Restart(){
        virus.Reset()
        counter = 0
        isPlaying = true
        Play()
    }


}
