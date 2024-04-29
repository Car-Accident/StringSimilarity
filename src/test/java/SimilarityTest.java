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

    private void assertIllegalArgumentException(Similarity similarity) {
        assertThrows(IllegalArgumentException.class, () -> {similarity.getLengthScore();});
    }
}