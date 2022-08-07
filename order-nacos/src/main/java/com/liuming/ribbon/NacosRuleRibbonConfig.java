package com.liuming.ribbon;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 修改默认负载均衡配置
 * 注意不能放在ComponentScan路径下被扫描到，否则变成全局的定义
 */
@Configuration
public class NacosRuleRibbonConfig {

    @Bean
    public IRule iRule() {
        // nacos的负载均衡
        return new NacosRule();
    }
}
