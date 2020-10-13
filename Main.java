import java.util.Scanner;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args){
        System.out.println("Input your date:");

        Scanner scan=new Scanner(System.in);
        String name; //name of customer
        String product; //name of product
        int count; //count of product

        TreeMap<String, TreeMap<String,Integer>> customers= new TreeMap<>();//create TreeMap (for products) in TreeMap(for customers)

        String[] str= scan.nextLine().split(" "); //cut our string

        while (!str[0].isEmpty()){
            TreeMap<String, Integer> products = new TreeMap<>(); //new TreeMap for product

            name= str[0];
            product= str[1];
            count=Integer.parseInt(str[2]);

            if(customers.containsKey(name)) { //create twig for customer on name
                products = customers.get(name);
                if (products.containsKey(product)) { //twig for product on name of product
                    count += products.get(product); //get count of product
                    products.put(product, count);
                } else {
                    products.put(product, count);
                }
                customers.put(name, products);
            }else {
                products.put(product, count);
                customers.put(name, products);
            }
                str=scan.nextLine().split(" ");
            }

            for(String customer: customers.keySet()){ //output our TreeMap
                System.out.println(customer+":"); //name of customer
                for(String prod: customers.get(customer).keySet()){ // products and they count
                    System.out.println(prod+" "+customers.get(customer).get(prod));
                }
                System.out.print('\n');
            }
        }

    }
