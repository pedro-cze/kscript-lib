package cz.my2n.lib.utils.kscript

import com.beust.klaxon.Klaxon
import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * Header function e.g
 *
 * val contentType = header("Content-Type")
 * val applicationJson = contentType("applicaiton/json")
 *
 * @param header: String
 * @param value: String
 */
val header: (String) -> (String) -> Headers = { header ->
    { value ->
        Headers.headersOf(header, value)
    }
}

/**
 * Generic request function for http requests
 * @param baseUrl
 * @param path
 * @param requestBody
 * @param headers
 * @param method
 * @return Request
 */
fun request(baseUrl: String, path: String, headers: Headers, method: String, requestBody: RequestBody?): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .method(method, requestBody)
            .build()
}

/**
 * Basic GET request function
 * @param baseUrl
 * @param path
 * @param headers
 * @return Request
 */
fun get(baseUrl: String, path: String, headers: Headers): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .get()
            .build()
}

/**
 * Basic POST request function
 * @param baseUrl
 * @param path
 * @param requestBody
 * @param headers
 * @return Request
 */
fun post(baseUrl: String, path: String, requestBody: RequestBody, headers: Headers): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .post(requestBody)
            .build()
}

/**
 * Basic PUT request function
 * @param baseUrl
 * @param path
 * @param requestBody
 * @param headers
 * @return Request
 */
fun put(baseUrl: String, path: String, requestBody: RequestBody, headers: Headers): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .put(requestBody)
            .build()
}

/**
 * Basic PATCH request function
 * @param baseUrl
 * @param path
 * @param requestBody
 * @param headers
 * @return Request
 */
fun patch(baseUrl: String, path: String, requestBody: RequestBody, headers: Headers): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .patch(requestBody)
            .build()
}

/**
 * Basic DELETE request function
 * @param baseUrl
 * @param path
 * @param requestBody
 * @param headers
 * @return Request
 */
fun delete(baseUrl: String, path: String, requestBody: RequestBody?, headers: Headers): Request {
    return Request.Builder()
            .url("$baseUrl/$path")
            .headers(headers)
            .delete(requestBody)
            .build()
}

/**
 * Call request function to execute given http request
 * @param request: Request
 * @return Response
 */
fun call(request: Request): Response {
    val client = OkHttpClient().newBuilder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .build()
    return client.newCall(request).execute()
}

/**
 * Klaxon takes returned json string and deserializes into list of T objects.
 * An empty list is returned in case the response body is null.
 * @param response: Response
 * @return Collection<T>
 */
inline fun <reified T> parseResponse(response: Response): Collection<T> {
    val result = response.body?.let { responseBody ->
        return@let Klaxon().parseArray<T>(responseBody.byteStream())
    }
    return result ?: emptyList()
}
