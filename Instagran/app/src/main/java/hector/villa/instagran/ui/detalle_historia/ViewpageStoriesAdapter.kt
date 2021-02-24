package hector.villa.instagran.ui.detalle_historia

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.teresaholfeld.stories.StoriesProgressView
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia

class ViewpageStoriesAdapter(val mContext: Context, val historias: ArrayList<Historia>): PagerAdapter()
{
    private var layoutInflater: LayoutInflater? = null
    private lateinit var btnCloseStory: View
    private lateinit var imageViewHistoria: ImageView
    private lateinit var storyImage: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var profileArea: View
    private lateinit var areaIzquierda: View
    private lateinit var areaDerecha: View
    private lateinit var areaCentro: View
    private lateinit var storiesProgressView: StoriesProgressView

    private var storyPosition = 0

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_viewpage_historia, container, false)
        findViews(view)
        setupUi(position)
        setupStoriesProgressView(position)
        setupClickListeners(position)
        container.addView(view)
        return view
    }

    private fun findViews(view: View) {
        btnCloseStory = view.findViewById(R.id.btnCloseStory)
        imageViewHistoria = view.findViewById(R.id.imageViewHistoria)
        storyImage = view.findViewById(R.id.storyImage)
        tvUsername = view.findViewById(R.id.tvUsername)
        profileArea = view.findViewById(R.id.profileArea)
        areaIzquierda = view.findViewById(R.id.areaIzquierda)
        areaDerecha = view.findViewById(R.id.areaDerecha)
        storiesProgressView = view.findViewById(R.id.stories)
        areaCentro = view.findViewById(R.id.areaCentro)
    }

    private fun setupUi(position: Int){
        tvUsername.text = historias[position].username

        Glide.with(mContext)
            .load(historias[position].profileImage)
            .into(imageViewHistoria)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupClickListeners(position: Int) {
        profileArea.setOnClickListener {
            //TODO abrir perfil de usuario para despues
        }

        areaIzquierda.setOnClickListener {
            if(storyPosition > 0) storiesProgressView.reverse()
            else if(position != 0) {
                //TODO Regresar al anterior item
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
            //TODO Cerrar el activity
        }
    }

    private fun setupStoriesProgressView(position: Int) {
        storyPosition = 0
        storiesProgressView.setStoriesCount(historias[position].imagenes.size)
        storiesProgressView.setStoryDuration(3000L)
        storiesProgressView.setStoriesListener( object : StoriesProgressView.StoriesListener {
            override fun onComplete() {
                //TODO Cambiar al siguiente item
                //TODO Si es el último cerrar el activity
            }

            override fun onNext() {
                storyPosition++
                setStoryImage(storyPosition, position)
            }

            override fun onPrev() {
                storyPosition--
                setStoryImage(storyPosition, position)
            }
        })

        storiesProgressView.startStories(storyPosition)
        setStoryImage(storyPosition, position)
    }

    private fun setStoryImage(pos: Int, position: Int) {
        Glide.with(mContext)
            .load(historias[position].imagenes[pos].urlImage)
            .into(storyImage)
    }

    override fun getCount(): Int {
        return historias.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        storiesProgressView.destroy()
        collection.removeView(view as View)
    }
}