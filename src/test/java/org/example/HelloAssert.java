package org.example;

import org.testng.annotations.Test;
import org.testng.Assert;

public class HelloAssert {
    @Test
    public static void test_equal_true() {
        int responseStatus = 200;
        //
        Assert.assertEquals(responseStatus, 200);
    }

    @Test
    public static void test_equal_false() {
        int responseStatus = 404;
        //
        Assert.assertEquals(responseStatus, 200);
    }

    @Test
    public static void test_notequal() {
        int responseStatus = 302;
        //
        Assert.assertNotEquals(responseStatus, 404);
    }
}
