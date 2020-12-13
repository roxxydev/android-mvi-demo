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
import com.android.mvi.presentation.UiUtil
import kotlinx.android.synthetic.main.character_list_item.view.*

class CharacterRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Character> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CharacterViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(characterList: List<Character>) {
        items = characterList
        notifyDataSetChanged()
    }

    class CharacterViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.character_image
        private val tvName: TextView = itemView.character_name
        private val tvDesc: TextView = itemView.character_description

        fun bind(character: Character) {
            tvName.text = character.title
            tvDesc.text = character.body

            UiUtil.displayImage(itemView.context, character.image, imageView)
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