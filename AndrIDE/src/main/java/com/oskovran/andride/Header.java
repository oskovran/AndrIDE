package com.oskovran.andride;

import android.util.Log;

/**
 *
 * @author Ondra
 */
class Header {

    private static final String TAG = AndrideActivity.class.getSimpleName();

    private static final int TYPE_HEADER_ITEM = 0x0000;//	0x70
    private static final int TYPE_STRING_ID_ITEM = 0x0001;//	0x04
    private static final int TYPE_TYPE_ID_ITEM = 0x0002;//	0x04
    private static final int TYPE_PROTO_ID_ITEM = 0x0003;//	0x0c
    private static final int TYPE_FIELD_ID_ITEM = 0x0004;//	0x08
    private static final int TYPE_METHOD_ID_ITEM = 0x0005;//	0x08
    private static final int TYPE_CLASS_DEF_ITEM = 0x0006;//	0x20
    private static final int TYPE_MAP_LIST = 0x1000;//	4 + (item.size * 12)
    private static final int TYPE_TYPE_LIST = 0x1001;//	4 + (item.size * 2)
    private static final int TYPE_ANNOTATION_SET_REF_LIST = 0x1002;//	4 + (item.size * 4)
    private static final int TYPE_ANNOTATION_SET_ITEM = 0x1003;//	4 + (item.size * 4)
    private static final int TYPE_CLASS_DATA_ITEM = 0x2000;//	implicit; must parse
    private static final int TYPE_CODE_ITEM = 0x2001;//	implicit; must parse
    private static final int TYPE_STRING_DATA_ITEM = 0x2002;//	implicit; must parse
    private static final int TYPE_DEBUG_INFO_ITEM = 0x2003;//	implicit; must parse
    private static final int TYPE_ANNOTATION_ITEM = 0x2004;//	implicit; must parse
    private static final int TYPE_ENCODED_ARRAY_ITEM = 0x2005;//	implicit; must parse
    private static final int TYPE_ANNOTATIONS_DIRECTORY_ITEM = 0x2006;//	implicit; must parse

    int checksum;
    byte[] signature;
    int file_size;
    int header_size;
    int endian_tag;// = ENDIAN_CONSTANT;
    int link_size;
    int link_off;
    int map_off;
    int string_ids_size;
    int string_ids_off;
    int type_ids_size;
    int type_ids_off;
    int proto_ids_size;
    int proto_ids_off;
    int field_ids_size;
    int field_ids_off;
    int method_ids_size;
    int method_ids_off;
    int class_defs_size;
    int class_defs_off;
    int data_size;
    int data_off;

    Header() {
        
    }

    Header(File f) throws Exception {// TODO Exception
        Log.d(TAG, "MAGIC constructing");
        checkMagic(f);
        Log.d(TAG, "magic check successfull");
        checksum = f.readInt();
        Log.d(TAG, "checksum" + " = " + checksum);
        signature = f.read(20);
        Log.d(TAG, "signature" + " = " + new String(signature));
        file_size = f.readInt();
        Log.d(TAG, "file_size" + " = " + file_size);
        header_size = f.readInt();
        Log.d(TAG, "header_size" + " = " + header_size);
        endian_tag = f.readInt();
        Log.d(TAG, "endian_tag" + " = " + endian_tag);
        link_size = f.readInt();
        Log.d(TAG, "link_size" + " = " + link_size);
        link_off = f.readInt();
        Log.d(TAG, "link_off" + " = " + link_off);
        map_off = f.readInt();
        Log.d(TAG, "map_off" + " = " + map_off);
        string_ids_size = f.readInt();
        Log.d(TAG, "string_ids_size" + " = " + string_ids_size);
        string_ids_off = f.readInt();
        Log.d(TAG, "string_ids_off" + " = " + string_ids_off);
        type_ids_size = f.readInt();
        Log.d(TAG, "type_ids_size" + " = " + type_ids_size);
        type_ids_off = f.readInt();
        Log.d(TAG, "type_ids_off" + " = " + type_ids_off);
        proto_ids_size = f.readInt();
        Log.d(TAG, "proto_ids_size" + " = " + proto_ids_size);
        proto_ids_off = f.readInt();
        Log.d(TAG, "proto_ids_off" + " = " + proto_ids_off);
        field_ids_size = f.readInt();
        Log.d(TAG, "field_ids_size" + " = " + field_ids_size);
        field_ids_off = f.readInt();
        Log.d(TAG, "field_ids_off" + " = " + field_ids_off);
        method_ids_size = f.readInt();
        Log.d(TAG, "method_ids_size" + " = " + method_ids_size);
        method_ids_off = f.readInt();
        Log.d(TAG, "method_ids_off" + " = " + method_ids_off);
        class_defs_size = f.readInt();
        Log.d(TAG, "class_defs_size" + " = " + class_defs_size);
        class_defs_off = f.readInt();
        Log.d(TAG, "class_defs_off" + " = " + class_defs_off);
        data_size = f.readInt();
        Log.d(TAG, "data_size" + " = " + data_size);
        data_off = f.readInt();
        Log.d(TAG, "data_off" + " = " + data_off);
    }

    void write(File f) {
        f.write(new byte[] {0x64, 0x65, 0x78, 0x0a, 0x30, 0x33, 0x35, 0x00});
        f.writeInt(checksum);
        f.write(signature);
        f.writeInt(file_size);
        f.writeInt(header_size);
        f.writeInt(endian_tag);
        f.writeInt(link_size);
        f.writeInt(link_off);
        f.writeInt(map_off);
        f.writeInt(string_ids_size);
        f.writeInt(string_ids_off);
        f.writeInt(type_ids_size);
        f.writeInt(type_ids_off);
        f.writeInt(proto_ids_size);
        f.writeInt(proto_ids_off);
        f.writeInt(field_ids_size);
        f.writeInt(field_ids_off);
        f.writeInt(method_ids_size);
        f.writeInt(method_ids_off);
        f.writeInt(class_defs_size);
        f.writeInt(class_defs_off);
        f.writeInt(data_size);
        f.writeInt(data_off);
    }

    private void checkMagic(File f) throws Exception {
        if (f.readUbyte() != 0x64
                || f.readUbyte() != 0x65
                || f.readUbyte() != 0x78
                || f.readUbyte() != 0x0a
                || f.readUbyte() != 0x30
                || f.readUbyte() != 0x33
                || f.readUbyte() != 0x35
                || f.readUbyte() != 0x00) {
            throw new Exception();// TODO
        }
    }
    
    @Override
    public String toString() {
        return "Header.toString TODO";
    }
}