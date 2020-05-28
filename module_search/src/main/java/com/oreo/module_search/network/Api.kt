package com.oreo.module_search.network

import com.oreo.module_search.SearchAct
import com.oreooo.baselibrary.network.Network

class Api {
    companion object createSearchService {
        fun create(): SearchService {
            return Network.getInstance().retrofit(SearchAct.mContext).create(SearchService::class.java)
        }
    }
}