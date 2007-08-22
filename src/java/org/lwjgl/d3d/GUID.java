package org.lwjgl.d3d;

public class GUID {
    public long Data1;                  //4 DWORD
    public short Data2;                   //2 WORD
    public short Data3;                   //2 WORD
    public byte Data4[] = new byte[8];  //8 BYTE

    public String toString() {
        return 
            "\nGUID = " + padDigits(Integer.toHexString((int)Data1).toUpperCase(), 8) + "-" + 
            padDigits(Integer.toHexString(Data2).toUpperCase(), 4) + "-" +
            padDigits(Integer.toHexString(Data3).toUpperCase(), 4) + "-" +
            padDigits(Integer.toHexString(((((int)Data4[0]) << 8) + ((int)Data4[1])) & 0xFFFF).toUpperCase(), 4) + "-" + 
            padDigits(Integer.toHexString((((int)Data4[2]) << 24) + (((int)Data4[3]) << 16) + (((int)Data4[4]) << 8) + ((int)Data4[5])).toUpperCase(), 8) + 
            padDigits(Integer.toHexString(((((int)Data4[6]) << 8) + ((int)Data4[7])) & 0xFFFF).toUpperCase(), 4); 
    }
    
    private String padDigits(String source, int size) {
        while(source.length() < size) {
            source = "0" + source;
        }
        
        return source;
    }
}
