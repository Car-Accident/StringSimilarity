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
    }

    @Test
    void returnFullScoreWhenLengthMatched() {
        assertEquals(60, new Similarity("", "").getLengthScore());
        assertEquals(60, new Similarity("ASD", "DSA").getLengthScore());
        assertEquals(60, new Similarity("XCVPSDFK", "SPQWMFPD").getLengthScore());
        assertEquals(60, new Similarity("Z", "A").getLengthScore());
    }

    private void assertIllegalArgumentException(Similarity similarity) {
        assertThrows(IllegalArgumentException.class, () -> {similarity.getLengthScore();});
    }
}