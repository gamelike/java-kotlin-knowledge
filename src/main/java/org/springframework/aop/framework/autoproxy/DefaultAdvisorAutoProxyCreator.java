package org.springframework.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
//注入错了类，aop.Advice是 接口，是根。   下面的是实现类
//import org.aspectj.weaver.Advice;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 在beanFactory生成之前做前置处理，添加aop东西，来生成新bean
 *
 * @author gjd
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

  private DefaultListableBeanFactory beanFactory;

  private Set<Object> earlyProxyReferences = new HashSet<>();


  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = (DefaultListableBeanFactory) beanFactory;
  }

  /**
   * 初始化值之前
   *
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (!earlyProxyReferences.contains(beanName)) {
      return wrapIfNecessary(bean, beanName);
    }
    return bean;
  }

  @Override
  public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
    earlyProxyReferences.add(beanName);
    return wrapIfNecessary(bean, beanName);
  }

  protected Object wrapIfNecessary(Object bean, String beanName) {
    //避免死循环，跳过这个跟aop相关的类
    if (isInfrastructureClass(bean.getClass())) {
      return bean;
    }
    //提前获取到所有 aop advisor相关类
    Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

    for (AspectJExpressionPointcutAdvisor advisor : advisors) {
      ClassFilter classFilter = advisor.getPointcut().getClassFilter();
      if (classFilter.matches(bean.getClass())) {
        AdvisedSupport advisedSupport = new AdvisedSupport();

        TargetSource targetSource = new TargetSource(bean);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
        advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

        //返回代理对象
        return new ProxyFactory(advisedSupport).getProxy();
      }
    }
    return bean;
  }

  /**
   * 初始化bean对象之前
   *
   * @param beanClass
   * @param beanName
   * @return
   */
  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
    return null;
  }

  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    return true;
  }

  private boolean isInfrastructureClass(Class<?> beanClass) {
    return Advice.class.isAssignableFrom(beanClass)
        || Pointcut.class.isAssignableFrom(beanClass)
        || Advisor.class.isAssignableFrom(beanClass);
  }

  @Override
  public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
    return pvs;
  }
}
