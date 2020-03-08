AOP源码阅读笔记：

首先，AOP的实现是通过代理模式。那么，在spring容器创建bean时，对那些需要被代理的类，在创建时时需要做一些配置的。

根据上节课DI的源码分析，定位到了AbstractAutowireCapableBeanFactory的initializeBean方法。

阅读源码发现，spring在初始化bean的前后分别调用了该类的applyBeanPostProcessorsBeforeInitialization和applyBeanPostProcessorsAfterInitialization方法，而这两个方法的具体实现都是由BeanPostProcessor接口的实现类中的postProcessBeforeInitialization和postProcessAfterInitialization方法实现。

跟着老师的步骤，我也选择applyBeanPostProcessorsAfterInitialization方法为例具体分析下

该方法主要就是遍历容器配置的后置处理器去调用postProcessAfterInitialization方法，代码如下：

for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
			//调用Bean实例所有的后置处理中的初始化后处理方法，为Bean实例对象在
			//初始化之后做一些自定义的处理操作
			Object current = beanProcessor.postProcessAfterInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}

getBeanPostProcessors()返回的是当前容器为了创建bean的时候设置的后置处理器的list。至于为啥不用map而直接遍历，emmm，暂时也想不到

这里调用的BeanPostProcessor实现类有很多，我也就按老师的思路，选择AbstractAutoProxyCreator类分析

AbstractAutoProxyCreator.postProcessAfterInitialization具体调用是在该类的wrapIfNecessary方法里

wrapIfNecessary会对给定的bean进行分析，如果需要被代理就会调用该类的createProxy方法创建代理，而createProxy是调用ProxyFactory.getProxy方法实现的，看一下该方法代码：

public Object getProxy(@Nullable ClassLoader classLoader) {
		return createAopProxy().getProxy(classLoader);
	}

这里createAopProxy是调用到DefaultAopProxyFactory.createAopProxy方法

该方法判断bean的class有没有实现接口，如果有，则返回JdkDynamicAopProxy，也就是JDK动态代理，否则返回ObjenesisCglibAopProxy，也就是Cglib代理，再调用返回的类的getProxy方法去获取代理对象。

以JDK为例：
public Object getProxy(@Nullable ClassLoader classLoader) {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating JDK dynamic proxy: target source is " + this.advised.getTargetSource());
		}
		Class<?>[] proxiedInterfaces = AopProxyUtils.completeProxiedInterfaces(this.advised, true);
		findDefinedEqualsAndHashCodeMethods(proxiedInterfaces);
		return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
	}

原理基本就是反射，至于cglib相对复杂些，这次先不展开

至此，AOP的创建已经完成，下面来看Spring对AOP调用的处理：

依旧按照老师的步骤，以JDK代理为例说明：

对于JDK代理来说，调用代理方法其实就是代理􏳔invoke方法，（这里说明下，cglib代理的调用和创建要复杂多，有时间一定要去研究）

查看该方法源码后发现：

该方法在进行了一些校验后，便调用AdvisedSupport.getInterceptorsAndDynamicInterceptionAdvice方法获取该方法的通知链（责任链模式），如果有通知，则执行通知的invoke，而这些通知的invoke方法里，最终都有对前置或者后置通知的处理，例如：

@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis() );
		return mi.proceed();
	}

	先调用before，在调用原来方法。
􏲴􏲵 􏵔􏰊􏳬􏵕􏳋􏱨􏰐􏰌􏲊􏱬􏲘􏱪􏲉􏱩􏰋􏱪􏳊􏳋 􏱸􏳣􏳤􏲴􏲵􏶥􏲶􏵨􏳞􏵍􏱯􏲅􏱳􏲂􏶦􏲂 􏲃􏲼 􏳑􏱨􏳲􏱪􏱬􏰐􏰈􏲊􏱪􏱨􏳱􏰐














