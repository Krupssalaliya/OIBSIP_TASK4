import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class UserAuthenticationSystem {
    private Map<String, String> userDatabase; // In-memory user database
    private Map<Integer, String> mcqs; // Sample multiple-choice questions
    private Timer timer;

    public UserAuthenticationSystem() {
        userDatabase = new HashMap<>();
        // Hardcoded user data (username, password)
        userDatabase.put("user1", "password1");
        userDatabase.put("user2", "password2");

        mcqs = new HashMap<>();
        // Sample multiple-choice questions (MCQs)
        mcqs.put(1, "What is the term used to describe a state of deep inner peace and tranquility often sought through meditation and spiritual practices?");
        mcqs.put(2, "Which spiritual tradition emphasizes the concept of karma, the belief that actions have consequences that affect future lives?");
        mcqs.put(3, "what term is used to describe the cycle of birth, death, and rebirth?");
        mcqs.put(4, "Who founded the philosophy of Transcendentalism ?");
        mcqs.put(5, "What year did the Titanic sink?");

        timer = new Timer();
    }

    public boolean login(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public void updateProfile(String username, String newUsername) {
        if (userDatabase.containsKey(username)) {
            String password = userDatabase.remove(username); // Remove old entry
            userDatabase.put(newUsername, password); // Add new entry
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public void updatePassword(String username, String newPassword) {
        if (userDatabase.containsKey(username)) {
            userDatabase.put(username, newPassword); // Update password
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public void takeExam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the online examination system!");
        System.out.println("Please answer the following multiple-choice questions:");

        // Display and answer MCQs
        for (Map.Entry<Integer, String> entry : mcqs.entrySet()) {
            System.out.println("\nQuestion " + entry.getKey() + ": " + entry.getValue());
            System.out.println("A. Option A");
            System.out.println("B. Option B");
            System.out.println("C. Option C");
            System.out.println("D. Option D");
            System.out.print("Your answer (A/B/C/D): ");
            String answer = scanner.next().toUpperCase();
            System.out.println("Your answer for Question " + entry.getKey() + ": " + answer);
        }

        // Auto-submit exam after timer expires
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Exam auto-submitted.");
                // Add auto-submission logic here
            }
        };

        // Set the timer for 10 minutes (600,000 milliseconds)
        timer.schedule(task, 600000);
    }

    public static void main(String[] args) {
        UserAuthenticationSystem authSystem = new UserAuthenticationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Update Password");
            System.out.println("4. Take Exam");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    if (authSystem.login(username, password)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String usernameToUpdate = scanner.next();
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.next();
                    authSystem.updateProfile(usernameToUpdate, newUsername);
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    String usernameToUpdatePass = scanner.next();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.next();
                    authSystem.updatePassword(usernameToUpdatePass, newPassword);
                    break;
                case 4:
                    authSystem.takeExam();
                    break;
                case 5:
                    System.out.println("Logged out successfully!");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
