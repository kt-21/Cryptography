import org.junit.Test;

import java.util.Scanner;

public class CryptoTest {

    @Test
    public void testCrypto(){

        KTsai_Cryptography c1 = new KTsai_Cryptography(new KTsai_UnstoppableCrypt());
        KTsai_Cryptography c2 = new KTsai_Cryptography(new KTsai_VigenereCrypt());
        KTsai_Cryptography c3 = new KTsai_Cryptography(new KTsai_PigLatin());
    }
}
