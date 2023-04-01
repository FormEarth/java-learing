package com.example.demo.test.db;

import com.example.demo.test.util.Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raining_heavily
 * @date 2023/3/23 16:34
 */
public class PoiExcelDemo {

    /**
     * 读取第2、3个sheet
     */
    static int[] SHEET_INDEXES = {1, 2};
    /**
     * 读取列<br>
     * 分类  zh  en  mib 读写  说明  长度  取值范围  参数说明  分类
     **/
    static int[] COLUM_INDEXES = {0, 1, 2, 3, 7, 9, 11, 12, 14, 16};
    static int MARK_COLOR_INDEX = 64;

    static void readExcel() {

        List<ProductAttrProperty> list = new ArrayList<>();
        String excelPath = "E:/qiyuan_work/文档/FTTA的MIB参数V1.6 (20230321).xlsx";
        Sheet sheet;
        Row row;
        Cell cell;
        String firstCol = "";
        try (XSSFWorkbook workbook = new XSSFWorkbook(excelPath)) {
            for (int index : SHEET_INDEXES) {
                sheet = workbook.getSheetAt(index);
                int rowNum = sheet.getPhysicalNumberOfRows();
                System.out.printf("total row:%s in sheet %s%n", rowNum, sheet.getSheetName());
                for (int i = 0; i < rowNum; i++) {
                    row = sheet.getRow(i);
                    int cellNum = row.getPhysicalNumberOfCells();
                    System.out.printf("%sth line: total %s cell%n", i + 1, cellNum);
                    cell = row.getCell(1);
                    if (cell == null) {
                        System.out.println("cell is null");
                    } else {
                        XSSFColor color = (XSSFColor) cell.getCellStyle().getFillBackgroundColorColor();
                        if (color == null) {
                            System.out.println("color is null");
                        } else {
                            if (color.getIndex() == MARK_COLOR_INDEX) {
                                ProductAttrProperty property = new ProductAttrProperty();
                                for (int ci : COLUM_INDEXES) {
                                    String value = "";
                                    cell = row.getCell(ci);
                                    if (cell != null) {
                                        value = getValue(cell);
                                        if (ci == 0) {
                                            if (CellType.BLANK.equals(cell.getCellType())) {
                                                value = firstCol;
                                            } else {
                                                firstCol = value;
                                            }
                                        }
                                        setData(ci, value, property);
                                        System.out.print(value + "\t");
                                    }
                                }
                                list.add(property);
                                System.out.println("");
                            }
                        }
                    }
                }
            }
            System.out.printf("total: %s", list.size());
            try (FileWriter writer = new FileWriter("C:/Users/Administrator/Desktop/mib.json")) {
                writer.write(Util.toJsonStr(list));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getValue(Cell cell) {

        String value;
        if (CellType.NUMERIC.equals(cell.getCellType())) {
            value = String.valueOf((int) cell.getNumericCellValue());
        } else if (CellType.STRING.equals(cell.getCellType())) {
            value = cell.getStringCellValue();
        } else if (CellType.BLANK.equals(cell.getCellType())) {
            value = "";
        } else {
            value = "NO-HANDLE";
        }
        return value;
    }

    static void setData(int i, String value, ProductAttrProperty property) {
        // 分类  zh  en  mib  读写  说明  长度  取值范围  参数说明  分类
        // 0,   1,   2, 3,   7,   9,   11,  12,      14,     16
        switch (i) {
            case 0: {
                property.setDirectory(value);
                break;
            }
            case 2: {
                property.setAttrPropertyWeb(value);
                break;
            }
            case 3: {
                property.setAttrProperty(value);
                break;
            }
            case 7: {
                System.out.printf("-->%s %s", i, value);
                property.setIsReadOnly("R".equals(value) ? 1 : 0);
                break;
            }
            case 9: {
                property.setRemark(value);
                break;
            }
            case 11: {
                property.setPropertyLength(Integer.valueOf(value));
                break;
            }
            case 12: {
                property.setValueRange(value);
                break;
            }
            case 14: {
                property.setRemarkDetail(value);
                break;
            }
            case 16: {
                property.setCategory(value);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        readExcel();
    }

}
