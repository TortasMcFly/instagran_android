package hector.villa.instagran.ui.detalle_historia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import hector.villa.instagran.R
import hector.villa.instagran.domain.Historia

class StoriesActivity : AppCompatActivity() {
    private lateinit var historias: ArrayList<Historia>
    private var startUserPosition = 0

    private lateinit var viewPagerStories: ViewPager
    private lateinit var viewpageStoriesAdapter: ViewpageStoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        historias = intent.getSerializableExtra("historias_usuarios") as ArrayList<Historia>
        startUserPosition = intent.getIntExtra("start_position", 0)

        findViews()
        setupViewpager()
    }

    private fun findViews() {
        viewPagerStories = findViewById(R.id.viewPagerStories)
    }

    private fun setupViewpager() {
        viewpageStoriesAdapter = ViewpageStoriesAdapter(this, historias)
        viewPagerStories.adapter = viewpageStoriesAdapter
    }
}