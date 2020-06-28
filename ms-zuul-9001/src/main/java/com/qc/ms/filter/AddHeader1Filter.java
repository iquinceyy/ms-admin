package com.qc.ms.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * quincey
 * Date 2020/6/28 19:24
 */

@Component
public class AddHeader1Filter  extends ZuulFilter {
    @Override
    public String filterType() {// 指定filter的类型（有四种）
        return FilterConstants.PRE_TYPE;// 魔法数字
    }

    @Override
    public int filterOrder() {
        return 2;// 越小越先执行
    }

    @Override
    public boolean shouldFilter() {// 是否开启这个过滤器 true就是开启
        return true;
    }

    @Override
    public Object run() throws ZuulException {// 核心方法
        System.err.println("这个是pre过滤器1");
        RequestContext currentContext = RequestContext.getCurrentContext();// 得到 请求上下文对象
        String s = "Basic " + Base64.encode(("qianfeng:java").getBytes(Charset.forName("ISO_8859_1")));
        currentContext.addZuulRequestHeader("authorization", s);// 增加路由的请求头信息。
        // 就是输入流，就能改变输入流
        return null;
    }
}
