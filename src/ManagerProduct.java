import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManagerProduct {
    private final Scanner scanner;
    private List<Product> products;
    private final FileIO<List<Product>> fileIO;

    public ManagerProduct() {
        scanner = new Scanner(System.in);
        fileIO = new FileIO<>();
        readFileProduct();
    }

    private final List<Product> list = new ArrayList<>();
    private final ManagerCategory managerCategory = new ManagerCategory();


    private List<Product> getProductsInit() {
        list.add(new Product(1, "CocaCola", "15000", managerCategory.getCategoryById("1"), "Carbonated, good refreshment"));
        list.add(new Product(2, "Pepsi", "14500", managerCategory.getCategoryById("1"), "Carbonated, good refreshment"));
        list.add(new Product(3, "Lollipop", "5000", managerCategory.getCategoryById("3"), "Hard and sweet"));
        list.add(new Product(4, "Donut", "20000", managerCategory.getCategoryById("2"), "Small, pretty, and chocolatey on the surface"));
        list.add(new Product(5, "Mochi cake", "35000", managerCategory.getCategoryById("2"), "Small, pretty and sweet"));
        list.add(new Product(6, "Milkita", "5000", managerCategory.getCategoryById("3"), "Carbonated, good refreshment"));
        list.add(new Product(7, "Sting", "13000", managerCategory.getCategoryById("1"), "Carbonated, increased alertness"));
        list.add(new Product(8, "Redbul", "14500", managerCategory.getCategoryById("1"), "Increase energy and concentration"));
        list.add(new Product(9, "Tiramisu", "40000", managerCategory.getCategoryById("2"), "biscuits interspersed with cream cheese, topped with cocoa powder or coffee"));
        list.add(new Product(10, "Cupcake", "350000", managerCategory.getCategoryById("2"), "Small, pretty, and chocolatey on the surface"));
        list.add(new Product(11, "Limburg", "350000", managerCategory.getCategoryById("2"), "Soft cake, has the aroma of milk and cool of fruit"));
        list.add(new Product(12, "Suku", "55000", managerCategory.getCategoryById("1"), "Carbonated, delicious and not too hard"));
        return list;
    }


    private int maxId = 0;
    private int count;
    private boolean flag;
    private Pattern pattern;
    private int id;

    private int getMaxId() {
        for (Product product : products) {
            if (maxId < product.getId()) {
                maxId = product.getId();
            }
        }
        return maxId;
    }

    private void propertyCheck() {
        count = 0;
        flag = true;
    }

    private String inputNameProduct() {
        propertyCheck();
        pattern = Pattern.compile("^.+$");
        System.out.println("Enter name product: ");
        return getString();
    }

    public String inputPriceProduct() {
        propertyCheck();
        pattern = Pattern.compile("^[1-9]\\d{3,}$");
        System.out.println("Enter price product: ");
        return getString();
    }

    public String inputIdCategoryProduct() {
        propertyCheck();
        pattern = Pattern.compile("^\\d+$");
        System.out.println("List category: ");
        managerCategory.displayCategory();
        System.out.println("Enter id category product: ");
        return getString();
    }

    private String getString() {
        String str = scanner.nextLine();
        while (count < 3 && flag) {
            try {
                if (pattern.matcher(str).matches()) {
                    flag = false;
                } else {
                    count++;
                    System.out.println("you entered the wrong " + count + " times");
                    str = scanner.nextLine();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return str;
    }

    private String[] inputNamePriceDescription() {
        String[] input = new String[4];
        input[0] = inputNameProduct();
        if (!input[0].isEmpty()){
            input[1] = inputPriceProduct();
            input[2] = inputIdCategoryProduct();
            System.out.println("Enter description product: ");
            input[3] = scanner.nextLine();
        }
        return input;
    }

    public void createProduct() {
        maxId = getMaxId();
        String[] strings = inputNamePriceDescription();
        if (!strings[0].isEmpty()){
            Category category = managerCategory.getCategoryById(strings[2]);
            products.add(new Product(++maxId, strings[0], strings[1], category, strings[3]));
            System.out.println("Add success!!!");
        }
        else {
            System.out.println("Add failed!!!");
        }
    }

    private boolean checkDel(String str) {
        flag = Integer.parseInt(str) == 1;
        return flag;
    }

    public void deleteProduct(String index) {
        try {
            id = Integer.parseInt(index);
            if (checkId(index)) {
                System.out.println(products.get(id - 1));
                System.out.println("Are you sure you want to delete?");
                System.out.println("1-Yes/ 2-No");
                pattern = Pattern.compile("^[1,0]$");
                String str = getString();
                if (checkDel(str)) {
                    products.remove(id - 1);
                }
            } else {
                System.out.println("Id not fond!!!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct data type!!");
        }
    }

    public String inputId() {
        propertyCheck();
        pattern = Pattern.compile("^\\d+$");
        System.out.println("Enter id product: ");
        return getString();
    }

    private boolean checkId(String index) {
        int id = Integer.parseInt(index);
        flag = false;
        for (Product product : products) {
            if (id == product.getId()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void updateProduct(String index) {
        try {
            id = Integer.parseInt(index);
            if (checkId(index)) {
                String[] input = inputNamePriceDescription();
                if (!input[0].isEmpty()){
                    products.get(id - 1).setNameProduct(input[0]);
                    setPriceCategoryDescription(input);
                }
                else {
                    setPriceCategoryDescription(input);
                }
            } else {
                System.out.println("Id not fond!!!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct data type!!");
        }
    }

    private void setPriceCategoryDescription(String[] input) {
        products.get(id - 1).setPrice(input[1]);
        Category category = managerCategory.getCategoryById(input[2]);
        products.get(id - 1).setCategory(category);
        products.get(id - 1).setDescription(input[3]);
        System.out.println("Update success!!!");
    }

    public void displayProduct() {
        System.out.printf("%-5s %-15s %-10s %-12s %-18s %-1s\n",
                "Id", "Name Product", "Price", "Id Category", "Name Category", "Description");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void writeFileProduct() {
        fileIO.writeFile("Product-data", products);
    }

    public void readFileProduct() {
            products = fileIO.readFile("Product-data");
            if (products == null){
                products = getProductsInit();
            }

    }

    private List<Product> findProductMinPrice() {
        List<Product> listProductMinPrice = new ArrayList<>();
        double minPrice = Double.parseDouble(products.get(0).getPrice());
        for (Product product : products) {
            if (minPrice > Double.parseDouble(product.getPrice())) {
                minPrice = Double.parseDouble(product.getPrice());
            }
        }
        for (Product product : products) {
            if (minPrice == Double.parseDouble(product.getPrice())) {
                listProductMinPrice.add(product);
            }
        }
        return listProductMinPrice;
    }

    public void showProductMinPrice() {
        List<Product> list1 = findProductMinPrice();
        for (Product product : list1) {
            System.out.println(product);
        }
    }

    private List<Product> findProductMaxPrice() {
        List<Product> listProductMaxPrice = new ArrayList<>();
        double maxPrice = Double.parseDouble(products.get(0).getPrice());
        for (Product product : products) {
            if (maxPrice < Double.parseDouble(product.getPrice())) {
                maxPrice = Double.parseDouble(product.getPrice());
            }
        }
        for (Product product : products) {
            if (maxPrice == Double.parseDouble(product.getPrice())) {
                listProductMaxPrice.add(product);
            }
        }
        return listProductMaxPrice;
    }

    public void showProductMaxPrice() {
        List<Product> list1 = findProductMaxPrice();
        for (Product product : list1) {
            System.out.println(product);
        }
    }

    private boolean checkNameProduct(String str) {
        flag = false;
        for (Product product : products) {
            if (product.getNameProduct().toLowerCase().equals(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void findProductByName() {
        String name = inputNameProduct();
        if (!name.isEmpty()) {
            if (checkNameProduct(name)) {
                for (Product product : products) {
                    if (product.getNameProduct().toLowerCase().equals(name)) {
                        System.out.printf("%-5s %-15s %-10s %-12s %-18s %-1s\n",
                                "Id", "Name Product", "Price", "Id Category", "Name Category", "Description");
                        System.out.println(product);
                    }
                }
            } else {
                System.out.println("Product name not found!!!");
            }
        } else {
            System.out.println("Product name cannot be left blank!!!");
        }

    }

    public void findProductByPrice(String price) {
        if (!price.isEmpty()) {
            for (Product product : products) {
                if (Double.parseDouble(price) >= Double.parseDouble(product.getPrice())) {
                    System.out.println(product);
                }
            }
        } else {
            System.out.println("No products matching the above price!!!");
        }
    }

    public void displayListProductByCategory(String id) {
        if (!id.isEmpty()) {
            if (checkId(id)) {
                for (Product product : products) {
                    if (product.getCategory().getId() == Integer.parseInt(id)) {
                        System.out.println(product);
                    }
                }
            } else {
                System.out.println("Product id not found!!!");
            }
        } else {
            System.out.println("Please try again!!!");
        }

    }

    public void setProductByCategory(){
        managerCategory.readFile();
       List<Category> categories = managerCategory.getCategories();
       for (Product product : products){
           for (Category category : categories){
               if (product.getCategory().getId() == category.getId()){
                   product.setCategory(category);
               }
           }
       }

    }


}
