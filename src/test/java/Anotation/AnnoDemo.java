package Anotation;

import org.testng.annotations.*;

public class AnnoDemo
{
    @BeforeSuite
            public void suiteTest() {System.out.println("Before Suite");}
//    @BeforeTest
//    {System.out.println("Before Suite");}
    @BeforeClass
        public void beforeClass() {System.out.println("Before class");}
    @Test
            public void test() {System.out.println("test1");}

//    @Test{System.out.println("test2");}
//    @AfterClass{System.out.println("Before class");}
//    @AfterTest{System.out.println("Before Test");}
//    @AfterSuite{System.out.println("Before Suite");}
}
