package com.example.latihanstudikasus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.latihanstudikasus.R
import com.example.latihanstudikasus.datastore.UserManager
import com.example.latihanstudikasus.model.UserResponseItem
import com.example.latihanstudikasus.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var userManage : UserManager
    lateinit var listUserlogin : List<UserResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            Login()
        }

        tv_login_buat_akun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    fun Login(){
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.loginUserAPI()
        viewModel.getLiveLogin().observe(this, Observer {
            if (it != null){
                listUserlogin = it
                loginAuth(listUserlogin)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Gagal Login!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loginAuth(listlogin : List<UserResponseItem>){
        userManage = UserManager(this)
        val username = et_login_username.text.toString()
        val  password = et_login_password.text.toString()
        for(i in listlogin.indices){
            if (username == listlogin[i].username && password == listlogin[i].password) {
                GlobalScope.launch {
                    userManage.setBoolean(true)
                    userManage.saveData(
                        listlogin[i].name,
                        listlogin[i].id,
                        listlogin[i].password,
                        listlogin[i].image,
                        listlogin[i].umur.toString(),
                        listlogin[i].username,
                        listlogin[i].address
                    )
                }
                Toast.makeText(this, "Welcome User!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}