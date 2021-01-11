package com.github.leleact.jtest.spring.context;

import com.github.leleact.jtest.spring.context.annotation.MyCli;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.LinkedHashSet;

/**
 * {@link MyCli} client tests
 *
 * @author leleact
 * @since 2021-01-09
 */
@Slf4j
//@SpringJUnitConfig(locations = "classpath*:META-INF/spring/spring-context.xml")
@SpringJUnitConfig(value = CliAnnotationBeanFactoryPostProcessorTests.class)
@ComponentScan(value = {"com.github.leleact.jtest.spring.context"})
class CliAnnotationBeanFactoryPostProcessorTests {
    public static class MyCliFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {
        private ApplicationContext applicationContext;

        @Override
        public T getObject() throws Exception {
            return null;
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }

    @Component
    public static class CliBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
                @Override
                protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                    boolean isCandidate = false;
                    if (beanDefinition.getMetadata().isIndependent()) {
                        if (!beanDefinition.getMetadata().isAnnotation()) {
                            isCandidate = true;
                        }
                    }
                    return isCandidate;
                }
            };
            //scanner.setResourceLoader(this.resourceLoader);
            scanner.addIncludeFilter(new AnnotationTypeFilter(MyCli.class));
            candidateComponents.addAll(scanner.findCandidateComponents("com.github.leleact.jtest.spring.context"));
            log.info("candidateComponents: {}", candidateComponents);
            candidateComponents.forEach(beanDefinition -> {
                beanDefinition.setBeanClassName(MyCliFactoryBean.class.getName());
            });
            // TODO generate bean
        }
    }

    @Test
    void clientTest() {
        log.info("clientTest");
    }

}
