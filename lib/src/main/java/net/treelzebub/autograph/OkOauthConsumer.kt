package net.treelzebub.autograph

import oauth.signpost.AbstractOAuthConsumer
import oauth.signpost.http.HttpRequest
import okhttp3.Request


/**
 * Created by treelzebub on 9/23/2017
 */
class OkOauthConsumer(consumerKey: String, consumerSecret: String) : AbstractOAuthConsumer(consumerKey, consumerSecret) {

    override fun wrap(request: Any?): HttpRequest {
        if (request !is Request) {
            throw IllegalArgumentException("Request is not of okhttp3.")
        }
        return OkHttpRequestAdapter(request)
    }
}