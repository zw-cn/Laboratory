import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.net.URL;

/**
 * <p>Title: Laboratory-PACKAGE_NAME</p>
 * <p>Description: 测试POI</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.zw.com</p>
 *
 * @author zw-cn
 * @version 1.0
 * @date 10/20/2020
 */
public class POITest {
    private String currSheetName;
    private Sheet targetSheet;

    @ParameterizedTest
//    @ValueSource(strings = {"E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/cp3.xlsx","E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/cp3-new.xlsx"})
//    @CsvSource(value = {"E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/cp3.xlsx,E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/中国邮政储蓄银行ZX银行-信贷系统项目数据库表设计-表结构V1.0(超链接映射).xlsx"}, delimiter = ',')
    @CsvSource(value = {"E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/tranintf文件_修改删除标黄.xls,E:/文档/个人/代码仓库/Laboratory/File/target/classes/files/tranintf文件(超链接映射).xls"}, delimiter = ',')
    public void read(String path, String target) {
        try (InputStream fis = new FileInputStream(path);
             OutputStream os = new FileOutputStream(target)) {
            Workbook sheets = WorkbookFactory.create(fis);
            CreationHelper helper = sheets.getCreationHelper();
            Hyperlink hyperlink = null;
            //主菜单页
            Sheet menuSheet = sheets.getSheetAt(0);
            //遍历每个单元格，然后修改明细sheet页超链接
            for (Row cells : menuSheet) {
                for (Cell cell : cells) {
//                  targetSheet = getFirstRows(sheets, targetSheet, cell, cells);
                    getDoubleRows2(sheets, cell, cells);

                    if (cell.getHyperlink() != null) {
                        //遍历targetSheet，目标列D
                        for (Row targetRow : targetSheet) {
                            Cell targetCell = targetRow.getCell(3);
                            //一致则设置目标sheet页超链接
                            if (targetCell != null && targetCell.getHyperlink() != null && targetCell.toString().equals(cell.toString())) {
                                hyperlink = helper.createHyperlink(HyperlinkType.DOCUMENT);
                                hyperlink.setAddress(menuSheet.getSheetName() + "!" + cell.getAddress());
                                targetCell.setHyperlink(hyperlink);
                                System.out.println(targetSheet.getSheetName() + ">" + targetCell.getAddress() + "->" + hyperlink.getAddress());
                                break;
                            }
                        }
                    }

                }
            }
            Row row = menuSheet.getRow(0);
            Cell cell = row.getCell(0);
            sheets.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Sheet getFirstRows(Workbook sheets, Sheet targetSheet, Cell cell) {
        String currSheetName;
        if (cell.getColumnIndex() == 0 && !"".equals(cell.toString())) {
            currSheetName = cell.toString();
            targetSheet = sheets.getSheet(currSheetName);
        }
        return targetSheet;
    }

    private Sheet getDoubleRows(Workbook sheets, Sheet targetSheet, Cell cell, Row cells) {
        if (cell.getColumnIndex() % 2 == 1 && !"".equals(cell.toString())) {
            String s = cell + "_" + cells.getCell(cell.getColumnIndex() - 1);
            targetSheet = sheets.getSheet(s.replaceAll("[\\(\\)\\（\\）]", ""));
        }
        return targetSheet;
    }

    private void getDoubleRows2(Workbook sheets, Cell cell, Row cells) {
        if (cell.getColumnIndex() % 2 == 1 && !"".equals(cell.toString())) {
            String s = cell + "_" + cells.getCell(cell.getColumnIndex() - 1);
            targetSheet = sheets.getSheet(s.replaceAll("[\\(\\)\\（\\）]", ""));
        }
    }
}
