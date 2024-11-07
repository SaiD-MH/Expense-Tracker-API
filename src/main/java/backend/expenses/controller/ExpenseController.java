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

    @PostMapping("/{userId}")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense, @PathVariable int userId) {

        expenseService.addExpense(expense, userId);

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
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable("userId") int userId, @PathVariable("expenseId") int expenseId) {

        return new ResponseEntity<>(expenseService.updateExpense(expense, expenseId, userId), HttpStatus.OK);
    }


    @DeleteMapping("{userId}/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable("userId") int userId, @PathVariable("expenseId") int expenseId) {

        expenseService.deleteExpense(userId, expenseId);

        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }


}
