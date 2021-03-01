/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author Clinton Begin
 */
public interface Interceptor {

  // 用于覆盖被拦截对象的原有方法（在调用代理对象 Plugin 的 invoke()方法时被调用）
  Object intercept(Invocation invocation) throws Throwable;

  // target 是被拦截对象，这个方法的作用是给被拦截对象生成一个代理对象，并返回它
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  // 插件初始化时调用，只调用一次，插件配置的属性从这里设置进来:设置参数
  default void setProperties(Properties properties) {
    // NOP
  }

}
