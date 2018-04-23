package com.os.operando.slice_provider

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import java.text.SimpleDateFormat
import java.util.*

class SampleSliceProvider : SliceProvider() {

    private var counter = 0

    override fun onCreateSliceProvider(): Boolean {
        Log.i("SliceProvider", "Creating slice provider")
        return true
    }

    override fun onBindSlice(sliceUri: Uri): Slice {
        Log.i("SliceProvider", "Creating slices")

//        context.revokeUriPermission(Uri.parse("content://com.os.operando.slice_provider"),
//                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
//                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//                        or Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)

        return when (sliceUri.path) {
            "/demo" -> createDemoSlice(context, sliceUri)
            "/yucca" -> createYuccaSlice(context, sliceUri)
            "/search" -> createSearchSlice(context, sliceUri)
            "/time" -> createTimeSlice(sliceUri)
            else -> createDemoSlice(context, sliceUri)
        }
    }

    override fun onMapIntentToUri(intent: Intent?): Uri {
        return when (intent?.action) {
            Intent.ACTION_VIEW -> Uri.parse("content://com.os.operando.slice_provider/time")
            Intent.ACTION_SCREEN_ON -> Uri.parse("content://com.os.operando.slice_provider/demo")
            else -> super.onMapIntentToUri(intent)
        }
    }

    private fun createTimeSlice(sliceUri: Uri): Slice = ListBuilder(context, sliceUri)
            .apply {
                counter++
                setHeader(
                        ListBuilder.HeaderBuilder(this)
                                .setTitle("What's the time now?")
                )
                addRow(
                        ListBuilder.RowBuilder(this)
                                .setTitle("It is ${SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)}")
                )
                addRow(
                        ListBuilder.RowBuilder(this)
                                .setTitle("Slice has called $counter times")
                )
            }
            .build()

}