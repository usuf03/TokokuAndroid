package com.example.tokoku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tokoku.MainActivity
import com.example.tokoku.R
import com.example.tokoku.app.ApiConfig
import com.example.tokoku.helper.SharedPref
import com.example.tokoku.model.ResponModel
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s = SharedPref(this)

        btn_register.setOnClickListener {
            register()
        }
    }

    fun register() {
        if(edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        } else if(edt_email.text.isEmpty()) {
            edt_email.error = "Kolom email tidak boleh kosong"
            edt_email.requestFocus()
            return
        } else if(edt_phone.text.isEmpty()) {
            edt_phone.error = "Kolom nomor telepon tidak boleh kosong"
            edt_phone.requestFocus()
            return
        } else if(edt_password.text.isEmpty()) {
            edt_password.error = "Kolom password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(), edt_email.text.toString(), edt_phone.text.toString(), edt_password.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                // handel ketika berhasil
                pb.visibility = View.GONE
                val respon = response.body()!!

                if(respon.success == 1) {
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Selamat datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity, ""+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                // handel ketika gagal
                pb.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, ""+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}