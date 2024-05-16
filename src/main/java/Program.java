public class Program {

    private static class Item{
        String name;
        int cost;

        public Item(String name, int cost){
            this.name = name;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", cost=" + cost +
                    '}';
        }
    }
    private static class Customer{

        enum Genders{MALE, FEMALE}

        String name;
        int age;
        String phoneNumber;

        public Customer(String name, int age, String phoneNumber){
            this.name = name;
            this.age = age;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }

    private static class Order{
        Customer customer;
        Item item;
        int amount;

        public Order(Customer customer, Item item, int amount){
            this.customer = customer;
            this.item = item;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "customer=" + customer +
                    ", item=" + item +
                    ", amount=" + amount +
                    '}';
        }
    }

    public static class CustomerException extends RuntimeException {
        public CustomerException(String message) {super(message);}
    }
    public static class ProductException extends RuntimeException{
        public ProductException(String message) {super(message);}
    }
    public static class AmountException extends RuntimeException{
        public AmountException(String message) {super(message);}
    }

    private final static Customer[] people = {
            new Customer("Ivan", 20, "+1-222-333-44-55"),
            new Customer("Petr", 30, "+2-333-444-55-66")
    };

    private final static Item[] items = {
            new Item("Ball", 100),
            new Item("Sandwich", 1000),
            new Item("Table", 10000),
            new Item("Car", 100000),
            new Item("Rocket", 10000000)
    };

    private static Order[] orders = new Order[5];

    private static boolean isInArray(Object[] array, Object object){
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(object)) return true;
        }
        return false;
    }

    public static Order buy(Customer who, Item what, int howMuch){
        if(!isInArray(people, who))
            throw new CustomerException("Unknown customer: " + who);
        if(!isInArray(items, what))
            throw new ProductException(("Unknown item: " + what));
        if(howMuch < 0 || howMuch > 100)
            throw new AmountException("Incorrect amount: " + howMuch);
        return new Order(who, what, howMuch);
    }

    public static void main(String[] args) {
        Object[][] info = {
                {people[0], items[0], 1},
                {people[1], items[1], -1},
                {people[0], items[2], 150},
                {people[1], new Item("Flower", 10), 1},
                {new Customer("Fedor", 40, "+3-444-555-66-77"), items[3], 1}
        };
        int capacity = 0;
        int i = 0;
        while (capacity != orders.length - 1 || i != info.length){
            try {
                orders[capacity] = buy((Customer) info[i][0], (Item) info[i][1], (int) info[i][2]);
                capacity++;
            }catch (ProductException e){
                e.printStackTrace();
            }catch (AmountException e){
                orders[capacity++] = buy((Customer) info[i][0], (Item) info[i][1], 1);
            }catch (CustomerException e){
                throw new RuntimeException(e);
            }finally {
                System.out.println("Orders made: " + capacity);
            }
            ++i;
        }
    }

    enum Parties{NONE, NEW_YEAR, MARCH_8, FEB_23}

    private static final Parties today = Parties.NONE;

    private static void celebrate(Employee[] emp){
        for (Employee employee : emp) {
            switch (today) {
                case NEW_YEAR:
                    System.out.println(employee.name + ", Happy New Year!");
                    break;
                case FEB_23:
                    if (employee.gender == Employee.Genders.MALE)
                        System.out.println(employee.name + ", happy february 23rd!");
                    break;
                case MARCH_8:
                    if (employee.gender == Employee.Genders.FEMALE)
                        System.out.println(employee.name + ", happy march 8rd!");
                    break;
                default:
                    System.out.println(employee.name + ", celebrate this morning!");
            }
        }
    }
}
