package com.kaisha.flixstertrending


interface OnListFragmentInteractionListener {
    /**
     * This interface is used by the [TrendingFragmentRecyclerViewAdapter] to ensure
     * it has an appropriate Listener.
     *
     * In this app, it's implemented by [TrendingFragment]
     */
    fun onItemClick(item: Movie)

}