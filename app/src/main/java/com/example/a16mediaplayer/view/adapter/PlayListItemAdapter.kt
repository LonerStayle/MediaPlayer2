package com.example.a16mediaplayer.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.media2.common.MediaItem
import androidx.media2.common.MediaMetadata
import androidx.recyclerview.widget.RecyclerView
import com.example.a16mediaplayer.R
import com.example.a16mediaplayer.databinding.ViewholderMediaListItemBinding

class PlayListItemAdapter : RecyclerView.Adapter<PlayListItemAdapter.ViewHolder>() {
    var playList: List<MediaItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding =
            DataBindingUtil.bind<ViewholderMediaListItemBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_media_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.let { view ->
            playList[position].metadata?.let { meta ->
                view.apply {
                    album = meta.getString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI)
                    title = meta.getString(MediaMetadata.METADATA_KEY_TITLE)
                    artist = meta.getString(MediaMetadata.METADATA_KEY_ARTIST)
                }
            }
        }
    }

    override fun getItemCount() = playList.size
}

