import java.util.Scanner;

public class LibraryCollection {

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Book[] books = new Book[100];

        int bookstoreBooksCount = 0;
        int libraryBooksCount = 0;

        System.out.println("Welcome to the Bookstore!");

        String inputFromUser = "yes";
        do {
            System.out.println("Would you like to create a book object? (yes/no)");
            inputFromUser = sc.nextLine();

            if (inputFromUser.equalsIgnoreCase("no")){
                break;
            }

            if (!inputFromUser.equalsIgnoreCase("yes")) {
                System.out.printf("I’m sorry but %s isn’t a valid answer. Please enter either yes or no:\n", inputFromUser);
                inputFromUser = sc.nextLine();
            }

            if (inputFromUser.equalsIgnoreCase("no")){
                break;
            }

            System.out.println("Please enter the author, title and the isbn of the book separated by / :");
            String bookInfo = sc.nextLine();
            String[] bookstoreBookInfoArray = bookInfo.split("/");

            String author = bookstoreBookInfoArray[0];
            String title = bookstoreBookInfoArray[1];
            String isbn = bookstoreBookInfoArray[2];

            System.out.println("Got it!");
            System.out.println("Now, tell me if the book is a bookstore book or a library book (BB/LB) (enter BB for bookstore book and LB for library book)");
            String bookType = sc.nextLine();

            while(!bookType.equalsIgnoreCase("BB") && !bookType.equalsIgnoreCase("LB")){
                System.out.println("Oops! That's not a valid entry. Please try again: ");
                bookType = sc.nextLine();
            }

            System.out.println("Got it!");

            if (bookType.equalsIgnoreCase("BB")){
                System.out.println("Please enter the list price of " + title + " by " + author + ":");
                double listPrice = sc.nextDouble();
                sc.nextLine();

                System.out.println("Is it on sale? (y/n)");
                String sale = sc.nextLine();

                boolean onSale = false;
                double discountPrice = 0;
                if(sale.equals("y")){
                    System.out.println("Deduction percentage: ");
                    discountPrice = sc.nextDouble();
                    sc.nextLine();
                    onSale = true;
                }

                System.out.println("Got it!");
                books[bookstoreBooksCount] = new BookstoreBook(author, title, isbn, listPrice, onSale, discountPrice);
                System.out.println("Here is your bookstore book information: ");
                System.out.println(books[bookstoreBooksCount].toString());
                bookstoreBooksCount++;

            } else {
                books[libraryBooksCount] = new LibraryBook(author, title, isbn);
                System.out.println("Here is your library book information: ");
                System.out.println(books[libraryBooksCount].toString());
                libraryBooksCount++;
            }
        } while(inputFromUser.equalsIgnoreCase("yes"));

        System.out.println("Here are all your books...");
        System.out.println();
        System.out.printf("Bookstore Books (%d)%n", bookstoreBooksCount);
        System.out.println();

        for(int i = 0; i < bookstoreBooksCount; i++){
            System.out.println(books[i].toString());
        }
        System.out.println();
        System.out.printf("Library Books (%d)%n", libraryBooksCount);
        System.out.println();

        for(int i = 0; i<libraryBooksCount; i++) {
            System.out.println(books[i].toString());
        }
        sc.close();
    }
}

abstract class Book {
    String author;
    String title;
    String isbn;

    protected Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public abstract String toString();
}

class BookstoreBook extends Book {
    double bookPrice;
    boolean isBookSale;
    double discount;

    public BookstoreBook(String author, String title, String isbn, double bookPrice, boolean isBookOnSale, double discount) {
        super(author, title, isbn);
        this.bookPrice = bookPrice;
        this.isBookSale = isBookOnSale;
        this.discount = discount;
    }

    public double getPriceAfterDiscount() {
        return (bookPrice - (bookPrice * this.discount / 100));
    }

    @Override
    public String toString(){
        return "[" + isbn + "-" + title + " by " + author + ", $" + bookPrice + " listed for $" + getPriceAfterDiscount() + "]";
    }
}

class LibraryBook extends Book {
    String callNum; 
    int floorNumber;

    public LibraryBook(String author, String title, String isbn) {
        super(author, title, isbn);
        floorNumber = (int) (Math.random() * 99 + 1);
        this.callNum = setUpCallNumber();
    }

    public String setUpCallNumber() {
        if (floorNumber < 10) {
            return "0" + floorNumber + "." + author.substring(0, 3) + "." + isbn.charAt(isbn.length() - 1);
        } else {
            return floorNumber + "." + author.substring(0, 3) + "." + isbn.charAt(isbn.length() - 1);
        }
    }

    @Override
    public String toString() {
        return "[" + isbn + "-" + title + " by " + author + "-" + callNum + "]";
    }
}
