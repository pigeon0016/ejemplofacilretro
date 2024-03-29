package com.example.ejemplo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnResClickListener {
    private lateinit var adapter : Adapter // cuando incializas una variable q no es en la misma linea
    private var articulos2 = mutableListOf<Resultado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniReycler()
        getEmailPopular()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/").addConverterFactory(GsonConverterFactory.create()).build()
        //crea la conexion y el tipo de serializacion
    }


    fun getEmailPopular() {
        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<Respuesta> = getRetrofit().create(APIService::class.java).getResponse("emailed/7.json?api-key=1Kslpxv1DBWgxLBlROlf4Wsx4xty13BD")
            val respuesta: Respuesta? = call.body()
            val articulos: List<Resultado> = respuesta?.results ?: emptyList()
            runOnUiThread{
                if(call.isSuccessful){
                    if(respuesta != null){
                      //  findViewById<TextView>(R.id.titletextview).text = respuesta.results.first().title
                        articulos2.clear()
                        articulos2.addAll(articulos)
                        adapter.notifyDataSetChanged()
                    }
                }else{

                }

            }
        }
    }
// inicializar el recycler vuew
    fun iniReycler(){
        adapter = Adapter(articulos2,this)
    findViewById<RecyclerView>(R.id.rv1).layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    findViewById<RecyclerView>(R.id.rv1).adapter = adapter
    }

   override fun onResItemClicked(position: Int){
       val myWebView: WebView = findViewById(R.id.wvPagina)
       myWebView.loadUrl(articulos2[position].url)

   }

}