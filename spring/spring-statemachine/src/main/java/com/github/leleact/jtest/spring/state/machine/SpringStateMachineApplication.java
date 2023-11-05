package com.github.leleact.jtest.spring.state.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

/**
 * spring state machine application
 *
 * @author leleact
 * @since 2023-11-05
 */
@SpringBootApplication
public class SpringStateMachineApplication implements CommandLineRunner {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(SpringStateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }
}
