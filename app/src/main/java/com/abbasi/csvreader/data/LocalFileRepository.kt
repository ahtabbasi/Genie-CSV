package com.abbasi.csvreader.data

import android.content.Context
import android.net.Uri
import com.abbasi.csvreader.R
import com.abbasi.csvreader.commons.utils.Resource
import com.abbasi.csvreader.domain.FileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class LocalFileRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
) : FileRepository {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readCsv(uri: Uri): Resource<String> {

        return withContext(dispatcher) {
            try {

                val stringBuilder = StringBuilder()
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).use { reader ->
                        var line: String? = reader.readLine()
                        while (line != null) {
                            stringBuilder.append(line)
                            line = reader.readLine()
                            if (line != null) stringBuilder.append('\n')
                        }
                    }
                }
                Resource.Valid(stringBuilder.toString())
            } catch (e: Exception) {

                Resource.Invalid(e.message ?: context.getString(R.string.error_reading_file))
            }
        }
    }
}