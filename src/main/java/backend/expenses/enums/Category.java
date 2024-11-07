package backend.expenses.enums;

public enum Category {


    GROCERIES, LEISURE, CLOTHING, HEALTH, UTILITIES, ELECTRONICS, FOOD, ENTERTAINMENT, OTHER;


    public String toString() {


        switch (this) {

            case GROCERIES -> {
                return "Groceries";
            }
            case LEISURE -> {
                return "Leisure";
            }
            case CLOTHING -> {
                return "Clothing";
            }
            case HEALTH -> {
                return "Health";
            }
            case UTILITIES -> {
                return "Utilities";
            }
            case ELECTRONICS -> {
                return "Electronics";
            }
            case FOOD -> {
                return "Food";
            }
            case ENTERTAINMENT -> {
                return "Entertainment";
            }
            case OTHER -> {
                return "Other";
            }
            default -> {
                return "NA";
            }

        }


    }
}
