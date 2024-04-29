import java.util.*;

public class Similarity {
    public static final int ALPHABET_MAX_SCORE = 40;
    public static final int LENGTH_MAX_SCORE = 60;
    private String[] stringArray = new String[2];

    public Similarity(String str0, String str1) {
        stringArray[0] = str0;
        stringArray[1] = str1;
        checkStringValidation();
    }

    public int getLengthScore() {
        if (isStringLengthMatched()) {
            return LENGTH_MAX_SCORE;
        } else if (isStringLengthDoublyDifferent()) {
            return 0;
        } else {
            return calcPartialScore();
        }
    }

    public int getAlphabetScore() {
        String upperString1 = stringArray[0].toUpperCase();
        String upperString2 = stringArray[1].toUpperCase();
        String mergeString = upperString1 + upperString2;

        ArrayList<Character> characterArrayList = getDistinctCharArrayList(mergeString);

        int totalCnt = characterArrayList.size();
        int sameCnt = calcSameCount(characterArrayList, upperString1, upperString2);

        return (int)((double)sameCnt / totalCnt * ALPHABET_MAX_SCORE);
    }

    private boolean isStringLengthMatched() {
        return stringArray[0].length() == stringArray[1].length();
    }

    private boolean isStringLengthDoublyDifferent() {
        if (stringArray[0].length() >= stringArray[1].length()*2)   return true;
        if (stringArray[1].length() >= stringArray[0].length()*2)   return true;
        return false;
    }

    private int calcPartialScore() {
        int stringLength1 = stringArray[0].length();
        int stringLength2 = stringArray[1].length();
        if (stringLength1 > stringLength2) {
            double gap = stringLength1 - stringLength2;
            return (int) ((1 - gap / stringLength2) * LENGTH_MAX_SCORE);
        } else {
            double gap = stringLength2 - stringLength1;
            return (int) ((1 - gap / stringLength1) * LENGTH_MAX_SCORE);
        }
    }

    private void checkStringValidation() {
        for (String str : stringArray) {
            checkNullString(str);
            checkEmptyString(str);
            checkAlphabetString(str);
        }
    }

    private static void checkNullString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String should not be null");
        }
    }

    private static void checkEmptyString(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("String should not be empty");
        }
    }

    private static void checkAlphabetString(String str) {
        for (char ch : str.toCharArray()) {
            if (isAlphabet(ch) == false) {
                throw new IllegalArgumentException("String should be alphabet");
            }
        }
    }

    private static boolean isAlphabet(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private static ArrayList<Character> getDistinctCharArrayList(String mergeString) {
        ArrayList<Character> characterArrayList = new ArrayList<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (hasCharacter(mergeString, ch)) {
                characterArrayList.add(new Character(ch));
            }
        }
        return characterArrayList;
    }

    private static int calcSameCount(ArrayList<Character> characterArrayList, String string1, String string2) {
        int sameCnt = 0;
        for (char ch : characterArrayList) {
            if (hasCharacter(string1, ch) && string2.indexOf(ch) >= 0) {
                sameCnt++;
            }
        }
        return sameCnt;
    }

    private static boolean hasCharacter(String string, char ch) {
        return string.indexOf(ch) >= 0;
    }
}
