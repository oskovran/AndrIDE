package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class TypeId extends Item {

    private StringData descriptor;

    TypeId(File f, Chain<StringData> string_ids) {
        descriptor = string_ids.get(f.readInt());
    }

    protected void write(File f) {

    }
    
    @Override
    public String toString() {
    	return descriptor.string_data;
    }
}
