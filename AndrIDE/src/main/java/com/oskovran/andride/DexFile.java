package com.oskovran.andride;

import android.util.Log;

/**
 *
 * @author Ondra
 */
class DexFile {

    private static final String TAG = DexFile.class.getSimpleName();
    
    private static final int ACC_PUBLIC = 0x1;
    private static final int ACC_PRIVATE = 0x2;
    private static final int ACC_PROTECTED = 0x4;
    private static final int ACC_STATIC = 0x8;
    private static final int ACC_FINAL = 0x10;
    private static final int ACC_SYNCHRONIZED = 0x20;
    private static final int ACC_VOLATILE = 0x40;
    private static final int ACC_BRIDGE = 0x40;
    private static final int ACC_TRANSIENT = 0x80;
    private static final int ACC_VARARGS = 0x80;
    private static final int ACC_NATIVE = 0x100;
    private static final int ACC_INTERFACE = 0x200;
    private static final int ACC_ABSTRACT = 0x400;
    private static final int ACC_STRICT = 0x800;
    private static final int ACC_SYNTHETIC = 0x1000;
    private static final int ACC_ANNOTATION = 0x2000;
    private static final int ACC_ENUM = 0x4000;
    private static final int ACC_UNUSED = 0x8000;
    private static final int ACC_CONSTRUCTOR = 0x10000;
    private static final int ACC_DECLARED_SYNCHRONIZED = 0x20000;

    private static final int VALUE_BYTE = 0x00;
    private static final int VALUE_SHORT = 0x02;
    private static final int VALUE_CHAR = 0x03;
    private static final int VALUE_INT = 0x04;
    private static final int VALUE_LONG = 0x06;
    private static final int VALUE_FLOAT = 0x10;
    private static final int VALUE_DOUBLE = 0x11;
    private static final int VALUE_STRING = 0x17;
    private static final int VALUE_TYPE = 0x18;
    private static final int VALUE_FIELD = 0x19;
    private static final int VALUE_METHOD = 0x1a;
    private static final int VALUE_ENUM = 0x1b;
    private static final int VALUE_ARRAY = 0x1c;
    private static final int VALUE_ANNOTATION = 0x1d;
    private static final int VALUE_NULL = 0x1e;
    private static final int VALUE_BOOLEAN = 0x1f;

    final Header header_item;
    final ItemChain<StringData> string_ids;
    final ItemChain<TypeId> type_ids;
    final ItemChain<ProtoId> proto_ids;
    final ItemChain<FieldId> field_ids;
    final ItemChain<MethodId> method_ids;
    final ItemChain<ClassDef> class_defs;
    
    private int len;

    // * * * NEW * * *
    public DexFile() throws Exception {
    	header_item = new Header();
    	string_ids = new ItemChain<StringData>();
        type_ids = new ItemChain<TypeId>();
        proto_ids = new ItemChain<ProtoId>();
        field_ids = new ItemChain<FieldId>();
        method_ids = new ItemChain<MethodId>();
        class_defs = new ItemChain<ClassDef>();
    }

    // * * * OPEN * * *
    public DexFile(byte[] Code) throws Exception {
    	
    	File f = new File(Code);
    	
    	header_item = new Header(f);
    	
    	string_ids = new ItemChain<StringData>();
    	for(int i = 0; i < header_item.string_ids_size; i++) {
            string_ids.add(new StringData(f));
            Log.i("", "string_id(" + i + ") = " + string_ids.get(i));
    	}

        type_ids = new ItemChain<TypeId>();
    	for(int i = 0; i < header_item.type_ids_size; i++) {
            type_ids.add(new TypeId(f, string_ids));
            Log.i("", "type_id(" + i + ") = " + type_ids.get(i));
    	}

        proto_ids = new ItemChain<ProtoId>();
    	for(int i = 0; i < header_item.proto_ids_size; i++) {
            proto_ids.add(new ProtoId(f, string_ids, type_ids));
    	}

        field_ids = new ItemChain<FieldId>();
    	for(int i = 0; i < header_item.field_ids_size; i++) {
            field_ids.add(new FieldId(f, string_ids, type_ids));
    	}

        method_ids = new ItemChain<MethodId>();
    	for(int i = 0; i < header_item.method_ids_size; i++) {
            method_ids.add(new MethodId(f, string_ids, type_ids, proto_ids));
    	}

        class_defs = new ItemChain<ClassDef>();
    	for(int i = 0; i < header_item.class_defs_size; i++) {
            class_defs.add(new ClassDef(f, string_ids, type_ids, field_ids, method_ids));
    	}

        len = Code.length;
        //test
        save();
    }
    
    // * * * SAVE * * *
    final void save() {

        File f = new File(len);
    	
    	header_item.write(f);
    	string_ids.write(f);

        f.seal();

        Log.d(TAG, "----------- THE END ------------");
    }
}
