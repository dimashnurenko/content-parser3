package com.huk.services;

import org.junit.*;

public class TestTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tear down class");
    }

    @Before
    public void setUp() {
        System.out.println("before");
    }

    @Test
    @Ignore
    public void test() {
        System.out.println("test");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @After
    public void tearDown() {
        System.out.println("tear down");
    }
}
