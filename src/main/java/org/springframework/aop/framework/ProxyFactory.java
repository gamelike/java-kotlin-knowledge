package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @author gjd3
 */
public class ProxyFactory {
  private AdvisedSupport advisedSupport;

  public ProxyFactory(AdvisedSupport advisedSupport) {
    this.advisedSupport = advisedSupport;
  }

  public Object getProxy() {
    return createAopProxy().getProxy();
  }

  /**
   * 判断生成哪一类代理，cglib 或者 jdk
   *
   * @return
   */
  private AopProxy createAopProxy() {
    return advisedSupport.isProxyTargetClass() ? new CglibAopProxy(advisedSupport) : new JdkDynamicAopProxy(advisedSupport);
  }
}
