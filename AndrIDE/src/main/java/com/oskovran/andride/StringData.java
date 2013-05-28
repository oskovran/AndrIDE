package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class StringData extends Item {

    final String string_data;

    StringData(File f) throws Exception {
        int string_data_off = f.readInt();
        f.push(string_data_off);
        int utf16_size = f.readUleb128();
        string_data = f.readMUTF8(utf16_size);
        f.pop();
    }

    protected void write(File f) {
    	//f.writeInt(string_data_off);
    }
    
    @Override
    public String toString() {
    	return string_data;
    }
}
