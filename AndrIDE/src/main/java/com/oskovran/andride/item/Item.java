package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */

final class Item<T extends ItemInterface>
{
    Object E;

    Item<T> prev;
    Item<T> next;
    final T thiz;

    Item(Class<T> clazz, final DexFile dex, final File f) throws Exception
    {
        this.thiz = clazz.newInstance();
        this.thiz.init(dex, f);
    }

    final void insert(Item<T> item)
    {
        if(item != null)
        {
            item.prev = this;
            item.next = next;

            if(next != null)
            {
                next.prev = this;
            }

            next = item;

            item.thiz.inserted();
        }
    }

    final void remove()
    {
        if(prev != null)
        {
            prev.next = next;
        }

        if(next != null)
        {
            next.prev = prev;
        }

        thiz.removed();
    }
}
