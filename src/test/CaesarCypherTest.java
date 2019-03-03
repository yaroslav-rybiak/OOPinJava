import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCypherTest {

    @Test
    public void testEncrypt() {
        CaesarCipher cc1 = new CaesarCipher(5);
        assertEquals("Ymj rtwj N qjfws fgtzy ujtuqj, ymj rtwj N qnpj rd itl.", cc1.encrypt("The more I learn about people, the more I like my dog."));
        CaesarCipher cc2 = new CaesarCipher(24);
        assertEquals("Zcuypc md dyjqc ilmujcbec; gr gq kmpc bylecpmsq rfyl gelmpylac.", cc2.encrypt("Beware of false knowledge; it is more dangerous than ignorance."));
    }

    @Test
    public void testEncryptTwoKeys() {
        CaesarCipher cc1 = new CaesarCipher(25, 1);
        assertEquals("Zbzbzbz", cc1.encryptTwoKeys("Aaaaaaa"));
        CaesarCipher cc2 = new CaesarCipher(24, 2);
        assertEquals("Aeaeaea", cc2.encryptTwoKeys("Ccccccc"));
        CaesarCipher cc3 = new CaesarCipher(24, 6);
        assertEquals("Run like wild to beat the wind", cc3.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
    }
}
