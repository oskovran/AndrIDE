package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class MethodId extends Item {

    private TypeId clazz;
    private ProtoId proto;
    private StringData name;

    MethodId(File f, ItemChain<StringData> string_ids, ItemChain<TypeId> type_ids, ItemChain<ProtoId> proto_ids) {
        clazz = type_ids.get(f.readUshort());
        proto = proto_ids.get(f.readUshort());
        name = string_ids.get(f.readInt());
    }

    protected void write(File f) {

    }
    
    @Override public String toString() {
    	return "class" +
                "=" +
                clazz +
                "\n" +
                proto +
                "\n" +
                "name" +
                "=" +
                name;
    }
}
