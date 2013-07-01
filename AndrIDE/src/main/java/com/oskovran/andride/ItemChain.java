package com.oskovran.andride;

/**
 * Created by oskovran on 6/2/13.
 */
class ItemChain<T extends Item> extends Item
{
    Item first;
    Item last;

    private final Item items[] = new Item[5120];

    private int valid = 0;

    void add(T item)
    {
        if(first == null)
        {
            first = last = item;
        }
        else
        {
            last.insert(item);
            last = item;
            item.added(this.index);
        }
    }

    T get(int index)
    {
        Item item = first;

        for(int i = 0; i < index; i++)
        {
            item = item.next;
        }

        return (T) item;
    }

    protected void added(int index)
    {

    }

    protected final void inserted(int index)
    {

    }

    protected final void removed(int index)
    {

    }

    protected void write(File f) {

    }

}
