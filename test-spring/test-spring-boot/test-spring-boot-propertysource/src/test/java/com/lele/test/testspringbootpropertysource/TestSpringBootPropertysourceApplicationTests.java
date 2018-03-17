package com.lele.test.testspringbootpropertysource;

import com.lele.test.testspringbootpropertysource.configure.AConfig;
import com.lele.test.testspringbootpropertysource.configure.DataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBootPropertysourceApplicationTests {

	@Autowired
	private AConfig aConfig;

	@Autowired
	private DataSourceConfig dataSourceConfig;

	@Test
	public void contextLoads() {
        Assert.assertEquals("f1", aConfig.getC());
        Assert.assertEquals("f2", aConfig.getD());

        Assert.assertEquals("root", dataSourceConfig.getUsername());
	}

}
