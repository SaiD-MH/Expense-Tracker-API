package backend.expenses.service;

import java.io.IOException;

public interface ReportGeneratorService {

    byte[] generateExpenseReport(int userId);

    byte[] generateExpenseReportOpenCSV(int userId) throws IOException;
}
