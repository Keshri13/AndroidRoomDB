package com.example.learnroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.learnroomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appDb : StudentDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = StudentDatabase.getDatabase(this)
        binding.btnRead.setOnClickListener  { 
            readData()

        }

        binding.btnWrite.setOnClickListener{
            writeData()

        }

    }

    private fun writeData() {

        val name = binding.etName.text.toString()
        val marks = binding.etMarks.text.toString()
        val roll = binding.etRollNo.text.toString()

        if (name.isNotEmpty() && marks.isNotEmpty() && roll.isNotEmpty()){

            val student = Student(null,name, marks.toInt(), roll.toInt())
            GlobalScope.launch(Dispatchers.IO){

                appDb.studentDao().insert(student)

            }

            binding.etName.text.clear()
            binding.etMarks.text.clear()
            binding.etRollNo.text.clear()

            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Try Again with some data",Toast.LENGTH_SHORT).show()
        }

    }

    private fun readData() {

        val roll = binding.etRollNoSearch.text.toString()
        if (roll.isNotEmpty()){
            lateinit var student: Student
            GlobalScope.launch {
                student = appDb.studentDao().findByRoll(roll.toInt())
                displayData(student)
            }
        }else{

        }

    }

    private suspend fun displayData(student: Student) {
        withContext(Dispatchers.Main){
            binding.tvDisplayDemoData.text = student.name + " : " + student.marks
        }

    }
}