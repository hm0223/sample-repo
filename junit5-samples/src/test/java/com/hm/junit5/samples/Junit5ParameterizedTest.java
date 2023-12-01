package com.hm.junit5.samples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Junit5ParameterizedTest. 参数化执行测试 简化测试代码
 *
 * @author huwenfeng
 */
@Slf4j
class Junit5ParameterizedTest {

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = { 2016, 2020, 2048 })
    void if_it_is_one_of_the_following_years(int year) {
      log.info("if_it_is_one_of_the_following_years receive {}", year);  
    }
    
}
