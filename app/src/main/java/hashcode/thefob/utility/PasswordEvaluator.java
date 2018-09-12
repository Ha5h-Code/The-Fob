package hashcode.thefob.utility;

import java.util.regex.Pattern;



public class PasswordEvaluator
{
    public static boolean hasCapital(String password)
    {
        return Pattern.matches("(.*[A-Z].*)",password);
    }

    public static boolean hasSimple(String password)
    {
        return Pattern.matches("(.*[a-z].*)",password);
    }

    public static boolean hasDigits(String password)
    {
        return Pattern.matches("(.*\\d.*)",password);
    }

    public static boolean checkLength(String password,int l)
    {
        return Pattern.matches("[a-zA-Z0-9-+_!@#$%^&*.,?]{"+l+",}",password);
    }

    public static boolean hasSpecialCharacters(String password)
    {
        return Pattern.matches("(.*[-+_!@#$%^&*.,?].*)",password);
    }

    public static boolean checkAll(String password,String confirmPassword,int l)
    {
        return (password.equals(confirmPassword) && hasCapital(password) && hasSimple(password) && hasDigits(password) && hasSpecialCharacters(password) && checkLength(password,l));
    }

}
