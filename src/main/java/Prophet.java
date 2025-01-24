import java.util.Scanner;
public class Prophet {
    public static void main(String[] args) {
        String horizontalLine = "--------------------------------------------------------------------------------\n";
        System.out.println(horizontalLine
                + "Hi! Welcome to the Daily Prophet! You can call me Prophet for short.\n"
                + "What can I do for you?\n"
                + horizontalLine);
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        while (!str.equals("bye")) {
            System.out.println(horizontalLine + str + "\n" + horizontalLine);
            str = sc.next();
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again!\n" + horizontalLine);
    }
}
