package enzapps.com.kotlin_volley_gson

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    var queue:RequestQueue? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue=Volley.newRequestQueue(this)
        getData()
    }
    public fun getData()
    {
        var url="https://jsonplaceholder.typicode.com/posts/1"
        var dialog:ProgressDialog= ProgressDialog(this)
        dialog.setMessage("Loading...")
        dialog.show()
        var request : StringRequest =object:StringRequest(Method.GET,url, Response.Listener { response ->
            dialog.dismiss()

                var builder: GsonBuilder = GsonBuilder()
                var gson: Gson = builder.create()
                /**Below line used to when occurs response as JsonArray**/
                /*var model: List<Model> = gson.fromJson(response.toString(),object :TypeToken<List<Model>>(){}.type)
                model.forEach { println("Id"+it.id)
                    Toast.makeText(this,it.id,Toast.LENGTH_SHORT).show()}*/
                /** Below line is used to when occurs response as JsonObject **/
                var model=gson.fromJson<Model>(response,Model::class.java)
                Toast.makeText(this,model.id.toString(),Toast.LENGTH_SHORT).show()



        }, Response.ErrorListener { error ->
            dialog.dismiss()
        }){}
        queue?.add(request)
    }
}
