import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files
import java.lang.Math;

public class Day4 {

    public static void main(String[] args) {
        int password1 = 152085;
        int password2 = 670283;

        int total = 0;
        int totalPart2 = 0;

        for (int i = password1; i <= password2; i++) {
            if (isValidPassword(i)) {
                total++;
            }
        }

        for (int i = password1; i <= password2; i++) {
            if (isValidPasswordPart2(i)) {
                totalPart2++;
            }
        }


        System.out.println(total);
        System.out.println(totalPart2);

    }

    private static boolean isValidPassword(int password) {
        boolean notDecreasing = true;
        boolean hasDouble = false;

        String stringifiedPassword = Integer.toString(password);

        for (int i = 0; i < stringifiedPassword.length() - 1; i++) {
            if (stringifiedPassword.charAt(i) == stringifiedPassword.charAt(i+1)) {
                hasDouble = true;
            }
            if (stringifiedPassword.charAt(i) > stringifiedPassword.charAt(i+1)) {
                notDecreasing = false;
            }
        }

        return notDecreasing && hasDouble;
    }

    private static boolean isValidPasswordPart2(int password) {
        boolean notDecreasing = true;
        boolean hasDouble = false;

        String stringifiedPassword = Integer.toString(password);

        for (int i = 0; i < stringifiedPassword.length() - 1; i++) {
            if (stringifiedPassword.charAt(i) > stringifiedPassword.charAt(i+1)) {
                notDecreasing = false;
            }
        }

        for (int i = 0; i < stringifiedPassword.length() - 1; i++) {
            if (stringifiedPassword.charAt(i) == stringifiedPassword.charAt(i + 1)) {
                if (i >= stringifiedPassword.length() - 2) {
                    hasDouble = true;
                } else if (stringifiedPassword.charAt(i + 1) != stringifiedPassword.charAt(i + 2)) {
                    hasDouble = true;
                } else {
                    for (int j = i+1; j < stringifiedPassword.length() - 1; j++) {
                        if (stringifiedPassword.charAt(i) == stringifiedPassword.charAt(j)) {
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return notDecreasing && hasDouble;
    }
}
