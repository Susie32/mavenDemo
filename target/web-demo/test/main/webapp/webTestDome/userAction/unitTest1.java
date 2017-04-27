package main.webapp.webTestDome.userAction;

import org.junit.*;

/**
 * Created by Susie on 2017/4/22.
 */
public class unitTest1 {
    @Before
    public void before1()
    {
        System.out.println("--1---before1");
    }
    @BeforeClass
    public static void beforeClass1()
    {
        System.out.println("--1---beforeClass");
    }
    @Test
    public void test1()
    {
        System.out.println("--1---test1");
    }
    @Test
    public void test2()
    {
        System.out.println("--1---test2");
    }
    @AfterClass
    public static void afterClass1()
    {
        System.out.println("--1---afterClass");
    }
    @After
    public void after1()
    {
        System.out.println("--1---after1");
    }
}

