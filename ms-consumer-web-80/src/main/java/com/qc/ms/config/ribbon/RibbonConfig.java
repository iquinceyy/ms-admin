package com.qc.ms.config.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quincey
 * Date 2020/6/24 17:25
 */

@Configuration
public class RibbonConfig {

    @Bean
    public IRule iRule(){

        return new MyRule();
    }
}
