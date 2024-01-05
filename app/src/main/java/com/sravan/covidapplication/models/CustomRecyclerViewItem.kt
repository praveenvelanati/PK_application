package com.sravan.covidapplication.models

sealed class CustomRecyclerViewItem {

    class Title(val id: Int, val title: String): CustomRecyclerViewItem()

    class Movies(val id: Int, val title: String, val poster: String, val date: String): CustomRecyclerViewItem()

    class Director(val id: Int, val name: String, val moviesCount: Int): CustomRecyclerViewItem()
}