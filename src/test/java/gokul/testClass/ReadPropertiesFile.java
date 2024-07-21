package gokul.testClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadPropertiesFile {
  public static void main(String args[]) throws IOException {
	  FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/java/gokul/data/GlobalData.properties"));
	  Properties prop=new Properties();
	  prop.load(fis);
	  System.out.println(prop.getProperty("browser"));
  }
}
