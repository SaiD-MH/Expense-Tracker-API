package backend.expenses.controller;

import backend.expenses.entity.Expense;
import backend.expenses.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense) {

        expenseService.addExpense(expense);

        return new ResponseEntity<>("Expense add successfully", HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable int userId,
                                                        @RequestParam("filterBy") String filterBy,
                                                        @RequestParam(value = "days", required = false) Optional<Integer> days,
                                                        @RequestParam(value = "months", required = false) Optional<Integer> months,
                                                        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
                                                        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {

        return new ResponseEntity<>(expenseService.getAllExpenses(userId, filterBy, days, months, startDate, endDate), HttpStatus.OK);

    }

    @PutMapping("/{userId}/{expenseId}")
    public String updateExpense(@PathVariable("userId") int userId, @PathVariable("expenseId") int expenseId) {

        System.out.println("uId : " + userId + " expId : " + expenseId);
        return "Sui";
    }


}
