package org.springframework.test.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.test.common.WorldServiceInterceptor;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

/**
 * @author gjd
 */
public class DynamicProxyTest {

  private AdvisedSupport advisedSupport;

  @Before
  public void setUp() {
    WorldService worldService = new WorldServiceImpl();
    TargetSource targetSource = new TargetSource(worldService);

    //切面方法
    WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();

    //切点表达式
    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))");

    //切点表达式的匹配方法
    MethodMatcher methodMatcher = aspectJExpressionPointcut.getMethodMatcher();
    advisedSupport = new AdvisedSupport();
    advisedSupport.setTargetSource(targetSource);
    advisedSupport.setMethodInterceptor(worldServiceInterceptor);
    advisedSupport.setMethodMatcher(methodMatcher);
  }

  @Test
  public void runJdkDynamicProxy() {
    WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
    proxy.explode();
  }

  @Test
  public void runCglibDynamicProxy() {
    WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
    proxy.explode();
  }

  @Test
  public void testProxyFactory() {
    advisedSupport.setProxyTargetClass(false);
    WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
    proxy.explode();

    advisedSupport.setProxyTargetClass(true);
    WorldService proxy2 = (WorldService) new ProxyFactory(advisedSupport).getProxy();
    proxy.explode();
  }
}
