package com.github.leleact.jtest.apache.commons.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.junit.jupiter.api.Test;

public class PoolTests {


    class MyPoolableFactory implements PooledObjectFactory<Pojo> {

        @Override
        public PooledObject<Pojo> makeObject() throws Exception {
            return null;
        }

        @Override
        public void destroyObject(PooledObject<Pojo> pooledObject) throws Exception {

        }

        @Override
        public boolean validateObject(PooledObject<Pojo> pooledObject) {
            return false;
        }

        @Override
        public void activateObject(PooledObject<Pojo> pooledObject) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<Pojo> pooledObject) throws Exception {

        }
    }


    @Test
    public void poolTest() {
    }
}
