package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */
class TypeId implements ItemInterface
{
    private StringId descriptor;

    public void init(final DexFile dex, final File f)
    {
        descriptor = dex.string_ids.get(f.readInt());
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
    	return descriptor.string_data;
    }
}
