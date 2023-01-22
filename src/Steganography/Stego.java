package Steganography;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import Details.DetailsMethods;
public class Stego {
	//global
	//in this path we will save the modified image
	public static final String STEGIMAGEFILE = "D:\\steg.png";
	//in this path we will save message after extracting it from the modified image
	public static final String DECODEDMESSAGEFILE = "D:\\message_dec.txt";
	// number of image bytes required to store one stego byte
	private static final int DATA_SIZE = 8;
	/*
	 * hide() loads the text from the text file textFnm, the PNG image from the imFnm file,
and stores the modified image in <imFnm>Msg.png.
	 */
	
	public static boolean hide(String textFnm, String imFnm) throws IOException
	{
	// read in the message, readTextFile reads the file and return the content as a string
	String inputText = readTextFile(textFnm);
	//System.out.println(inputText);
	// if there is no content , it return false and exit
	if ((inputText == null) || (inputText.length() == 0))
	return false;
	//
	byte[] stego = buildStego(inputText);
	// access the image's data as a byte array
	BufferedImage im = loadImage(imFnm);
	if (im == null)
	return false;
	byte imBytes[] = accessBytes(im);
	if (!singleHide(imBytes, stego)) // im is modified with the stego
	return false;
	// store the modified image in <fnm>Msg.png
	File f = new File (STEGIMAGEFILE);
	return ImageIO.write(im, "png", f);
	} // end of hide()
	
	
	public static boolean reveal(String imFnm) throws IOException
	{
	// get the image's data as a byte array
	BufferedImage im = loadImage(imFnm);
	if (im == null)
	return false;
	byte[] imBytes = accessBytes(im);
	//System.out.println("Byte length of image: " + imBytes.length);
	// get msg length at the start of the image
	int msgLen = getMsgLength(imBytes, 0);
	if (msgLen == -1)
	return false;
	//System.out.println("Byte length of message: " + msgLen);
	// get message located after the length info in the image
	String msg = getMessage(imBytes, msgLen, MAX_INT_LEN*DATA_SIZE);
	String cipher=DetailsMethods.readTheHead(msg);
	if(cipher.equals("notEncrypted"))
		JOptionPane.showMessageDialog(null,"your message wasn't encrypted");
	else
	JOptionPane.showMessageDialog(null,"your message was encrypted using "+ cipher);
	if (msg != null) { // save message in a text file
		PrintWriter out = new PrintWriter(new FileWriter(DECODEDMESSAGEFILE, true), true);
		out.write(msg);
		out.close();
		return true;
	}
	else {
		DetailsMethods.messageWithBeep("no message was found");
	return false;
	}
	} // end of reveal()
	/*
	 * readTextFile() reads in the text file, returning it as a string. loadImage() uses
ImageIO.read() to load the image file, returning it as a BufferedImage.
buildStego() constructs a byte array consisting of the two field: the size of the text
message in binary form, and the binary message itself.
	 */
	public static String readTextFile (String messageFile) throws FileNotFoundException{
		String contentOfMessageFile = "";
		File a = new File (messageFile);
		Scanner scan = new Scanner (a);
		while (scan.hasNextLine()){
		String next = scan.nextLine();
		contentOfMessageFile += next;
		if (scan.hasNextLine()){
		contentOfMessageFile += "\n";
		}
		}
		scan.close();
		return contentOfMessageFile;
		}
	public static BufferedImage loadImage(String COVERIMAGEFILE){
		BufferedImage theImage = null;
		File p = new File (COVERIMAGEFILE);
		try{
		theImage = ImageIO.read(p);
		}catch (IOException e){
		e.printStackTrace();
		System.exit(1);
		}
		return theImage;
		}
	
	private static byte[] buildStego(String inputText)
	{
	// convert string data to byte arrays
	byte[] msgBytes = inputText.getBytes();
	//how many bytes do we have?
	byte[] lengthOfBytes = intToBytes(msgBytes.length);
	//now we calculate the total length of the size + actual message
	int totalLength = lengthOfBytes.length + msgBytes.length;
	//now we define a new byte array with the totalLength size
	byte[] stego = new byte[totalLength]; // for holding resulting stego
	// combine the two fields into one byte array
	System.arraycopy(lengthOfBytes, 0, stego, 0, lengthOfBytes.length);
	// length of binary message
	System.arraycopy(msgBytes, 0, stego, lengthOfBytes.length,msgBytes.length);
	// binary message
	return stego;
	} // end of buildStego()
	//buildStego() makes use of System.arraycopy() to build up the byte array;
	/*
	 * It copies the contents of src starting from the scrPos position. The data is copied into
dest starting at the destPos position, and the copy is length bytes long.
intToBytes() converts its integer argument into a byte array by utilizing the fact that a
Java integer is four bytes in size. Each byte of the integer is extracted, and placed in a
separate cell of the array.
	 */
	// global
	private static final int MAX_INT_LEN = 4;
	private static byte[] intToBytes(int i)
	{
	// map the parts of the integer to a byte array
	byte[] integerBs = new byte[MAX_INT_LEN];
	integerBs[0] = (byte) ((i >>> 24) & 0xFF);
	integerBs[1] = (byte) ((i >>> 16) & 0xFF);
	integerBs[2] = (byte) ((i >>> 8) & 0xFF);
	integerBs[3] = (byte) (i & 0xFF);
	return integerBs;
	} // end of intToBytes()
	/*
	 * accessBytes() accesses the image's pixel data as a byte array, so the stego and the
image are in the same byte array format.
	 */
	private static byte[] accessBytes(BufferedImage image)
	{
	WritableRaster raster = image.getRaster();
	DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
	return buffer.getData();
	} // end of accessBytes()
	/*
	 * Having obtained a byte array for the stego (by calling buildStego()), and a byte array
for the image data (by calling accessBytes()), the two arrays are combined as shown
in Figure 3. singleHide() does some checking first, before calling hideStego() to do
the necessary bit manipulation.
	 */
	private static boolean singleHide(byte[] imBytes, byte[] stego)
	{
	int imLen = imBytes.length;
	//System.out.println("Byte length of image: " + imLen);
	int totalLen = stego.length;
	//System.out.println("Total byte length of message: " + totalLen);
	// check that the stego will fit into the image
	/* multiply stego length by number of image bytes
	required to store one stego byte */
	if ((totalLen*DATA_SIZE) > imLen) {
	//System.out.println("Image not big enough for message");
		DetailsMethods.messageWithBeep("image not big enough for message");
	return false;
	}
	hideStego(imBytes, stego, 0); // hide at start of image
	return true;
	} // end of singleHide()
	/*
	 * singleHide() checks whether the image is big enough to store the stego, bearing in
mind that each bit of the stego requires one byte in the image.
hideStego() loops through the bits of the stego, modifying the LSB of each image
byte, starting at the 'offset' byte position.
	 */
	private static void hideStego(byte[] imBytes, byte[] stego,
			int offset)
			{
			for (int i = 0; i < stego.length; i++) { // loop through stego
			int byteVal = stego[i];
			for(int j=7; j >= 0; j--) { // loop through 8 bits of stego byte
			int bitVal = (byteVal >>> j) & 1;
			// change last bit of image byte to be the stego bit
			imBytes[offset] = (byte)((imBytes[offset] & 0xFE) | bitVal);
			offset++;
			}
			}
			} // end of hideStego()
	/*
	 * The outer for-loop iterates through the bytes of the stego, while the inner loop
processes the bits of each byte. The ">>>" operator is a right shift with zero
extension which, when combined with the bit-wise AND ("&"), extracts bits starting
from the left-hand side of the byte. The j counter of the inner loop refers to the bit
indices
	 */
	/*
	 * Bits are accessed from left to right, so 0, 1, 1, 1, 0, 0, 0, and 0 would be returned from
the byte in Figure 6.
Each bit is added to an image byte by combining bitwise-AND ("&") and bitwise-OR
("|") in:
imBytes[offset] & 0xFE) | bitVal
The bitwise-AND operation with the hexadecimal FE (1111110 binary) clears the
right-most bit of imBytes[offset] (i.e. the LSB), and the bitwise-OR places the stego
bit value into the empty space.
hide() finishes by calling writeImageToFile(), which saves the BufferedImage into a
new file. It's not necessary to create a new BufferedImage object, since modifications
to the image's byte array (i.e. imBytes[]) automatically update the loaded image
because it's a WriteableRaster.
	 */
	private static int getMsgLength(byte[] imBytes, int offset)
	{
	byte[] lenBytes = extractHiddenBytes(imBytes, MAX_INT_LEN, offset);
	// get the binary message length as a byte array
	if (lenBytes == null)
	return -1;
	// convert the byte array into an integer
	int msgLen = ((lenBytes[0] & 0xff) << 24) |
	((lenBytes[1] & 0xff) << 16) |
	((lenBytes[2] & 0xff) << 8) |
	(lenBytes[3] & 0xff);
	if ((msgLen <= 0) || (msgLen > imBytes.length)) {
		//System.out.println("Incorrect message length");
		DetailsMethods.messageWithBeep("Incorrect message length");
		return -1;
		}
		return msgLen;
		} // end of getMsgLength()
	private static byte[] extractHiddenBytes(byte[] imBytes,
			int size, int offset)
			{
			int finalPosn = offset + (size*DATA_SIZE);
			if (finalPosn > imBytes.length) {
			//System.out.println("End of image reached");
				DetailsMethods.messageWithBeep("End of image reached");
			return null;
			}
			byte[] hiddenBytes = new byte[size];
			for (int j = 0; j < size; j++) { // loop through hidden bytes
			for (int i=0; i < DATA_SIZE; i++) {
			// make one hidden byte from DATA_SIZE image bytes
			hiddenBytes[j] = (byte) ((hiddenBytes[j] << 1) |
			(imBytes[offset] & 1));
			/* shift existing bits left;
			store LSB of image byte on the right */
			offset++;
			}
			}
			return hiddenBytes;
			} // end of extractHiddenBytes()
	private static String getMessage(byte[] imBytes, int msgLen, int
			offset)
			{
			byte[] msgBytes = extractHiddenBytes(imBytes, msgLen, offset);
			// the message is msgLen bytes long
			if (msgBytes == null)
			return null;
			String msg = new String(msgBytes);
			// check the message is all characters
			return msg;
			} // end of getMessage()
	public static void main (String args[]) throws IOException {
	hide("D:\\key.txt", "D:\\cover.png");
	//reveal("D:\\steg.png");
	}
}
