package com.liuming.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的流量控制测试 sentinel
 */
public class SentinelSimpleTest {

    /**
     * ~/logs/csp/com-liuming-sentinel-SentinelSimpleTest-metrics.log.yyyy-MM-dd
     * <p>
     * 日志解析
     * p 代表通过的请求,
     * block 代表被阻止的请求,
     * s 代表成功执行完成的请求个数,
     * e 代表用户自定义的异常,
     * rt 代表平均响应时长。
     *
     * @param args
     */
    public static void main(String[] args) {

        // 配置规则  流量控制
        initFlowRules();

        int count = 50;

        while (count > 0) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("helloWorld")) {
                // 被保护的逻辑
                System.out.println(count + "，hello world");
            } catch (BlockException e) {
                // 处理被流控的逻辑
                System.out.println(count + "，被限流了!");
            }
            count--;
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("helloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 访问的QPS
        rule.setCount(20); // QPS 为20
        rule.setLimitApp("default");
        // 流速
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 预热
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        rule.setWarmUpPeriodSec(15);
        // 流速与预热
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER);
        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }
}
