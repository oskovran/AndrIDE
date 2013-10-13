package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */

class MethodId implements ItemInterface
{
    private TypeId clazz;
    private ProtoId proto;
    private StringId name;

    public void init(final DexFile dex, final File f) throws Exception
    {
        clazz = dex.type_ids.get(f.readUshort());
        proto = dex.proto_ids.get(f.readUshort());
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
               "proto" + "=" + proto + "\n" +
               "name" + "=" + name;
    }
}
