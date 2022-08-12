package org.danp.laboratorio10

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import org.danp.laboratorio10.workers.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)

        btn1.setOnClickListener {
            val loadDepartments = OneTimeWorkRequestBuilder<LoadDepartments>()
                .build()
            WorkManager.getInstance(this).enqueue(loadDepartments)
        }

        btn2.setOnClickListener {
            val counter = PeriodicWorkRequestBuilder<Counter>(15, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(this).enqueue(counter)
        }

        btn3.setOnClickListener {
            val edt: EditText = findViewById(R.id.edt)
            val loadDishes = OneTimeWorkRequestBuilder<LoadDishes>()
                .setInputData(
                    workDataOf(
                        "ID_DEPARTMENT" to edt.text.toString()
                    )
                )
                .build()
            WorkManager.getInstance(this).enqueue(loadDishes)
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(loadDishes.id)
                .observe(this) { info ->
                    if (info != null && info.state.isFinished) {
                        val myResult = info.outputData.getInt(
                            RESULT,
                            1
                        )
                        Log.e("Dishes size", myResult.toString())
                    }
                }
        }

        btn4.setOnClickListener {
            Log.e("Question", "4")
            val workA = OneTimeWorkRequestBuilder<WorkA>()
                .build()
            val workB = OneTimeWorkRequestBuilder<WorkB>()
                .build()

            WorkManager.getInstance(this)
                .beginWith(workA)
                .then(workB)
                .enqueue()
        }

        btn5.setOnClickListener {
            Log.e("Question", "5")
            val workA = OneTimeWorkRequestBuilder<WorkA>()
                .build()
            val workB = OneTimeWorkRequestBuilder<WorkB>()
                .build()

            WorkManager.getInstance(this)
                .beginWith(listOf(workA, workB))
                .enqueue()
        }
    }
}