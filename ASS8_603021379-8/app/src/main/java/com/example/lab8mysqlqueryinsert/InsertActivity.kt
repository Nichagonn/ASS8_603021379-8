package com.example.ass8_3968


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.lass8_3968.EmployeeAPI
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
    }
    fun onAdd(v: View){
        val api : EmployeeAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeAPI :: class.java)
            var radioGroup: RadioGroup = findViewById(R.id.radioGroup)
            var selectedId: Int = radioGroup.checkedRadioButtonId;
            var radioButton: RadioButton = findViewById(selectedId);
        api.insertEmp(
            edit_username.text.toString(),
            radioButton.text as String,
            edit_email.text.toString(),
            edit_salary.text.toString().toInt()).enqueue(object : Callback<Employee> {

            override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                if (response.isSuccessful()){

                    Toast.makeText(applicationContext, "Successfully Inserted", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailur " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun onCancle(v:View){
        finish()
    }
}