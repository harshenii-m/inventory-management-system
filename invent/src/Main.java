public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean running = true;

    while (running) {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Store Data");
        System.out.println("2. Update Data");
        System.out.println("3. Delete Data");
        System.out.println("4. View Data");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {


            case 1 -> {
                boolean storeMenu = true;
                while (storeMenu) {
                    System.out.println("\n----- STORE MENU -----");
                    System.out.println("1. Store Product");
                    System.out.println("2. Store Supplier");
                    System.out.println("3. Store Customer");
                    System.out.println("4. Store Purchase");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int storeChoice = sc.nextInt();
                    sc.nextLine();

                    switch (storeChoice) {
                        case 1 -> crudoperation.storeProduct();
                        case 2 -> crudoperation.storeSupplier();
                        case 3 -> crudoperation.storeCustomer();
                        case 4 -> crudoperation.storePurchase();
                        case 5 -> storeMenu = false;
                        default -> System.out.println(" Invalid option! Try again.");
                    }
                }
            }

            case 2 -> {
                boolean updateMenu = true;
                while (updateMenu) {
                    System.out.println("\n----- UPDATE MENU -----");
                    System.out.println("1. Update Product");
                    System.out.println("2. Update Supplier");
                    System.out.println("3. Update Customer");
                    System.out.println("4. Update Purchase");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int updateChoice = sc.nextInt();
                    sc.nextLine();

                    switch (updateChoice) {
                        case 1 -> crudoperation.updateProduct();
                        case 2 -> crudoperation.updateSupplier();
                        case 3 -> crudoperation.updateCustomer();
                        case 4 -> crudoperation.updatePurchase();
                        case 5 -> updateMenu = false;
                        default -> System.out.println(" Invalid option! Try again.");
                    }
                }
            }


            case 3 -> {
                boolean deleteMenu = true;
                while (deleteMenu) {
                    System.out.println("\n----- DELETE MENU -----");
                    System.out.println("1. Delete Product");
                    System.out.println("2. Delete Supplier");
                    System.out.println("3. Delete Customer");
                    System.out.println("4. Delete Purchase");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int deleteChoice = sc.nextInt();
                    sc.nextLine();

                    switch (deleteChoice) {
                        case 1 -> crudoperation.deleteProduct();
                        case 2 -> crudoperation.deleteSupplier();
                        case 3 -> crudoperation.deleteCustomer();
                        case 4 -> crudoperation.deletePurchase();
                        case 5 -> deleteMenu = false;
                        default -> System.out.println(" Invalid option! Try again.");
                    }
                }
            }


            case 4 -> {
                boolean viewMenu = true;
                while (viewMenu) {
                    System.out.println("\n----- VIEW MENU -----");
                    System.out.println("1. View Products");
                    System.out.println("2. View Suppliers");
                    System.out.println("3. View Customers");
                    System.out.println("4. View Purchase Records");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int viewChoice = sc.nextInt();
                    sc.nextLine();

                    switch (viewChoice) {
                        case 1 -> crudoperation.viewProducts();
                        case 2 -> crudoperation.viewSuppliers();
                        case 3 -> crudoperation.viewCustomers();
                        case 4 -> crudoperation.viewPurchase();
                        case 5 -> viewMenu = false;
                        default -> System.out.println(" Invalid option! Try again.");
                    }
                }
            }


            case 5 -> {
                System.out.println(" Exiting program. Goodbye!");
                running = false;
            }

            default -> System.out.println("Invalid main menu choice! Try again.");
        }
    }

    sc.close();
}
