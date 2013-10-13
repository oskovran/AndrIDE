package com.oskovran.andride.item;

/**
 * Created by oskovran on 7/20/13.
 */
final class ItemGroup<T extends ItemInterface> implements ItemInterface
{
    final Class<T> typeParameterClass;
    final DexFile dex;
    private Item<T> first;
    private Item<T> last;

    ItemGroup(final DexFile dex, Class<T> typeParameterClass)
    {
        this.dex = dex;
        this.typeParameterClass = typeParameterClass;
    }

    ItemGroup(final DexFile dex, Class<T> typeParameterClass, final int size)
    {
        this.dex = dex;
        this.typeParameterClass = typeParameterClass;
    }

    void add(final File f) throws Exception
    {
        final Item<T> item = new Item<T>(typeParameterClass, dex, f);

        if(first == null)
        {
            first = last = item;
        }
        else
        {
            last.next = item;
            last = item;
        }

        item.thiz.added();
    }

    T get(final int index)
    {
        return null;
    }

    @Override
    public void init(DexFile dex, final File f)
    {

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
}
