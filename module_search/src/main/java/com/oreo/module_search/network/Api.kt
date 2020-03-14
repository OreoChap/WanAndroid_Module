package com.oreo.module_search.network

import com.oreooo.baselibrary.network.Network

class Api {
    companion object createSearchService {
        fun create(): SearchService {
            return Network.getInstance().retrofit().create(SearchService::class.java)
        }
    }
}