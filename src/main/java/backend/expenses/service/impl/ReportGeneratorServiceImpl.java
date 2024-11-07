package backend.expenses.service.impl;

import backend.expenses.entity.Expense;
import backend.expenses.repository.ExpenseRepository;
import backend.expenses.repository.UserRepository;
import backend.expenses.service.ReportGeneratorService;
import backend.expenses.validation.DbCheck;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ReportGeneratorServiceImpl implements ReportGeneratorService {


    private DbCheck dbCheck;
    private ExpenseRepository expenseRepository;

    @Autowired
    public ReportGeneratorServiceImpl(DbCheck dbCheck, ExpenseRepository expenseRepository) {
        this.dbCheck = dbCheck;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public byte[] generateExpenseReport(int userId) {

        if (!dbCheck.isUserExist(userId))
            throw new RuntimeException("User Not Exist");

        List<Expense> expenses = expenseRepository.findByUserId(userId);

        StringBuilder csvData = new StringBuilder();

        csvData.append("ID,Amount,Date,Category,Description\n");

        for (Expense expense : expenses) {
            csvData.append(String.format("%d,%.2f,%s,%s,%s\n",
                    expense.getId(),
                    expense.getAmount(),
                    expense.getCreatedAt(),
                    expense.getCategory(),
                    expense.getDescription()
            ));
        }


        return csvData.toString().getBytes();
    }

    @Override
    public byte[] generateExpenseReportOpenCSV(int userId) throws IOException {

        if (!dbCheck.isUserExist(userId))
            throw new RuntimeException("User Not Exist");

        List<Expense> expenses = expenseRepository.findByUserId(userId);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);

        // Write header row
        String[] header = {"ID", "UserID", "Amount", "Date", "Category", "Description"};
        csvWriter.writeNext(header);

        // Write data rows
        for (Expense expense : expenses) {
            String[] data = {
                    String.valueOf(expense.getId()),
                    String.valueOf(expense.getUserId()),
                    String.format("%.2f", expense.getAmount()),
                    expense.getCreatedAt().toString(),
                    expense.getCategory(),
                    expense.getDescription()
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
        
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);

    }
}
