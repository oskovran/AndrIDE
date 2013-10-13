package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */
class FieldId implements ItemInterface
{
    private TypeId clazz;
    private TypeId type;
    private StringId name;
    private DexFile dex;

    public void init(final DexFile dex, final File f)
    {
        this.dex = dex;
        clazz = dex.type_ids.get(f.readUshort());
        type = dex.type_ids.get(f.readUshort());
        name = dex.string_ids.get(f.readInt());
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
    	return "class" + "=" + clazz + "\n" +
               "type" + "=" + type + "\n" +
               "name" + "=" + name;
    }
}
