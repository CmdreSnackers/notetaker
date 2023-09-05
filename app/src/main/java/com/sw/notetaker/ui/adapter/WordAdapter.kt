package com.sw.notetaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sw.notetaker.data.repository.NoteTest
import com.sw.notetaker.databinding.LayoutWordBinding


class WordAdapter(
    private var notes:List<NoteTest>

):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: LayoutWordBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = LayoutWordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class WordViewHolder(val binding: LayoutWordBinding):RecyclerView.ViewHolder(binding.root)


}