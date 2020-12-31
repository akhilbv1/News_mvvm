package org.akiapps.news.mainactivity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.akiapps.news.R
import org.akiapps.news.extensions.showToast
import org.akiapps.news.viewmodelextensions.GenericSavedStateViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

    @Inject
    lateinit var mainFactory: MainViewModelFactory

    val mainViewModel: MainViewModel by viewModels {
        GenericSavedStateViewModelFactory(mainFactory, this, intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
        //getArticles()
    }

    private fun setupBottomNavigationView(){
        bnvMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_selected_country -> {
                    showToast("Hurray Working")
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun getArticles() {
        mainViewModel.fetchArticles()
        mainViewModel.getArticles().observe(this, Observer {
            Log.d(TAG, "articles list is${it.toList()}")
        })
        mainViewModel.getErrorLiveData().observe(this, Observer {
            Log.d(TAG, "error is $it")
        })
    }


}