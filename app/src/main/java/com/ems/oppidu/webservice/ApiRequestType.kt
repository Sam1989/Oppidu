package  com.ems.oppidu.webservice

import org.json.JSONObject
import java.lang.reflect.Type

class ApiRequestType {

    lateinit var ApiName : String
    lateinit var responseType : Type
    lateinit var url : String
    lateinit var requestType : RequestType
}
