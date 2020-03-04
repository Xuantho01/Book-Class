import java.util.ArrayList;
import java.util.Scanner;

public class BookTest {
    static ArrayList<Book> ListBook = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String regex_INT = "\\d{0,10}";
    static String regex_DOUBLE = "\\d{0,19}";

    public static void setBook(){
        int choice;
        do {
            System.out.println("Selection type of book to add: ");
            System.out.println("1. Programming Book: ");
            System.out.println("2. Fiction Book: ");
            System.out.println("0. Exit!");
            try{
            choice = scanner.nextInt();
            }catch (Exception e){
                choice = 3;
                System.out.println("Nhap ky tu, yeu cau nhap lai:");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ListBook.add(
                            enterInformationOfProgramingBook());
                    break;
                case 2:
                   ListBook.add(enterInformationOfFictionBook());
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }

    public static Book enterInformationOfBook() {
        Book book = new Book();
        String bookCode;
        String price;
        String name;
        String author;
        int choice;
        do {
            System.out.println("Enter information of Book : ");
            System.out.println("1. Enter name: ");
            System.out.println("2. Enter book code: ");
            System.out.println("3. Enter Price: ");
            System.out.println("4. Enter author: ");
            System.out.println("0. Exit!");

            try {
                choice = scanner.nextInt();
            }catch (Exception e){
                choice = 5;
                System.out.println("Invalid");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter book name: ");
                    name = scanner.nextLine();
                    book.setName(name);
                    break;
                case 2:
                    do {
                        System.out.println("Enter book code: ");
                        bookCode = scanner.nextLine();
                    } while (!bookCode.matches(regex_INT));
                    book.setBookCode(Integer.parseInt(bookCode));
                    break;
                case 3:
                    do {
                        System.out.println("Enter price of book: ");
                        price = scanner.nextLine();
                    } while (!price.matches(regex_DOUBLE));
                    book.setPrice(Double.parseDouble(price));
                    break;
                case 4:
                    System.out.println("Enter book author: ");
                    author = scanner.nextLine();
                    book.setAuthor(author);
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
        return book;
    }

    public static ProgrammingBook enterInformationOfProgramingBook() {
        ProgrammingBook programmingBook = new ProgrammingBook();
        String language;
        String framework;
        Book book = enterInformationOfBook();
        int choice;
        do {
            System.out.println("4. Enter language: ");
            System.out.println("5. Enter FrameWork: ");
            System.out.println("0. Exit!");
            try {
                choice = scanner.nextInt();
            }catch (Exception e){
                System.out.println("invalid!");
                choice = 10;
            }
            scanner.nextLine();

            switch (choice) {
                case 4:
                    System.out.println("Enter language: ");
                    language = scanner.nextLine();
                    programmingBook.setLanguage(language);
                    break;
                case 5:
                    System.out.println("Enter frameWork: ");
                    framework = scanner.nextLine();
                    programmingBook.setFramework(framework);
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
        programmingBook.setBookCode(book.getBookCode());
        programmingBook.setName(book.getName());
        programmingBook.setPrice(book.getPrice());
        return programmingBook;

    }
    public static FictionBook enterInformationOfFictionBook() {
        FictionBook fictionBook = new FictionBook();

        String category;

        Book book = enterInformationOfBook();
        fictionBook.setBookCode(book.getBookCode());
        fictionBook.setName(book.getName());
        fictionBook.setPrice(book.getPrice());
        fictionBook.setAuthor(book.getAuthor());

        System.out.println("Enter category of book: ");
        category = scanner.nextLine();
        fictionBook.setCategory(category);

        return fictionBook;

    }
    public static void main(String[] args) {

        setBook();

        System.out.println(ListBook.toString());

        System.out.println("Enter this Book that you want to search: ");
        String book_Search = scanner.nextLine();
        String searchPrice = searchPriceBook(book_Search);

        double total_Price = totalPrice(ListBook);
        int total_javaBook = coutJavaBook(ListBook);

        System.out.println(searchPrice);
        System.out.println("Total price of List Book: " + total_Price);

        System.out.println("Total java Book: " + total_javaBook);

        System.out.println("\nBubble sort: ");
        System.out.println("\n" + bubbleSort(ListBook));

        System.out.println("\nSelection sort: ");
        System.out.println("\n" + selectionSort(ListBook));

        System.out.println("\ninsertion Sort: ");
        System.out.println("\n" + insertionSort(ListBook));

        System.out.println(binarySearch(insertionSort(ListBook), "fiction Book 3"));
    }

    public static String searchPriceBook(String nameOfBook) {
        for (Book list : ListBook) {
            if (nameOfBook.equals(list.getName())) {
                return "price of " + nameOfBook + " is :" + list.getPrice();
            }
        }
        return "Not found";
    }

    public static double totalPrice(ArrayList<Book> listBook) {
        double price = 0;
        for (Book lists : listBook) {
            price += lists.getPrice();
        }
        return price;
    }

    public static int coutJavaBook(ArrayList<Book> listBook) {
        int totalJavaBook = 0;
        for (Book list_java_book : listBook) {
            boolean isProgrammingBook = list_java_book instanceof ProgrammingBook;
            if (isProgrammingBook) {
                boolean isJavaBook = ((ProgrammingBook) list_java_book).getLanguage().equals("Java");
                if (isJavaBook) {
                    totalJavaBook++;
                }
            }
        }
        return totalJavaBook;
    }

    public static ArrayList<Book> bubbleSort(ArrayList<Book> listBook) {
        for (int i = 1; i < listBook.size(); i++) {
            for (int j = 0; j < listBook.size() - i; j++) {
                if (listBook.get(j).getPrice() > listBook.get(j + 1).getPrice()) {
                    Book temp = listBook.get(j);
                    listBook.set(j, listBook.get(j + 1));
                    listBook.set(j + 1, temp);
                }
            }
        }
        return listBook;
    }

    public static ArrayList<Book> selectionSort(ArrayList<Book> listBook) {
        for (int i = 0; i < listBook.size(); i++) {
            Book minPriceOfBook = listBook.get(i);
            int currentIndex = listBook.indexOf(listBook.get(i));
            for (int j = i + 1; j < listBook.size(); j++) {
                if (minPriceOfBook.getPrice() > listBook.get(j).getPrice()) {

                    minPriceOfBook = listBook.get(j);
                    currentIndex = listBook.indexOf(listBook.get(j));
                }
            }
            if (currentIndex != i) {
                listBook.set(currentIndex, listBook.get(i));
                listBook.set(i, minPriceOfBook);
            }
        }
        return listBook;
    }

    public static ArrayList<Book> insertionSort(ArrayList<Book> listBook) {
        for (int i = 1; i < listBook.size(); i++) {
            Book currentBook = listBook.get(i);
            int j = i - 1;
            while ((j >= 0) && listBook.get(j).getPrice() > currentBook.getPrice()) {
                listBook.set(j + 1, listBook.get(j));
                j--;
            }
            listBook.set(j + 1, currentBook);
        }
        return listBook;
    }

    public static double binarySearch(ArrayList<Book> listBook, String name) {
        int low = 0;
        int hight = listBook.size() - 1;
        while (low <= hight) {
            int mid = low + (hight - 1) / 2;
            int result = name.compareTo(listBook.get(mid).getName());
            if (result == 0) {
                return listBook.get(mid).getPrice();
            }
            if (result > 0) {
                low = mid + 1;
            } else
                hight = mid - 1;
        }
        return -1;
    }
}


