package com.ausom.moobee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ausom.moobee.ui.movie.detail.MovieDetailFragmentArgs
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavController.OnDestinationChangedListener {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navHostFragment.findNavController().apply {
            addOnDestinationChangedListener(this@MainActivity)
        })
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when(destination.id){
            R.id.movieDetailFragment  -> {
                supportActionBar?.setDisplayShowHomeEnabled(false)
                supportActionBar?.title = MovieDetailFragmentArgs
                    .fromBundle(arguments!!)
                    .movieDetailParameter
                    .movieModel
                    .title
            }
            else -> supportActionBar?.apply {
                setDisplayUseLogoEnabled(true)
                setDisplayShowHomeEnabled(true)
                setIcon(R.mipmap.ic_launcher)
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.findNavController().navigateUp()
    }
}
