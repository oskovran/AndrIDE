package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */
class ClassDef implements ItemInterface
{
    private TypeId      clazz;
    private int         access_flags;
    private TypeId      superclass;
    private int         interfaces_off;
    private StringId source_file;
    private int         annotations_off;
    private int         class_data_off;
    private int         static_values_off;
    //
    private ClassData   class_data;

    @Override
    public void init(DexFile dex, final File f)
    {
        this.clazz              = dex.type_ids.get(f.readInt());
        this.access_flags       = f.readInt();
        this.superclass         = dex.type_ids.get(f.readInt());
        this.interfaces_off     = f.readInt();
        this.source_file        = dex.string_ids.get(f.readInt());
        this.annotations_off    = f.readInt();
        this.class_data_off     = f.readInt();
        this.static_values_off  = f.readInt();

        if(class_data_off != 0)
        {
            f.push(class_data_off);
            this.class_data = new ClassData();
            f.pop();
        }
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

    private class ClassData implements ItemInterface
    {
        /** uleb128 the number of static fields defined in this item */
        private int static_fields_size;
        /** uleb128 the number of instance fields defined in this item */
        private int instance_fields_size;
        /** uleb128 the number of direct methods defined in this item */
        private int direct_methods_size;
        /** uleb128 the number of virtual methods defined in this item */
        private int virtual_methods_size;
        /** encoded_field[static_fields_size] the defined static fields, represented as a sequence of encoded elements. The fields must be sorted by field_idx in increasing order. */
        private ItemGroup<EncodedField> static_fields;
        /** encoded_field[instance_fields_size] the defined instance fields, represented as a sequence of encoded elements. The fields must be sorted by field_idx in increasing order. */
        private ItemGroup<EncodedField> instance_fields;
        /** encoded_method[direct_methods_size] the defined direct (any of static, private, or constructor) methods, represented as a sequence of encoded elements. The methods must be sorted by method_idx in increasing order. */
        private ItemGroup<EncodedMethod> direct_methods;
        /** encoded_method[virtual_methods_size] the defined virtual (none of static, private, or constructor) methods, represented as a sequence of encoded elements. This list should not include inherited methods unless overridden by the class that this item represents. The methods must be sorted by method_idx in increasing order */
        private ItemGroup<EncodedMethod> virtual_methods;

        @Override
        public void init(final DexFile dex, final File f) throws Exception
        {
            static_fields_size      = f.readUleb128();
            instance_fields_size    = f.readUleb128();
            direct_methods_size     = f.readUleb128();
            virtual_methods_size    = f.readUleb128();

            static_fields = ItemHelper.news(EncodedField.class, dex, f, static_fields_size);
            instance_fields = ItemHelper.news(EncodedField.class, dex, f, instance_fields_size);
            direct_methods = ItemHelper.news(EncodedMethod.class, dex, f, direct_methods_size);
            virtual_methods = ItemHelper.news(EncodedMethod.class, dex, f, virtual_methods_size);
        }

        private class EncodedField implements ItemInterface
        {
            private FieldId     field_idx_diff;
            private int         access_flags;

            @Override
            public void init(DexFile dex, final File f) throws Exception
            {
                field_idx_diff  = dex.field_ids.get(f.readUleb128());
                access_flags    = f.readUleb128();
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
            	return "field_idx_diff" + "=" + field_idx_diff + "\n" +
            	       "access_flags" + "=" + access_flags;
            }
        }

        private class EncodedMethod implements ItemInterface
        {
            private MethodId    method_idx_diff;
            private int         access_flags;
            private Code        code;

            @Override
            public void init(DexFile dex, final File f) throws Exception
            {
                method_idx_diff = dex.method_ids.get(f.readUleb128());
                access_flags    = f.readUleb128();
                int off         = f.readUleb128();

                if(off != 0)
                {
                    f.push(off);
                    code = Code.class.newInstance();
                    code.init(dex, f);
                    f.pop();
                }
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
            	return "method_idx_diff" + "=" + method_idx_diff + "\n" +
            	       "access_flags" + "=" + access_flags + "\n" +
            	       "code_off" + "=" + "TODO";
            }
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
        	return "static_fields_size" + "=" + static_fields_size + "\n" +
        	       "instance_fields_size" + "=" + instance_fields_size + "\n" +
        	       "direct_methods_size" + "=" + direct_methods_size + "\n" +
        	       "virtual_methods_size" + "=" + virtual_methods_size;
        }
    }
    
    @Override
    public String toString()
    {
    	return "class" + "=" + clazz + "\n" +
    	       "access_flags" + "=" + access_flags + "\n" +
    	       "superclass" + "=" + superclass + "\n" +
    	       "interfaces_off" + "=" + interfaces_off + "\n" +
    	       "source_file" + "=" + source_file + "\n" +
    	       "annotations_off" + "=" + annotations_off + "\n" +
    	       "class_data_off" + "=" + class_data_off + "\n" +
    	       "static_values_off" + "=" + static_values_off;
    }
}