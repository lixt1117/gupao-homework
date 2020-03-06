ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
                
分析语句：Person person = (Person) context.getBean("person");

因为ClassPathXmlApplicationContext没有实现getBean方法，所以是调用到了父类的AbstractApplicationContext的getBean方法

而AbstractApplicationContext又调用了AbstractBeanFactory的同名方法，最终的具体逻辑是AbstractApplicationContext的doGetBean方法实现

在doGetBean方法中，大致有以下步骤：
1.将传入的name转成容器中的bean的name，这里主要是去掉FactoryBean的转义符号以及传入的是别名的话给转成beanName
2.接下来判断该bean是否是已经或者正在创建的单例
  （1）如果已创建就从缓存中查找返回
  （2）如果该bean在当前线程中已经在创建中了，则此次调用抛出异常。因为getBean不允许循环调用同一个
  （3）经过前两步的筛选，现在该准备创建bean了
      首先，如果当前的BeanFactory容器不包含该bean且父级容器存在，则委派父级容器中创建该bean。然后此处就此返回，方法结束。
      接下来，对于该bean依赖的其他bean，循环的递归调用getBean本身来获取
      之后，根据要创建的bean的模式来区分，
      如果是单例模式，则先从容器缓存中获取，获取不到再创建
      如果是原型模式（多例），则直接创建
      如果以上两种都不是，则根据Bean定义资源中配置的生命周期范围，选择实例化Bean的合适方法，这种在Web应用程序中比较常用，如：
      request、session、application等生命周期。
      不管哪种模式，其创建bean的逻辑都是委托AbstractAutowireCapableBeanFactory类的createBean方法，进一步说，是该类的
      doCreateBean方法。
  （4）最后，对得到的bean进行类型检查，如果需要，会进行类型转换

  下面，开始分析AbstractAutowireCapableBeanFactory类的doCreateBean方法。容器对Bean的初始化已经属性注入应该都是在其中完成

该方法大致做了以下内容：
1.如果是单例bean，则先调用该类的createBeanInstance方法进行实例化。
  该方法有三种策略对bean进行创建，分别是使用工厂方法、使用容器的自动装配方法、使用Bean的构造方法（其实源码里还有一种是通过配置好的回调方法来实例化，这次就不展开了）

  工厂方法：区分静态工厂和实例工厂，
  自动装配：通过配置的参数个数和类型来筛选构造方法
  无参数的构造方法（默认）：先选择实例化策略，是JDK反射还是CGlib，然后调用对应策略类的instantiate方法实例化
  对于有参数的实例化，spring首先会从缓存中获取方法，如果缓存没有命中才会去解析，解析万后再写入缓存
2.调用populateBean方法处理依赖注入
3.调用initializeBean方法初始化bean
4.调用registerDisposableBeanIfNecessary方法向容器中注册已经完成的bean


  