package com.oskovran.andride;

/**
 *
 * @author Ondra
 */


class Chain<E> extends Base {

    private Base first = null;
    private Base last = null;

    void add(E base) {
        Base l_base = (Base) base;
        if(first == null) {
            first = last = l_base;
        } else {
            l_base.prev = last;
            last.next = l_base;
            last = l_base;
        }
        l_base.added();
    }

    E get(int index) {
        Base item = first;
        for(int i = 0; i <= index; i++) {
                item = item.next;
        }
        return (E) item;
    }

    protected void added() {

    }

    protected void inserted() {

    }

    protected void removed() {

    }

    protected void write(File f) {
        Base base_info = first;
        while(base_info != null) {
            base_info.write(f);
            base_info = base_info.next;
        }
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();

        Base base_info = first;
        while(base_info != null) {
            sb.append(base_info).append("\n").append("\n");
            base_info = base_info.next;
        }
        
        return sb.toString();
    }
}