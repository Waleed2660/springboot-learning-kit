package com.springboot.learning.kit.unit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SampleUnitTest {

    @Test
    void sampleTest() {
        assertTrue(true, "This is a sample unit test");
    }
}
