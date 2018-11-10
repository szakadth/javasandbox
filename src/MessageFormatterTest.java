import com.sun.org.apache.xerces.internal.util.MessageFormatter;

import java.text.MessageFormat;

/**
 * Created by bogrea on 2018.07.19..
 */
public class MessageFormatterTest {
    public static void main( String[] args ) {
        System.out.println(MessageFormat.format("{0}, ''almafa'' ", "alma"));
    }
}
