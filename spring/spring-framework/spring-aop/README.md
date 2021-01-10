### Aop stacks

```java
getProxy:195,CglibAopProxy(org.springframework.aop.framework)
        getProxy:110,ProxyFactory(org.springframework.aop.framework)
        createProxy:461,AbstractAutoProxyCreator(org.springframework.aop.framework.autoproxy)
        wrapIfNecessary:340,AbstractAutoProxyCreator(org.springframework.aop.framework.autoproxy)
        getEarlyBeanReference:240,AbstractAutoProxyCreator(org.springframework.aop.framework.autoproxy)
        getEarlyBeanReference:974,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        lambda$doCreateBean$1:602,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        getObject:-1,4073506(org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory$$Lambda$329)
        getSingleton:194,DefaultSingletonBeanRegistry(org.springframework.beans.factory.support)
        getSingleton:168,DefaultSingletonBeanRegistry(org.springframework.beans.factory.support)
        doGetBean:256,AbstractBeanFactory(org.springframework.beans.factory.support)
        getBean:208,AbstractBeanFactory(org.springframework.beans.factory.support)
        getBean:1159,AbstractApplicationContext(org.springframework.context.support)
        postConstructor:31,Executor(com.github.leleact.jtest.spring.aop)
        invoke0:-1,NativeMethodAccessorImpl(sun.reflect)
        invoke:62,NativeMethodAccessorImpl(sun.reflect)
        invoke:43,DelegatingMethodAccessorImpl(sun.reflect)
        invoke:498,Method(java.lang.reflect)
        invoke:389,InitDestroyAnnotationBeanPostProcessor$LifecycleElement(org.springframework.beans.factory.annotation)
        invokeInitMethods:333,InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata(org.springframework.beans.factory.annotation)
        postProcessBeforeInitialization:157,InitDestroyAnnotationBeanPostProcessor(org.springframework.beans.factory.annotation)
        applyBeanPostProcessorsBeforeInitialization:429,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        initializeBean:1780,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        doCreateBean:609,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        createBean:531,AbstractAutowireCapableBeanFactory(org.springframework.beans.factory.support)
        lambda$doGetBean$0:335,AbstractBeanFactory(org.springframework.beans.factory.support)
        getObject:-1,706665172(org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$328)
        getSingleton:234,DefaultSingletonBeanRegistry(org.springframework.beans.factory.support)
        doGetBean:333,AbstractBeanFactory(org.springframework.beans.factory.support)
        getBean:208,AbstractBeanFactory(org.springframework.beans.factory.support)
        preInstantiateSingletons:944,DefaultListableBeanFactory(org.springframework.beans.factory.support)
        finishBeanFactoryInitialization:923,AbstractApplicationContext(org.springframework.context.support)
        refresh:588,AbstractApplicationContext(org.springframework.context.support)
        loadContext:127,AbstractGenericContextLoader(org.springframework.test.context.support)
        loadContext:60,AbstractGenericContextLoader(org.springframework.test.context.support)
        delegateLoading:275,AbstractDelegatingSmartContextLoader(org.springframework.test.context.support)
        loadContext:243,AbstractDelegatingSmartContextLoader(org.springframework.test.context.support)
        loadContextInternal:99,DefaultCacheAwareContextLoaderDelegate(org.springframework.test.context.cache)
        loadContext:124,DefaultCacheAwareContextLoaderDelegate(org.springframework.test.context.cache)
        getApplicationContext:123,DefaultTestContext(org.springframework.test.context.support)
        injectDependencies:118,DependencyInjectionTestExecutionListener(org.springframework.test.context.support)
        prepareTestInstance:83,DependencyInjectionTestExecutionListener(org.springframework.test.context.support)
        prepareTestInstance:244,TestContextManager(org.springframework.test.context)
        postProcessTestInstance:137,SpringExtension(org.springframework.test.context.junit.jupiter)
        lambda$invokeTestInstancePostProcessors$6:350,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        execute:-1,443721024(org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor$$Lambda$296)
        executeAndMaskThrowable:355,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        lambda$invokeTestInstancePostProcessors$7:350,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        accept:-1,1366342900(org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor$$Lambda$295)
        accept:-1,1888442711(java.util.stream.StreamSpliterators$WrappingSpliterator$$Lambda$225)
        accept:193,ReferencePipeline$3$1(java.util.stream)
        accept:175,ReferencePipeline$2$1(java.util.stream)
        forEachRemaining:1382,ArrayList$ArrayListSpliterator(java.util)
        copyInto:481,AbstractPipeline(java.util.stream)
        wrapAndCopyInto:471,AbstractPipeline(java.util.stream)
        forEachRemaining:312,StreamSpliterators$WrappingSpliterator(java.util.stream)
        forEachRemaining:743,Streams$ConcatSpliterator(java.util.stream)
        forEachRemaining:742,Streams$ConcatSpliterator(java.util.stream)
        forEach:580,ReferencePipeline$Head(java.util.stream)
        invokeTestInstancePostProcessors:349,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        lambda$instantiateAndPostProcessTestInstance$4:270,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        execute:-1,1661210650(org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor$$Lambda$294)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        instantiateAndPostProcessTestInstance:269,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        lambda$testInstancesProvider$2:259,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        get:-1,1550228904(org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor$$Lambda$287)
        orElseGet:267,Optional(java.util)
        lambda$testInstancesProvider$3:258,ClassBasedTestDescriptor(org.junit.jupiter.engine.descriptor)
        getTestInstances:-1,1089418272(org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor$$Lambda$236)
        getTestInstances:31,TestInstancesProvider(org.junit.jupiter.engine.execution)
        lambda$prepare$0:101,TestMethodTestDescriptor(org.junit.jupiter.engine.descriptor)
        execute:-1,1664598529(org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor$$Lambda$286)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        prepare:100,TestMethodTestDescriptor(org.junit.jupiter.engine.descriptor)
        prepare:65,TestMethodTestDescriptor(org.junit.jupiter.engine.descriptor)
        lambda$prepare$1:111,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:-1,790067787(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$179)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        prepare:111,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:79,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        accept:-1,355790875(org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService$$Lambda$198)
        forEach:1257,ArrayList(java.util)
        invokeAll:38,SameThreadHierarchicalTestExecutorService(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$5:143,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:-1,2081191879(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$194)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$7:129,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        invoke:-1,1219161283(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$193)
        around:137,Node(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$8:127,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:-1,1019384604(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$192)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        executeRecursively:126,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:84,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        accept:-1,355790875(org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService$$Lambda$198)
        forEach:1257,ArrayList(java.util)
        invokeAll:38,SameThreadHierarchicalTestExecutorService(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$5:143,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:-1,2081191879(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$194)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$7:129,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        invoke:-1,1219161283(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$193)
        around:137,Node(org.junit.platform.engine.support.hierarchical)
        lambda$executeRecursively$8:127,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:-1,1019384604(org.junit.platform.engine.support.hierarchical.NodeTestTask$$Lambda$192)
        execute:73,ThrowableCollector(org.junit.platform.engine.support.hierarchical)
        executeRecursively:126,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        execute:84,NodeTestTask(org.junit.platform.engine.support.hierarchical)
        submit:32,SameThreadHierarchicalTestExecutorService(org.junit.platform.engine.support.hierarchical)
        execute:57,HierarchicalTestExecutor(org.junit.platform.engine.support.hierarchical)
        execute:51,HierarchicalTestEngine(org.junit.platform.engine.support.hierarchical)
        execute:108,EngineExecutionOrchestrator(org.junit.platform.launcher.core)
        execute:88,EngineExecutionOrchestrator(org.junit.platform.launcher.core)
        lambda$execute$0:54,EngineExecutionOrchestrator(org.junit.platform.launcher.core)
        accept:-1,1975546571(org.junit.platform.launcher.core.EngineExecutionOrchestrator$$Lambda$147)
        withInterceptedStreams:67,EngineExecutionOrchestrator(org.junit.platform.launcher.core)
        execute:52,EngineExecutionOrchestrator(org.junit.platform.launcher.core)
        execute:96,DefaultLauncher(org.junit.platform.launcher.core)
        execute:75,DefaultLauncher(org.junit.platform.launcher.core)
        startRunnerWithArgs:71,JUnit5IdeaTestRunner(com.intellij.junit5)
        startRunnerWithArgs:33,IdeaTestRunner$Repeater(com.intellij.rt.junit)
        prepareStreamsAndStart:220,JUnitStarter(com.intellij.rt.junit)
        main:53,JUnitStarter(com.intellij.rt.junit)
```

`CglibAopProxy`
```java
private Callback[]getCallbacks(Class<?> rootClass)throws Exception{
    // Parameters used for optimization choices...
    boolean exposeProxy=this.advised.isExposeProxy();
    boolean isFrozen=this.advised.isFrozen();
    boolean isStatic=this.advised.getTargetSource().isStatic();

    // Choose an "aop" interceptor (used for AOP calls).
    Callback aopInterceptor=new DynamicAdvisedInterceptor(this.advised);

    // Choose a "straight to target" interceptor. (used for calls that are
    // unadvised but can return this). May be required to expose the proxy.
    Callback targetInterceptor;
    if(exposeProxy){
        targetInterceptor=(isStatic?
        new StaticUnadvisedExposedInterceptor(this.advised.getTargetSource().getTarget()):
        new DynamicUnadvisedExposedInterceptor(this.advised.getTargetSource()));
    }
    else{
        targetInterceptor=(isStatic?
        new StaticUnadvisedInterceptor(this.advised.getTargetSource().getTarget()):
        new DynamicUnadvisedInterceptor(this.advised.getTargetSource()));
    }

    // Choose a "direct to target" dispatcher (used for
    // unadvised calls to static targets that cannot return this).
    Callback targetDispatcher=(isStatic?
    new StaticDispatcher(this.advised.getTargetSource().getTarget()):new SerializableNoOp());

    Callback[]mainCallbacks=new Callback[]{
        aopInterceptor,  // for normal advice
        targetInterceptor,  // invoke target without considering advice, if optimized
        new SerializableNoOp(),  // no override for methods mapped to this
        targetDispatcher,this.advisedDispatcher,
        new EqualsInterceptor(this.advised),
        new HashCodeInterceptor(this.advised)
    };

    Callback[]callbacks;

    // If the target is a static one and the advice chain is frozen,
    // then we can make some optimizations by sending the AOP calls
    // direct to the target using the fixed chain for that method.
    if(isStatic&&isFrozen){
        Method[]methods=rootClass.getMethods();
        Callback[]fixedCallbacks=new Callback[methods.length];
        this.fixedInterceptorMap=CollectionUtils.newHashMap(methods.length);

        // TODO: small memory optimization here (can skip creation for methods with no advice)
        for(int x=0;x<methods.length;x++){
            Method method=methods[x];
            List<Object> chain=this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,rootClass);
            fixedCallbacks[x]=new FixedChainStaticTargetInterceptor(
                chain,this.advised.getTargetSource().getTarget(),this.advised.getTargetClass());
            this.fixedInterceptorMap.put(method,x);
        }

        // Now copy both the callbacks from mainCallbacks
        // and fixedCallbacks into the callbacks array.
        callbacks=new Callback[mainCallbacks.length+fixedCallbacks.length];
        System.arraycopy(mainCallbacks,0,callbacks,0,mainCallbacks.length);
        System.arraycopy(fixedCallbacks,0,callbacks,mainCallbacks.length,fixedCallbacks.length);
        this.fixedInterceptorOffset=mainCallbacks.length;
    }
    else{
        callbacks=mainCallbacks;
    }
    return callbacks;
}
```