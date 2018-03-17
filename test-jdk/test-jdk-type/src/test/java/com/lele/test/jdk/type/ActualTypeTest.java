package com.lele.test.jdk.type;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ActualTypeTest {

    private static Logger log = LoggerFactory.getLogger(ActualTypeTest.class);

    public static class Req {
    }

    public static class Res {
    }

    public interface IHandle<REQ, RES> {
        RES invoke(REQ q);
    }

    public static class HandleImpl<REQ, RES> implements IHandle<REQ, RES> {

        @Override
        public RES invoke(REQ q) {
            Type[] types = this.getClass().getGenericInterfaces();
            log.debug("" + types.length);
            if (types.length >= 1) {
                log.debug(types[0].getTypeName());
                log.debug("" + ((ParameterizedType) types[0]).getActualTypeArguments()[0]);
                log.debug("" + ((ParameterizedType) types[0]).getActualTypeArguments()[1]);
                log.debug("ower type:[" + ((ParameterizedType) types[0]).getOwnerType() + "]");
                log.debug("raw type:[" + ((ParameterizedType) types[0]).getRawType() + "]");

            }
            return null;
        }
    }

    @Test
    public void test1() {
        {
            IHandle<Req, Res> handle = new HandleImpl<Req, Res>();
            Req q = new Req();
            Res s = handle.invoke(q);
            ParameterizedType type = (ParameterizedType) handle.getClass().getGenericInterfaces()[0];
            log.debug("actualType:[" + type.getActualTypeArguments()[0] + "]");
            log.debug("owner type:[" + type.getOwnerType() + "]");
            log.debug("raw type:[" + type.getRawType() + "]");
        }

        {
            IHandle<Req, Res> handle = new HandleImpl<Req, Res>() {
            };
            ParameterizedType type = (ParameterizedType) handle.getClass().getGenericSuperclass();
            log.debug("actualType:[" + type.getActualTypeArguments()[0] + "]");
            log.debug("owner type:[" + type.getOwnerType() + "]");
            log.debug("raw type:[" + type.getRawType() + "]");
        }
    }

}
