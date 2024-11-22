package app.megaeventapi.util

import java.math.BigInteger
import java.security.MessageDigest

class CryptographyUtil {
    companion object {
        fun encodeWithMD5(pass: String) = BigInteger(1, MessageDigest.getInstance("MD5")
            .digest(pass.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}