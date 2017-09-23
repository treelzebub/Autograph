package net.treelzebub.autograph

import oauth.signpost.http.HttpRequest
import oauth.signpost.http.HttpResponse
import okhttp3.OkHttpClient
import oauth.signpost.AbstractOAuthProvider
import okhttp3.Request


/**
 * Created by treelzebub on 9/23/2017
 */
class OauthProducer @JvmOverloads constructor(
        requestTokenEndpointUrl: String,
        accessTokenEndpointUrl: String,
        authorizationWebsiteUrl: String,
        @Transient private val okHttpClient: OkHttpClient = OkHttpClient()
) : AbstractOAuthProvider(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl) {

//    fun setOkHttpClient(okHttpClient: OkHttpClient) {
//        this.okHttpClient = okHttpClient
//    }

    @Throws(Exception::class)
    override fun createRequest(endpointUrl: String): HttpRequest {
        val request = Request.Builder().url(endpointUrl).build()
        return OkHttpRequestAdapter(request)
    }

    @Throws(Exception::class)
    override fun sendRequest(request: HttpRequest): HttpResponse {
        val response = okHttpClient.newCall(request.unwrap() as Request).execute()
        return OkHttpResponseAdapter(response)
    }
}