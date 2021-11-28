//import com.example.mydoctor.SignInBody
//import com.example.mydoctor.UserBody
//import okhttp3.OkHttpClient
//import okhttp3.ResponseBody
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.Headers
//import retrofit2.http.POST
//
//interface ApiInterface {
//    @Headers("Content-Type:application/json")
//    @POST("auth_tokens")
//    fun signin(@Body info: SignInBody): retrofit2.Call<ResponseBody>
//
//    @Headers("Content-Type:application/json")
//    @POST("users")
//    fun registerUser(
//        @Body info: UserBody
//    ): retrofit2.Call<ResponseBody>
//}
//class RetrofitInstance {
//    companion object {
//        val BASE_URL: String = "YOUR_URL_HERE"
//
//        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }
//
//        val client: OkHttpClient = OkHttpClient.Builder().apply {
//            this.addInterceptor(interceptor)
//        }.build()
//        fun getRetrofitInstance(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//    }
//}