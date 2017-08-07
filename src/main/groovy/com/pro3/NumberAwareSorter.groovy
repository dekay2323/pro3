package com.pro3

import com.pro3.domain.aux.LineItem

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by demian on 2017-08-07.
 */
public class NumberAwareSorter implements Comparator<LineItem> {
    public static final NumberAwareSorter INSTANCE =
            new NumberAwareSorter();

    private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");

    private NumberAwareStringComparator() {
    }

    public int compare(LineItem o1, LineItem o2) {
        CharSequence s1 = o1.getCode();
        CharSequence s2 = o2.getCode();
        Matcher m1 = PATTERN.matcher(s1);
        Matcher m2 = PATTERN.matcher(s2);

        // The only way find() could fail is at the end of a string
        while (m1.find() && m2.find()) {
            // matcher.group(1) fetches any non-digits captured by the
            // first parentheses in PATTERN.
            int nonDigitCompare = m1.group(1).compareTo(m2.group(1));
            if (0 != nonDigitCompare) {
                return nonDigitCompare;
            }

            // matcher.group(2) fetches any digits captured by the
            // second parentheses in PATTERN.
            if (m1.group(2).isEmpty()) {
                return m2.group(2).isEmpty() ? 0 : -1;
            } else if (m2.group(2).isEmpty()) {
                return +1;
            }

            BigInteger n1 = new BigInteger(m1.group(2));
            BigInteger n2 = new BigInteger(m2.group(2));
            int numberCompare = n1.compareTo(n2);
            if (0 != numberCompare) {
                return numberCompare;
            }
        }

        // Handle if one string is a prefix of the other.
        // Nothing comes before something.
        return m1.hitEnd() && m2.hitEnd() ? 0 :
                m1.hitEnd()                ? -1 : +1;
    }
}
