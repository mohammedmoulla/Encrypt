package BlowFish;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class BlowFishEnc {
	public String getKey(String strkey) {
		//create a key
		SecretKeySpec  key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
		 /* Get key in encoding format */
		  byte encoded[] = key.getEncoded();

		  /*
		   * Encodes the specified byte array into a String using Base64 encoding
		   * scheme
		   */
		  String encodedKey = Base64.getEncoder().encodeToString(encoded);
		 return encodedKey;
	}
	public static String encryptBlowfish(String to_encrypt, String strkey) {
		  try {
//create a key
		    SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
//create a cipher based upon blowfish
		     Cipher cipher = Cipher.getInstance("Blowfish");
//initialise cipher with secret key, bind them together
		     cipher.init(Cipher.ENCRYPT_MODE, key);
//encrypting the text and return it
		     String result="b#";
		     String string=new String(cipher.doFinal(to_encrypt.getBytes()));
		     result+=string;
		     return result;
		  } catch (Exception e) { return null; }
		}
	public static String decryptBlowfish(String to_decrypt, String strkey) {
		  try {
			  to_decrypt=to_decrypt.substring(2,to_decrypt.length());
//get the key and create it
		     SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
//create the cipher
		     Cipher cipher = Cipher.getInstance("Blowfish");
//initialise cipher with the secret key
		     cipher.init(Cipher.DECRYPT_MODE, key);
//decrypting the text and return it
		     byte[] decrypted = cipher.doFinal(to_decrypt.getBytes());
		     return new String(decrypted);
		  } catch (Exception e) { return null; }
		}
  public static void main(String[] args) throws Exception {
  }
}