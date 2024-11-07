package backend.expenses.controller;


import backend.expenses.service.ReportGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExpenseReportController {

    private ReportGeneratorService reportGeneratorService;

    public ExpenseReportController(ReportGeneratorService reportGeneratorService) {
        this.reportGeneratorService = reportGeneratorService;
    }

    @GetMapping("/api/report/{userId}")
    public ResponseEntity<byte[]> generateExpenseReport(@PathVariable int userId) throws IOException {

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"expense_report.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(reportGeneratorService.generateExpenseReportOpenCSV(userId));

    }
}
