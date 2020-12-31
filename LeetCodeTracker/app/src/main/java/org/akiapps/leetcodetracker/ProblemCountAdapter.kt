package org.akiapps.leetcodetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ProblemCountAdapter(val problems:ArrayList<CountSelectedModel>,val appPref:AppPreference,val listener:OnCardClickListener):RecyclerView.Adapter<ProblemCountAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_checklist,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateUi(position)
    }

    override fun getItemCount(): Int  = problems.size

    interface OnCardClickListener{
        fun onClickCard(count:Int)
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvCount:TextView = itemView.findViewById(R.id.cbItem)
        val cvCount:MaterialCardView = itemView.findViewById(R.id.cvCount)

        init {
            itemView.setOnClickListener {
                problems.forEach {
                    if(it.count == adapterPosition+1){
                        it.isCompleted = !it.isCompleted
                    }
                }
                if(problems[adapterPosition].isCompleted){
                    tvCount.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
                    cvCount.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.green))
                }else {
                    tvCount.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
                    cvCount.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.white))
                }
                appPref.setCount(problems)
                listener.onClickCard(problems.count { it.isCompleted })
            }
        }

        fun updateUi(pos:Int){
            tvCount.text = (problems[pos].count).toString()
            if(problems[pos].isCompleted){
                tvCount.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
                cvCount.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.green))
            }else {
                tvCount.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
                cvCount.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.white))
            }
        }
    }
}