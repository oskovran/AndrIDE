package com.oskovran.andride.item;

public interface ItemInterface
{
    // TODO desc
    public void init(final DexFile dex, final File f) throws Exception;
    public void added();
    public void inserted();
    public void removed();
    public void write(File f);
}