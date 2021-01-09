package  com.ems.oppidu.webservice

import android.content.Context
import android.net.ConnectivityManager
import androidx.databinding.library.BuildConfig
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils


import com.oppidu.oppidu.R
import com.ems.oppidu.base.AsyncViewController
import com.ems.oppidu.base.MainApplication
import com.ems.oppidu.model.response.homedata.HomeDataResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class RestClient() {

    val CONNECTION_TIMEOUT = 300

    var asyncViewController: AsyncViewController? = null
    var apiResponseListener: ApiResponseListener? = null
    private var apiInterface: ApiInterface?
    private val activeApiCalls = ArrayList<Call<*>>()


    var gson: Gson

    init {
        apiInterface = getApiInterface()

        gson = Gson()
    }


    /**
     * provides retrofit client with proxy implemented api interface
     *
     * @return
     */
    private fun getApiInterface(): ApiInterface? {

        if (apiInterface == null) {
            val client = getOkHttpClient() ?: return null
            val gson = GsonBuilder().setLenient().create()
            val builder = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
            builder.baseUrl(ApiRegister.BASE_URL)
            return builder.build().create(ApiInterface::class.java)
        } else {
            return apiInterface
        }
    }

    /**
     * get OkHttpClient
     *
     * @return OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient? {
        try {
            val okClientBuilder = OkHttpClient.Builder()

            okClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            okClientBuilder.readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            okClientBuilder.writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okClientBuilder.addInterceptor(httpLoggingInterceptor)

            }
            return okClientBuilder.build()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * format error string here
     *
     * @param rawError
     * @return
     */
    private fun getError(rawError: String?): String {
        if (rawError == null) {
            return "Error Occurred"
        }
        val formulatedError = MainApplication.get().getContext()
            .getString(R.string.bad_response)
        return if (rawError.contains("JsonReader.setLenient")) {
            formulatedError
        } else if (rawError.contains("Unable to resolve host")) {
            "Couldn't connect to server"
        } else
            rawError
    }


    /**
     * checks network connectivity
     *
     * @return
     */
    private fun isConnectedToNetwork(): Boolean {
        val cm =
            MainApplication.get().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null
    }


    /**
     * showProgressDialog while api call is active
     */
    private fun showProgressDialog() {
        asyncViewController?.showProgressDialog()
    }

    /**
     * hide progress after api calling
     */
    private fun hideProgressDialog() {
        asyncViewController?.hideProgressDialog()
    }


    /**
     *  common checks before any api calling
     *
     * @param String
     * @return
     */
    private fun passChecks(): Boolean {
        if (!isConnectedToNetwork()) {
            asyncViewController?.onNoInternet()
            return false
        }
        return apiInterface != null
    }


    fun callKerchingApi(String: String, requestPojo: Any?, dataCarrier: MutableLiveData<*>?) {
        callKerchingApi(String, requestPojo, dataCarrier, true)
    }

    fun callKerchingApi(
        String: String,
        requestPojo: Any?,
        dataCarrier: MutableLiveData<*>?,
        showProgressDialog: Boolean
    ) {

        if (!passChecks()) {
            return
        }

        val apiRequestType = ApiRegister.getApiRequestType(String)

        var call: Call<ResponseBody>? = null

        if (apiRequestType.requestType === RequestType.URLENCODED) {

            if (apiRequestType.url.contains(ApiRegister.GET_TOKEN)) {
                call =
                    getApiInterface()!!.postGetToken(apiRequestType.url, "COMPANY_ID", "ACCESS_TOKEN")

            } else if (apiRequestType.url.contains(ApiRegister.GET_HOME_DATA)) {
                call =
                    getApiInterface()!!.postGetHomeData(apiRequestType.url, requestPojo.toString())

            } else if (apiRequestType.url.contains(ApiRegister.GET_MERCHANT_DATA) ||
                apiRequestType.url.contains(ApiRegister.GET_BEST_CASHBACK_MERCHANT)
            ) {

            } else if (apiRequestType.url.contains(ApiRegister.CMS_LIST)) {
                call =
                    getApiInterface()!!.postCmsList(apiRequestType.url, requestPojo.toString())
            } else if (apiRequestType.url.contains(ApiRegister.GET_VISITED_AND_SIMILAR_MERCHANT)) {


            } else if (apiRequestType.ApiName.equals(ApiRegister.GET_MEMBER_DETAILS)) {

            }

        }

        if (showProgressDialog) {
            showProgressDialog()
        }

        call!!.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                activeApiCalls.remove(call)

                val responseBody = response.body()

                if (responseBody != null) {

                    val responseString: String
                    try {
                        responseString = responseBody.string()

                        if (apiRequestType.url.contains(ApiRegister.GET_HOME_DATA)) {
                            val master: HomeDataResponse =
                                gson.fromJson(responseString, apiRequestType.responseType)

                            if (dataCarrier != null) {
                                dataCarrier.value = master
                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    ToastUtils.showLong(
                        MainApplication.get().getString(R.string.something_went_wrong)
                    )
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                activeApiCalls.remove(call)
                val err = getError(t.message)

                ToastUtils.showLong(err)

                hideProgressDialog()
            }
        })
    }
}
