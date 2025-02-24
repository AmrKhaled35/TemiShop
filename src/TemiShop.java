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
        System.out.print("🔹 Enter Your Name: ");
        String username = in.nextLine();

        boolean isAdmin = username.equals("Amr Khaled");

        while (true) {
            System.out.println("\n🔹 Welcome to the E-Commerce Store 🔹");
            if (isAdmin) {
                System.out.println("1️⃣ Update Product ");
                System.out.println("2️⃣ Add Product");
                System.out.println("3️⃣ View All Products");
                System.out.println("4️⃣ Remove Product");
            }

            System.out.println("5️⃣ Check Product Availability ");
            System.out.println("6️⃣ Add to Cart ");
            System.out.println("7️⃣ Remove from Cart ");
            System.out.println("8️⃣ View Your Cart ");
            System.out.println("9️⃣ Calculate Total ");
            System.out.println("0️⃣ Exit");

            System.out.print("🔸 Choose an option: ");
            int choice = in.nextInt();

            if (isAdmin) {
                if (choice == 1) {
                    System.out.println("**Hello Admin**");
                    System.out.print("Enter the Category of Product to Update: ");
                    String c = in.next();
                    System.out.print("Enter Product Name: ");
                    String n = in.next();
                    System.out.print("Set New Price: ");
                    double p = in.nextDouble();
                    System.out.print("Set New Quantity: ");
                    int q = in.nextInt();
                    updateP(n, p, q);
                }
                if (choice == 2) {
                    System.out.print("Enter Category: ");
                    String c = in.next();
                    System.out.print("Enter Product Name: ");
                    String n = in.next();
                    System.out.print("Set Price: ");
                    double p = in.nextDouble();
                    System.out.print("Set Quantity: ");
                    int q = in.nextInt();
                    addP(c, n, p, q);
                }
                if (choice == 3) {
                    System.out.println("All Products:");
                    ViewAll();
                }
                if (choice == 4) {
                    System.out.println("**Hello Admin**");
                    System.out.print("Enter Product Name to Remove: ");
                    String n = in.next();
                    removeP(n);
                }
            }

            if (choice == 5) {
                System.out.println("**Hello Customer**");
                System.out.print("Enter Product Name: ");
                String n = in.next();
                double q = isProductAvailable(n);
                if (q != 0) {
                    System.out.println("Product is available! Quantity: " + (int) q);
                } else {
                    System.out.println("Product is not available.");
                }
            }

            if (choice == 6) {
                System.out.println("**Hello Customer**");
                System.out.print("View All Products? (y/n): ");
                String s = in.next();
                if (s.equalsIgnoreCase("y")) {
                    ViewAll();
                }
                System.out.print("Enter Product Name: ");
                String n = in.next();
                System.out.print("Enter Quantity: ");
                int qu = in.nextInt();
                double q = isProductAvailable(n);
                if (q != 0) {
                    addToCart(n, qu);
                } else {
                    System.out.println("Product is not available.");
                }
            }

            if (choice == 7) {
                System.out.println("**Your Cart**");
                ViewCart();
                System.out.print("Enter Product Name to Remove: ");
                String n = in.next();
                removeFromCart(n);
            }

            if (choice == 8) {
                System.out.println("**Your Cart**");
                ViewCart();
            }

            if (choice == 9) {
                System.out.println("**Checkout**");
                double total = calculateTotal();
                System.out.println("Total: " + total);
            }

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }
        }


    }
}
