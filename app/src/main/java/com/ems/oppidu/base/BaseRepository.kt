package com.ems.oppidu.base

import com.ems.oppidu.webservice.RestClient


open class BaseRepository(asyncViewController: AsyncViewController?) {

    val restClient: RestClient = RestClient()

    init {
        restClient.asyncViewController = asyncViewController
    }
}