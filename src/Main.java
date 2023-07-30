import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagerCategory managerCategory = new ManagerCategory();
        ManagerProduct managerProduct = new ManagerProduct();
        Pattern pattern = Pattern.compile("^\\d+$");
        String choice;
        do {
            System.out.println("Menu: ");
            System.out.println("1. Add category: ");
            System.out.println("2. Display list category: ");
            System.out.println("3. Update category: ");
            System.out.println("4. Delete category: ");
            System.out.println("5. Add product: ");
            System.out.println("6. Display list product: ");
            System.out.println("7. Update product: ");
            System.out.println("8. Delete product: ");
            System.out.println("9. Display products with the lowest price: ");
            System.out.println("10. Display products with the highest price: ");
            System.out.println("11. Find product by name: ");
            System.out.println("12. Find product by price: ");
            System.out.println("13. Display list product by category: ");
            System.out.println("14. Save changes: ");
            System.out.println("0. Exit: ");
            System.out.println("--------------------------------------------------");
            System.out.println("Enter choice: ");
            choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        managerCategory.addCategory();
                        break;
                    case 2:
                        managerCategory.displayCategory();
                        break;
                    case 3:
                        managerCategory.updateCategory(managerCategory.inputId());
                        managerCategory.writeFile();
                        managerProduct.setProductByCategory();
                        managerProduct.writeFileProduct();
                        System.out.println("Update success!!!");
                        break;
                    case 4:
                        managerCategory.deleteCategory(managerCategory.inputId());
                        break;
                    case 5:
                        managerProduct.createProduct();
                        break;
                    case 6:
                        managerProduct.displayProduct();
                        break;
                    case 7:
                        managerProduct.updateProduct(managerProduct.inputId());
                        break;
                    case 8:
                        managerProduct.deleteProduct(managerProduct.inputId());
                        break;
                    case 9:
                        managerProduct.showProductMinPrice();
                        break;
                    case 10:
                        managerProduct.showProductMaxPrice();
                        break;
                    case 11:
                        managerProduct.findProductByName();
                        break;
                    case 12:
                        managerProduct.findProductByPrice(managerProduct.inputPriceProduct());
                        break;
                    case 13:
                        managerProduct.displayListProductByCategory(managerProduct.inputIdCategoryProduct());
                        break;
                    case 14:
                        managerProduct.writeFileProduct();
                        managerCategory.writeFile();
                        System.out.println("Success!!!");
                        break;
                }
            }
        } while (Integer.parseInt(choice) != 0);
    }
}
