package com.example.weatherapp

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class MockDispatchers : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {

        return when (request.path) {

            VALID_SEARCH_PARAM -> {
                System.out.println(" the current path ${ request.path } bae")

                MockResponse().setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
            else -> {
                System.out.println(" the current path ${ request.path }")
                System.out.println(" the current path $VALID_SEARCH_PARAM check")
                System.out.println(" the current path ${ "/weather?q=Kenya&units=metric&appId=123456T6" == request.path} equality")
                MockResponse().setResponseCode(500)
                    .setBody("No response form server")
            }
        }

    }
}


const val KENYA = "Kenya"

const val INVALID_QUERY_PARAM = "NONE"

const val TEMPERATURE_UNIT_METRIC = "metric"

const val APP_KEY = "123456T6"

private const val VALID_SEARCH_PARAM =
    "/weather?q=$KENYA&units=$TEMPERATURE_UNIT_METRIC&appId=$APP_KEY"

private const val INVALID_SEARCH_PARAM =
    "/weather?q=$INVALID_QUERY_PARAM&units=$TEMPERATURE_UNIT_METRIC&appId=$APP_KEY"
