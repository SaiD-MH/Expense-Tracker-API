package backend.expenses.entity;

import backend.expenses.enums.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Document(collection = "expense")
public class Expense {


    @Id
    private int id;
    private int userId;
    private double amount;
    private Date createdAt;
    private String description;
    private String category;


    public Expense() {
    }


    public Expense(int id, int userId, double amount, Date createdAt, String description, String category) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }


    public String enumSettingCategory(String inputCategory) {

        try {

            Category category = Category.valueOf(inputCategory.toUpperCase());
            return category.toString();

        } catch (Exception exception) {
            throw new RuntimeException("Invalid Category type");
        }


    }

}
