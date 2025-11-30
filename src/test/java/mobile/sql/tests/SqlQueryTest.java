package mobile.sql.tests;

import mobile.sql.helpers.Attach;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import mobile.sql.screens.SqlPracticeScreen;

import static com.codeborne.selenide.Selenide.screenshot;

@Tag("mobile")
public class SqlQueryTest extends MobileTestBase {

    @Test
    void sqlQueryTest() {
        Allure.description("Тест проверяет выполнение SQL запроса в приложении SQL Practice PRO");

        SqlPracticeScreen sqlScreen = new SqlPracticeScreen();

        sqlScreen
                .clickOkButton()
                .selectTask()
                .enterSqlQuery()
                .submitQuery()
                .verifyResults();

        screenshot("sql_query_results");
        Attach.screenshotAs("SQL Query Results");
        Attach.pageSource();

    }
}