package cz.my2n.lib.utils.kscript

import com.beust.klaxon.Klaxon
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

data class Credentials(val username: String, val password: String)
data class ApiKey(val accessToken: String, val issued: String, val validity: String)

const val APPLICATION_JSON = "application/json"
const val UNAUTHORIZED = "Unauthorized"

/**
 * Authenticate function
 * @param baseUrl: String
 * @param credentials: Credentials
 * @return ApiKey?
 * @throws Exception
 */
fun authenticate(baseUrl: String, credentials: Credentials): ApiKey? {
    val requestBody = Klaxon().toJsonString(credentials).toRequestBody(APPLICATION_JSON.toMediaType())
    val contentType = header("Content-Type")
    val request = post(baseUrl, "auth", requestBody, contentType(APPLICATION_JSON))
    val response = call(request)
    response.body?.let {
        if (response.code != 401) {
            return parseResponse<ApiKey>(response).first()
        }
    }
    throw Exception(UNAUTHORIZED)
}
