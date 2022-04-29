package com.example.ejemplo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/").addConverterFactory(GsonConverterFactory.create()).build()
        //crea la conexion y el tipo de serializacion
    }


    fun getEmailPopular(view: View) {
        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<Respuesta> = getRetrofit().create(APIService::class.java).getResponse("emailed/7.json?api-key=1Kslpxv1DBWgxLBlROlf4Wsx4xty13BD")
            val respuesta: Respuesta? = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    if(respuesta != null){
                        findViewById<TextView>(R.id.titletextview).text = respuesta.results.first().title
                    }
                }else{

                }

            }
        }
    }


}