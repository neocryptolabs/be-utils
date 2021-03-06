package com.neocryptolabs.core.service.bithumb

import java.io.*
import java.net.*
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder
import java.security.AccessController
import java.security.GeneralSecurityException
import java.security.PrivilegedAction
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import java.util.zip.GZIPInputStream
import javax.net.ssl.*

/**
 * A fluid interface for making HTTP requests using an underlying
 * [HttpURLConnection] (or sub-class).
 *
 *
 * Each instance supports making a single request and cannot be reused for
 * further requests.
 */
class BithumbHttpRequest {
    /**
     * Creates [HTTP connections][HttpURLConnection] for [urls][URL].
     */
    interface ConnectionFactory {
        /**
         * Open an [HttpURLConnection] for the specified [URL].
         *
         * @throws IOException
         */
        @Throws(IOException::class)
        fun create(url: URL): HttpURLConnection

        /**
         * Open an [HttpURLConnection] for the specified [URL] and
         * [Proxy].
         *
         * @throws IOException
         */
        @Throws(IOException::class)
        fun create(url: URL, proxy: Proxy?): HttpURLConnection

        companion object {
            /**
             * A [ConnectionFactory] which uses the built-in
             * [URL.openConnection]
             */
            val DEFAULT: ConnectionFactory = object : ConnectionFactory {
                @Throws(IOException::class)
                override fun create(url: URL): HttpURLConnection {
                    return url.openConnection() as HttpURLConnection
                }

                @Throws(IOException::class)
                override fun create(url: URL, proxy: Proxy?): HttpURLConnection {
                    return url.openConnection(proxy) as HttpURLConnection
                }
            }
        }
    }

    /**
     * Callback interface for reporting upload progress for a request.
     */
    interface UploadProgress {
        /**
         * Callback invoked as data is uploaded by the request.
         *
         * @param uploaded
         * The number of bytes already uploaded
         * @param total
         * The total number of bytes that will be uploaded or -1 if
         * the length is unknown.
         */
        fun onUpload(uploaded: Long, total: Long)

        companion object {
            val DEFAULT: UploadProgress = object : UploadProgress {
                override fun onUpload(uploaded: Long, total: Long) {}
            }
        }
    }

    /**
     *
     *
     * Encodes and decodes to and from Base64 notation.
     *
     *
     *
     * I am placing this code in the Public Domain. Do with it as you will. This
     * software comes with no guarantees or warranties but with plenty of
     * well-wishing instead! Please visit [http://iharder.net/base64](http://iharder.net/base64)
     * periodically to check for updates or to contribute improvements.
     *
     *
     * @author Robert Harder
     * @author rob@iharder.net
     * @version 2.3.7
     */
    object Base64 {
        /** The equals sign (=) as a byte.  */
        private const val EQUALS_SIGN = '='.toByte()

        /** Preferred encoding.  */
        private const val PREFERRED_ENCODING = "US-ASCII"

        /** The 64 valid Base64 values.  */
        private val _STANDARD_ALPHABET = byteArrayOf('A'.toByte(),
                'B'.toByte(), 'C'.toByte(), 'D'.toByte(), 'E'.toByte(), 'F'.toByte(),
                'G'.toByte(), 'H'.toByte(), 'I'.toByte(), 'J'.toByte(), 'K'.toByte(),
                'L'.toByte(), 'M'.toByte(), 'N'.toByte(), 'O'.toByte(), 'P'.toByte(),
                'Q'.toByte(), 'R'.toByte(), 'S'.toByte(), 'T'.toByte(), 'U'.toByte(),
                'V'.toByte(), 'W'.toByte(), 'X'.toByte(), 'Y'.toByte(), 'Z'.toByte(),
                'a'.toByte(), 'b'.toByte(), 'c'.toByte(), 'd'.toByte(), 'e'.toByte(),
                'f'.toByte(), 'g'.toByte(), 'h'.toByte(), 'i'.toByte(), 'j'.toByte(),
                'k'.toByte(), 'l'.toByte(), 'm'.toByte(), 'n'.toByte(), 'o'.toByte(),
                'p'.toByte(), 'q'.toByte(), 'r'.toByte(), 's'.toByte(), 't'.toByte(),
                'u'.toByte(), 'v'.toByte(), 'w'.toByte(), 'x'.toByte(), 'y'.toByte(),
                'z'.toByte(), '0'.toByte(), '1'.toByte(), '2'.toByte(), '3'.toByte(),
                '4'.toByte(), '5'.toByte(), '6'.toByte(), '7'.toByte(), '8'.toByte(),
                '9'.toByte(), '+'.toByte(), '/'.toByte())

        /**
         *
         *
         * Encodes up to three bytes of the array <var>source</var> and writes
         * the resulting four Base64 bytes to <var>destination</var>. The source
         * and destination arrays can be manipulated anywhere along their length
         * by specifying <var>srcOffset</var> and <var>destOffset</var>. This
         * method does not check to make sure your arrays are large enough to
         * accomodate <var>srcOffset</var> + 3 for the <var>source</var> array
         * or <var>destOffset</var> + 4 for the <var>destination</var> array.
         * The actual number of significant bytes in your array is given by
         * <var>numSigBytes</var>.
         *
         *
         *
         * This is the lowest level of the encoding methods with all possible
         * parameters.
         *
         *
         * @param source
         * the array to convert
         * @param srcOffset
         * the index where conversion begins
         * @param numSigBytes
         * the number of significant bytes in your array
         * @param destination
         * the array to hold the conversion
         * @param destOffset
         * the index where output will be put
         * @return the <var>destination</var> array
         * @since 1.3
         */
        private fun encode3to4(source: ByteArray, srcOffset: Int,
                               numSigBytes: Int, destination: ByteArray, destOffset: Int): ByteArray {
            val ALPHABET = _STANDARD_ALPHABET
            val inBuff = ((if (numSigBytes > 0) source[srcOffset].toInt() shl 24 ushr 8 else 0)
                    or (if (numSigBytes > 1) source[srcOffset + 1].toInt() shl 24 ushr 16 else 0)
                    or if (numSigBytes > 2) source[srcOffset + 2].toInt() shl 24 ushr 24 else 0)
            return when (numSigBytes) {
                3 -> {
                    destination[destOffset] = ALPHABET[inBuff ushr 18]
                    destination[destOffset + 1] = ALPHABET[inBuff ushr 12 and 0x3f]
                    destination[destOffset + 2] = ALPHABET[inBuff ushr 6 and 0x3f]
                    destination[destOffset + 3] = ALPHABET[inBuff and 0x3f]
                    destination
                }
                2 -> {
                    destination[destOffset] = ALPHABET[inBuff ushr 18]
                    destination[destOffset + 1] = ALPHABET[inBuff ushr 12 and 0x3f]
                    destination[destOffset + 2] = ALPHABET[inBuff ushr 6 and 0x3f]
                    destination[destOffset + 3] = EQUALS_SIGN
                    destination
                }
                1 -> {
                    destination[destOffset] = ALPHABET[inBuff ushr 18]
                    destination[destOffset + 1] = ALPHABET[inBuff ushr 12 and 0x3f]
                    destination[destOffset + 2] = EQUALS_SIGN
                    destination[destOffset + 3] = EQUALS_SIGN
                    destination
                }
                else -> destination
            }
        }

        /**
         * Encode string as a byte array in Base64 annotation.
         *
         * @param string
         * @return The Base64-encoded data as a string
         */
        fun encode(string: String): String {
            val bytes: ByteArray
            bytes = try {
                string.toByteArray(charset(PREFERRED_ENCODING))
            } catch (e: UnsupportedEncodingException) {
                string.toByteArray()
            }
            return encodeBytes(bytes)
        }
        /**
         * Encodes a byte array into Base64 notation.
         *
         * @param source
         * The data to convert
         * @param off
         * Offset in array where conversion should begin
         * @param len
         * Length of data to convert
         * @return The Base64-encoded data as a String
         * @throws NullPointerException
         * if source array is null
         * @throws IllegalArgumentException
         * if source array, offset, or length are invalid
         * @since 2.0
         */
        /**
         * Encodes a byte array into Base64 notation.
         *
         * @param source
         * The data to convert
         * @return The Base64-encoded data as a String
         * @throws NullPointerException
         * if source array is null
         * @throws IllegalArgumentException
         * if source array, offset, or length are invalid
         * @since 2.0
         */
        @JvmOverloads
        fun encodeBytes(source: ByteArray, off: Int = 0, len: Int = source.size): String {
            val encoded = encodeBytesToBytes(source, off, len)
            return try {
                String(encoded, Charset.forName(PREFERRED_ENCODING))
            } catch (uue: UnsupportedEncodingException) {
                String(encoded)
            }
        }

        /**
         * Similar to [.encodeBytes] but returns a byte
         * array instead of instantiating a String. This is more efficient if
         * you're working with I/O streams and have large data sets to encode.
         *
         *
         * @param source
         * The data to convert
         * @param off
         * Offset in array where conversion should begin
         * @param len
         * Length of data to convert
         * @return The Base64-encoded data as a String if there is an error
         * @throws NullPointerException
         * if source array is null
         * @throws IllegalArgumentException
         * if source array, offset, or length are invalid
         * @since 2.3.1
         */
        fun encodeBytesToBytes(source: ByteArray?, off: Int, len: Int): ByteArray {
            if (source == null) throw NullPointerException("Cannot serialize a null array.")
            require(off >= 0) { "Cannot have negative offset: $off" }
            require(len >= 0) { "Cannot have length offset: $len" }
            require(off + len <= source.size) {
                String.format(
                        "Cannot have offset of %d and length of %d with array of length %d",
                        off, len, source.size)
            }

            // Bytes needed for actual encoding
            val encLen = len / 3 * 4 + if (len % 3 > 0) 4 else 0
            val outBuff = ByteArray(encLen)
            var d = 0
            var e = 0
            val len2 = len - 2
            while (d < len2) {
                encode3to4(source, d + off, 3, outBuff, e)
                d += 3
                e += 4
            }
            if (d < len) {
                encode3to4(source, d + off, len - d, outBuff, e)
                e += 4
            }
            return if (e <= outBuff.size - 1) {
                val finalOut = ByteArray(e)
                System.arraycopy(outBuff, 0, finalOut, 0, e)
                finalOut
            } else outBuff
        }
    }

    /**
     * HTTP request exception whose cause is always an [IOException]
     */
    class HttpRequestException
    /**
     * Create a new HttpRequestException with the given cause
     *
     * @param cause
     */
    (cause: IOException?) : RuntimeException(cause) {
        /**
         * Get [IOException] that triggered this request exception
         *
         * @return [IOException] cause
         */
        override val cause: IOException
            get() = super.cause as IOException

        companion object {
            private const val serialVersionUID = -1170466989781746231L
        }
    }

    /**
     * Operation that handles executing a callback once complete and handling
     * nested exceptions
     *
     * @param <V>
    </V> */
    protected abstract class Operation<V> : Callable<V> {
        /**
         * Run operation
         *
         * @return result
         * @throws HttpRequestException
         * @throws IOException
         */
        @Throws(HttpRequestException::class, IOException::class)
        protected abstract fun run(): V

        /**
         * Operation complete callback
         *
         * @throws IOException
         */
        @Throws(IOException::class)
        protected abstract fun done()

        @Throws(HttpRequestException::class)
        override fun call(): V {
            var thrown = false
            return try {
                run()
            } catch (e: HttpRequestException) {
                thrown = true
                throw e
            } catch (e: IOException) {
                thrown = true
                throw HttpRequestException(e)
            } finally {
                try {
                    done()
                } catch (e: IOException) {
                    if (!thrown) throw HttpRequestException(e)
                }
            }
        }
    }

    /**
     * Class that ensures a [Closeable] gets closed with proper exception
     * handling.
     *
     * @param <V>
    </V> */
    protected abstract class CloseOperation<V>
    /**
     * Create closer for operation
     *
     * @param closeable
     * @param ignoreCloseExceptions
     */ protected constructor(private val closeable: Closeable,
                              private val ignoreCloseExceptions: Boolean) : Operation<V>() {
        @Throws(IOException::class)
        override fun done() {
            if (closeable is Flushable) (closeable as Flushable).flush()
            if (ignoreCloseExceptions) try {
                closeable.close()
            } catch (e: IOException) {
                // Ignored
            } else closeable.close()
        }
    }

    /**
     * Class that and ensures a [Flushable] gets flushed with proper
     * exception handling.
     *
     * @param <V>
    </V> */
    protected abstract class FlushOperation<V>
    /**
     * Create flush operation
     *
     * @param flushable
     */ protected constructor(private val flushable: Flushable) : Operation<V>() {
        @Throws(IOException::class)
        override fun done() {
            flushable.flush()
        }
    }

    /**
     * Request output stream
     */
    class RequestOutputStream(stream: OutputStream?,
                              charset: String?, bufferSize: Int) : BufferedOutputStream(stream, bufferSize) {
        val encoder: CharsetEncoder

        /**
         * Write string to stream
         *
         * @param value
         * @return this stream
         * @throws IOException
         */
        @Throws(IOException::class)
        fun write(value: String?): RequestOutputStream {
            val bytes = encoder.encode(CharBuffer.wrap(value))
            super.write(bytes.array(), 0, bytes.limit())
            return this
        }

        /**
         * Create request output stream
         *
         * @param stream
         * @param charset
         * @param bufferSize
         */
        init {
            encoder = Charset.forName(getValidCharset(charset)).newEncoder()
        }
    }

    private var connection: HttpURLConnection? = null
    private val url: URL
    private val requestMethod: String
    private var output: RequestOutputStream? = null
    private var multipart = false
    private var form = false
    private var ignoreCloseExceptions = true
    private var uncompress = false
    private var bufferSize = 8192
    private var totalSize: Long = -1
    private var totalWritten: Long = 0
    private var httpProxyHost: String? = null
    private var httpProxyPort = 0
    private var progress = UploadProgress.DEFAULT

    /**
     * Create HTTP connection wrapper
     *
     * @param url
     * Remote resource URL.
     * @param method
     * HTTP request method (e.g., "GET", "POST").
     * @throws HttpRequestException
     */
    constructor(url: CharSequence, method: String) {
        try {
            this.url = URL(url.toString())
        } catch (e: MalformedURLException) {
            throw HttpRequestException(e)
        }
        requestMethod = method
    }

    /**
     * Create HTTP connection wrapper
     *
     * @param url
     * Remote resource URL.
     * @param method
     * HTTP request method (e.g., "GET", "POST").
     * @throws HttpRequestException
     */
    constructor(url: URL, method: String) {
        this.url = url
        requestMethod = method
    }

    private fun createProxy(): Proxy {
        return Proxy(Proxy.Type.HTTP, InetSocketAddress(httpProxyHost,
                httpProxyPort))
    }

    private fun createConnection(): HttpURLConnection {
        return try {
            val connection: HttpURLConnection
            connection = if (httpProxyHost != null) CONNECTION_FACTORY.create(url, createProxy()) else CONNECTION_FACTORY.create(url)
            connection.requestMethod = requestMethod
            connection
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    override fun toString(): String {
        return method() + ' ' + url()
    }

    /**
     * Get underlying connection
     *
     * @return connection
     */
    fun getConnection(): HttpURLConnection? {
        if (connection == null) connection = createConnection()
        return connection
    }

    /**
     * Set whether or not to ignore exceptions that occur from calling
     * [Closeable.close]
     *
     *
     * The default value of this setting is `true`
     *
     * @param ignore
     * @return this request
     */
    fun ignoreCloseExceptions(ignore: Boolean): BithumbHttpRequest {
        ignoreCloseExceptions = ignore
        return this
    }

    /**
     * Get whether or not exceptions thrown by [Closeable.close] are
     * ignored
     *
     * @return true if ignoring, false if throwing
     */
    fun ignoreCloseExceptions(): Boolean {
        return ignoreCloseExceptions
    }

    /**
     * Get the status code of the response
     *
     * @return the response code
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun code(): Int {
        return try {
            closeOutput()
            getConnection()!!.responseCode
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    /**
     * Set the value of the given [AtomicInteger] to the status code of
     * the response
     *
     * @param output
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun code(output: AtomicInteger): BithumbHttpRequest {
        output.set(code())
        return this
    }

    /**
     * Is the response code a 200 OK?
     *
     * @return true if 200, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun ok(): Boolean {
        return HttpURLConnection.HTTP_OK == code()
    }

    /**
     * Is the response code a 201 Created?
     *
     * @return true if 201, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun created(): Boolean {
        return HttpURLConnection.HTTP_CREATED == code()
    }

    /**
     * Is the response code a 204 No Content?
     *
     * @return true if 204, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun noContent(): Boolean {
        return HttpURLConnection.HTTP_NO_CONTENT == code()
    }

    /**
     * Is the response code a 500 Internal Server Error?
     *
     * @return true if 500, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun serverError(): Boolean {
        return HttpURLConnection.HTTP_INTERNAL_ERROR == code()
    }

    /**
     * Is the response code a 400 Bad Request?
     *
     * @return true if 400, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun badRequest(): Boolean {
        return HttpURLConnection.HTTP_BAD_REQUEST == code()
    }

    /**
     * Is the response code a 404 Not Found?
     *
     * @return true if 404, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun notFound(): Boolean {
        return HttpURLConnection.HTTP_NOT_FOUND == code()
    }

    /**
     * Is the response code a 304 Not Modified?
     *
     * @return true if 304, false otherwise
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun notModified(): Boolean {
        return HttpURLConnection.HTTP_NOT_MODIFIED == code()
    }

    /**
     * Get status message of the response
     *
     * @return message
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun message(): String {
        return try {
            closeOutput()
            getConnection()!!.responseMessage
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    /**
     * Disconnect the connection
     *
     * @return this request
     */
    fun disconnect(): BithumbHttpRequest {
        getConnection()!!.disconnect()
        return this
    }

    /**
     * Set chunked streaming mode to the given size
     *
     * @param size
     * @return this request
     */
    fun chunk(size: Int): BithumbHttpRequest {
        getConnection()!!.setChunkedStreamingMode(size)
        return this
    }

    /**
     * Set the size used when buffering and copying between streams
     *
     *
     * This size is also used for send and receive buffers created for both char
     * and byte arrays
     *
     *
     * The default buffer size is 8,192 bytes
     *
     * @param size
     * @return this request
     */
    fun bufferSize(size: Int): BithumbHttpRequest {
        require(size >= 1) { "Size must be greater than zero" }
        bufferSize = size
        return this
    }

    /**
     * Get the configured buffer size
     *
     *
     * The default buffer size is 8,192 bytes
     *
     * @return buffer size
     */
    fun bufferSize(): Int {
        return bufferSize
    }

    /**
     * Set whether or not the response body should be automatically uncompressed
     * when read from.
     *
     *
     * This will only affect requests that have the 'Content-Encoding' response
     * header set to 'gzip'.
     *
     *
     * This causes all receive methods to use a [GZIPInputStream] when
     * applicable so that higher level streams and readers can read the data
     * uncompressed.
     *
     *
     * Setting this option does not cause any request headers to be set
     * automatically so [.acceptGzipEncoding] should be used in
     * conjunction with this setting to tell the server to gzip the response.
     *
     * @param uncompress
     * @return this request
     */
    fun uncompress(uncompress: Boolean): BithumbHttpRequest {
        this.uncompress = uncompress
        return this
    }

    /**
     * Create byte array output stream
     *
     * @return stream
     */
    protected fun byteStream(): ByteArrayOutputStream {
        val size = contentLength()
        return if (size > 0) ByteArrayOutputStream(size) else ByteArrayOutputStream()
    }
    /**
     * Get response as [String] in given character set
     *
     *
     * This will fall back to using the UTF-8 character set if the given charset
     * is null
     *
     * @param charset
     * @return string
     * @throws HttpRequestException
     */
    /**
     * Get response as [String] using character set returned from
     * [.charset]
     *
     * @return string
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun body(charset: String? = this.charset()): String {
        val output = byteStream()
        return try {
            copy(buffer(), output)
            output.toString(getValidCharset(charset))
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    /**
     * Get the response body as a [String] and set it as the value of the
     * given reference.
     *
     * @param output
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun body(output: AtomicReference<String?>): BithumbHttpRequest {
        output.set(body())
        return this
    }

    /**
     * Get the response body as a [String] and set it as the value of the
     * given reference.
     *
     * @param output
     * @param charset
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun body(output: AtomicReference<String?>,
             charset: String?): BithumbHttpRequest {
        output.set(body(charset))
        return this
    }

    /**
     * Is the response body empty?
     *
     * @return true if the Content-Length response header is 0, false otherwise
     * @throws HttpRequestException
     */
    @get:Throws(HttpRequestException::class)
    val isBodyEmpty: Boolean
        get() = contentLength() == 0

    /**
     * Get response as byte array
     *
     * @return byte array
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun bytes(): ByteArray {
        val output = byteStream()
        try {
            copy(buffer(), output)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return output.toByteArray()
    }

    /**
     * Get response in a buffered stream
     *
     * @see .bufferSize
     * @return stream
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun buffer(): BufferedInputStream {
        return BufferedInputStream(stream(), bufferSize)
    }

    /**
     * Get stream to response body
     *
     * @return stream
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun stream(): InputStream? {
        var stream: InputStream?
        if (code() < HttpURLConnection.HTTP_BAD_REQUEST) stream = try {
            getConnection()!!.inputStream
        } catch (e: IOException) {
            throw HttpRequestException(e)
        } else {
            stream = getConnection()!!.errorStream
            if (stream == null) stream = try {
                getConnection()!!.inputStream
            } catch (e: IOException) {
                if (contentLength() > 0) throw HttpRequestException(e) else ByteArrayInputStream(ByteArray(0))
            }
        }
        return if (!uncompress || ENCODING_GZIP != contentEncoding()) stream else try {
            GZIPInputStream(stream)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }
    /**
     * Get reader to response body using given character set.
     *
     *
     * This will fall back to using the UTF-8 character set if the given charset
     * is null
     *
     * @param charset
     * @return reader
     * @throws HttpRequestException
     */
    /**
     * Get reader to response body using the character set returned from
     * [.charset]
     *
     * @return reader
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun reader(charset: String? = this.charset()): InputStreamReader {
        return try {
            InputStreamReader(stream(), getValidCharset(charset))
        } catch (e: UnsupportedEncodingException) {
            throw HttpRequestException(e)
        }
    }
    /**
     * Get buffered reader to response body using the given character set r and
     * the configured buffer size
     *
     *
     * @see .bufferSize
     * @param charset
     * @return reader
     * @throws HttpRequestException
     */
    /**
     * Get buffered reader to response body using the character set returned
     * from [.charset] and the configured buffer size
     *
     * @see .bufferSize
     * @return reader
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun bufferedReader(charset: String? = this.charset()): BufferedReader {
        return BufferedReader(reader(charset), bufferSize)
    }

    /**
     * Stream response body to file
     *
     * @param file
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun receive(file: File?): BithumbHttpRequest {
        val output: OutputStream
        output = try {
            BufferedOutputStream(FileOutputStream(file),
                    bufferSize)
        } catch (e: FileNotFoundException) {
            throw HttpRequestException(e)
        }
        return object : CloseOperation<BithumbHttpRequest?>(output, ignoreCloseExceptions) {
            @Throws(HttpRequestException::class, IOException::class)
            override fun run(): BithumbHttpRequest {
                return receive(output)
            }
        }.call()!!
    }

    /**
     * Stream response to given output stream
     *
     * @param output
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun receive(output: OutputStream?): BithumbHttpRequest {
        return try {
            copy(buffer(), output)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    /**
     * Stream response to given print stream
     *
     * @param output
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun receive(output: PrintStream?): BithumbHttpRequest {
        return receive(output as OutputStream?)
    }

    /**
     * Receive response into the given appendable
     *
     * @param appendable
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun receive(appendable: Appendable): BithumbHttpRequest {
        val reader = bufferedReader()
        return object : CloseOperation<BithumbHttpRequest?>(reader, ignoreCloseExceptions) {
            @Throws(IOException::class)
            public override fun run(): BithumbHttpRequest {
                val buffer = CharBuffer.allocate(bufferSize)
                var read: Int
                while (reader.read(buffer).also { read = it } != -1) {
                    buffer.rewind()
                    appendable.append(buffer, 0, read)
                    buffer.rewind()
                }
                return this@BithumbHttpRequest
            }
        }.call()!!
    }

    /**
     * Receive response into the given writer
     *
     * @param writer
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun receive(writer: Writer): BithumbHttpRequest {
        val reader = bufferedReader()
        return object : CloseOperation<BithumbHttpRequest?>(reader, ignoreCloseExceptions) {
            @Throws(IOException::class)
            public override fun run(): BithumbHttpRequest {
                return copy(reader, writer)
            }
        }.call()!!
    }

    /**
     * Set read timeout on connection to given value
     *
     * @param timeout
     * @return this request
     */
    fun readTimeout(timeout: Int): BithumbHttpRequest {
        getConnection()!!.readTimeout = timeout
        return this
    }

    /**
     * Set connect timeout on connection to given value
     *
     * @param timeout
     * @return this request
     */
    fun connectTimeout(timeout: Int): BithumbHttpRequest {
        getConnection()!!.connectTimeout = timeout
        return this
    }

    /**
     * Set header name to given value
     *
     * @param name
     * @param value
     * @return this request
     */
    fun header(name: String?, value: String?): BithumbHttpRequest {
        getConnection()!!.setRequestProperty(name, value)
        return this
    }

    /**
     * Set header name to given value
     *
     * @param name
     * @param value
     * @return this request
     */
    fun header(name: String?, value: Number?): BithumbHttpRequest {
        return header(name, value?.toString())
    }

    /**
     * Set all headers found in given map where the keys are the header names
     * and the values are the header values
     *
     * @param headers
     * @return this request
     */
    fun headers(headers: HashMap<String, String>): BithumbHttpRequest {
        if (!headers.isEmpty()) for (header in headers.entries) header(header)
        return this
    }

    /**
     * Set header to have given entry's key as the name and value as the value
     *
     * @param header
     * @return this request
     */
    fun header(header: Map.Entry<String?, String?>): BithumbHttpRequest {
        return header(header.key, header.value)
    }

    /**
     * Get a response header
     *
     * @param name
     * @return response header
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun header(name: String?): String {
        closeOutputQuietly()
        return getConnection()!!.getHeaderField(name)
    }

    /**
     * Get all the response headers
     *
     * @return map of response header names to their value(s)
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun headers(): Map<String, List<String?>> {
        closeOutputQuietly()
        return getConnection()!!.headerFields
    }
    /**
     * Get a date header from the response falling back to returning the given
     * default value if the header is missing or parsing fails
     *
     * @param name
     * @param defaultValue
     * @return date, default value on failures
     * @throws HttpRequestException
     */
    /**
     * Get a date header from the response falling back to returning -1 if the
     * header is missing or parsing fails
     *
     * @param name
     * @return date, -1 on failures
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun dateHeader(name: String?, defaultValue: Long = -1L): Long {
        closeOutputQuietly()
        return getConnection()!!.getHeaderFieldDate(name, defaultValue)
    }
    /**
     * Get an integer header value from the response falling back to the given
     * default value if the header is missing or if parsing fails
     *
     * @param name
     * @param defaultValue
     * @return header value as an integer, default value when missing or parsing
     * fails
     * @throws HttpRequestException
     */
    /**
     * Get an integer header from the response falling back to returning -1 if
     * the header is missing or parsing fails
     *
     * @param name
     * @return header value as an integer, -1 when missing or parsing fails
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun intHeader(name: String?, defaultValue: Int = -1): Int {
        closeOutputQuietly()
        return getConnection()!!.getHeaderFieldInt(name, defaultValue)
    }

    /**
     * Get all values of the given header from the response
     *
     * @param name
     * @return non-null but possibly empty array of [String] header values
     */
    fun headers(name: String): Array<String?> {
        val headers = headers()
        if (headers == null || headers.isEmpty()) return EMPTY_STRINGS
        val values = headers[name]
        return if (values != null && !values.isEmpty()) values.toTypedArray() else EMPTY_STRINGS
    }

    /**
     * Get parameter with given name from header value in response
     *
     * @param headerName
     * @param paramName
     * @return parameter value or null if missing
     */
    fun parameter(headerName: String?, paramName: String): String? {
        return getParam(header(headerName), paramName)
    }

    /**
     * Get all parameters from header value in response
     *
     *
     * This will be all key=value pairs after the first ';' that are separated
     * by a ';'
     *
     * @param headerName
     * @return non-null but possibly empty map of parameter headers
     */
    fun parameters(headerName: String?): Map<String, String> {
        return getParams(header(headerName))
    }

    /**
     * Get parameter values from header value
     *
     * @param header
     * @return parameter value or null if none
     */
    protected fun getParams(header: String?): Map<String, String> {
        if (header == null || header.length == 0) return emptyMap()
        val headerLength = header.length
        var start = header.indexOf(';') + 1
        if (start == 0 || start == headerLength) return emptyMap()
        var end = header.indexOf(';', start)
        if (end == -1) end = headerLength
        val params: MutableMap<String, String> = LinkedHashMap()
        while (start < end) {
            val nameEnd = header.indexOf('=', start)
            if (nameEnd != -1 && nameEnd < end) {
                val name = header.substring(start, nameEnd).trim { it <= ' ' }
                if (name.length > 0) {
                    val value = header.substring(nameEnd + 1, end).trim { it <= ' ' }
                    val length = value.length
                    if (length != 0) if (length > 2 && '"' == value[0] && '"' == value[length - 1]) params[name] = value.substring(1, length - 1) else params[name] = value
                }
            }
            start = end + 1
            end = header.indexOf(';', start)
            if (end == -1) end = headerLength
        }
        return params
    }

    /**
     * Get parameter value from header value
     *
     * @param value
     * @param paramName
     * @return parameter value or null if none
     */
    protected fun getParam(value: String?, paramName: String): String? {
        if (value == null || value.length == 0) return null
        val length = value.length
        var start = value.indexOf(';') + 1
        if (start == 0 || start == length) return null
        var end = value.indexOf(';', start)
        if (end == -1) end = length
        while (start < end) {
            val nameEnd = value.indexOf('=', start)
            if (nameEnd != -1 && nameEnd < end && paramName == value.substring(start, nameEnd).trim { it <= ' ' }) {
                val paramValue = value.substring(nameEnd + 1, end).trim { it <= ' ' }
                val valueLength = paramValue.length
                if (valueLength != 0) return if (valueLength > 2 && '"' == paramValue[0] && '"' == paramValue[valueLength - 1]) paramValue.substring(1, valueLength - 1) else paramValue
            }
            start = end + 1
            end = value.indexOf(';', start)
            if (end == -1) end = length
        }
        return null
    }

    /**
     * Get 'charset' parameter from 'Content-Type' response header
     *
     * @return charset or null if none
     */
    fun charset(): String? {
        return parameter(HEADER_CONTENT_TYPE, PARAM_CHARSET)
    }

    /**
     * Set the 'User-Agent' header to given value
     *
     * @param userAgent
     * @return this request
     */
    fun userAgent(userAgent: String?): BithumbHttpRequest {
        return header(HEADER_USER_AGENT, userAgent)
    }

    /**
     * Set the 'Referer' header to given value
     *
     * @param referer
     * @return this request
     */
    fun referer(referer: String?): BithumbHttpRequest {
        return header(HEADER_REFERER, referer)
    }

    /**
     * Set value of [HttpURLConnection.setUseCaches]
     *
     * @param useCaches
     * @return this request
     */
    fun useCaches(useCaches: Boolean): BithumbHttpRequest {
        getConnection()!!.useCaches = useCaches
        return this
    }

    /**
     * Set the 'Accept-Encoding' header to given value
     *
     * @param acceptEncoding
     * @return this request
     */
    fun acceptEncoding(acceptEncoding: String?): BithumbHttpRequest {
        return header(HEADER_ACCEPT_ENCODING, acceptEncoding)
    }

    /**
     * Set the 'Accept-Encoding' header to 'gzip'
     *
     * @see .uncompress
     * @return this request
     */
    fun acceptGzipEncoding(): BithumbHttpRequest {
        return acceptEncoding(ENCODING_GZIP)
    }

    /**
     * Set the 'Accept-Charset' header to given value
     *
     * @param acceptCharset
     * @return this request
     */
    fun acceptCharset(acceptCharset: String?): BithumbHttpRequest {
        return header(HEADER_ACCEPT_CHARSET, acceptCharset)
    }

    /**
     * Get the 'Content-Encoding' header from the response
     *
     * @return this request
     */
    fun contentEncoding(): String {
        return header(HEADER_CONTENT_ENCODING)
    }

    /**
     * Get the 'Server' header from the response
     *
     * @return server
     */
    fun server(): String {
        return header(HEADER_SERVER)
    }

    /**
     * Get the 'Date' header from the response
     *
     * @return date value, -1 on failures
     */
    fun date(): Long {
        return dateHeader(HEADER_DATE)
    }

    /**
     * Get the 'Cache-Control' header from the response
     *
     * @return cache control
     */
    fun cacheControl(): String {
        return header(HEADER_CACHE_CONTROL)
    }

    /**
     * Get the 'ETag' header from the response
     *
     * @return entity tag
     */
    fun eTag(): String {
        return header(HEADER_ETAG)
    }

    /**
     * Get the 'Expires' header from the response
     *
     * @return expires value, -1 on failures
     */
    fun expires(): Long {
        return dateHeader(HEADER_EXPIRES)
    }

    /**
     * Get the 'Last-Modified' header from the response
     *
     * @return last modified value, -1 on failures
     */
    fun lastModified(): Long {
        return dateHeader(HEADER_LAST_MODIFIED)
    }

    /**
     * Get the 'Location' header from the response
     *
     * @return location
     */
    fun location(): String {
        return header(HEADER_LOCATION)
    }

    /**
     * Set the 'Authorization' header to given value
     *
     * @param authorization
     * @return this request
     */
    fun authorization(authorization: String?): BithumbHttpRequest {
        return header(HEADER_AUTHORIZATION, authorization)
    }

    /**
     * Set the 'Proxy-Authorization' header to given value
     *
     * @param proxyAuthorization
     * @return this request
     */
    fun proxyAuthorization(proxyAuthorization: String?): BithumbHttpRequest {
        return header(HEADER_PROXY_AUTHORIZATION, proxyAuthorization)
    }

    /**
     * Set the 'Authorization' header to given values in Basic authentication
     * format
     *
     * @param name
     * @param password
     * @return this request
     */
    fun basic(name: String, password: String): BithumbHttpRequest {
        return authorization("Basic " + Base64.encode("$name:$password"))
    }

    /**
     * Set the 'Proxy-Authorization' header to given values in Basic
     * authentication format
     *
     * @param name
     * @param password
     * @return this request
     */
    fun proxyBasic(name: String, password: String): BithumbHttpRequest {
        return proxyAuthorization("Basic "
                + Base64.encode("$name:$password"))
    }

    /**
     * Set the 'If-Modified-Since' request header to the given value
     *
     * @param ifModifiedSince
     * @return this request
     */
    fun ifModifiedSince(ifModifiedSince: Long): BithumbHttpRequest {
        getConnection()!!.ifModifiedSince = ifModifiedSince
        return this
    }

    /**
     * Set the 'If-None-Match' request header to the given value
     *
     * @param ifNoneMatch
     * @return this request
     */
    fun ifNoneMatch(ifNoneMatch: String?): BithumbHttpRequest {
        return header(HEADER_IF_NONE_MATCH, ifNoneMatch)
    }
    /**
     * Set the 'Content-Type' request header to the given value and charset
     *
     * @param contentType
     * @param charset
     * @return this request
     */
    /**
     * Set the 'Content-Type' request header to the given value
     *
     * @param contentType
     * @return this request
     */
    @JvmOverloads
    fun contentType(contentType: String,
                    charset: String? = null): BithumbHttpRequest {
        return if (charset != null && charset.length > 0) {
            val separator = "; " + PARAM_CHARSET + '='
            header(HEADER_CONTENT_TYPE, contentType + separator
                    + charset)
        } else header(HEADER_CONTENT_TYPE, contentType)
    }

    /**
     * Get the 'Content-Type' header from the response
     *
     * @return response header value
     */
    fun contentType(): String {
        return header(HEADER_CONTENT_TYPE)
    }

    /**
     * Get the 'Content-Length' header from the response
     *
     * @return response header value
     */
    fun contentLength(): Int {
        return intHeader(HEADER_CONTENT_LENGTH)
    }

    /**
     * Set the 'Content-Length' request header to the given value
     *
     * @param contentLength
     * @return this request
     */
    fun contentLength(contentLength: String): BithumbHttpRequest {
        return contentLength(contentLength.toInt())
    }

    /**
     * Set the 'Content-Length' request header to the given value
     *
     * @param contentLength
     * @return this request
     */
    fun contentLength(contentLength: Int): BithumbHttpRequest {
        getConnection()!!.setFixedLengthStreamingMode(contentLength)
        return this
    }

    /**
     * Set the 'Accept' header to given value
     *
     * @param accept
     * @return this request
     */
    fun accept(accept: String?): BithumbHttpRequest {
        return header(HEADER_ACCEPT, accept)
    }

    /**
     * Set the 'Accept' header to 'application/json'
     *
     * @return this request
     */
    fun acceptJson(): BithumbHttpRequest {
        return accept(CONTENT_TYPE_JSON)
    }

    /**
     * Copy from input stream to output stream
     *
     * @param input
     * @param output
     * @return this request
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun copy(input: InputStream,
                       output: OutputStream?): BithumbHttpRequest {
        return object : CloseOperation<BithumbHttpRequest?>(input, ignoreCloseExceptions) {
            @Throws(IOException::class)
            public override fun run(): BithumbHttpRequest {
                val buffer = ByteArray(bufferSize)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output!!.write(buffer, 0, read)
                    totalWritten += read.toLong()
                    progress.onUpload(totalWritten, totalSize)
                }
                return this@BithumbHttpRequest
            }
        }.call()!!
    }

    /**
     * Copy from reader to writer
     *
     * @param input
     * @param output
     * @return this request
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun copy(input: Reader, output: Writer): BithumbHttpRequest {
        return object : CloseOperation<BithumbHttpRequest?>(input, ignoreCloseExceptions) {
            @Throws(IOException::class)
            public override fun run(): BithumbHttpRequest {
                val buffer = CharArray(bufferSize)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    totalWritten += read.toLong()
                    progress.onUpload(totalWritten, -1)
                }
                return this@BithumbHttpRequest
            }
        }.call()!!
    }

    /**
     * Set the UploadProgress callback for this request
     *
     * @param callback
     * @return this request
     */
    fun progress(callback: UploadProgress?): BithumbHttpRequest {
        progress = callback ?: UploadProgress.DEFAULT
        return this
    }

    private fun incrementTotalSize(size: Long): BithumbHttpRequest {
        if (totalSize == -1L) totalSize = 0
        totalSize += size
        return this
    }

    /**
     * Close output stream
     *
     * @return this request
     * @throws HttpRequestException
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun closeOutput(): BithumbHttpRequest {
        progress(null)
        if (output == null) return this
        if (multipart) output!!.write(CRLF + "--" + BOUNDARY + "--" + CRLF)
        if (ignoreCloseExceptions) try {
            output!!.close()
        } catch (ignored: IOException) {
            // Ignored
        } else output!!.close()
        output = null
        return this
    }

    /**
     * Call [.closeOutput] and re-throw a caught [IOException]s as
     * an [HttpRequestException]
     *
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    protected fun closeOutputQuietly(): BithumbHttpRequest {
        return try {
            closeOutput()
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }

    /**
     * Open output stream
     *
     * @return this request
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun openOutput(): BithumbHttpRequest {
        if (output != null) return this
        getConnection()!!.doOutput = true
        val charset = getParam(
                getConnection()!!.getRequestProperty(HEADER_CONTENT_TYPE),
                PARAM_CHARSET)
        output = RequestOutputStream(getConnection()!!.outputStream,
                charset, bufferSize)
        return this
    }

    /**
     * Start part of a multipart
     *
     * @return this request
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun startPart(): BithumbHttpRequest {
        if (!multipart) {
            multipart = true
            contentType(CONTENT_TYPE_MULTIPART).openOutput()
            output!!.write("--" + BOUNDARY + CRLF)
        } else output!!.write(CRLF + "--" + BOUNDARY + CRLF)
        return this
    }
    /**
     * Write part header
     *
     * @param name
     * @param filename
     * @param contentType
     * @return this request
     * @throws IOException
     */
    /**
     * Write part header
     *
     * @param name
     * @param filename
     * @return this request
     * @throws IOException
     */
    @Throws(IOException::class)
    protected fun writePartHeader(name: String?,
                                  filename: String?, contentType: String? = null): BithumbHttpRequest {
        val partBuffer = StringBuilder()
        partBuffer.append("form-data; name=\"").append(name)
        if (filename != null) partBuffer.append("\"; filename=\"").append(filename)
        partBuffer.append('"')
        partHeader("Content-Disposition", partBuffer.toString())
        if (contentType != null) partHeader(HEADER_CONTENT_TYPE, contentType)
        return send(CRLF)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param part
     * @return this request
     */
    fun part(name: String?, part: String?): BithumbHttpRequest {
        return part(name, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             part: String?): BithumbHttpRequest {
        return part(name, filename, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param contentType
     * value of the Content-Type part header
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             contentType: String?, part: String?): BithumbHttpRequest {
        try {
            startPart()
            writePartHeader(name, filename, contentType)
            output!!.write(part)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return this
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, part: Number?): BithumbHttpRequest {
        return part(name, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             part: Number?): BithumbHttpRequest {
        return part(name, filename, part?.toString())
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, part: File): BithumbHttpRequest {
        return part(name, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             part: File): BithumbHttpRequest {
        return part(name, filename, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param contentType
     * value of the Content-Type part header
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             contentType: String?, part: File): BithumbHttpRequest {
        val stream: InputStream
        try {
            stream = BufferedInputStream(FileInputStream(part))
            incrementTotalSize(part.length())
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return part(name, filename, contentType, stream)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, part: InputStream): BithumbHttpRequest {
        return part(name, null, null, part)
    }

    /**
     * Write part of a multipart request to the request body
     *
     * @param name
     * @param filename
     * @param contentType
     * value of the Content-Type part header
     * @param part
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun part(name: String?, filename: String?,
             contentType: String?, part: InputStream): BithumbHttpRequest {
        try {
            startPart()
            writePartHeader(name, filename, contentType)
            copy(part, output)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return this
    }

    /**
     * Write a multipart header to the response body
     *
     * @param name
     * @param value
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun partHeader(name: String, value: String): BithumbHttpRequest {
        return send(name).send(": ").send(value).send(CRLF)
    }

    /**
     * Write contents of file to request body
     *
     * @param input
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun send(input: File): BithumbHttpRequest {
        val stream: InputStream
        try {
            stream = BufferedInputStream(FileInputStream(input))
            incrementTotalSize(input.length())
        } catch (e: FileNotFoundException) {
            throw HttpRequestException(e)
        }
        return send(stream)
    }

    /**
     * Write byte array to request body
     *
     * @param input
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun send(input: ByteArray?): BithumbHttpRequest {
        if (input != null) incrementTotalSize(input.size.toLong())
        return send(ByteArrayInputStream(input))
    }

    /**
     * Write stream to request body
     *
     *
     * The given stream will be closed once sending completes
     *
     * @param input
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun send(input: InputStream): BithumbHttpRequest {
        try {
            openOutput()
            copy(input, output)
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return this
    }

    /**
     * Write reader to request body
     *
     *
     * The given reader will be closed once sending completes
     *
     * @param input
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun send(input: Reader): BithumbHttpRequest {
        try {
            openOutput()
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        val writer: Writer = OutputStreamWriter(output,
                output!!.encoder.charset())
        return object : FlushOperation<BithumbHttpRequest?>(writer) {
            @Throws(IOException::class)
            override fun run(): BithumbHttpRequest {
                return copy(input, writer)
            }
        }.call()!!
    }

    /**
     * Write char sequence to request body
     *
     *
     * The charset configured via [.contentType] will be used and
     * UTF-8 will be used if it is unset.
     *
     * @param value
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun send(value: CharSequence): BithumbHttpRequest {
        try {
            openOutput()
            output!!.write(value.toString())
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return this
    }

    /**
     * Create writer to request output stream
     *
     * @return writer
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun writer(): OutputStreamWriter {
        return try {
            openOutput()
            OutputStreamWriter(output, output!!.encoder.charset())
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
    }
    /**
     * Write the key and value in the entry as form data to the request body
     *
     *
     * The pair specified will be URL-encoded and sent with the
     * 'application/x-www-form-urlencoded' content-type
     *
     * @param entry
     * @param charset
     * @return this request
     * @throws HttpRequestException
     */
    /**
     * Write the key and value in the entry as form data to the request body
     *
     *
     * The pair specified will be URL-encoded in UTF-8 and sent with the
     * 'application/x-www-form-urlencoded' content-type
     *
     * @param entry
     * @return this request
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun form(entry: Map.Entry<*, *>, charset: String? = CHARSET_UTF8): BithumbHttpRequest {
        return form(entry.key!!, entry.value, charset)
    }
    /**
     * Write the name/value pair as form data to the request body
     *
     *
     * The values specified will be URL-encoded and sent with the
     * 'application/x-www-form-urlencoded' content-type
     *
     * @param name
     * @param value
     * @param charset
     * @return this request
     * @throws HttpRequestException
     */
    /**
     * Write the name/value pair as form data to the request body
     *
     *
     * The pair specified will be URL-encoded in UTF-8 and sent with the
     * 'application/x-www-form-urlencoded' content-type
     *
     * @param name
     * @param value
     * @return this request
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun form(name: Any, value: Any?,
             charset: String? = CHARSET_UTF8): BithumbHttpRequest {
        var charset = charset
        val first = !form
        if (first) {
            contentType(CONTENT_TYPE_FORM, charset)
            form = true
        }
        charset = getValidCharset(charset)
        try {
            openOutput()
            if (!first) output!!.write('&'.toInt())
            output!!.write(URLEncoder.encode(name.toString(), charset))
            output!!.write('='.toInt())
            if (value != null) output!!.write(URLEncoder.encode(value.toString(), charset))
        } catch (e: IOException) {
            throw HttpRequestException(e)
        }
        return this
    }
    /**
     * Write the values in the map as encoded form data to the request body
     *
     * @param values
     * @param charset
     * @return this request
     * @throws HttpRequestException
     */
    /**
     * Write the values in the map as form data to the request body
     *
     *
     * The pairs specified will be URL-encoded in UTF-8 and sent with the
     * 'application/x-www-form-urlencoded' content-type
     *
     * @param values
     * @return this request
     * @throws HttpRequestException
     */
    @JvmOverloads
    @Throws(HttpRequestException::class)
    fun form(values: Map<*, *>, charset: String? = CHARSET_UTF8): BithumbHttpRequest {
        if (!values.isEmpty()) for (entry in values.entries) form(entry, charset)
        return this
    }

    /**
     * Configure HTTPS connection to trust all certificates
     *
     *
     * This method does nothing if the current request is not a HTTPS request
     *
     * @return this request
     * @throws HttpRequestException
     */
    @Throws(HttpRequestException::class)
    fun trustAllCerts(): BithumbHttpRequest {
        val connection = getConnection()
        if (connection is HttpsURLConnection) connection.sslSocketFactory = trustedFactory
        return this
    }

    /**
     * Configure HTTPS connection to trust all hosts using a custom
     * [HostnameVerifier] that always returns `true` for each
     * host verified
     *
     *
     * This method does nothing if the current request is not a HTTPS request
     *
     * @return this request
     */
    fun trustAllHosts(): BithumbHttpRequest {
        val connection = getConnection()
        if (connection is HttpsURLConnection) connection.hostnameVerifier = trustedVerifier
        return this
    }

    /**
     * Get the [URL] of this request's connection
     *
     * @return request URL
     */
    fun url(): URL {
        return getConnection()!!.url
    }

    /**
     * Get the HTTP method of this request
     *
     * @return method
     */
    fun method(): String {
        return getConnection()!!.requestMethod
    }

    /**
     * Configure an HTTP proxy on this connection. Use {
     * [.proxyBasic] if this proxy requires basic
     * authentication.
     *
     * @param proxyHost
     * @param proxyPort
     * @return this request
     */
    fun useProxy(proxyHost: String?, proxyPort: Int): BithumbHttpRequest {
        check(connection == null) { "The connection has already been created. This method must be called before reading or writing to the request." }
        httpProxyHost = proxyHost
        httpProxyPort = proxyPort
        return this
    }

    /**
     * Set whether or not the underlying connection should follow redirects in
     * the response.
     *
     * @param followRedirects
     * - true fo follow redirects, false to not.
     * @return this request
     */
    fun followRedirects(followRedirects: Boolean): BithumbHttpRequest {
        getConnection()!!.instanceFollowRedirects = followRedirects
        return this
    }

    companion object {
        /**
         * 'UTF-8' charset name
         */
        const val CHARSET_UTF8 = "UTF-8"

        /**
         * 'application/x-www-form-urlencoded' content type header value
         */
        const val CONTENT_TYPE_FORM = "application/x-www-form-urlencoded"

        /**
         * 'application/json' content type header value
         */
        const val CONTENT_TYPE_JSON = "application/json"

        /**
         * 'gzip' encoding header value
         */
        const val ENCODING_GZIP = "gzip"

        /**
         * 'Accept' header name
         */
        const val HEADER_ACCEPT = "Accept"

        /**
         * 'Accept-Charset' header name
         */
        const val HEADER_ACCEPT_CHARSET = "Accept-Charset"

        /**
         * 'Accept-Encoding' header name
         */
        const val HEADER_ACCEPT_ENCODING = "Accept-Encoding"

        /**
         * 'Authorization' header name
         */
        const val HEADER_AUTHORIZATION = "Authorization"

        /**
         * 'Cache-Control' header name
         */
        const val HEADER_CACHE_CONTROL = "Cache-Control"

        /**
         * 'Content-Encoding' header name
         */
        const val HEADER_CONTENT_ENCODING = "Content-Encoding"

        /**
         * 'Content-Length' header name
         */
        const val HEADER_CONTENT_LENGTH = "Content-Length"

        /**
         * 'Content-Type' header name
         */
        const val HEADER_CONTENT_TYPE = "Content-Type"

        /**
         * 'Date' header name
         */
        const val HEADER_DATE = "Date"

        /**
         * 'ETag' header name
         */
        const val HEADER_ETAG = "ETag"

        /**
         * 'Expires' header name
         */
        const val HEADER_EXPIRES = "Expires"

        /**
         * 'If-None-Match' header name
         */
        const val HEADER_IF_NONE_MATCH = "If-None-Match"

        /**
         * 'Last-Modified' header name
         */
        const val HEADER_LAST_MODIFIED = "Last-Modified"

        /**
         * 'Location' header name
         */
        const val HEADER_LOCATION = "Location"

        /**
         * 'Proxy-Authorization' header name
         */
        const val HEADER_PROXY_AUTHORIZATION = "Proxy-Authorization"

        /**
         * 'Referer' header name
         */
        const val HEADER_REFERER = "Referer"

        /**
         * 'Server' header name
         */
        const val HEADER_SERVER = "Server"

        /**
         * 'User-Agent' header name
         */
        const val HEADER_USER_AGENT = "User-Agent"

        /**
         * 'DELETE' request method
         */
        const val METHOD_DELETE = "DELETE"

        /**
         * 'GET' request method
         */
        const val METHOD_GET = "GET"

        /**
         * 'HEAD' request method
         */
        const val METHOD_HEAD = "HEAD"

        /**
         * 'OPTIONS' options method
         */
        const val METHOD_OPTIONS = "OPTIONS"

        /**
         * 'POST' request method
         */
        const val METHOD_POST = "POST"

        /**
         * 'PUT' request method
         */
        const val METHOD_PUT = "PUT"

        /**
         * 'TRACE' request method
         */
        const val METHOD_TRACE = "TRACE"

        /**
         * 'charset' header value parameter
         */
        const val PARAM_CHARSET = "charset"
        private const val BOUNDARY = "00content0boundary00"
        private const val CONTENT_TYPE_MULTIPART = ("multipart/form-data; boundary="
                + BOUNDARY)
        private const val CRLF = "\r\n"
        private val EMPTY_STRINGS = arrayOfNulls<String>(0)
        private var TRUSTED_FACTORY: SSLSocketFactory? = null
        private var TRUSTED_VERIFIER: HostnameVerifier? = null
        private fun getValidCharset(charset: String?): String {
            return if (charset != null && charset.length > 0) charset else CHARSET_UTF8
        }// Intentionally left blank

        // Intentionally left blank
        @get:Throws(HttpRequestException::class)
        private val trustedFactory: SSLSocketFactory?
            private get() {
                if (TRUSTED_FACTORY == null) {
                    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                        override fun getAcceptedIssuers(): Array<X509Certificate?> {
                            return arrayOfNulls(0)
                        }

                        override fun checkClientTrusted(chain: Array<X509Certificate>,
                                                        authType: String) {
                            // Intentionally left blank
                        }

                        override fun checkServerTrusted(chain: Array<X509Certificate>,
                                                        authType: String) {
                            // Intentionally left blank
                        }
                    })
                    try {
                        val context = SSLContext.getInstance("TLS")
                        context.init(null, trustAllCerts, SecureRandom())
                        TRUSTED_FACTORY = context.socketFactory
                    } catch (e: GeneralSecurityException) {
                        val ioException = IOException(
                                "Security exception configuring SSL context")
                        ioException.initCause(e)
                        throw HttpRequestException(ioException)
                    }
                }
                return TRUSTED_FACTORY
            }

        private val trustedVerifier: HostnameVerifier?
            private get() {
                if (TRUSTED_VERIFIER == null) TRUSTED_VERIFIER = HostnameVerifier { hostname, session -> true }
                return TRUSTED_VERIFIER
            }

        private fun addPathSeparator(baseUrl: String,
                                     result: StringBuilder): StringBuilder {
            // Add trailing slash if the base URL doesn't have any path segments.
            //
            // The following test is checking for the last slash not being part of
            // the protocol to host separator: '://'.
            if (baseUrl.indexOf(':') + 2 == baseUrl.lastIndexOf('/')) result.append('/')
            return result
        }

        private fun addParamPrefix(baseUrl: String,
                                   result: StringBuilder): StringBuilder {
            // Add '?' if missing and add '&' if params already exist in base url
            val queryStart = baseUrl.indexOf('?')
            val lastChar = result.length - 1
            if (queryStart == -1) result.append('?') else if (queryStart < lastChar && baseUrl[lastChar] != '&') result.append('&')
            return result
        }

        private fun addParam(key: Any, value: Any,
                             result: StringBuilder): StringBuilder {
            var value: Any? = value
            if (value != null && value.javaClass.isArray) value = arrayToList(value)
            if (value is Iterable<*>) {
                val iterator = value.iterator()
                while (iterator.hasNext()) {
                    result.append(key)
                    result.append("[]=")
                    val element = iterator.next()
                    if (element != null) result.append(element)
                    if (iterator.hasNext()) result.append("&")
                }
            } else {
                result.append(key)
                result.append("=")
                if (value != null) result.append(value)
            }
            return result
        }

        private var CONNECTION_FACTORY = ConnectionFactory.DEFAULT

        /**
         * Specify the [ConnectionFactory] used to create new requests.
         */
        fun setConnectionFactory(
                connectionFactory: ConnectionFactory?) {
            if (connectionFactory == null) CONNECTION_FACTORY = ConnectionFactory.DEFAULT else CONNECTION_FACTORY = connectionFactory
        }

        /**
         * Represents array of any type as list of objects so we can easily iterate
         * over it
         *
         * @param array
         * of elements
         * @return list with the same elements
         */
        private fun arrayToList(array: Any): List<Any> {
            if (array is Array<*>) return Arrays.asList(*array as Array<Any>)
            val result: MutableList<Any> = ArrayList()
            // Arrays of the primitive types can't be cast to array of Object, so
            // this:
            if (array is IntArray) for (value in array) result.add(value) else if (array is BooleanArray) for (value in array) result.add(value) else if (array is LongArray) for (value in array) result.add(value) else if (array is FloatArray) for (value in array) result.add(value) else if (array is DoubleArray) for (value in array) result.add(value) else if (array is ShortArray) for (value in array) result.add(value) else if (array is ByteArray) for (value in array) result.add(value) else if (array is CharArray) for (value in array) result.add(value)
            return result
        }

        /**
         * Encode the given URL as an ASCII [String]
         *
         *
         * This method ensures the path and query segments of the URL are properly
         * encoded such as ' ' characters being encoded to '%20' or any UTF-8
         * characters that are non-ASCII. No encoding of URLs is done by default by
         * the [BithumbHttpRequest] constructors and so if URL encoding is needed
         * this method should be called before calling the [BithumbHttpRequest]
         * constructor.
         *
         * @param url
         * @return encoded URL
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun encode(url: CharSequence): String {
            val parsed: URL
            parsed = try {
                URL(url.toString())
            } catch (e: IOException) {
                throw HttpRequestException(e)
            }
            var host = parsed.host
            val port = parsed.port
            if (port != -1) host = host + ':' + Integer.toString(port)
            return try {
                var encoded = URI(parsed.protocol, host,
                        parsed.path, parsed.query, null).toASCIIString()
                val paramsStart = encoded.indexOf('?')
                if (paramsStart > 0 && paramsStart + 1 < encoded.length) encoded = (encoded.substring(0, paramsStart + 1)
                        + encoded.substring(paramsStart + 1)
                        .replace("+", "%2B"))
                encoded
            } catch (e: URISyntaxException) {
                val io = IOException("Parsing URI failed")
                io.initCause(e)
                throw HttpRequestException(io)
            }
        }

        /**
         * Append given map as query parameters to the base URL
         *
         *
         * Each map entry's key will be a parameter name and the value's
         * [Object.toString] will be the parameter value.
         *
         * @param url
         * @param params
         * @return URL with appended query params
         */
        fun append(url: CharSequence, params: Map<*, *>?): String {
            val baseUrl = url.toString()
            if (params == null || params.isEmpty()) return baseUrl
            val result = StringBuilder(baseUrl)
            addPathSeparator(baseUrl, result)
            addParamPrefix(baseUrl, result)
            var entry: Map.Entry<*, *>
            val iterator: Iterator<*> = params.entries.iterator()
            entry = iterator.next() as Map.Entry<*, *>
            addParam(entry.key.toString(), entry.value!!, result)
            while (iterator.hasNext()) {
                result.append('&')
                entry = iterator.next() as Map.Entry<*, *>
                addParam(entry.key.toString(), entry.value!!, result)
            }
            return result.toString()
        }

        /**
         * Append given name/value pairs as query parameters to the base URL
         *
         *
         * The params argument is interpreted as a sequence of name/value pairs so
         * the given number of params must be divisible by 2.
         *
         * @param url
         * @param params
         * name/value pairs
         * @return URL with appended query params
         */
        fun append(url: CharSequence, vararg params: Any?): String {
            val baseUrl = url.toString()
            if (params == null || params.size == 0) return baseUrl
            require(params.size % 2 == 0) { "Must specify an even number of parameter names/values" }
            val result = StringBuilder(baseUrl)
            addPathSeparator(baseUrl, result)
            addParamPrefix(baseUrl, result)
            addParam(params[0]!!, params[1]!!, result)
            var i = 2
            while (i < params.size) {
                result.append('&')
                addParam(params[i]!!, params[i + 1]!!, result)
                i += 2
            }
            return result.toString()
        }

        /**
         * Start a 'GET' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        operator fun get(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_GET)
        }

        /**
         * Start a 'GET' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        operator fun get(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_GET)
        }

        /**
         * Start a 'GET' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param params
         * The query parameters to include as part of the baseUrl
         * @param encode
         * true to encode the full URL
         *
         * @see .append
         * @see .encode
         * @return request
         */
        operator fun get(baseUrl: CharSequence,
                         params: Map<*, *>?, encode: Boolean): BithumbHttpRequest {
            val url = append(baseUrl, params)
            return Companion[if (encode) encode(url) else url]
        }

        /**
         * Start a 'GET' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param encode
         * true to encode the full URL
         * @param params
         * the name/value query parameter pairs to include as part of the
         * baseUrl
         *
         * @see .append
         * @see .encode
         * @return request
         */
        operator fun get(baseUrl: CharSequence,
                         encode: Boolean, vararg params: Any?): BithumbHttpRequest {
            val url: String = append(baseUrl, *params)
            return Companion[if (encode) encode(url) else url]
        }

        /**
         * Start a 'POST' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun post(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_POST)
        }

        /**
         * Start a 'POST' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun post(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_POST)
        }

        /**
         * Start a 'POST' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param params
         * the query parameters to include as part of the baseUrl
         * @param encode
         * true to encode the full URL
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun post(baseUrl: CharSequence,
                 params: Map<*, *>?, encode: Boolean): BithumbHttpRequest {
            val url = append(baseUrl, params)
            return post(if (encode) encode(url) else url)
        }

        /**
         * Start a 'POST' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param encode
         * true to encode the full URL
         * @param params
         * the name/value query parameter pairs to include as part of the
         * baseUrl
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun post(baseUrl: CharSequence,
                 encode: Boolean, vararg params: Any?): BithumbHttpRequest {
            val url: String = append(baseUrl, *params)
            return post(if (encode) encode(url) else url)
        }

        /**
         * Start a 'PUT' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun put(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_PUT)
        }

        /**
         * Start a 'PUT' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun put(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_PUT)
        }

        /**
         * Start a 'PUT' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param params
         * the query parameters to include as part of the baseUrl
         * @param encode
         * true to encode the full URL
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun put(baseUrl: CharSequence,
                params: Map<*, *>?, encode: Boolean): BithumbHttpRequest {
            val url = append(baseUrl, params)
            return put(if (encode) encode(url) else url)
        }

        /**
         * Start a 'PUT' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param encode
         * true to encode the full URL
         * @param params
         * the name/value query parameter pairs to include as part of the
         * baseUrl
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun put(baseUrl: CharSequence,
                encode: Boolean, vararg params: Any?): BithumbHttpRequest {
            val url: String = append(baseUrl, *params)
            return put(if (encode) encode(url) else url)
        }

        /**
         * Start a 'DELETE' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun delete(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_DELETE)
        }

        /**
         * Start a 'DELETE' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun delete(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_DELETE)
        }

        /**
         * Start a 'DELETE' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param params
         * The query parameters to include as part of the baseUrl
         * @param encode
         * true to encode the full URL
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun delete(baseUrl: CharSequence,
                   params: Map<*, *>?, encode: Boolean): BithumbHttpRequest {
            val url = append(baseUrl, params)
            return delete(if (encode) encode(url) else url)
        }

        /**
         * Start a 'DELETE' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param encode
         * true to encode the full URL
         * @param params
         * the name/value query parameter pairs to include as part of the
         * baseUrl
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun delete(baseUrl: CharSequence,
                   encode: Boolean, vararg params: Any?): BithumbHttpRequest {
            val url: String = append(baseUrl, *params)
            return delete(if (encode) encode(url) else url)
        }

        /**
         * Start a 'HEAD' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun head(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_HEAD)
        }

        /**
         * Start a 'HEAD' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun head(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_HEAD)
        }

        /**
         * Start a 'HEAD' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param params
         * The query parameters to include as part of the baseUrl
         * @param encode
         * true to encode the full URL
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun head(baseUrl: CharSequence,
                 params: Map<*, *>?, encode: Boolean): BithumbHttpRequest {
            val url = append(baseUrl, params)
            return head(if (encode) encode(url) else url)
        }

        /**
         * Start a 'GET' request to the given URL along with the query params
         *
         * @param baseUrl
         * @param encode
         * true to encode the full URL
         * @param params
         * the name/value query parameter pairs to include as part of the
         * baseUrl
         *
         * @see .append
         * @see .encode
         * @return request
         */
        fun head(baseUrl: CharSequence,
                 encode: Boolean, vararg params: Any?): BithumbHttpRequest {
            val url: String = append(baseUrl, *params)
            return head(if (encode) encode(url) else url)
        }

        /**
         * Start an 'OPTIONS' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun options(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_OPTIONS)
        }

        /**
         * Start an 'OPTIONS' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun options(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_OPTIONS)
        }

        /**
         * Start a 'TRACE' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun trace(url: CharSequence): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_TRACE)
        }

        /**
         * Start a 'TRACE' request to the given URL
         *
         * @param url
         * @return request
         * @throws HttpRequestException
         */
        @Throws(HttpRequestException::class)
        fun trace(url: URL): BithumbHttpRequest {
            return BithumbHttpRequest(url, METHOD_TRACE)
        }

        /**
         * Set the 'http.keepAlive' property to the given value.
         *
         *
         * This setting will apply to all requests.
         *
         * @param keepAlive
         */
        fun keepAlive(keepAlive: Boolean) {
            setProperty("http.keepAlive", java.lang.Boolean.toString(keepAlive))
        }

        /**
         * Set the 'http.maxConnections' property to the given value.
         *
         *
         * This setting will apply to all requests.
         *
         * @param maxConnections
         */
        fun maxConnections(maxConnections: Int) {
            setProperty("http.maxConnections", Integer.toString(maxConnections))
        }

        /**
         * Set the 'http.proxyHost' and 'https.proxyHost' properties to the given
         * host value.
         *
         *
         * This setting will apply to all requests.
         *
         * @param host
         */
        fun proxyHost(host: String?) {
            setProperty("http.proxyHost", host)
            setProperty("https.proxyHost", host)
        }

        /**
         * Set the 'http.proxyPort' and 'https.proxyPort' properties to the given
         * port number.
         *
         *
         * This setting will apply to all requests.
         *
         * @param port
         */
        fun proxyPort(port: Int) {
            val portValue = Integer.toString(port)
            setProperty("http.proxyPort", portValue)
            setProperty("https.proxyPort", portValue)
        }

        /**
         * Set the 'http.nonProxyHosts' property to the given host values.
         *
         *
         * Hosts will be separated by a '|' character.
         *
         *
         * This setting will apply to all requests.
         *
         * @param hosts
         */
        fun nonProxyHosts(vararg hosts: String?) {
            if (hosts != null && hosts.size > 0) {
                val separated = StringBuilder()
                val last = hosts.size - 1
                for (i in 0 until last) separated.append(hosts[i]).append('|')
                separated.append(hosts[last])
                setProperty("http.nonProxyHosts", separated.toString())
            } else setProperty("http.nonProxyHosts", null)
        }

        /**
         * Set property to given value.
         *
         *
         * Specifying a null value will cause the property to be cleared
         *
         * @param name
         * @param value
         * @return previous value
         */
        private fun setProperty(name: String, value: String?): String {
            val action: PrivilegedAction<String>
            action = if (value != null) PrivilegedAction { System.setProperty(name, value) } else PrivilegedAction { System.clearProperty(name) }
            return AccessController.doPrivileged(action)
        }
    }
}