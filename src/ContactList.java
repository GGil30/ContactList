// Gabriel Gil
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {
    private ArrayList<Person> contacts;

    public ContactList(){
        contacts = new ArrayList<Person>();
    }

    public ArrayList<Person> getContacts(){
        return this.contacts;
    }

    public void addContact(Person p){
        this.contacts.add(p);
    }

    public static void main(String[] args) {
        ContactList c = new ContactList();
        c.run();
    }

    private void printConsole(){
        System.out.println("Welcome to your Contacts List");
        System.out.println("Please pick from the following menu options");
        System.out.println();
        System.out.println("Menu:");
        System.out.println("1. Add Contact");
        System.out.println("2. List All Contacts By First Name");
        System.out.println("3. List All Contacts By Last Name");
        System.out.println("4. List All Contacts By Phone Number");
        System.out.println("5. List All Students");
        System.out.println("6. Search By First Name");
        System.out.println("7. Search By Last Name");
        System.out.println("8. Search By Phone Number");
        System.out.println("0. Exit");
    }

    public void printContacts(){
       for(int i = 0; i < contacts.size(); i++){
           System.out.println(contacts.get(i));
       }
    }

    public void sort(int sortBy){
        int n = contacts.size();
        Person p;

        if(sortBy == 0) {
            for(int i = 0; i < n-1 ; i++){
                for(int j = 0; j < n - 1 - i; j++){
                    if((contacts.get(j).getFirstName().compareTo(contacts.get(j+1).getFirstName())) > 0) {
                        p = contacts.get(j);
                        contacts.set(j, contacts.get(j + 1));
                        contacts.set(j + 1, p);
                    }
                }
            }
        }

        if(sortBy == 1) {
            for(int i = 0; i < n-1 ; i++){
                for(int j = 0; j < n - 1 - i; j++){
                    if((contacts.get(j).getLastName().compareTo(contacts.get(j+1).getLastName())) > 0) {
                        p = contacts.get(j);
                        contacts.set(j, contacts.get(j + 1));
                        contacts.set(j + 1, p);
                    }
                }
            }
        }

        if(sortBy == 2) {
            for(int i = 0; i < n-1 ; i++){
                for(int j = 0; j < n - 1 - i; j++){
                    if((contacts.get(j).getPhoneNumber().compareTo(contacts.get(j+1).getPhoneNumber())) > 0) {
                        p = contacts.get(j);
                        contacts.set(j, contacts.get(j + 1));
                        contacts.set(j + 1, p);
                    }
                }
            }
        }
    }

    public Person searchByFirstName(String firstName) {
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getFirstName().equals(firstName)){
                return contacts.get(i);
            }
        }
        return null;
    }

    public Person searchByLastName(String lastName) {
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getLastName().equals(lastName)){
                return contacts.get(i);
            }
        }
        return null;
    }

    public Person searchByPhoneNumber(String phoneNumber) {
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getPhoneNumber().equals(phoneNumber)){
                return contacts.get(i);
            }
        }
        return null;
    }

    public void listStudents() {
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i) instanceof Student){
                System.out.println(contacts.get(i));
            }
        }
    }

    public void run(){
        int i;
        Scanner input = new Scanner(System.in);
        while(true){
            printConsole();
            i = input.nextInt();
            input.nextLine();

            if(i == 0) {
                break;
            }
            if(i == 1){
                System.out.println("Select a type of contact to add:");
                System.out.println("1. Student");
                System.out.println("2. Athlete");
                int type = input.nextInt();
                input.nextLine();
                addContactHelper(input, type);
            }

            if(i >=2 && i <=4){
                sort(i-2);
                printContacts();
            }

            if(i == 5){
                listStudents();
            }

            if(i >= 6 && i <= 8){
                String toPrint = searchHelper(input, i-6);
                System.out.println(toPrint);
            }
        }
    }

    public void addContactHelper(Scanner s, int type){
        String firstName;
        String lastName;
        String phoneNumber;
        System.out.println("Please fill in the following information.");
        System.out.println("First Name:");
        firstName = s.nextLine();
        System.out.println("Last Name:");
        lastName = s.nextLine();
        System.out.println("Phone Number:");
        phoneNumber = s.nextLine();
        if(type == 1){
            System.out.println("Grade:");
            int grade = s.nextInt();
            s.nextLine();
            addContact(new Student(firstName, lastName, phoneNumber, grade));
        }
        if(type == 2){
            System.out.println("Sport: ");
            String sport = s.nextLine();
            addContact(new Athlete(firstName, lastName, phoneNumber, sport));
        }
    }

    public String searchHelper(Scanner s, int searchBy) {
        String searchFor;
        Person p;
        if(searchBy< 2){
            System.out.println("Enter a name:");
            searchFor = s.nextLine();
        }
        else{
            System.out.println("Enter a phone number:");
            searchFor = s.nextLine();
        }
        if(searchBy == 0){
            p = searchByFirstName(searchFor);
        }
        else if(searchBy == 1){
            p = searchByLastName(searchFor);
        }
        else{
            p = searchByPhoneNumber(searchFor);
        }

        if(p == null){
            return searchFor + " is not in the list.";
        }
        return p.toString();
    }





}
