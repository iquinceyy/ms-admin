package com.qc.ms.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * quincey
 * Date 2020/6/28 19:25
 */

@Component
public class routeFilter1 extends ZuulFilter {
    @Override
    public String filterType() {
        //魔法数字
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
//        越小越先执行
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
