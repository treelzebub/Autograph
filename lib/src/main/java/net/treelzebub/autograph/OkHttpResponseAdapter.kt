package net.treelzebub.autograph

import oauth.signpost.http.HttpResponse
import okhttp3.Response
import java.io.InputStream

/**
 * Created by treelzebub on 9/23/2017
 */
class OkHttpResponseAdapter(private val response: Response) : HttpResponse {

    override fun getStatusCode(): Int {
        return response.code()
    }

    override fun getContent(): InputStream? {
        return response.body()?.byteStream()
    }

    override fun getReasonPhrase(): String? {
        return response.message()
    }

    override fun unwrap(): Any {
        return response
    }
}