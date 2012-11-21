
package ar.com.aleatoria.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author facuq
 */
public class StringUtility {

    private static final Log log = LogFactory.getLog(StringUtility.class);

    /**
     * @param values
     * @param separator
     * @return all the strings of values concatenated using the given separator
     */
    public static String concatenateWith(List<?> values, String separator) {
        String value = "";
        for (int j = 0; j < values.size(); j++) {
            value += values.get(j);
            if (j != values.size() - 1) {
                value += separator;
            }
        }
        return value;
    }

    /**
     * @param values
     * @param regex
     * @param replacement
     * @return all the strings of the list values were all occurrences of regex
     *         were replaced for replacement
     */
    public static ArrayList<String> replaceAllWith(ArrayList<String> values, String regex,
            String replacement) {
        ArrayList<String> result = new ArrayList<String>();
        for (int j = 0; j < values.size(); j++) {
            String value = values.get(j);

            value = value.replaceAll(regex, replacement);
            result.add(value);
        }
        return result;
    }

    public static String bufferedReaderToString(BufferedReader reader) {
        String thisLine;
        String output = "";
        try {
            while ((thisLine = reader.readLine()) != null) {
                output += thisLine;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return output;
    }

}
