package com.github.leleact.jtest.log.log4j2.async;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;

@Plugin(name = "MyCustomAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class MyCustomAppender extends AbstractAppender {
    protected MyCustomAppender(String name, Filter filter) {
        super(name, filter, null);
    }

    protected MyCustomAppender(String name, Filter filter, org.apache.logging.log4j.core.Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @PluginFactory
    public static MyCustomAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Filter") Filter filter) {
        return new MyCustomAppender(name, filter);
    }

    @Override
    public void append(LogEvent event) {

    }
}
