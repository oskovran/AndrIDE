package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

abstract class Item
{
    Object E;

    protected Item prev;
    protected Item next;

    protected int index;

    protected void added(int index) {}
    protected void inserted(int index) {}
    protected void removed(int index) {}

    protected abstract void write(File f);

    final void insert(Item item)
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

            inserted(index);
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

        removed(index);
    }
}
