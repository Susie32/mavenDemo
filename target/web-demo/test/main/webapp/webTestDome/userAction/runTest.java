package main.webapp.webTestDome.userAction;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Susie on 2017/4/21.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({unitTest1.class,unitTest2.class})
public class runTest {
    @Before
    public void before1()
    {
        System.out.println("--3---before1");
    }
    @BeforeClass
    public static void beforeClass1()
    {
        System.out.println("--3---beforeClass");
    }
    @Test
    public void test1()
    {
        System.out.println("--3---test1");
    }
    @Test
    public void test2()
    {
        System.out.println("--3---test2");
    }
    @AfterClass
    public static void afterClass1()
    {
        System.out.println("--3---afterClass");
    }
    @After
    public void after1()
    {
        System.out.println("--3---after1");
    }
}
