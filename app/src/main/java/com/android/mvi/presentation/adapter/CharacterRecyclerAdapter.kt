package com.android.mvi.presentation.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mvi.R
import com.android.mvi.domain.model.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.character_list_item.view.*

class CharacterRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Character> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is CharacterViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(characterList: List<Character>) {
        items = characterList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CharacterViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        val characterImage: ImageView = itemView.character_image
        val characterName: TextView = itemView.character_name
        val characterDescription: TextView = itemView.character_description

        fun bind(character: Character) {
            characterName.text = character.title
            characterDescription.text = character.body

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(character.image)
                .into(characterImage)
        }
    }

    class TopSpacingDecoration(private val padding: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) > 0) {
                outRect.top = padding
            }
        }
    }
}