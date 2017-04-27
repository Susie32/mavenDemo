package main.webapp.webTestDome.userAction;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Susie on 2017/4/23.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(runTest.class)
public class runTest_1 {
    @Before
    public void before1()
    {
        System.out.println("--4---before1");
    }
    @BeforeClass
    public static void beforeClass1()
    {
        System.out.println("--4---beforeClass");
    }
    @Test
    public void test1()
    {
        System.out.println("--4---test1");
    }
    @Test
    public void test2()
    {
        System.out.println("--4---test2");
    }
    @AfterClass
    public static void afterClass1()
    {
        System.out.println("--4---afterClass");
    }
    @After
    public void after1()
    {
        System.out.println("--4---after1");
    }
}
