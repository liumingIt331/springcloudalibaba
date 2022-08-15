package com.liuming.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

/**
 * 用注解的方式定义sentinel控制模块
 */
public class SentinelAnnotationTest {

    /**
     * 这样，helloWorld() 方法就成了我们的一个资源
     * 注意注解支持模块需要配合 Spring AOP 或者 AspectJ 一起使用。
     */
    @SentinelResource("helloWorld-Anno")
    public void helloWorld() {
        // 资源中的逻辑
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        while (true) {
            new SentinelAnnotationTest().helloWorld();
        }
    }
}
