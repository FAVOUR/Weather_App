package com.example.weatherapp

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class MockDispatchers() : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {

        return when (request.path) {

            BuildConfig.BASEURL -> {

                MockResponse().setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
            else -> {
                MockResponse().setResponseCode(500)
                    .setBody("No response form server")
            }
        }

    }
}
