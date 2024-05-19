package extentmanagers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Date;

public class ExtentManager {
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    public static Date date = new Date();
    public static String reportsPath = date.toString().replace(" ", "_").replace(":", "_");

    public static ExtentReports getReporterInstance() {
        if (extentReports == null) {
            extentSparkReporter = new ExtentSparkReporter("./reports/" + reportsPath);
            extentSparkReporter.config().setReportName("Themoviedb Test Report");
            extentSparkReporter.config().setDocumentTitle("Themoviedb");
            extentReports = new ExtentReports();
            extentReports.setSystemInfo("QA", "Magdalena Kustwan");
            extentReports.attachReporter(extentSparkReporter);
        }
        return extentReports;
    }
}
