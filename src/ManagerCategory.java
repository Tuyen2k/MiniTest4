import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManagerCategory {
    private final Scanner scanner;
    private List<Category> categories;
    private final FileIO<List<Category>> fileIO;
    private Pattern pattern;

    public ManagerCategory() {
        scanner = new Scanner(System.in);
        fileIO = new FileIO<>();
        readFile();

    }



    private final List<Category> list = new ArrayList<>();

    private List<Category> categoriesTemp() {
        list.add(new Category(1, "Drink"));
        list.add(new Category(2, "Cake"));
        list.add(new Category(3, "Candy"));
        return list;
    }

    private int maxId = 0;
    private int count;
    private boolean flag;
    private int id;

    private int getMaxId() {
        for (Category category : categories) {
            if (maxId < category.getId()) {
                maxId = category.getId();
            }
        }
        return maxId;
    }


    private String inputNameCategory() {
        count = 0;
        flag = true;
        pattern = Pattern.compile("^.+$");
        System.out.println("Enter name category: ");
        return getString();
    }

    private String getString() {
        String str = scanner.nextLine();
        while (count < 3 && flag) {
            if (pattern.matcher(str).matches()) {
                flag = false;
            } else {
                count++;
                System.out.println("You have entered invalid " + count + " times");
                str = scanner.nextLine();
            }
        }
        return str;
    }


    public void addCategory() {
        maxId = getMaxId();
        String nameAdd = inputNameCategory();
        if (!nameAdd.isEmpty()) {
            categories.add(new Category(++maxId, nameAdd));
        } else {
            System.out.println("Add failed. Please try again!!!");
        }
    }

    public void deleteCategory(String index) {
        try {
            id = Integer.parseInt(index);
            if (checkId(index)) {
                System.out.println(categories.get(id - 1));
                System.out.println("Are you sure you want to delete?");
                System.out.println("1-Yes/ 2-No");
                pattern = Pattern.compile("^[1,0]$");
                String str = getString();
                if (checkDel(str)) {
                    categories.get(id - 1).setNameCategory(null);
                }
            } else {
                System.out.println("Id not fond!!!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct data type!!");
        }
    }

    private boolean checkDel(String str) {
        flag = Integer.parseInt(str) == 1;
        return flag;
    }


    public String inputId() {
        count = 0;
        flag = true;
        pattern = Pattern.compile("^\\d+$");
        System.out.println("Enter id category: ");
        return getString();
    }

    private boolean checkId(String index) {
        id = Integer.parseInt(index);
        flag = false;
        for (Category category : categories) {
            if (id == category.getId()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void updateCategory(String index) {
        int id;
        try {
            id = Integer.parseInt(index) - 1;
            if (checkId(index)) {
                String nameUpdate = inputNameCategory();
                if (!nameUpdate.isEmpty()) {
                    Category category = categories.get(id);
                    category.setNameCategory(nameUpdate);
                    System.out.println("Update category success!!!");
                }
            } else {
                System.out.println("Id not fond!!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct data type!!");
        }
    }

    public void displayCategory() {
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void writeFile() {
        fileIO.writeFile("Category-data", categories);
    }

    public void readFile() {
        categories = fileIO.readFile("Category-data");
        if (categories == null){
            categories = categoriesTemp();
        }

    }

    public Category getCategoryById(String id) {
        int index;
        Category category = new Category();
        try {
            index = Integer.parseInt(id) - 1;
            if (checkId(id)) {
                category = categories.get(index);
            }else {
                System.out.println("Id not found!!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id!!!");
        }
        return category;
    }


}
