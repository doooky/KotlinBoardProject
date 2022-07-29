package kdh.kotlinBoardProject.exception

class CustomException(
    var errorCode: ErrorCode? = null
) : RuntimeException()