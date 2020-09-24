package com.example.a16mediaplayer.media.service

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.RingtoneManager
import androidx.media2.common.MediaMetadata
import androidx.media2.common.UriMediaItem


object MediaLibrary {

    fun playList(context: Context): List<UriMediaItem> = mutableListOf<UriMediaItem>().apply {
        val ringtoneManager = RingtoneManager(context).apply { setType(RingtoneManager.TYPE_ALL) }
        val cursor = ringtoneManager.cursor

        //use 함수 close() 자동처리를 해줌
        cursor.use {
            val first = cursor.moveToFirst()

            //커서 위치에 따른  혹은 활동 가능상태에 따른 불리언 반환
            while (first && cursor.moveToNext()) {
                val uri = ringtoneManager.getRingtoneUri(cursor.position)
                val retriver = MediaMetadataRetriever().apply { setDataSource(context, uri) }

                val title =
                    retriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?:"Unkwoun"
                val duration =
                    retriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!
                        .toLong()

                add(
                    UriMediaItem.Builder(uri)
                        .setMetadata(
                            MediaMetadata.Builder()
                                .putString(MediaMetadata.METADATA_KEY_MEDIA_ID, uri.toString())
                                .putString(MediaMetadata.METADATA_KEY_MEDIA_URI, uri.toString())
                                .putString(MediaMetadata.METADATA_KEY_TITLE, title)
                                .putLong(MediaMetadata.METADATA_KEY_DURATION, duration)
                                .putLong(
                                    MediaMetadata.METADATA_KEY_BROWSABLE,
                                    MediaMetadata.BROWSABLE_TYPE_NONE
                                )
                                .putLong(MediaMetadata.METADATA_KEY_PLAYABLE, 1)
                                .build()

                        )
                        .build()
                )

            }


        }
    }
}