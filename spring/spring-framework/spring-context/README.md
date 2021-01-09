spring-{module} xml config processor in org.springframework.{module}.config.ContextNamespaceHandler

`<context:annotation-config/>` handler `AnnotationConfigBeanDefinitionParser` process `Configuration` in class `ConfigurationClassPostProcessor`

`<context:component-scan/>` have attribute `annotation-config` default value `true`, so `ConfigurationClassPostProcessor` will be load by `<context:component-scan/>` too.

