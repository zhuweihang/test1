package com.shuzutech.interfacetest.makeInvoice;

import org.testng.annotations.Test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    String string;
    String body="";

    @Test
    public String readFile() throws IOException {
        File file = new File("D:\\Space\\InterfaceAutoTest\\InterfaceTest\\src\\main\\resources\\kaipiao\\youhuikaipiaoxml");
        if (!file.exists()){
            System.out.println("找不到指定的文件");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        while ((string = bufferedReader.readLine())!=null){
//            System.out.println(string);
            body = body + string;
        }
        bufferedReader.close();
        System.out.println("=============================");
//        System.out.println(body);
        return body;
    }

}
