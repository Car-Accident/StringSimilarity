public class Similarity {
    private String[] stringArray = new String[2];

    public Similarity(String str0, String str1) {
        stringArray[0] = str0;
        stringArray[1] = str1;
    }

    public int getLengthScore() {
        checkStringValidation();

        if (isStringLengthMatched()) {
            return 60;
        } else if (isStringLengthDoublyDifferent()) {
            return 0;
        } else {
            return calcPartialScore();
        }
    }

    private int calcPartialScore() {
        int stringLength1 = stringArray[0].length();
        int stringLength2 = stringArray[1].length();
        if (stringLength1 > stringLength2) {
            double gap = stringLength1 - stringLength2;
            return (int) ((1 - gap / stringLength2) * 60);
        } else {
            double gap = stringLength2 - stringLength1;
            return (int) ((1 - gap / stringLength1) * 60);
        }
    }

    private boolean isStringLengthMatched() {
        return stringArray[0].length() == stringArray[1].length();
    }

    private boolean isStringLengthDoublyDifferent() {
        if (stringArray[0].length() >= stringArray[1].length()*2)   return true;
        if (stringArray[1].length() >= stringArray[0].length()*2)   return true;
        return false;
    }

    private void checkStringValidation() {
        for (String str : stringArray) {
            checkNullString(str);
            checkEmptyString(str);
            checkAlphabetString(str);
        }
    }

    private static void checkAlphabetString(String str) {
        for (char ch : str.toCharArray()) {
            if (isAlphabet(ch) == false) {
                throw new IllegalArgumentException("String should be alphabet");
            }
        }
    }

    private static void checkEmptyString(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("String should not be empty");
        }
    }

    private static void checkNullString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String should not be null");
        }
    }

    private static boolean isAlphabet(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}
