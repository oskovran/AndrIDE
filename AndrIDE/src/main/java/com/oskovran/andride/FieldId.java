package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class FieldId extends Item {

    private TypeId clazz;
    private TypeId type;
    private StringData name;

    FieldId(File f, ItemChain<StringData> string_ids, ItemChain<TypeId> type_ids) {
        clazz = type_ids.get(f.readUshort());
        type = type_ids.get(f.readUshort());
        name = string_ids.get(f.readInt());
    }

    protected void write(File f) {

    }
    
    @Override
    public String toString() {
    	return "class" +
        "=" +
    	clazz +
    	"\n" +
        "type" +
        "=" +
    	type +
    	"\n" +
        "name=" +
    	name;
    }
}
