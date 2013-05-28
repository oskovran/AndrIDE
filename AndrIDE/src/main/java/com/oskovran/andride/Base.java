package com.oskovran.andride;

/**
 *
 * @author Ondra
 */

abstract class Base {

    Object E;
    protected Base prev;
    protected Base next;

    protected abstract void added();
    protected abstract void inserted();
    protected abstract void removed();
    protected abstract void write(File f);

    private void insert(Base itemSource) {
        Base base2 = itemSource;
        prev = base2.prev;
        next = base2;
        prev.next = this;
        base2.prev = this;
        inserted();
    }

    private void remove() {
        if(prev != null) {
                prev.next = next;
        }
        if(next != null) {
                next.prev = prev;
        }
        removed();
    }
}
