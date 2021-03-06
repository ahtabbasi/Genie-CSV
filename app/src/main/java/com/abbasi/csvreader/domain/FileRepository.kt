package com.abbasi.csvreader.domain

import android.net.Uri
import com.abbasi.csvreader.commons.utils.Resource

interface FileRepository {

    /**
     * Returns the content of the file.
     */
    suspend fun readCsv(uri: Uri): Resource<String>
}