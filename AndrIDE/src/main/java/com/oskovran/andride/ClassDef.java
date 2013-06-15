package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

class ClassDef extends Item {

    private TypeId clazz;
    private int access_flags;
    private TypeId superclass;
    private int interfaces_off;
    private StringData source_file;
    private int annotations_off;
    private int class_data_off;
    private int static_values_off;
    //
    private final File f;
    private ItemChain<StringData> string_ids;
    private final ItemChain<FieldId> field_ids;
    private final ItemChain<MethodId> method_ids;
    //
    private ClassData class_data;

    ClassDef(File f,
            ItemChain<StringData> string_ids,
            ItemChain<TypeId> type_ids,
            ItemChain<FieldId> field_ids,
            ItemChain<MethodId> method_ids) throws Exception {
        this.f = f;
        this.field_ids = field_ids;
        this.method_ids = method_ids;
        this.clazz = type_ids.get(f.readInt());
        this.access_flags = f.readInt();
        this.superclass = type_ids.get(f.readInt());
        this.interfaces_off = f.readInt();
        this.source_file = string_ids.get(f.readInt());
        this.annotations_off = f.readInt();
        this.class_data_off = f.readInt();
        this.static_values_off = f.readInt();

        if(class_data_off != 0) {
            f.push(class_data_off);
            this.class_data = new ClassData();
            f.pop();
        }
    }

    protected void write(File f) {

    }

    private class ClassData {

        private final int static_fields_size;// uleb128 the number of static fields defined in this item
        private final int instance_fields_size;// uleb128 the number of instance fields defined in this item
        private final int direct_methods_size;// uleb128 the number of direct methods defined in this item
        private final int virtual_methods_size;// uleb128 the number of virtual methods defined in this item
        private final ItemChain<EncodedField> static_fields;// encoded_field[static_fields_size] the defined static fields, represented as a sequence of encoded elements. The fields must be sorted by field_idx in increasing order.
        private final ItemChain<EncodedField> instance_fields;// encoded_field[instance_fields_size] the defined instance fields, represented as a sequence of encoded elements. The fields must be sorted by field_idx in increasing order.
        private final ItemChain<EncodedMethod> direct_methods;// encoded_method[direct_methods_size] the defined direct (any of static, private, or constructor) methods, represented as a sequence of encoded elements. The methods must be sorted by method_idx in increasing order.
        private final ItemChain<EncodedMethod> virtual_methods;// encoded_method[virtual_methods_size] the defined virtual (none of static, private, or constructor) methods, represented as a sequence of encoded elements. This list should not include inherited methods unless overridden by the class that this item represents. The methods must be sorted by method_idx in increasing order

        private ClassData() throws Exception {
            static_fields_size = f.readUleb128();
            instance_fields_size = f.readUleb128();
            direct_methods_size = f.readUleb128();
            virtual_methods_size = f.readUleb128();

            static_fields = new ItemChain<EncodedField>();
            for(int i = 0; i < static_fields_size; i++) {
                static_fields.add(new EncodedField());
            }
            instance_fields = new ItemChain<EncodedField>();
            for(int i = 0; i < instance_fields_size; i++) {
                instance_fields.add(new EncodedField());
            }
            direct_methods = new ItemChain<EncodedMethod>();
            for(int i = 0; i < direct_methods_size; i++) {
                direct_methods.add(new EncodedMethod());
            }
            virtual_methods = new ItemChain<EncodedMethod>();
            for(int i = 0; i < virtual_methods_size; i++) {
                virtual_methods.add(new EncodedMethod());
            }
        }

        private class EncodedField extends Item {

            private FieldId field_idx_diff;
            private int access_flags;

            private EncodedField() throws Exception {
                field_idx_diff = field_ids.get(f.readUleb128());
                access_flags = f.readUleb128();
            }

            protected void write(File f) {

            }
            
            @Override
            public String toString() {
            	return "field_idx_diff" +
            	"=" +
            	field_idx_diff +
            	"\n" +
            	"access_flags" +
            	"=" +
            	access_flags;
            }
        }

        private class EncodedMethod extends Item {

            private MethodId method_idx_diff;
            private int access_flags;
            private Code code_off;

            private EncodedMethod() throws Exception {
                int index = f.readUleb128();
                method_idx_diff = method_ids.get(index);
                access_flags = f.readUleb128();
                int off = f.readUleb128();
                if(off != 0) {
                    f.push(off);
                    code_off = new Code(f);
                    f.pop();
                }
            }

            protected void write(File f) {

            }
            
            @Override
            public String toString() {
            	return "method_idx_diff" +
            	"=" +
            	method_idx_diff +
            	"\n" +
            	"access_flags" +
            	"=" +
            	access_flags +
            	"\n" +
            	"code_off" +
            	"=" +
            	code_off;
            }
        }

        protected void write(File f) {

        }
        
        @Override
        public String toString() {
        	return "static_fields_size" +
        	"=" +
        	static_fields_size +
        	"\n" +
        	"instance_fields_size" +
        	"=" +
        	instance_fields_size +
        	"\n" +
        	"direct_methods_size" +
        	"=" +
        	direct_methods_size +
        	"\n" +
        	"virtual_methods_size" +
        	"=" +
        	virtual_methods_size;
        }
    }
    
    @Override
    public String toString() {
    	return "class" +
    	"=" +
    	clazz +
    	"\n" +
    	"access_flags" +
    	"=" +
    	access_flags +
    	"\n" +
    	"superclass" +
    	"=" +
    	superclass +
    	"\n" +
    	"interfaces_off" +
    	"=" +
    	interfaces_off +
    	"\n" +
    	"source_file" +
    	"=" +
    	source_file +
    	"\n" +
    	"annotations_off" +
    	"=" +
    	annotations_off +
    	"\n" +
    	"class_data_off" +
    	"=" +
    	class_data_off +
    	"\n" +
    	"static_values_off" +
    	"=" +
    	static_values_off;
    }
}