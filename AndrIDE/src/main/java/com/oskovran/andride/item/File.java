package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */

public class File
{
    private final byte[] code;
    public int PC = 0;

    File(final int length)
    {
        code = new byte[length];
    }

    File(final byte[] code)
    {
        this.code = code;
    }

    private byte[] get()
    {
        return code;
    }

    int readUbyte()
    {
        return code[PC++] & 255;
    }

    public int readUshort()
    {
        return  (code[PC++] & 255) | ((code[PC++] & 255) << 8);
    }

    public int readInt()
    {
        return (code[PC++] & 255) | ((code[PC++] & 255) << 8) | ((code[PC++] & 255) << 16) | (code[PC++] << 24);
    }

    public int readSleb128() throws Exception
    {
        int number = 0;
        int shift = 0;

        for(int i = 0; i < 5; i++)
        {// CHECKIT
            int tmp = code[PC++];
            number |= (tmp & 127) << shift;
            shift += 7;

            if((tmp & 128) == 0)
            {
                return (number << (32 - shift)) >> (32 - shift);
            }
        }

        throw new Exception("LEB128");
    }

    public int readUleb128() throws Exception
    {
        int number = 0;
        int shift = 0;

        for(int i = 0; i < 5; i++)
        {//CHECKIT
            int tmp = code[PC++];
            number |= (tmp & 127) << shift;
            shift += 7;

            if((tmp & 128) == 0)
            {
                return number;
            }
        }

        throw new Exception();
    }

    byte[] read(int length)
    {
        byte[] data = new byte[length];
        System.arraycopy(code, PC, data, 0, length);
        PC += length;

        return data;
    }

    public String readMUTF8(int stringLength) throws Exception
    {
        char[] chars = new char[stringLength];

        for (int j = 0, j_length = chars.length; j < j_length; j++)
        {
            int data = readUbyte();
            switch (data >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    chars[j] = (char)data;
                    break;
                case 12:
                case 13:
                    chars[j] = (char)(((data & 0x1F) << 6) | (readUbyte() & 0x3F));
                    break;
                case 14:
                    chars[j] = (char)(((data & 0x0F) << 12) | ((readUbyte() & 0x3F) << 6) | (readUbyte() & 0x3F));
                    break;
                default:
                    throw new Exception();
            }
        }

        return new String(chars);
    }

    String readMUTF82(int stringLength) throws Exception
    {
        String str = new String(code, PC, stringLength, "MUTF-8");
        PC += stringLength;

        return str;
    }

    private void writeUbyte(int value)
    {
        code[PC++] = (byte) value;
    }

    private void writeUshort(int value)
    {
        code[PC++] = (byte) value;
        code[PC++] = (byte) (value >> 8);
    }

    void writeInt(int value)
    {
        code[PC++] = (byte) value;
        code[PC++] = (byte) (value >> 8);
        code[PC++] = (byte) (value >> 16);
        code[PC++] = (byte) (value >> 24);
    }

    void write(byte[] code)
    {
        System.arraycopy(code, 0, this.code, PC, code.length);
        PC += code.length;
    }

    private int[] PCs = new int[16];
    private int PCl = 0;

    public void push(int PC)
    {
        PCs[PCl] = this.PC;
        this.PC = PC;
        PCl++;
    }

    public void pop()
    {
        PCl--;
        PC = PCs[PCl];
    }

    void seal()
    {// TODO
        PC = 0;
    }
}
