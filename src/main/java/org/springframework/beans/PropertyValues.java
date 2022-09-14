package org.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjd3
 */
public class PropertyValues {
  private final List<PropertyValue> propertyValueList = new ArrayList<>();

  //添加方法
  public void addPropertyValue(PropertyValue propertyValue) {
    for (int i = 0; i < propertyValueList.size(); i++) {
      PropertyValue currentPropertyValue = propertyValueList.get(i);
      //更新当前值
      if (currentPropertyValue.getName().equals(propertyValue.getName())) {
        this.propertyValueList.set(i, propertyValue);
        return;
      }
    }
    propertyValueList.add(propertyValue);
  }

  //获取全部之
  public PropertyValue[] getPropertys() {
    return propertyValueList.toArray(new PropertyValue[propertyValueList.size()]);
  }

  //单个获取值
  public PropertyValue getPropertyValue(String propertyName) {
    for (PropertyValue propertyValue : propertyValueList) {
      if (propertyValue.getName().equals(propertyName)) {
        return propertyValue;
      }
    }
    return null;
  }

  public List<PropertyValue> getPropertyValueList() {
    return propertyValueList;
  }
}
