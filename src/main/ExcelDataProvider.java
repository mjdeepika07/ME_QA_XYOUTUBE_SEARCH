package demo;

import java.io.IOException;
import java.util.Arrays;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider{

@DataProvider(name="getExcelData")
public String[][] getExcelData() throws IOException{
    String[][] excelDataArray = ExcelUtility.readExcelFile("Sheet1");

    // Print the data for debugging purposes
    System.out.println("Data from ExcelDataProvider:");
    System.out.println(Arrays.deepToString(excelDataArray));
    // return ExcelUtility.readExcelFile("TestCase01");
    return excelDataArray;

}

}
