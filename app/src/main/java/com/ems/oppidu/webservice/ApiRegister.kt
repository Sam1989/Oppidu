package  com.ems.oppidu.webservice


object ApiRegister {


    //----------- DEMO URL ------------------
    const val API_BASE_URL = "http://kerching.projectstatus.in/AppServices/KCAPI.asmx/"
    const val BASE_URL = API_BASE_URL

    const val GET_TOKEN = "GetToken"
    const val GET_HOME_DATA = "GetHomeData"
    const val GET_MEMBER_DETAILS = "MemberDetails"
    const val GET_MERCHANT_DATA = "GetMerchantData"
    const val GET_BEST_CASHBACK_MERCHANT = "GetBestCashbackMerchant"
    const val CMS_LIST = "CMSList"
    const val GET_VISITED_AND_SIMILAR_MERCHANT = "GetVisitedAndSimilarMerchant"
    const val MY_REWARDS = "MyRewards"
    const val REFERRAL_DETAILS = "ReferralDetails"
    const val TRACK_AND_REDIRECT = "TrackAndRedirect"
    const val GET_CONTACT_US_SUBJECT = "GetContactUsSubject"
    const val SUBMIT_CONTACT_US = "SubmitContactUs"

    const val TRANSACTION_SUMMARY = "MyTransactionsummary"

    const val WITHDRAW_HISTORY = "MyWithdrawalHistory"

    const val PAY_TO_MY_ACCOUNT = "PayToMyAccount"

    const val UPDATE_MEMBER_DETAILS = "UpdateMemberDetails"
    const val CHANGE_EMAIL = "ChangeEmail"
    const val CHANGE_PASSWORD = "ChangePassword"
    const val UPDATE_BANK_DETAILS = "UpdateBankDetails"
    const val CLOSE_MEMBER_ACCOUNT = "CloseMemberAccount"

    const val SUBMIT_CLAIM = "SubmitClaim"


    fun getApiRequestType(url: String): ApiRequestType {

        val result = ApiRequestType()

        when (url) {

            /***************** START ****************/



           /* GET_HOME_DATA -> {
                result.responseType = object :
                    TypeToken<HomeDataResponse>() {}.type
                result.url = BASE_URL + GET_HOME_DATA
                result.ApiName = GET_HOME_DATA
                result.requestType = RequestType.URLENCODED
                return result
            }



            GET_BEST_CASHBACK_MERCHANT -> {
                result.responseType = object :
                    TypeToken<CategoryResponse>() {}.type
                result.url = BASE_URL + GET_BEST_CASHBACK_MERCHANT
                result.ApiName = GET_BEST_CASHBACK_MERCHANT
                result.requestType = RequestType.URLENCODED
                return result
            }*/




            /***************** END ****************/


        }
        throw IllegalStateException("API is not registered")
    }
}