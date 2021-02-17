package hector.villa.instagran.ui.detalle_historia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.teresaholfeld.stories.StoriesProgressView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia

class DetalleHistoriaActivity : AppCompatActivity(), StoriesProgressView.StoriesListener {

    private lateinit var historias: ArrayList<Historia>
    private lateinit var historia: Historia
    private var startUserPosition = 0
    private lateinit var btnCloseStory: View
    private lateinit var imageViewHistoria: ImageView
    private lateinit var storyImage: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var profileArea: View
    private lateinit var storiesProgressView: StoriesProgressView

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_historia)
        title = "Historia"
        historias = intent.getSerializableExtra("historias_usuarios") as ArrayList<Historia>
        startUserPosition = intent.getIntExtra("start_position", 0)
        setupUi()
        changeUserStories()
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
        storiesProgressView = findViewById(R.id.stories)
    }

    private fun setupClickListeners() {
        profileArea.setOnClickListener {
            //TODO abrir perfil de usuario para despues
        }

        btnCloseStory.setOnClickListener {
            finish()
        }
    }

    private fun changeUserStories() {
        historia = historias[startUserPosition]
        position = 0
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

        storiesProgressView.startStories(position)
        setStoryImage(position)
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
        position++
        setStoryImage(position)
    }

    override fun onPrev() {
        position--
        setStoryImage(position)
    }

    override fun onDestroy() {
        storiesProgressView.destroy()
        super.onDestroy()
    }
}