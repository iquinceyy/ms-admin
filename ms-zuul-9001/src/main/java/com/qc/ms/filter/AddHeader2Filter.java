package com.qc.ms.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * quincey
 * Date 2020/6/28 19:24
 */

@Component
public class AddHeader2Filter  extends ZuulFilter {
    @Override
    public String filterType() {// 指定filter的类型（有四种）
        return FilterConstants.PRE_TYPE;// 魔法数字
    }

    @Override
    public int filterOrder() {
        return 3;// 越小越先执行
    }

    @Override
    public boolean shouldFilter() {// 是否开启这个过滤器 true就是开启
        return true;
    }

    @Override
    public Object run() throws ZuulException {// 核心方法
        System.err.println("这个是pre过滤器2");

        return null;
    }
}
