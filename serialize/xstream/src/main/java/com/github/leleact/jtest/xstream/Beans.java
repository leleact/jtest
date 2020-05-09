package com.github.leleact.jtest.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("beans")
public class Beans {

    @XStreamAlias("bean")
    public static class Bean {

        @XStreamAsAttribute
        private String id;

        @XStreamAsAttribute
        private String name;

        @XStreamAsAttribute
        @XStreamAlias("class")
        private String className;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    @XStreamImplicit(itemFieldName = "bean")
    private List<Bean> beans;

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}
