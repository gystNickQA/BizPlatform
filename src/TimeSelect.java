import java.time.LocalDate;


/**
 * Created by nkorchyk on 03.12.15.
 */
public class TimeSelect {
    public static void main(String[] args){
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        System.out.println(dayOfWeek);
    }
}
