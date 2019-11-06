package com.shuzutech.interfacetest.testCase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestDBConnection {

    String str = null;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("@BeforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("@AfterSuite");
    }

    @Test
    public void runOtherTest1(){
        str = "Hello,World";
        System.out.println("@Test - runOtherTest1");
        System.out.println(str);

    }
    @Test(dependsOnMethods = {"runOtherTest1"})
    public void runOtherTest2(){
        System.out.println("@Test - runOtherTest2"+str);
    }
}
