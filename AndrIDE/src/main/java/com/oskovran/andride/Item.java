package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

abstract class Item extends Base {
    private int index;
    private int refCounter;

    protected void added() {//??? (Item)
            index = (prev == null) ? 0 : ((Item) prev).index + 1;
    }

    protected void inserted() {
            reindex();
    }

    protected void removed() {
            reindex();
    }

    private void reindex() {//??? (Item)
            if(prev == null) {
                    index = 0;
            }
            Item item = this;
            do {
                    item = (Item) item.next;
                    if(item == null) {
                            break;
                    }
                    item.index = ((Item) item.prev).index + 1;
            } while(true);
    }
}
