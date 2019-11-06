package com.shuzutech.interfacetest.testCase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 参数化测试
 * 1.XML参数化
 * 2.DataProvider参数化
 */
public class DataProviderTest {
    @Test(dataProvider = "data")
    public void dataProviderTest(String name,int age){
        System.out.println("姓名:"+name+";年龄:"+age);
    }
    @DataProvider(name = "data")
    public Object[][] providerData(){
            Object[][] o = new Object[][]{
                    {"张三",25},
                    {"李四",18},
                    {"王五",14}
            };

            return o;

    }
}
