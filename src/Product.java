import java.io.Serializable;

public class Product implements Serializable {
   private final long serializable = 123456789;
   private int id;
   private String nameProduct;
   private String price;
   private Category category;
   private String description;

   public Product() {
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public Product(int id, String nameProduct, String price, Category category, String description) {
      this.id = id;
      this.nameProduct = nameProduct;
      this.price = price;
      this.category = category;
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNameProduct() {
      return nameProduct;
   }

   public void setNameProduct(String nameProduct) {
      this.nameProduct = nameProduct;
   }

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }


   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public String toString() {
      return String.format("%-5d %-15s %-15s %-15s %-20s", id, nameProduct, price,category,description);
   }
}
