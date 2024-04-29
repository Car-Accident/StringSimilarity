import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityTest {

    @Test
    void throwIllegalArgumentExceptionWhenNullString() {
        assertIllegalArgumentException(new Similarity(null, null));
        assertIllegalArgumentException(new Similarity(null, "DSA"));
        assertIllegalArgumentException(new Similarity("ASD", null));
        assertIllegalArgumentException(new Similarity("!ICd", "ADS"));
        assertIllegalArgumentException(new Similarity("ADS", "!ICd"));
        assertIllegalArgumentException(new Similarity("1288dCD", "DSPD"));
        assertIllegalArgumentException(new Similarity("DSPD", "1288dCD"));
        assertIllegalArgumentException(new Similarity("CLSOD", ""));
        assertIllegalArgumentException(new Similarity("", "C"));
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

    private void assertIllegalArgumentException(Similarity similarity) {
        assertThrows(IllegalArgumentException.class, () -> {similarity.getLengthScore();});
    }
}