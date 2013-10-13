package com.oskovran.andride.item;

class ItemHelper
{
    static <T extends ItemInterface> ItemGroup<T> news(final Class<T> clazz, final DexFile dex, final File f, final int size) throws Exception
    {
        final ItemGroup<T> itemGroup = new ItemGroup<T>(dex, clazz, size);

        for(int i = 0; i < size; i++)
        {
            itemGroup.add(f);
        }

        return itemGroup;
    }

    // TODO move to ItemGroup
    /*
    static StringId newStringDataOut(final DexFile dex, final File f, final int length) throws Exception
    {
        if(recycleStringData == null)
        {
            return new StringId(f);
        }

        StringId stringData = recycleStringData;

        recycleStringData = (StringId) stringData.next;

        stringData.prev = stringData.next = null;

        stringData.init(f);

        return stringData;
    }
    */

}
