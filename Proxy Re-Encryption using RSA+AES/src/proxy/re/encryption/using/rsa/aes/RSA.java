package proxy.re.encryption.using.rsa.aes;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Divyansh
 */

public class RSA
{
    public BigInteger p, q;
    public BigInteger n;
    public BigInteger PhiN;
    public BigInteger e, d;
    public RSA()
    {
        Initialize();
    }
    
    //Generate e and d
    //e:- Public Key
    //d:- Private Key
    public void Initialize()
    {
        
        int SIZE = 512;
        // p and q are 2 154 digit Prime Numbers which are used in the generation of RSA Keys
        p=new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519074969");
        q=new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328586993");
        n = p.multiply(q);
        PhiN = p.subtract(BigInteger.valueOf(1));
        PhiN = PhiN.multiply( q.subtract( BigInteger.valueOf(1)));
        do
        {
            e = new BigInteger(2*SIZE, new Random());
        }
        while( (e.compareTo(PhiN) != 1)||(e.gcd(PhiN).compareTo(BigInteger.valueOf(1)) != 0));
        d = e.modInverse(PhiN);
    }
    
    public BigInteger encrypt(BigInteger plaintext)
    {
        return plaintext.modPow(e, n);
    }
    
    public BigInteger decrypt(BigInteger ciphertext)
    {
        return ciphertext.modPow(d, n);
    }
}