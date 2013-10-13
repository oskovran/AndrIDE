package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */

class StringId implements ItemInterface
{
    String string_data;// TODO make it array

    public void init(DexFile dex, final File f) throws Exception
    {
        int string_data_off = f.readInt();
        f.push(string_data_off);
        int utf16_size = f.readUleb128();
        string_data = f.readMUTF8(utf16_size);
        f.pop();
    }

    @Override
    public void added()
    {

    }

    @Override
    public void inserted()
    {

    }

    @Override
    public void removed()
    {

    }

    @Override
    public void write(File f)
    {

    }
    
    @Override
    public String toString()
    {
    	return string_data;
    }
}
