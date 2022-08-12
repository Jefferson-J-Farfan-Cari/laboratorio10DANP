package org.danp.laboratorio10.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.danp.laboratorio10.models.Department
import org.danp.laboratorio10.utils.getJsonDataFromAsset

class LoadDepartments(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e("Question", "1")
        loadFile()
        return Result.success()
    }

    private fun loadFile() {
        val departments: List<Department>
        val jsonFileString = getJsonDataFromAsset(applicationContext, "department.json")
        val gson = Gson()
        val listDepartmentType = object : TypeToken<List<Department>>() {}.type
        departments = gson.fromJson(jsonFileString, listDepartmentType)
        departments.forEachIndexed { idx, department ->
            Log.i("data", "> Item $idx:\n$department")
        }
    }
}