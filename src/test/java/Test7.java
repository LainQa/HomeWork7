import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.codeborne.xlstest.XLS;
import java.io.File;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.*;
import org.apache.commons.io.IOUtils;

public class Test7 extends TestBase {

    @Test
    @DisplayName("File upload by relative path")
    void fileNameShouldBeDisplayedAfterUploadActionRelativeTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("1.jpg");
        $(".qq-upload-list").shouldHave(text("1"));
    }

    @Test
    @DisplayName("Downloading and checking XLS file")
    void downloadXLS() throws IOException {
        open("http://testingtask.online/test.html");
        File file = $("#xls").download();
        assertThat(file.exists()).isEqualTo(true);
        XLS parsedXls = new XLS(file);
        boolean checkPassed = parsedXls.excel
                .getSheetAt(0)
                .getRow(0)
                .getCell(0)
                .getStringCellValue()
                .contains("Test file xls");
        assertTrue(checkPassed);
    }

    @Test
    @DisplayName("Downloading and checking TXT file")
    void downloadSimpleTextFileTest() throws IOException {
        open("https://www.rfc-editor.org/rfc/");
        File file = $("a[href*='bcp-index']").download();
        String fileContent = IOUtils.toString(new FileReader(file));
        assertTrue(fileContent.contains("BCP INDEX"));
    }
}
