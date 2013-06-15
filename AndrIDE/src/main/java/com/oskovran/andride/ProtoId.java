package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class ProtoId extends Item {

    private StringData shorty;
    private TypeId return_type;
    private int parameters_off;

    ProtoId(File f, ItemChain<StringData> string_ids, ItemChain<TypeId> type_ids) {
        shorty = string_ids.get(f.readInt());
        return_type = type_ids.get(f.readInt());
        parameters_off = f.readInt();
    }

    protected void write(File f) {

    }
    
    @Override
    public String toString() {
    	return "shorty" +
                "=" +
                shorty.string_data +
                "\n" +
                "return_type" +
                "=" +
                return_type;
    }
}
