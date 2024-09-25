import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProvider {
    @DataProvider(name = "excelData")
public Object[][] getData() throws IOException {
    String excelFilePath = "src/test/resource/dataLogin.xlsx";
    FileInputStream fis = new FileInputStream(excelFilePath);
    Workbook workbook = WorkbookFactory.create(fis);
    Sheet sheet = workbook.getSheetAt(0);
    int rowCount = sheet.getPhysicalNumberOfRows();
    int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

    Object[][] data = new Object[rowCount - 1][colCount];
    for (int i = 1; i < rowCount; i++) {
        Row row = sheet.getRow(i);
        for (int j = 0; j < colCount; j++) {
            data[i - 1][j] = row.getCell(j).toString();
        }
    }
    workbook.close();
    fis.close();
    return data;
}
}
