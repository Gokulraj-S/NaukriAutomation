package gokul.testClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gokul.utilities.BaseTest;

public class Read_JSON_File extends BaseTest{
	@Test(dataProvider="getData")
	public void print(HashMap<String, String> input) {
		System.out.println(input.get("username"));
		System.out.println(input.get("password"));
		
	}
		
	public List<HashMap<String,String>> read() throws IOException {
		String stringJSONContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/gokul/data/Login.JSON"),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> JSONData=mapper.readValue(stringJSONContent, new TypeReference<List<HashMap<String,String>>>(){});
		return JSONData;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data=read();
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}
