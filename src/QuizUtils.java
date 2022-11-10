import java.util.Random;

/**
 * Static methods generally useful in generating quiz data and incorrect answers
 * to questions.
 */

public class QuizUtils {

    /**
     * Generate a random string from a specified range of characters, with a specified minimum length.
     * + some range.
     * @param minLen The generated string will be at least this long.
     * @param sizeRng The final string will be between {@code minLen} and {@code minLen + sizeRng} in
     *                length ({@code sizeRng} can be zero - in which case the string will be {@code minLen} long).
     * @param low The low end of the range of characters used in the string. If {@code low > high},
     *           {@code low} and {@code high} will be swapped.
     * @param high The low end of the range of characters used in the string. If {@code low > high},
     *           {@code low} and {@code high} will be swapped.
     * @return The randomly generated string of size between {@code minlen} and {@code minLen + sizeRng} made up of
     *              characters between {@code low} and {@code high} inclusive.
     *
     *  <h3>Example</h3>
     *  <p>{@code String example = genRandomString(50, 10, 'a', 'z');} - a string between 50 and 60 characters long
     *  made up of lower-case latin characters.</p>
     */

    /*Controls the range over which random permutations of strings will be attempted in
     * {@code permuteString} - make it smaller at your peril!
     */
    private static final double MIN_PERMUTATION_RNG = 0.2;

    public static final String genRandomString(final int minLen, final int sizeRng,
                                               char low, char high) {

        //Create Random object and ensure low >= high
        Random random = new Random();
        if (low > high) {
            final char temp = low;
            low = high;
            high = temp;
        }

        //Generate target string length and prep buffer
        final int targetStringLength = random.nextInt(sizeRng) + minLen;
        StringBuilder buffer = new StringBuilder(targetStringLength);

        //use the low and high characters to set the random generation range
        final int leftLmt = low;
        final int limLen = ((int) high - low);

        //Generate and return the string
        for (int i = 0; i < targetStringLength; i++) {
            final int randomLimitedInt = leftLmt + (int) (random.nextFloat() * (limLen));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * Make random permutations to a string - this is useful when generating <strong>wrong</strong> answers that
     * must be visibly similar to the correct answer - e.g. by swapping two characters near the middle.
     *
     * <h2>NOTE</h2>
     * <p>{@code permuteString} goes to some trouble to avoid ending up in an infinite loop - "randomly" trying
     * to swap characters when the provided parameters don't allow that to happen because they don't give
     * enough freedom to make changes - by returning the original string if no permutations are possible.
     * <strong>HOWEVER</strong> it is possible to force it to effectively do this when generating wrong answers - if
     * the supplied parameters do not allow enough different incorrect answers to be generated.</p>
     * @param dataString The string to be permuted
     * @param location The centre point of the change expressed as a fraction 0.0 to 1.0 - 0.0 represents the
     *                 start of the string; 0.5 the middle; 1.0 the end
     * @param range The amount of variation from {@code location} allowed - so 0.5 means +/1 half of the string. Must
     *              be at least MIN_PERMUTATION_RNG = 0.2
     * @param permutations The number of permutations attempted - note these are NOT guaranteed to be unique and picking
     *                     a small value for {@code range} will make it less likely they are, especially if the string
     *                     to permute is relatively short.
     * @return The permuted or original string - the original string is returned if no permutations are possible, or the
     * number of permutations requested is negative.
     *
     * <h2>Examples</h2>
     * {@code String val = permuteString(myString, 0.5, 0.3, 5); //5 permutations between 20% from the start & end of myString}
     * {@code String val = permuteString(myString, 1.0, 0.2, 1); //1 permutation within 20% of the end of myString}
     */
    public  final static String permuteString(final String dataString, final double location,
                                              final double range, final int permutations) {

        /*We only make changes if the number of permutations is +ve and if the range is large enough to avoid
        a high chance that no permutations will be possible
        */
        if (permutations < 1 || range < 0.2) {
            return dataString;
        }

        Random rnd = new Random();

        /* Generate lower and upper bounds - lowRange and highRange - between 0 and dataString.length() -1, based on
        the values of location and range
         */
        final int strLen = dataString.length();
        final long rawLowRange = Math.min(Math.round((location - range) * strLen), strLen - 1);
        final int lowRange = (int)(rawLowRange < 0 ? 0 : rawLowRange);
        final long rawHighRange = Math.round((location + range) * strLen);
        final int highRange = (int)(rawHighRange > strLen - 1 ? strLen - 1 : rawHighRange);
        final int randomRng = (int)(highRange - lowRange);
        System.out.println(lowRange + " " + randomRng);
        /*
        If the range of characters is not at least two, then no permutations are possible so return original string
         */
        if (randomRng < 2) {
            System.out.println("random range too small");
            return dataString;
        }

        //Turn String into char array and make permutation swaps
        char[] strList = dataString.toCharArray();
        for (int i = 0; i < permutations; i++) {

            /*Generate two random locations to swap, ensuring they are not the same
            Note that if the range of characters to swap is not at least 1, we will
            have returned the original string above - otherwise this could be an
            infinite loop
             */
            int loc1 = rnd.nextInt(randomRng) + lowRange;
            int loc2;
            do {
                loc2 = rnd.nextInt(randomRng) + lowRange;
            } while (loc2 == loc1);

            //perform the swap.
            char temp = strList[loc1];
            strList[loc1] = strList[loc2];
            strList[loc2] = temp;
        }

        return String.valueOf(strList);
    }
}
