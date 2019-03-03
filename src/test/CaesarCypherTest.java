import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCypherTest {

    CaesarCipher cc = new CaesarCipher();

    @Test
    public void testEncrypt() {
        assertEquals("Ymj rtwj N qjfws fgtzy ujtuqj, ymj rtwj N qnpj rd itl.", cc.encrypt("The more I learn about people, the more I like my dog.", 5));
        assertEquals("Zcuypc md dyjqc ilmujcbec; gr gq kmpc bylecpmsq rfyl gelmpylac.", cc.encrypt("Beware of false knowledge; it is more dangerous than ignorance.", 24));
    }

    @Test
    public void testEncryptTwoKeys() {
        assertEquals("Zbzbzbz", cc.encryptTwoKeys("Aaaaaaa", 25, 1));
        assertEquals("Aeaeaea", cc.encryptTwoKeys("Ccccccc", 24, 2));
        assertEquals("Run like wild to beat the wind", cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
    }
}
