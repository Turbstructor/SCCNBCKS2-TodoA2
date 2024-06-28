package spartacodingclub.nbcamp.kotlinspring.assignment.sccnbcks2todoa2.global.exception.type

import java.io.Serial

class NonExistentEntityException : RuntimeException {

    @Serial
    private val serialVersionUID: Long = -1072869312753892L


    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable): super(cause)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean)
    : super(message, cause, enableSuppression, writableStackTrace)

}