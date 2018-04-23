package com.os.operando.slice_provider

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.provider.Settings
import androidx.slice.Slice
import androidx.slice.builders.GridBuilder
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.ListBuilder.*
import androidx.slice.builders.SliceAction

@SuppressLint("RestrictedApi")
internal fun createDemoSlice(context: Context, sliceUri: Uri): Slice = ListBuilder(context, sliceUri)
        .apply {
            setHeader(
                    HeaderBuilder(this)
                            .setTitle("Header title")
                            .setSubtitle("Header subtitle")
                            .setSummarySubtitle("This is the summary subtitle")
                            .setPrimaryAction(sliceAction(context))
            )
            addRow(RowBuilder(context, sliceUri)
                    .setTitle("This is a row item title")
                    .setSubtitle("...and this is the subtitle")
                    .setTitleItem(Icon.createWithResource(context, R.drawable.ic_sun))
                    .addEndItem(sliceAction(context, true))
                    .addEndItem(sliceAction(context, true))
            )
            addInputRange(
                    InputRangeBuilder(this)
                            .setTitle("This is an input range item")
                            .setMax(7)
                            .setValue(5)
                            .setThumb(Icon.createWithResource(context, R.drawable.ic_sun))
                            .setAction(pendingIntent(context, 86586))
            )
            addRange(
                    RangeBuilder(this)
                            .setTitle("This is a range item")
                            .setMax(7)
                            .setValue(2)
            )
            addRow(RowBuilder(context, sliceUri)
                    .setTitle("This next item is a grid item:")
            )
            addGrid(
                    GridBuilder(this)
                            .apply {
                                addCell(GridBuilder.CellBuilder(this)
                                        .addImage(Icon.createWithResource(context, R.drawable.ic_android), GridBuilder.ICON_IMAGE)
                                        .addText("Icon")
                                        .addTitleText("Title text")
                                        .setContentIntent(pendingIntent(context, 8456))
                                )
                                addCell(GridBuilder.CellBuilder(this)
                                        .addImage(Icon.createWithResource(context, R.drawable.yucca), GridBuilder.SMALL_IMAGE)
                                        .addText("Small")
                                        .addTitleText("Title text")
                                        .setContentIntent(pendingIntent(context, 8964))
                                )
                                addCell(GridBuilder.CellBuilder(this)
                                        .addImage(Icon.createWithResource(context, R.drawable.dianella), GridBuilder.LARGE_IMAGE)
                                        .addText("Large")
                                        .addTitleText("Title text")
                                        .setContentIntent(pendingIntent(context, 6585))
                                )
                                addSeeMoreCell(GridBuilder.CellBuilder(this)
                                        .addText("See more!")
                                        .addTitleText("Title text")
                                        .setContentIntent(pendingIntent(context, 3287))
                                )
                            }
            )
        }
        .addSeeMoreRow(
                RowBuilder(context, sliceUri)
                        .setTitle("See more row item️")
        )
        .addAction(sliceAction(context))
        .addAction(sliceAction2(context))
        .build()

private fun sliceAction(context: Context, isChecked: Boolean = false) = SliceAction(
        pendingIntent(context, 1234),
        Icon.createWithResource(context, R.drawable.ic_android),
        "actionnnnnn",
        isChecked
)

private fun pendingIntent(context: Context, reqCode: Int): PendingIntent {
    return PendingIntent.getActivity(
            context,
            reqCode,
            Intent(Settings.ACTION_SETTINGS),
            PendingIntent.FLAG_UPDATE_CURRENT
    )
}

private fun sliceAction2(context: Context) = SliceAction(
        PendingIntent.getActivity(
                context,
                546754,
                Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS),
                PendingIntent.FLAG_UPDATE_CURRENT
        ),
        Icon.createWithResource(context, R.drawable.ic_android),
        "a lie"
)