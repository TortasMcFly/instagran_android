package hector.villa.instagran.ui.detalle_historia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.teresaholfeld.stories.StoriesProgressView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia
import java.lang.Math.abs

class DetalleHistoriaActivity : AppCompatActivity(),
        StoriesProgressView.StoriesListener,
        GestureDetector.OnGestureListener
{
    private lateinit var historias: ArrayList<Historia>
    private lateinit var historia: Historia
    private var startUserPosition = 0
    private var storyPosition = 0
    private lateinit var btnCloseStory: View
    private lateinit var imageViewHistoria: ImageView
    private lateinit var storyImage: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var profileArea: View
    private lateinit var areaIzquierda: View
    private lateinit var areaDerecha: View
    private lateinit var areaCentro: View
    private lateinit var storiesProgressView: StoriesProgressView

    lateinit var gestureDetector: GestureDetector
    private val swipeThreshold = 100
    private val swipeVelocityThreshold = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_historia)
        title = "Historia"
        historias = intent.getSerializableExtra("historias_usuarios") as ArrayList<Historia>
        startUserPosition = intent.getIntExtra("start_position", 0)
        setupUi()
        changeUserStories()
        gestureDetector = GestureDetector(this, this)
    }

    private fun setupUi() {
        findViews()
        setupClickListeners()
    }

    private fun findViews() {
        btnCloseStory = findViewById(R.id.btnCloseStory)
        imageViewHistoria = findViewById(R.id.imageViewHistoria)
        storyImage = findViewById(R.id.storyImage)
        tvUsername = findViewById(R.id.tvUsername)
        profileArea = findViewById(R.id.profileArea)
        areaIzquierda = findViewById(R.id.areaIzquierda)
        areaDerecha = findViewById(R.id.areaDerecha)
        storiesProgressView = findViewById(R.id.stories)
        areaCentro = findViewById(R.id.areaCentro)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupClickListeners() {
        profileArea.setOnClickListener {
            //TODO abrir perfil de usuario para despues
        }

        areaIzquierda.setOnClickListener {
            if(storyPosition > 0) storiesProgressView.reverse()
            else if(startUserPosition != 0) {
                startUserPosition--
                changeUserStories()
            }
        }

        areaDerecha.setOnClickListener {
            storiesProgressView.skip()
        }

        areaCentro.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN) storiesProgressView.pause()
            else if(event.action == MotionEvent.ACTION_UP) storiesProgressView.resume()

            true
        }

        btnCloseStory.setOnClickListener {
            finish()
        }
    }

    private fun changeUserStories() {
        historia = historias[startUserPosition]
        storyPosition = 0
        setupStoriesProgressView()
        tvUsername.text = historia.username

        Glide.with(this)
                .load( historia.profileImage )
                .into( imageViewHistoria )
    }

    private fun setupStoriesProgressView() {
        storiesProgressView.setStoriesCount(historia.imagenes.size)
        storiesProgressView.setStoryDuration(3000L)
        storiesProgressView.setStoriesListener(this)

        storiesProgressView.startStories(storyPosition)
        setStoryImage(storyPosition)
    }

    private fun setStoryImage(pos: Int) {
        Glide.with(this)
            .load( historia.imagenes[pos].urlImage )
            .into( storyImage )
    }

    override fun onComplete() {
        startUserPosition++
        if(startUserPosition >= historias.size) finish()
        else changeUserStories()
    }

    override fun onNext() {
        storyPosition++
        setStoryImage(storyPosition)
    }

    override fun onPrev() {
        storyPosition--
        setStoryImage(storyPosition)
    }

    override fun onDestroy() {
        storiesProgressView.destroy()
        super.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(e: MotionEvent?): Boolean = false

    override fun onShowPress(e: MotionEvent?) { }

    override fun onSingleTapUp(e: MotionEvent?): Boolean = false

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean = false

    override fun onLongPress(e: MotionEvent?) { }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                    if (diffX > 0) {
                        //Toast.makeText(applicationContext, "Left to Right swipe gesture", Toast.LENGTH_SHORT).show()
                        if(startUserPosition > 0) {
                            startUserPosition--
                            changeUserStories()
                        }
                    }
                    else {
                        //Toast.makeText(applicationContext, "Right to Left swipe gesture", Toast.LENGTH_SHORT).show()
                        startUserPosition++
                        if(startUserPosition >= historias.size) finish()
                        else changeUserStories()
                    }
                }
            }
        }
        catch (exception: Exception) {
            exception.printStackTrace()
        }
        return true
    }
}