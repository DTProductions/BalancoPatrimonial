package helpers;

public class ConversaoSegura {
    public static Double lerDouble(String str){
        try {
            return Double.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer lerInt(String str){
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
}
