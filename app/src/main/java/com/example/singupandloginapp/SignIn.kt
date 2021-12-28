package com.example.singupandloginapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignIn : AppCompatActivity() {
    lateinit var edtSignin : EditText
    lateinit var edsigninPass : EditText
    lateinit var signinBtn : Button
    lateinit var DB : DbHlpr
    var usrnam = ""
    var paswrd = ""
    var valid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        title = ""
        DB = DbHlpr(applicationContext)
        // init UI
        initSignin()



        signinBtn.setOnClickListener {
            usrnam=edtSignin.text.toString()
            paswrd=edsigninPass.text.toString()
            DB.rtrvinfo(usrnam)
            valid=DB.validatepass(usrnam)
            if (usrnam.isNotEmpty() && paswrd.isNotEmpty()){
                if (valid == paswrd){
                    Toast.makeText(this, "sign in success", Toast.LENGTH_LONG).show()
                    moveto()

                }else{
                    Toast.makeText(this, "Wrong password, try again", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "username & password must not empty", Toast.LENGTH_LONG).show()
            }

        }
    }

    // init Function
    fun initSignin(){
        edtSignin=findViewById(R.id.edSigninN)
        edsigninPass=findViewById(R.id.edSigninPas)
        signinBtn=findViewById(R.id.signNbtn)
    }

    //  fun validation(){}

    fun moveto(){
        var intent = Intent(this, Details::class.java)
        intent.putExtra("User", usrnam)
        startActivity(intent)
    }

}