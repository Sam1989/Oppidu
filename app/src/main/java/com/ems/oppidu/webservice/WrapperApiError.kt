package  com.ems.oppidu.webservice

data class WrapperApiError (val apiUrl : String = "",
                            val isSuccess : Boolean = false,
                            val msg : String = "", var validationErr : List<String> = emptyList<String>())