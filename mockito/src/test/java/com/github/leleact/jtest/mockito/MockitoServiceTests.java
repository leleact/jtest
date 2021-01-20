package com.github.leleact.jtest.mockito;

import com.github.leleact.jtest.mockito.entity.MockitoEntity;
import com.github.leleact.jtest.mockito.service.MockitoDependService;
import com.github.leleact.jtest.mockito.service.impl.MockitoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * mockito service tests
 *
 * @author leleact
 * @since 2021-01-20
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
class MockitoServiceTests {

    @InjectMocks
    private MockitoServiceImpl mockitoService;

    @Mock
    private MockitoDependService mockitoDependService;

    @Captor
    private ArgumentCaptor<MockitoEntity> captor;

    @Test
    void invokedMethodTest() {
        MockitoEntity entity = new MockitoEntity();
        entity.setName(this.getClass().getName());
        Mockito.doNothing().when(mockitoDependService).dependMethod(Mockito.anyObject());
        mockitoService.invokedMethod(entity);
        Mockito.verify(mockitoDependService).dependMethod(captor.capture());
        log.info("captor entity {}", captor.getAllValues());
    }
}
