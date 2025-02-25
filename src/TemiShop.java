import java.util.*;

public class TemiShop {
    Scanner in = new Scanner(System.in);
    static HashMap<String, HashMap<String, double[]>> products = new HashMap<>();
    static HashMap<String , double[]> cart = new HashMap<>();
    static HashMap<String, String[]> users = new HashMap<>();
    public static void addP(String cat, String name, double p, int q){
        products.put(cat, new HashMap<>());
        double[]pq = new double[]{p, q} ;
        products.get(cat).put(name, pq);
    }
    public static void updateP( String name, double np, int nq) {
        List<String> cats = new ArrayList<>(products.keySet());
        for (int i = 0; i < cats.size(); i++) {
            String c = cats.get(i);
            HashMap<String, double[]> mp = products.get(c);
                for (Map.Entry<String, double[]> mp2 : mp.entrySet()) {
                    String n = mp2.getKey();
                    double[] v = mp2.getValue();
                    if (n.equals(name)) {
                        v[0] = np;
                        v[1] = nq;
                        mp.put(name, v);
                    }
                }
        }
    }
    public static void removeP(String name){
        List<String> cats = new ArrayList<>(products.keySet());
        for (int i = 0; i < cats.size(); i++) {
            String c = cats.get(i);
            HashMap<String, double[]> mp = products.get(c);
                for (Map.Entry<String, double[]> mp2 : mp.entrySet()) {
                    String n = mp2.getKey();
                    double[] v = mp2.getValue();
                    if (n.equals(name)) {
                        mp.remove(name);
                        if (mp.isEmpty()) {
                            products.remove(c);
                        }
                    }
                }

        }
    }
    public static double isProductAvailable(String name){
        List<String> cats = new ArrayList<>(products.keySet());
        boolean test = false;
        for (int i = 0; i < cats.size(); i++) {
            String c = cats.get(i);
            HashMap<String, double[]> mp = products.get(c);
            for (Map.Entry<String, double[]> mp2 : mp.entrySet()) {
                String n = mp2.getKey();
                double[] v = mp2.getValue();
                double q = v[1];
                if(n.equals(name)){
                    return q;
                }
            }

        }
        return 0;
    }
    public static void ViewAll(){
         List<String> cats = new ArrayList<>(products.keySet());
         for (int i = 0; i < cats.size(); i++) {
             String cat = cats.get(i);
             HashMap<String, double[]> mp = products.get(cat);
             System.out.println("Category: " + cat );
                 for (Map.Entry<String, double[]> mp2 : mp.entrySet()) {
                     String n = mp2.getKey();
                     double[] v = mp2.getValue();
                         double p = v[0];
                         double q = v[1];

                     System.out.println("Product: " + n + " | Price: $" + p + " | Quantity: " + q);

                 }

         }

     }
    public static void addToCart(String n, double q ){
        for (String cat : products.keySet()){
            HashMap<String, double[]> mp = products.get(cat);
            for (String name : mp.keySet()){
                if(n.equals(name)){
                    double v1[] = mp.get(n);
                    double p = v1[0];
                    double[] v = new double[]{q, p};
                    cart.put(n ,v);
                }
            }

        }

    }
    public static void removeFromCart(String name){
        for (String n : cart.keySet()) {
            if(n.equals(name)){
                cart.remove(n);
            }else{
                System.out.println("Your Cart Not Contain This Product");
            }
        }

    }
    public static void ViewCart(){
        for (String name : cart.keySet()) {
             double v[] = cart.get(name);
            System.out.println("Product: " + name + " | Quantity: " + (int)v[0] + " | Price: $" + v[1]);
        }
    }
    public static double calculateTotal(){
        double total = 0 ;
        for (String name : cart.keySet()) {
            double v[] = cart.get(name);
            total +=  (v[0] * v[1]);
        }
        return total ;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("🔹 Welcome to the E-Commerce System 🔹");
        System.out.println("1️⃣ Admin");
        System.out.println("2️⃣ Customer");

        int userType;
        while (true) {
            System.out.print("🔸 Choose your role: ");
            try {
                userType = in.nextInt();
                if (userType == 1 || userType == 2) {
                    break;
                } else {
                    System.out.println("❌ Invalid choice. Please enter 1 for Admin or 2 for Customer.");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                in.nextLine();
            }
        }

        in.nextLine();
        System.out.print("🔹 Enter Your Name: ");
        String username = in.nextLine();

        boolean isAdmin = userType == 1 && username.equals("Amr Khaled");

        if (userType == 1 && !isAdmin) {
            System.out.println("❌ Access Denied! You are not an Admin.");
            return;
        }

        while (true) {
            if (isAdmin) {
                while (true) {
                    System.out.println("\n🔹 Admin Panel 🔹");
                    System.out.println("1️⃣ Add Product ");
                    System.out.println("2️⃣ Update Product ");
                    System.out.println("3️⃣ View All Products");
                    System.out.println("4️⃣ Remove Product");
                    System.out.println("5️⃣ Exit to Main Menu");

                    int choice;
                    while (true) {
                        System.out.print("🔸 Choose an option: ");
                        try {
                            choice = in.nextInt();
                            if (choice >= 1 && choice <= 5) break;
                            else System.out.println("❌ Invalid choice. Please select a valid option.");
                        } catch (Exception e) {
                            System.out.println("❌ Invalid input. Please enter a number.");
                            in.nextLine();
                        }
                    }

                    if (choice == 1) {
                        System.out.println("**Hello Admin**");
                        System.out.print("Enter Category: ");
                        String c = in.next();
                        System.out.print("Enter Product Name: ");
                        String n = in.next();

                        double p;
                        while (true) {
                            System.out.print("Set Price: ");
                            try {
                                p = in.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("❌ Invalid input. Please enter a valid price.");
                                in.nextLine();
                            }
                        }

                        int q;
                        while (true) {
                            System.out.print("Set Quantity: ");
                            try {
                                q = in.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("❌ Invalid input. Please enter a valid quantity.");
                                in.nextLine();
                            }
                        }

                        addP(c, n, p, q);
                    } else if (choice == 2) {
                        System.out.print("Enter Product Name to Update: ");
                        String n = in.next();

                        double p;
                        while (true) {
                            System.out.print("Set New Price: ");
                            try {
                                p = in.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("❌ Invalid input. Please enter a valid price.");
                                in.nextLine();
                            }
                        }

                        int q;
                        while (true) {
                            System.out.print("Set New Quantity: ");
                            try {
                                q = in.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("❌ Invalid input. Please enter a valid quantity.");
                                in.nextLine();
                            }
                        }

                        updateP(n, p, q);
                    } else if (choice == 3) {
                        ViewAll();
                    } else if (choice == 4) {
                        System.out.print("Enter Product Name to Remove: ");
                        String n = in.next();
                        removeP(n);
                    } else if (choice == 5) {
                        System.out.println("Returning to Main Menu...");
                        break;
                    }
                }
            } else {
                while (true) {
                    System.out.println("\n🔹 Customer Menu 🔹");
                    System.out.println("1️⃣ View All Products");
                    System.out.println("2️⃣ Check Product Availability ");
                    System.out.println("3️⃣ Add to Cart ");
                    System.out.println("4️⃣ Remove from Cart ");
                    System.out.println("5️⃣ View Your Cart ");
                    System.out.println("6️⃣ Calculate Total ");
                    System.out.println("7️⃣ Exit");

                    int choice;
                    while (true) {
                        System.out.print("🔸 Choose an option: ");
                        try {
                            choice = in.nextInt();
                            if (choice >= 1 && choice <= 7) break;
                            else System.out.println("❌ Invalid choice. Please select a valid option.");
                        } catch (Exception e) {
                            System.out.println("❌ Invalid input. Please enter a number.");
                            in.nextLine();
                        }
                    }

                    if (choice == 1) {
                        ViewAll();
                    } else if (choice == 2) {
                        System.out.print("Enter Product Name: ");
                        String n = in.next();
                        double q = isProductAvailable(n);
                        System.out.println(q != 0 ? "Product is available! Quantity: " + (int) q : "Product is not available.");
                    } else if (choice == 3) {
                        System.out.print("Enter Product Name: ");
                        String n = in.next();

                        int qu;
                        while (true) {
                            System.out.print("Enter Quantity: ");
                            try {
                                qu = in.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("❌ Invalid input. Please enter a valid quantity.");
                                in.nextLine();
                            }
                        }

                        double q = isProductAvailable(n);
                        if (q != 0) {
                            addToCart(n, qu);
                        } else {
                            System.out.println("Product is not available.");
                        }
                    } else if (choice == 4) {
                        System.out.print("Enter Product Name to Remove: ");
                        String n = in.next();
                        removeFromCart(n);
                    } else if (choice == 5) {
                        ViewCart();
                    } else if (choice == 6) {
                        System.out.println("Total: " + calculateTotal());
                    } else if (choice == 7) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
