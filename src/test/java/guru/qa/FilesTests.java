package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesTests {

    private final ClassLoader cl = JacksonJsonParsingTests.class.getClassLoader();

    @DisplayName("Чтение и проверка содержимого PDF-файла из ZIP архива")
    @Test
    void pdfFromZipTest() throws Exception {
        try (InputStream streamZip = cl.getResourceAsStream("fileZip.zip");
             ZipInputStream zis = new ZipInputStream(streamZip)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("JUnit 5 User Guide"));
                }
            }
        }
    }

    @DisplayName("Чтение и проверка содержимого CSV-файла из ZIP архива")
    @Test
    void csvFromZipTest() throws Exception {
        try (InputStream streamZip = cl.getResourceAsStream("fileZip.zip");
             ZipInputStream zis = new ZipInputStream(streamZip)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".csv")) {
                    Reader reader = new InputStreamReader(zis);
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> content = csvReader.readAll();

                    Assertions.assertEquals(3, content.size());
                    Assertions.assertArrayEquals(new String[]{"OU001", "", "Коммерческий департамент"}, content.get(0));
                    Assertions.assertArrayEquals(new String[]{"OU002", "OU001", "Маректинг и реклама"}, content.get(1));
                    Assertions.assertArrayEquals(new String[]{"OU003", "", "Департамент по работе с персоналом"}, content.get(2));
                }
            }
        }
    }

    @DisplayName("Чтение и проверка содержимого XLSX-файла из ZIP архива")
    @Test
    void xlsxFromZipTest() throws Exception {
        try (InputStream streamZip = cl.getResourceAsStream("fileZip.zip");
             ZipInputStream zis = new ZipInputStream(streamZip)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".xlsx")) {
                    XLS xls = new XLS(zis);

                    Assertions.assertEquals("United States",
                            xls.excel.getSheetAt(0).
                                    getRow(1)
                                    .getCell(4)
                                    .getStringCellValue());
                }
            }
        }
    }
}
