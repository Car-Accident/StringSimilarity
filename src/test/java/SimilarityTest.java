import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityTest {

    @Test
    void throwIllegalArgumentExceptionWhenNullString() {
        assertIllegalArgumentException(null, null);
        assertIllegalArgumentException(null, "DSA");
        assertIllegalArgumentException("ASD", null);
        assertIllegalArgumentException("!ICd", "ADS");
        assertIllegalArgumentException("ADS", "!ICd");
        assertIllegalArgumentException("1288dCD", "DSPD");
        assertIllegalArgumentException("DSPD", "1288dCD");
        assertIllegalArgumentException("CLSOD", "");
        assertIllegalArgumentException("", "C");
    }

    @Test
    void returnFullScoreWhenLengthMatched() {
        assertEquals(60, new Similarity("ASD", "DSA").getLengthScore());
        assertEquals(60, new Similarity("XCVPSDFK", "SPQWMFPD").getLengthScore());
        assertEquals(60, new Similarity("Z", "A").getLengthScore());
    }

    @Test
    void returnPartialScoreWhenLengthUnMatched() {
        assertEquals(20, new Similarity("AAABB", "BAA").getLengthScore());
        assertEquals(20, new Similarity("BAA", "AAABB").getLengthScore());
        assertEquals(48, new Similarity("SPEOFK", "DFSEW").getLengthScore());
        assertEquals(51, new Similarity("CPpPAJKF", "WEPQOWO").getLengthScore());
        assertEquals(51, new Similarity("WEPQOWO", "CPpPAJKF").getLengthScore());
    }

    @Test
    void returnZeroScoreWhenLengthDoublyDifferent() {
        assertEquals(0, new Similarity("ASD", "DSADSA").getLengthScore());
        assertEquals(0, new Similarity("ASD", "DSADSACISJD").getLengthScore());
        assertEquals(0, new Similarity("CVISDJF", "AD").getLengthScore());
    }

    @Test
    void returnFullScoreWhenAlphabetMatched() {
        assertEquals(40, new Similarity("ABC", "BCA").getAlphabetScore());
        assertEquals(40, new Similarity("CLSIDJ", "DDDCJSILLL").getAlphabetScore());
    }

    @Test
    void returnZeroScoreWhenNoAlphabetMatched() {
        assertEquals(0, new Similarity("ABC", "DEF").getAlphabetScore());
        assertEquals(0, new Similarity("DWPKDJ", "ZLOE").getAlphabetScore());
    }

    @Test
    void returnPartialScoreWhenSomeAlphabetMatched() {
        assertEquals(6, new Similarity("ABC", "CDEF").getAlphabetScore());
    }

    private void assertIllegalArgumentException(String str1, String str2) {
        assertThrows(IllegalArgumentException.class, () -> {new Similarity(str1, str2);});
    }
}