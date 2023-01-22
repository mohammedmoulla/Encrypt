package BlowFish;
import java.util.Arrays;
public class BlowFishMainMethod {
	public static void main(String[] args) throws Exception {
		String e = BlowFishEnc.encryptBlowfish("any text", "any key");
		System.out.println(e);
		byte[] arr=e.getBytes("UTF-8");
		for (byte x:arr) {
			System.out.println(x);
		}
		System.out.println(Arrays.toString(arr));
		String s=new String(arr, "UTF-8");
	    System.out.println(e);
	    String decode=BlowFishEnc.decryptBlowfish(s,"anykey");
	    System.out.println(decode);
	}
}
