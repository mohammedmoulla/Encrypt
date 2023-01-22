package IDEA;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

public class Convert {
	public static String unHex(String arg) {
	    String str = "";
	    for(int i=0;i<arg.length();i+=2)
	    {
	        String s = arg.substring(i, (i + 2));
	        int decimal = Integer.parseInt(s, 16);
	        str = str + (char) decimal;
	    }       
	    return str;
	}
    public static Binary[] stringToBinary(String text){
        int n = text.length();
        Binary [] result = new Binary[n];
        for (int i=0 ; i<n; i++){
            char c = text.charAt(i);
            long x = c ;
            String temp = Long.toBinaryString(x);
            result[i]=new Binary(16);
            result[i].set(temp);
        }        
        return result;
    }
    public static String binaryToString(Binary [] binary){
        String result = "";
        int n = binary.length;
        for (int i=0; i<n; i++){
            char c = (char)Long.parseLong(binary[i].get(),2);
            result+= c;
        }
        return result;
    }
    public static String binaryToHex(Binary [] binary){
        String result = "";
        int n = binary.length;
        for (int i=0; i<n; i++){
            long x = Long.parseLong(binary[i].get(),2);
            String t = Long.toHexString(x);
            result+= t;
        }
        return result;
    }
    public String hexToString(String hex){
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
  	  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
  	  for( int i=0; i<hex.length()-1; i+=2 ){
  	      //grab the hex in pairs
  	      String output = hex.substring(i, (i + 2));
  	      //convert hex to decimal
  	      int decimal = Integer.parseInt(output, 16);
  	      //convert the decimal to character
  	      sb.append((char)decimal);
  		  
  	      temp.append(decimal);
  	  }
  	  return sb.toString();
    }
    public static String toHexadecimal(String text) throws UnsupportedEncodingException
    {
        byte[] myBytes = text.getBytes("UTF-8");

        return DatatypeConverter.printHexBinary(myBytes);
    }
}