package org.akiapps.eyedropremainder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_drop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddDropRemainderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drop)
        setListeners()
    }


    private fun setListeners() {
        btnSave.setOnClickListener {
            verifyFields()
        }
    }

    private fun verifyFields() {
        if (etDropNumber.text?.trim().toString().isEmpty() || etDropNumber.text?.trim().toString()
                .isBlank()
        ) {
            etDropNumber.error = "Please Enter Drop Number"
        } else if (etDropGap.text?.trim().toString().isEmpty() || etDropGap.text?.trim().toString()
                .isBlank()
        ) {
            etDropNumber.error = ""
            etDropGap.error = "Please Enter Drop Gap"
        }/* else if (etDropTime.text?.trim().toString().isEmpty() || etDropTime.text?.trim()
                .toString()
                .isBlank()
        ) {
            etDropNumber.error = ""
            etDropGap.error = ""
            etDropTime.error = "Please Enter Drop starting time"
        }*/ else {
            saveDropRemainder()
        }
    }
    private val bgScope = CoroutineScope(Dispatchers.Default)
    private fun saveDropRemainder() {
        val obj = RemainderTable(
            dropNumber = etDropNumber.text?.trim().toString().toInt(),
            dropGap = etDropGap.text?.trim().toString().toInt(),
            time = System.currentTimeMillis()
        )
        bgScope.launch {
            RemainderDataBase.DatabaseCreator.getRemainderDataBase(applicationContext).remainderDao().insertDropRemainder(obj)
        }
    }
}