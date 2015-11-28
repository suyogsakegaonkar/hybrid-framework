package org.suyog.hybridframework.helper;

import java.util.HashMap;

import org.suyog.hybridframework.generated.Comments;
import org.suyog.hybridframework.generated.LoginDetails;

public class CodeTester {

  static HashMap<String, String> excelData = new HashMap<String, String>();

  public static void main(String[] args) throws Exception {
    ExcelReader excelReader = new ExcelReader();
    excelData = excelReader.getExcelTestScenario("facebookTest");


    LoginDetails loginDetails = XMLDeserializer.getPageData("loginDetails");
    Comments comments = XMLDeserializer.getPageData("comments");

    System.out.println("UserName:" + loginDetails.getUsername());
    System.out.println("Password:" + loginDetails.getPassword());
    System.out.println("Comments:" + comments.getStatement());

  }

}
