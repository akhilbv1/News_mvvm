package org.akiapps.leetcodetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProblemCountAdapter.OnCardClickListener {

    val appPref:AppPreference by lazy {
        AppPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCountUi()
    }

    private fun setupCountUi(){
        recCheckList.apply {
            adapter = ProblemCountAdapter(appPref.getProblemCountList(),appPref,this@MainActivity)
            layoutManager = GridLayoutManager(this@MainActivity,5)
        }
        tvCount.text = "0"
    }

    override fun onClickCard(count: Int) {
        tvCount.text = count.toString()
    }


}
data class CountSelectedModel(val count:Int,var isCompleted:Boolean=false)

fun getProblemCount():ArrayList<CountSelectedModel>{
    val probCountList:ArrayList<CountSelectedModel> = arrayListOf()
    for (i in 1..300){
        probCountList.add(CountSelectedModel(i))
    }
    return probCountList
}