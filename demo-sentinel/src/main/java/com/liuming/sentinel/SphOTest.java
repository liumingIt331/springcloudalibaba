package com.liuming.sentinel;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 返回布尔类型的资源定义
 */
public class SphOTest {

    private static final String NAME = "SphO-Test";

    private static final ExecutorService EXCUTOR_SERVICE = new ThreadPoolExecutor(20, 100, 1, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(2), new ThreadFactory() {
        private AtomicInteger i = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "Sentinel-Test-" + i);
            i.incrementAndGet();
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        final CountDownLatch downLatch = new CountDownLatch(1);

        initFlowRules();

        // 定义公共的任务
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (SphO.entry(NAME)) {
                // 务必保证finally会被执行
                try {
                    System.out.println(threadName + ", Hello World!");
                } finally {
                    SphO.exit();
                }
            } else {
                System.out.println(threadName + ", 被限流了");
            }
        };

        // 定义公共的任务
        Runnable task1 = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + ", Hello World!");
        };

        for (int i = 0; i < 100; i++) {
            EXCUTOR_SERVICE.execute(task);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 发令同时跑
        downLatch.countDown();
    }


    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource(NAME);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 访问的QPS
        /**
         * 这里设置QPS没有用，count与核心线程数，取大值
         * MAX(count, 核心线程数)
         */
        rule.setCount(12); // QPS 为20
        rule.setLimitApp("default");
        // 流速
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 预热
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        // 流速与预热
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER);
        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }
}
