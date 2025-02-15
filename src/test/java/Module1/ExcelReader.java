package Module1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReader 
{
	@Test
	  public void dataRead() throws IOException
	  {
		  
		  //File
		  File f1=new File(System.getProperty("user.dir")+"//TestData//Data.xlsx");
		  FileInputStream fs=new FileInputStream(f1);
		  
		  //Workbook
		  XSSFWorkbook wb=new XSSFWorkbook(fs);
		  
		  //Number of rows
		  int rows=wb.getSheet("userdata").getPhysicalNumberOfRows();//5
		  
		  //Number of Cells
		  int cells=wb.getSheet("userdata").getRow(0).getPhysicalNumberOfCells();//2
		  
		  //Array of same size like file
		  Object data[][]=new Object[rows-1][cells];
		  
		  for(int i=1;i<rows;i++)//rows
		  {
			  for(int j=0;j<cells;j++)
			  {
				  data[i-1][j]=wb.getSheet("userdata").getRow(i).getCell(j).getStringCellValue();
				  System.out.print(data[i-1][j]+" ");
			  }
			  
			  System.out.println();
		  }
	  }
}
