package org.springframework.test.aop;

import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.test.common.WorldServiceInterceptor;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

/**
 * @author gjd
 */
public class DynamicProxyTest {
  @Test
  public void runTest() {
    WorldService worldService = new WorldServiceImpl();

    TargetSource targetSource = new TargetSource(worldService);

    //切面方法
    WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();

    //切点表达式
    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))");

    //切点表达式的匹配方法
    MethodMatcher methodMatcher = aspectJExpressionPointcut.getMethodMatcher();


    AdvisedSupport advisedSupport = new AdvisedSupport();
    advisedSupport.setTargetSource(targetSource);
    advisedSupport.setMethodInterceptor(worldServiceInterceptor);
    advisedSupport.setMethodMatcher(methodMatcher);

    WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
    proxy.explode();
  }
}
