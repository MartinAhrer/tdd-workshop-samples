package at.martinahrer.tdd.sample.domain.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    public static void assertAddressLabel(String actualResult, String expectedCity, String expectedZip, String expectedLine1, String expectedLine2) {
        assertTrue(actualResult.contains(expectedCity), "label must contain city");
        assertTrue(actualResult.contains(expectedZip), "label must contain zip");
        assertTrue(actualResult.contains(expectedLine1), "label must contain line 1");
        assertTrue(actualResult.contains(expectedLine2), "label must contain line 2");
    }
}
