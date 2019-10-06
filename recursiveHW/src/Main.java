
/**
 * Be sure to read the README file.
 */
public class Main {
        public static void main (String[] args) {

        System.out.println("fib(0)  : " + fib(0));
        System.out.println("fib(1)  : " + fib(1));
        System.out.println("fib(2)  : " + fib(2));
        System.out.println("fib(5)  : " + fib(5));
        System.out.println("fib(10) : " + fib(10));

        char[] a = {'a', 'b', 'c'};
        System.out.println("max_letter({'a', 'b', 'c'}, 0)  : " + max_letter(a, 0));
        char[] b = {'c', 'b', 'a'};
        System.out.println("max_letter({'c', 'b', 'a'}, 0)  : " + max_letter(b, 0));
        char[] c = {'b', 'c', 'a'};
        System.out.println("max_letter({'b', 'c', 'a'}, 0)  : " + max_letter(c, 0));
        char[] d = {'a', 'a', 'a'};
        System.out.println("max_letter({'a', 'a', 'a'}, 0)  : " + max_letter(d, 0));
        char[] e = {'b', 'c', 'b'};
        System.out.println("max_letter({'b', 'c', 'b'}, 0)  : " + max_letter(e, 0));

        System.out.println("only_odds(0)  : " + only_odds(0));
        System.out.println("only_odds(1)  : " + only_odds(1));
        System.out.println("only_odds(10) : " + only_odds(10));
        System.out.println("only_odds(11) : " + only_odds(11));

        System.out.println("palindrome(\"a\")   : " + palindrome("a"));
        System.out.println("palindrome(\"ab\")  : " + palindrome("ab"));
        System.out.println("palindrome(\"abc\") : " + palindrome("abc"));

        System.out.println("triplicate(\"a\")   : " + triplicate("a"));
        System.out.println("triplicate(\"ab\")  : " + triplicate("ab"));
        System.out.println("triplicate(\"abc\") : " + triplicate("abc"));
    }

        /**
         * Given an array of char, find the largest character and return that item.
         */
        public static char max_letter ( char[] a, int index){
        if (index == a.length - 1) {
            return a[index];
        }
        if (a[index] > a[index + 1] == true) {
            char temp = a[index + 1];
            a[index + 1] = a[index];
            a[index] = temp;
            return max_letter(a, index + 1);
        } else {
            return max_letter(a, index + 1);
        }
    }

        /**
         * Accept a string and produce a string where each letter in the original
         * string is repeated three times.
         */
        public static String triplicate (String str) {
            if (str.length() <= 1) {
                return str + str + str;
            }
            String first = str.substring(0, 1);
            String rest = str.substring(1, str.length());
            str = triplicate(first);
            return str + triplicate(rest);
        }

        /**
         * Accept a string and create a palindrome, where the letters are mirrored.
         */
        
        public static String palindrome (String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            String first = Character.toString(str.charAt(0));
            String rest = palindrome(str.substring(1));
            return first + rest + first;
        }
    }

        /**
         * Print only the odd values in the range 0 thru the pass parameter.
         */
        public static String only_odds (int value) {
            if (value <= 0) {
                return "";
            } else {
                if (value % 2 == 1) {
                    return only_odds(value - 2) + " " + Integer.toString(value);
                } else {
                    return only_odds(value  - 1);
                }
            }
        }



        /**
         * Implements the fibonacci sequence from the book provided code.
         */
        public static int fib (int termIndex){
        int result = 0;
        if (termIndex == 0) {
            result = 0;
        } else if (termIndex == 1) {
            result = 1;
        } else {
            result = fib(termIndex - 1) + fib(termIndex - 2);
        }
        return result;
    }
}

