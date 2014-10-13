/**
* Images.java
* This class contains public static final arrays of bytes, that describes an image.
*
*/
class Images {

	// Override default constructor with a private one.
	// This class shall not be initialized.
	private Images(){}

	//Pirate flag:
	public static final byte[] PIRATE_FLAG = {
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xf3, (byte) 0xc7, (byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0x3f, (byte) 0x8f, (byte) 0x07, (byte) 0x03,
					(byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x1b, (byte) 0x61, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x07, (byte) 0x0b, (byte) 0x23,
					(byte) 0x03, (byte) 0x03, (byte) 0x07, (byte) 0x07, (byte) 0x1f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x0f, (byte) 0x0f, (byte) 0x87, (byte) 0xe3,
					(byte) 0xfb, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8,
					(byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xc1, (byte) 0x81, (byte) 0x83, (byte) 0x03, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f,
					(byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xc0, (byte) 0x80,
					(byte) 0x80, (byte) 0x80, (byte) 0x91, (byte) 0x82, (byte) 0x20, (byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x20, (byte) 0x00,
					(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x04, (byte) 0x08, (byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x63, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x3f, (byte) 0x3f, (byte) 0x1f, (byte) 0x1f, (byte) 0x0f,
					(byte) 0x0f, (byte) 0x07, (byte) 0x07, (byte) 0x83, (byte) 0x83, (byte) 0xc1, (byte) 0xc0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfc,
					(byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xfe, (byte) 0xfe, (byte) 0xfc, (byte) 0xfc, (byte) 0xf8, (byte) 0xf8, (byte) 0xf0, (byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xc1, (byte) 0xc1,
					(byte) 0x83, (byte) 0x9f, (byte) 0x33, (byte) 0xc0, (byte) 0x80, (byte) 0x00, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x0f, (byte) 0x07,
					(byte) 0x82, (byte) 0xe0, (byte) 0xf8, (byte) 0xf8, (byte) 0xc2, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f, (byte) 0x1f, (byte) 0x1f, (byte) 0x06,
					(byte) 0x82, (byte) 0x0c, (byte) 0x08, (byte) 0xc7, (byte) 0xfb, (byte) 0xc3, (byte) 0xc1, (byte) 0xe1, (byte) 0xe0, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0,
					(byte) 0xf8, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f,
					(byte) 0x3f, (byte) 0x9f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0x3f,
					(byte) 0x3f, (byte) 0xbf, (byte) 0x1f, (byte) 0x5f, (byte) 0x5f, (byte) 0x0f, (byte) 0x2f, (byte) 0x2f, (byte) 0x07, (byte) 0xff, (byte) 0x03, (byte) 0xfe,
					(byte) 0xf0, (byte) 0xf0, (byte) 0xe0, (byte) 0xf0, (byte) 0xd1, (byte) 0xc1, (byte) 0xf1, (byte) 0xc1, (byte) 0xc1, (byte) 0xf0, (byte) 0xc0, (byte) 0xf0,
					(byte) 0xe0, (byte) 0xf8, (byte) 0x1e, (byte) 0xff, (byte) 0xf1, (byte) 0x80, (byte) 0x00, (byte) 0x3f, (byte) 0x83, (byte) 0x1f, (byte) 0x7f, (byte) 0xbf,
					(byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0x7f, (byte) 0x23, (byte) 0x3d, (byte) 0x15, (byte) 0x1f,
					(byte) 0x1f, (byte) 0x0d, (byte) 0x8d, (byte) 0x84, (byte) 0xc0, (byte) 0x43, (byte) 0x01, (byte) 0xc1, (byte) 0xe1, (byte) 0xe0, (byte) 0xf0, (byte) 0xf0,
					(byte) 0xf2, (byte) 0xf8, (byte) 0xf9, (byte) 0xf8, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xe0, (byte) 0xcf, (byte) 0x0f, (byte) 0x3f, (byte) 0x3f, (byte) 0xff, (byte) 0x7f, (byte) 0x7f, (byte) 0xff,
					(byte) 0xff, (byte) 0x7f, (byte) 0x1f, (byte) 0x1f, (byte) 0x87, (byte) 0xe7, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfc, (byte) 0xfe,
					(byte) 0xff, (byte) 0xfe, (byte) 0xfe, (byte) 0xff, (byte) 0xfc, (byte) 0xf8, (byte) 0xf8, (byte) 0xf8, (byte) 0xf1, (byte) 0xf0, (byte) 0xf0, (byte) 0xe0,
					(byte) 0xe1, (byte) 0xe1, (byte) 0x61, (byte) 0x03, (byte) 0x83, (byte) 0xc0, (byte) 0x94, (byte) 0x87, (byte) 0x07, (byte) 0x0f, (byte) 0x0f, (byte) 0x1f,
					(byte) 0x1f, (byte) 0x1f, (byte) 0x3f, (byte) 0x3f, (byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf8, (byte) 0xf8, (byte) 0xf0, (byte) 0xf8, (byte) 0xf0,
					(byte) 0xf4, (byte) 0xe2, (byte) 0xea, (byte) 0xe3, (byte) 0xeb, (byte) 0xe1, (byte) 0xe9, (byte) 0xe0, (byte) 0xf6, (byte) 0xf8, (byte) 0xfc, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xf8,
					(byte) 0xf0, (byte) 0xe0, (byte) 0xe0, (byte) 0xe1, (byte) 0xe0, (byte) 0xf0, (byte) 0xf8, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xf0, (byte) 0xe2, (byte) 0xc3, (byte) 0xc7, (byte) 0xc7, (byte) 0x87,
					(byte) 0x8f, (byte) 0x8f, (byte) 0x9f, (byte) 0xde, (byte) 0xde, (byte) 0xec, (byte) 0xec, (byte) 0xf1, (byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
					(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
}