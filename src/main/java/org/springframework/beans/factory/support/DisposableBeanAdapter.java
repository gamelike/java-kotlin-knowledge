package org.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 实现销毁方法
 *
 * @author gjd
 */
public class DisposableBeanAdapter implements DisposableBean {

  private final Object bean;

  private final String beanName;

  private final String destroyMethodName;

  public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
    this.bean = bean;
    this.beanName = beanName;
    this.destroyMethodName = beanDefinition.getDestroyMethodName();
  }

  @Override
  public void destroy() throws Exception {
    if (bean instanceof DisposableBean) { // 都是从applicaitonContext中钩子函数过来进行销毁的， 先处理如果继承了DisposableBean的
      ((DisposableBean) bean).destroy();
    }
    //避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名
    if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destory".equals(this.destroyMethodName))) { //在处理在xml中配置 destory方法的  注意销毁方法执行两次的情况
      Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
      if (destroyMethod == null) {
        throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
      }
      destroyMethod.invoke(bean);
    }
  }

}
