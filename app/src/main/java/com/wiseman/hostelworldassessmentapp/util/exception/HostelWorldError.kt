package com.wiseman.hostelworldassessmentapp.util.exception

sealed class HostelWorldException(message: String) : Exception(message) {
    class NetworkError(message: String) : HostelWorldException(message)
    class ApiError(code: Int, message: String) : HostelWorldException("API Error: $code - $message")
    class ParsingError(message: String) : HostelWorldException("Parsing Error: $message")
    data object UnknownError : HostelWorldException("Unknown Error")
}

object ErrorMessages {
    const val NETWORK_ERROR = "No Internet connection"
}