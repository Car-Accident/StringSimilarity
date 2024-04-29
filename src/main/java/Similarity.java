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
            if (stringArray[0].length() > stringArray[1].length()) {
                double gap = stringArray[0].length() - stringArray[1].length();
                return (int) ((1 - gap / stringArray[1].length()) * 60);
            } else {
                double gap = stringArray[1].length() - stringArray[0].length();
                return (int) ((1 - gap / stringArray[0].length()) * 60);
            }
        }
    }

    private void checkStringValidation() {
        for (String str : stringArray) {
            if (str == null) {
                throw new IllegalArgumentException("String should not be null");
            }

            if (str.length() == 0) {
                throw new IllegalArgumentException("String should not be empty");
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
