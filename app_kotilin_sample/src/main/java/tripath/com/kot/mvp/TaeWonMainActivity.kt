package tripath.com.kot.mvp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tae_won_main.*
import kotlinx.android.synthetic.main.app_bar_tae_won_main.*
import tripath.com.kot.R

class TaeWonMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener ,MainContract.View{


    private val recyclerView by lazy {
        findViewById(R.id.recyclerView) as RecyclerView
    }

    private lateinit var imageAdapter : ImageAdapter

    private lateinit var presenter : MainPresenter

    /**
     * implement MainContract.View
     */
    override fun updateItems(items: ArrayList<ImageItem>, isClear: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * implement MainContract.View
     */
    override fun showToast(pos : Int) {


    }

    /**
     * implement MainContract.View
     */
    override fun notifyAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tae_won_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        // start Main Presenter

        imageAdapter = ImageAdapter( this )
        recyclerView.adapter = imageAdapter

        presenter = MainPresenter().apply {
            view = this@TaeWonMainActivity
            imageData = TaeWonImageRepository
            adapterModel = imageAdapter
            adapterView = imageAdapter
        }

        presenter.loadItem(this,false)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.tae_won_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
