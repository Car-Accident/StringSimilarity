public class Similarity {
    private String[] stringArray = new String[2];

    public Similarity(String str1, String str2) {
        stringArray[0] = str1;
        stringArray[1] = str2;
    }

    public int getLengthScore() {
        checkStringValidation();

        if (stringArray[0].length() == stringArray[1].length()) {
            return 60;
        } else {
            return 0;
        }
    }

    private void checkStringValidation() {
        for (String str : stringArray) {
            if (str == null) {
                throw new IllegalArgumentException("String should not be null");
            }

            for (char ch : str.toCharArray()) {
                if (isAlphabet(ch) == false) {
                    throw new IllegalArgumentException("String should be alphabet");
                }
            }
        }
    }

    private static boolean isAlphabet(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}
