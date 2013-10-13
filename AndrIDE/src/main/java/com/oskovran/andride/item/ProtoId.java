package com.oskovran.andride.item;

public class ProtoId implements ItemInterface
{
    private StringId shorty;
    private TypeId return_type;
    private int parameters_off;

    public void init(final DexFile dex, final File f) throws Exception
    {
        shorty = dex.string_ids.get(f.readInt());
        return_type = dex.type_ids.get(f.readInt());
        parameters_off = f.readInt();
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
    	return "shorty" + "=" + shorty.string_data + "\n" +
               "return_type" + "=" + return_type + "\n" +
               "parameters_off" + "=" + parameters_off;
    }
}
