package com.aakash.fithub.`object`

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aakash.fithub.R
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmail: EditText
    private lateinit var etfname: EditText
    private lateinit var etlname: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etfname=findViewById(R.id.etfname)
        etlname=findViewById(R.id.etlname)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        etCPassword=findViewById(R.id.etCPassword)
        btnRegister=findViewById(R.id.btnRegister)
        login=findViewById(R.id.login)

        btnRegister.setOnClickListener(){
            signupwithApi()
        }
    }

    private fun signupwithApi() {
        val fname=etfname.text.toString()
        val lname=etlname.text.toString()
        val email=etEmail.text.toString()
        val password=etPassword.text.toString()
        val cpassword=etCPassword.text.toString()
        if(password==cpassword){
            val user = User(fname=fname, lname= lname, email = email, password = password )
            CoroutineScope(Dispatchers.IO).launch {
                val repository= UserRepository()
                val response= repository.registerUSer(user)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                        clear()
                    }
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "Error Register", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        else{
            Toast.makeText(this, "p and cP is not same", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear() {
        etEmail.setText("")
        etfname.setText("")
        etlname.setText("")
        etPassword.setText("")
        etCPassword.setText("")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> {
                val intent = Intent(this, LoginActivity::class.java )
                startActivity(intent)
                finish()
            }
        }
    }
}