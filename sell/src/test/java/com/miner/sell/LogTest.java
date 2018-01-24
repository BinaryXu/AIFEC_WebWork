package com.miner.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 洪峰
 * @create 2018-01-17 22:39
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

    @Test
    public void test(){
        String name = "socket";
        String pass = "123";
        log.info("name={},pasword={}",name,pass);
        log.error("error...");
        log.debug("debug...");
        log.warn("warn...");
    }
}
