package net.treelzebub.autograph

import oauth.signpost.OAuthConsumer
import oauth.signpost.exception.OAuthException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * Created by treelzebub on 9/23/2017
 */
class AutographInterceptor(private val consumer: OAuthConsumer) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val request = chain.request()
            chain.proceed(consumer.sign(request).unwrap() as Request)
        } catch (e: OAuthException) {
            throw IOException("Could not sign request", e)
        }
    }
}