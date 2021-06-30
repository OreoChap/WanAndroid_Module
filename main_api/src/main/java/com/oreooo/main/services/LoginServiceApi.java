package com.oreooo.main.services;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.oreooo.baselibrary.route.RoutePath;

public interface LoginServiceApi extends IProvider {

    public int getUserId();
}
