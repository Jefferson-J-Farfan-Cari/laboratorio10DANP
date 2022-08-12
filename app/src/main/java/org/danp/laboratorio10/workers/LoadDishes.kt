package org.danp.laboratorio10.workers

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.labjetpackcompose.Models.Dish
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.danp.laboratorio10.utils.getJsonDataFromAsset

const val RESULT = "0"

class LoadDishes(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.e("Question", "3")
        val id = inputData.getString("ID_DEPARTMENT") ?: return Result.failure()
        val result = loadFile(id.toInt())
        val output: Data = workDataOf(RESULT to result.size)

        return Result.success(output)
    }

    private fun loadFile(idDepartment: Int):List<Dish>{
        val dishes: List<Dish>
        val jsonFileString = getJsonDataFromAsset(applicationContext, "dish.json")
        val gson = Gson()
        val listDepartmentType = object : TypeToken<List<Dish>>() {}.type
        val allDishes: List<Dish> = gson.fromJson(jsonFileString, listDepartmentType)
        dishes = allDishes.filter { d -> d.department_id == idDepartment }
        dishes.forEachIndexed { idx, dish -> Log.i("data", "> Item $idx:\n$dish") }
        return dishes
    }
}