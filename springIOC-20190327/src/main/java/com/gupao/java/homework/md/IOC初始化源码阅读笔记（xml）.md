IOC分析：

分析入口：
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

===================================================================

查看ClassPathXmlApplicationContext类构造方法，方法介绍如下：

Create a new ClassPathXmlApplicationContext, loading the definitions from the given XML file and automatically refreshing the context.
（创建一个新的ClassPathXmlApplicationContext，从给定的XML文件中加载定义，并自动刷新上下文。）

该方法调用了ClassPathXmlApplicationContext的（String[] configLocations, boolean refresh, @Nullable ApplicationContext parent）参数的构造方法，该方法介绍如下：

Create a new ClassPathXmlApplicationContext with the given parent, loading the definitions from the given XML files.
（使用给定的父级创建一个新的ClassPathXmlApplicationContext，从给定的XML文件中加载定义。）

该方法分为三步：
1.super(parent);
调用父类的构造方法，这里传的参数为null
2.setConfigLocations(configLocations);
解析Bean定义资源文件的路径，处理多个资源文件字符串数组，这里传的是"spring-context.xml"
3.if (refresh) {		refresh();			}
调用父类AbstractApplicationContext.java的refresh方法，该方法介绍如下：

Load or refresh the persistent representation of the configuration, which might an XML file, properties file, or relational database schema.
As this is a startup method, it should destroy already created singletons if it fails, to avoid dangling resources. In other words, after invocation of that method, either all or no singletons at all should be instantiated.
（加载或刷新配置的持久表示形式，可能是XML文件，属性文件或关系数据库模式。
由于这是一种启动方法，因此，如果失败，则应销毁已创建的单例，以避免悬挂资源。 换句话说，在调用该方法之后，应实例化所有单例或根本不实例化。）

先来看看第一步，调用父类构造方法：

发现这里面一路super，直接调到了AbstractApplicationContext类的构造方法里，该方法分为两步：
1.this.resourcePatternResolver = getResourcePatternResolver();
调用自己的无参数构造，通过getResourcePatternResolver方法设置了自身的resourcePatternResolver属性值，也就是获取一个Spring Source的加载器用于读入Spring Bean定义资源文件.
getResourcePatternResolver最终的语句为
return new PathMatchingResourcePatternResolver(this);
因此this.resourcePatternResolver应该是一个PathMatchingResourcePatternResolver对象，根据其构造方法，该对象的resourceLoader（资源加载器）为AbstractApplicationContext本身，因为AbstractApplicationContext继承自DefaultResourceLoader，因此也是一个资源加载器
2.setParent
由于这里传的参数是null，所以代码也不会执行，就不展开说了

接下来回到ClassPathXmlApplicationContext初始化的第二步，setConfigLocations(configLocations);
该方法参数为string数组，因为ClassPathXmlApplicationContext初始化是可以读取多个配置文件的，该方法其实就是遍历数组，调用resolvePath方法依次解析路径。

关于resolvePath方法，定义如下：

Resolve the given path, replacing placeholders with corresponding environment property values if necessary. Applied to config locations.
（解析给定的路径，必要时用相应的环境属性值替换占位符。 应用于配置位置。）

源码只是一句：return getEnvironment().resolveRequiredPlaceholders(path);

我们先看getEnvironment方法的定义：


Return the Environment for this application context in configurable form, allowing for further customization.
If none specified, a default environment will be initialized via
（以可配置的形式返回此应用程序上下文的环境，以便进行进一步的自定义。如果未指定，则默认环境将通过初始化）

返回值是一个ConfigurableEnvironment对象，ConfigurableEnvironment的注释太多，就不展开了，其实质上是一个PropertyResolver（用于针对任何基础源解析属性的接口。）

getEnvironment最终返回的是一个StandardEnvironment对象，一个适用于“标准”（即非Web）应用程序的环境的资源解析器。

而resolveRequiredPlaceholders方法的定义如下：

Resolve ${...} placeholders in the given text, replacing them with corresponding property values as resolved by getProperty. Unresolvable placeholders with no default value will cause an IllegalArgumentException to be thrown.
（在给定的文本中解析${...}占位符，将其替换为由getProperty解析的相应属性值。没有默认值的无法解析的占位符将导致引发IllegalArgumentException。）

看来是解析$之类的特殊符号，我这里也不涉及，就不展开

最后来看第三部，refresh方法，之前两步主要是选择对应的资源加载器和对url的特殊符号解析，看来老师说的定位、加载、注册三步这里应该只做了定位。剩下的猜测都是在refresh方法里完成

点开refresh方法，果然里面的步骤相当多。正常运行下应该一共有13步。

1.prepareRefresh()
Prepare this context for refreshing.
（为容器的上下文刷新做准备）
2.ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
Tell the subclass to refresh the internal bean factory.
（告诉子类刷新内部bean工厂。）
3.prepareBeanFactory(beanFactory);
Prepare the bean factory for use in this context.
（准备在当前容器上下文中使用的bean工厂。）
4.postProcessBeanFactory(beanFactory);
Allows post-processing of the bean factory in context subclasses.
（允许在上下文子类中对bean工厂进行后续处理。）
5.invokeBeanFactoryPostProcessors(beanFactory);
Invoke factory processors registered as beans in the context.
（调用在上下文中注册为bean的工厂处理器。）
6.registerBeanPostProcessors(beanFactory);
Register bean processors that intercept bean creation.
(注册拦截Bean创建的Bean处理器。)
7.initMessageSource();
Initialize message source for this context.
(为此上下文初始化消息源。)
8.initApplicationEventMulticaster();
Initialize event multicaster for this context.
(为此上下文初始化事件广播器。)
9.onRefresh();
Initialize other special beans in specific context subclasses.
(在特定上下文子类中初始化其他特殊bean。)
10.registerListeners();
Check for listener beans and register them.
(检查侦听器bean并注册它们。)
11.finishBeanFactoryInitialization(beanFactory);
Instantiate all remaining (non-lazy-init) singletons.
(实例化所有剩余的（非延迟初始化）单例。)
12.finishRefresh();
Last step: publish corresponding event.
(最后一步：发布相应的事件。)
13.resetCommonCaches();
Reset common introspection caches in Spring's core, since we might not ever need metadata for singleton beans anymore...
(在Spring的核心中重置常见的自省缓存，因为我们可能不再需要单例bean的元数据...)——该步骤写在finall块语句里的

下面一一分析这13步：
1.为容器的刷新做准备，主要是设置容器启动时间、激活状态、处理占位符、验证容器相关属性配置以及初始化上下文事件的容器
2.由子类创建对应的beanFactory，主要有以下几步：
	(1)判断如果已经有容器，则先销毁容器中的bean，关闭容器
	(2)创建BeanFactory对象，这里应该是DefaultListableBeanFactory，并设置序列化ID
	(3)设置容器的启动参数
	(4)调用加载Bean定义的方法（子类实现），这里直接调到了AbstractXmlApplicationContext里面去，具体的加载xml是在XmlBeanDefinitionReader类的loadBeanDefinitions方法中，而loadBeanDefinitions又是通过doLoadBeanDefinitions方法来将xml转成Docment对象，最终将xml中的bean转成beanDefinition对象存储在DefaultListableBeanFactory的beanDefinitionMap里面
3.配置容器特性，例如类加载器、事件处理器等，这里就不展开了
4.注册请求/会话范围，ServletContextAwareProcessor等。

==========================================================
到此先暂停，一是因为我已经晕了，二是因为老师在IOC篇中也只讲到这里，目前看来，在第二步obtainFreshBeanFactory()时已经完成了对资源的定位、加载、注册。后面的DI篇看了下，有对下面的一些步骤进行讲解。我还是先整理目前看的部分吧











