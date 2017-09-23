package net.treelzebub.autograph

import oauth.signpost.http.HttpRequest
import okhttp3.Request
import okio.Buffer
import java.io.IOException
import java.io.InputStream


/**
 * Created by treelzebub on 9/23/2017
 */
class OkHttpRequestAdapter(private var request: Request) : HttpRequest {

    override fun getAllHeaders(): Map<String, String>? {
        val headers = mutableMapOf<String, String>()
        request.headers().names().forEach {
            headers.put(it, request.header(it))
        }
        return headers
    }

    override fun getContentType(): String? {
        val body = request.body()
        return if (body != null) {
            if (body.contentType() != null) body.contentType().toString() else null
        } else null
    }

    override fun getHeader(key: String): String? {
        return request.header(key)
    }

    @Throws(IOException::class)
    override fun getMessagePayload(): InputStream? {
        val body = request.body() ?: return null
        Buffer().use {
            body.writeTo(it)
            return it.inputStream()
        }
    }

    override fun getMethod(): String? {
        return request.method()
    }

    override fun getRequestUrl(): String? {
        return request.url().toString()
    }

    override fun setHeader(key: String, value: String) {
        request = request.newBuilder().header(key, value).build()
    }

    override fun setRequestUrl(url: String) {
        request = request.newBuilder().url(url).build()
    }

    override fun unwrap(): Any {
        return request
    }
}