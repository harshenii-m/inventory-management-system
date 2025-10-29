import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
public class crudoperation {

    static String q1 = "CREATE TABLE IF NOT EXISTS products (" +
            "product_id INT PRIMARY KEY AUTO_INCREMENT, " +
            "product_name VARCHAR(100) NOT NULL, " +
            "category VARCHAR(50) NOT NULL, " +
            "quantity INT NOT NULL" +
            ")";

    static String q2 = "CREATE TABLE IF NOT EXISTS suppliers (" +
            "supplier_id INT PRIMARY KEY AUTO_INCREMENT, " +
            "supplier_name VARCHAR(100) NOT NULL, " +
            "contact_no VARCHAR(15), " +
            "email VARCHAR(100), " +
            "address VARCHAR(255)" +
            ")";

    static String q3 = "CREATE TABLE IF NOT EXISTS customers (" +
            "customer_id INT PRIMARY KEY AUTO_INCREMENT, " +
            "customer_name VARCHAR(100) NOT NULL, " +
            "contact_no VARCHAR(15), " +
            "email VARCHAR(100)" +
            ")";

    static String q4 = "CREATE TABLE IF NOT EXISTS purchase (" +
            "purchase_id INT PRIMARY KEY AUTO_INCREMENT, " +
            "product_id INT NOT NULL, " +
            "supplier_id INT, " +
            "quantity INT NOT NULL, " +
            "purchase_price DECIMAL(10,2) NOT NULL, " +
            "purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (product_id) REFERENCES products(product_id), " +
            "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)" +
            ")";


    static String insertProduct = "INSERT INTO products (product_name, category, quantity) VALUES (?,?,?)";
    static String insertSupplier = "INSERT INTO suppliers (supplier_name, contact_no, email, address) VALUES (?,?,?,?)";
    static String insertCustomer = "INSERT INTO customers (customer_name, contact_no, email) VALUES (?,?,?)";
    static String insertPurchase = "INSERT INTO purchase (product_id, supplier_id, quantity, purchase_price) VALUES (?,?,?,?)";

    public static void createTables() {
        try (Connection conn = db.getconnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(q1);
            stmt.executeUpdate(q2);
            stmt.executeUpdate(q3);
            stmt.executeUpdate(q4);
            System.out.println("All tables created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }


    public static void storeProduct() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEnter Product Details:");
            System.out.print("Product Name: ");
            String name = scan.nextLine();
            System.out.print("Category: ");
            String category = scan.nextLine();
            System.out.print("Quantity: ");
            int qty = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(insertProduct);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, qty);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Product added successfully!" : "Product insert failed.");
        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
        }
    }


    public static void storeSupplier() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEnter Supplier Details:");
            System.out.print("Supplier Name: ");
            String name = scan.nextLine();
            System.out.print("Contact No: ");
            String contact = scan.nextLine();
            System.out.print("Email: ");
            String email = scan.nextLine();
            System.out.print("Address: ");
            String address = scan.nextLine();

            PreparedStatement ps = conn.prepareStatement(insertSupplier);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, email);
            ps.setString(4, address);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Supplier added successfully!" : "Supplier insert failed.");
        } catch (SQLException e) {
            System.out.println("Error inserting supplier: " + e.getMessage());
        }
    }


    public static void storeCustomer() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n Enter Customer Details:");
            System.out.print("Customer Name: ");
            String name = scan.nextLine();
            System.out.print("Contact No: ");
            String contact = scan.nextLine();
            System.out.print("Email: ");
            String email = scan.nextLine();

            PreparedStatement ps = conn.prepareStatement(insertCustomer);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, email);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Customer added successfully!" : "Customer insert failed.");
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    public static void storePurchase() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEnter Purchase Details:");
            System.out.print("Product ID: ");
            int productId = scan.nextInt();
            System.out.print("Supplier ID: ");
            int supplierId = scan.nextInt();
            System.out.print("Quantity: ");
            int qty = scan.nextInt();
            System.out.print("Purchase Price: ");
            double price = scan.nextDouble();

            PreparedStatement ps = conn.prepareStatement(insertPurchase);
            ps.setInt(1, productId);
            ps.setInt(2, supplierId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Purchase record added!" : "⚠Purchase insert failed.");
        } catch (SQLException e) {
            System.out.println("Error inserting purchase: " + e.getMessage());
        }
    }

    static String updateProduct =
            "UPDATE products SET product_name = ?, category = ?, quantity = ? WHERE product_id = ?";

    static String updateSupplier =
            "UPDATE suppliers SET supplier_name = ?, contact_no = ?, email = ?, address = ? WHERE supplier_id = ?";

    static String updateCustomer =
            "UPDATE customers SET customer_name = ?, contact_no = ?, email = ? WHERE customer_id = ?";

    static String updatePurchase =
            "UPDATE purchase SET product_id = ?, supplier_id = ?, quantity = ?, purchase_price = ? WHERE purchase_id = ?";
    public static void updateProduct() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nUpdate Product Details:");
            System.out.print("Enter Product ID to update: ");
            int pid = scan.nextInt();
            scan.nextLine();

            System.out.print("New Product Name: ");
            String name = scan.nextLine();
            System.out.print("New Category: ");
            String category = scan.nextLine();
            System.out.print("New Quantity: ");
            int qty = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(updateProduct);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, qty);
            ps.setInt(4, pid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? " Product updated successfully!" : " No product found with that ID.");
        } catch (SQLException e) {
            System.out.println(" Error updating product: " + e.getMessage());
        }
    }
    public static void updateSupplier() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nUpdate Supplier Details:");
            System.out.print("Enter Supplier ID to update: ");
            int sid = scan.nextInt();
            scan.nextLine();

            System.out.print("New Supplier Name: ");
            String name = scan.nextLine();
            System.out.print("New Contact No: ");
            String contact = scan.nextLine();
            System.out.print("New Email: ");
            String email = scan.nextLine();
            System.out.print("New Address: ");
            String address = scan.nextLine();

            PreparedStatement ps = conn.prepareStatement(updateSupplier);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setInt(5, sid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "supplier updated successfully!" : "No supplier found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error updating supplier: " + e.getMessage());
        }
    }
    public static void updateCustomer() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nUpdate Customer Details:");
            System.out.print("Enter Customer ID to update: ");
            int cid = scan.nextInt();
            scan.nextLine();

            System.out.print("New Customer Name: ");
            String name = scan.nextLine();
            System.out.print("New Contact No: ");
            String contact = scan.nextLine();
            System.out.print("New Email: ");
            String email = scan.nextLine();

            PreparedStatement ps = conn.prepareStatement(updateCustomer);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, email);
            ps.setInt(4, cid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Customer updated successfully!" : "No customer found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }
    public static void updatePurchase() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nUpdate Purchase Details:");
            System.out.print("Enter Purchase ID to update: ");
            int pid = scan.nextInt();

            System.out.print("New Product ID: ");
            int productId = scan.nextInt();
            System.out.print("New Supplier ID: ");
            int supplierId = scan.nextInt();
            System.out.print("New Quantity: ");
            int qty = scan.nextInt();
            System.out.print("New Purchase Price: ");
            double price = scan.nextDouble();

            PreparedStatement ps = conn.prepareStatement(updatePurchase);
            ps.setInt(1, productId);
            ps.setInt(2, supplierId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.setInt(5, pid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Purchase updated successfully!" : " No purchase found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error updating purchase: " + e.getMessage());
        }
    }

    static String deleteProduct = "DELETE FROM products WHERE product_id = ?";
    static String deleteSupplier = "DELETE FROM suppliers WHERE supplier_id = ?";
    static String deleteCustomer = "DELETE FROM customers WHERE customer_id = ?";
    static String deletePurchase = "DELETE FROM purchase WHERE purchase_id = ?";
    public static void deleteProduct() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nDelete Product Record:");
            System.out.print("Enter Product ID to delete: ");
            int pid = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(deleteProduct);
            ps.setInt(1, pid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Product deleted successfully!" : "No product found with that ID.");
        } catch (SQLException e) {
            System.out.println(" Error deleting product: " + e.getMessage());
        }
    }
    public static void deleteSupplier() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nDelete Supplier Record:");
            System.out.print("Enter Supplier ID to delete: ");
            int sid = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(deleteSupplier);
            ps.setInt(1, sid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? " Supplier deleted successfully!" : " No supplier found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting supplier: " + e.getMessage());
        }
    }
    public static void deleteCustomer() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nDelete Customer Record:");
            System.out.print("Enter Customer ID to delete: ");
            int cid = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(deleteCustomer);
            ps.setInt(1, cid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? " Customer deleted successfully!" : "No customer found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }
    public static void deletePurchase() {
        try (Connection conn = db.getconnection()) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nDelete Purchase Record:");
            System.out.print("Enter Purchase ID to delete: ");
            int pid = scan.nextInt();

            PreparedStatement ps = conn.prepareStatement(deletePurchase);
            ps.setInt(1, pid);

            int row = ps.executeUpdate();
            System.out.println(row > 0 ? "Purchase deleted successfully!" : "No purchase found with that ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting purchase: " + e.getMessage());
        }
    }
    static String viewProducts = "SELECT * FROM products";
    static String viewSuppliers = "SELECT * FROM suppliers";
    static String viewCustomers = "SELECT * FROM customers";
    static String viewPurchase = "SELECT * FROM purchase";




    public static void viewProducts() {
        try (Connection conn = db.getconnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(viewProducts)) {

            System.out.println("\nPRODUCT LIST:");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "Name", "Category", "Quantity");
            System.out.println("-------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s %-10d%n",
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    public static void viewSuppliers() {
        try (Connection conn = db.getconnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(viewSuppliers)) {

            System.out.println("\n SUPPLIER LIST:");
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("%-5s %-20s %-15s %-25s %-20s%n", "ID", "Name", "Contact", "Email", "Address");
            System.out.println("---------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-25s %-20s%n",
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing suppliers: " + e.getMessage());
        }
    }

    public static void viewCustomers() {
        try (Connection conn = db.getconnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(viewCustomers)) {

            System.out.println("\nCUSTOMER LIST:");
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-5s %-20s %-15s %-25s%n", "ID", "Name", "Contact", "Email");
            System.out.println("--------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-25s%n",
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("contact_no"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(" Error viewing customers: " + e.getMessage());
        }
    }

    public static void viewPurchase() {
        try (Connection conn = db.getconnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(viewPurchase)) {

            System.out.println("\n PURCHASE RECORDS:");
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-10s %-10s %-12s %-20s%n",
                    "ID", "Prod_ID", "Supp_ID", "Qty", "Price", "Date");
            System.out.println("--------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-10d %-10d %-10d %-12.2f %-20s%n",
                        rs.getInt("purchase_id"),
                        rs.getInt("product_id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("purchase_price"),
                        rs.getString("purchase_date"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing purchase: " + e.getMessage());
        }
    }

}

