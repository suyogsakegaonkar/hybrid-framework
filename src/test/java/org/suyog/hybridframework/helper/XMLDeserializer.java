package org.suyog.hybridframework.helper;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.suyog.hybridframework.generated.PageData;

public class XMLDeserializer {

// TODO: Add comments for the method.
/*
 * 
 * 
 */
  public static <T> T getPageData(String key) throws Exception {
    if (CodeTester.excelData == null)
      throw new Exception("Excel Data is empty !!!");
    else {
      String value = CodeTester.excelData.get(key);
      JAXBContext jaxbContext = JAXBContext.newInstance("org.suyog.hybridframework.generated");
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      PageData pageData =
          (PageData) unmarshaller.unmarshal(new FileInputStream("src/main/resources/xml/Data.xml"));
      int a = pageData.getClass().getDeclaredFields().length;

      Method[] methods1 = pageData.getClass().getMethods();
      ArrayList<Object> abc = new ArrayList<Object>();
      for (Method mtd : methods1) {
        abc = (ArrayList<Object>) mtd.invoke(pageData, null);
        for (Object tempObj : abc) {
          if (tempObj.getClass().getSimpleName()
              .equals(key.substring(0, 1).toUpperCase() + key.substring(1))) {
            Method[] methods2 = tempObj.getClass().getMethods();
            for (Method mtd12 : methods2) {
              if (mtd12.getName().equals("getId"))
                if (mtd12.invoke(tempObj, null).equals(value)) {
                  return (T) tempObj;
                }
            }
          }
        }
      }
      return null;
    }
  }

}
