package com.oskovran.andride.item;

/**
 *
 * @author Ondra
 */
class Code implements ItemInterface
{
    //private int registers_size;
    //private int ins_size;
    //private int outs_size;
    //private int tries_size;
    //private int debug_info_off;
    //private int insns_size;
    ByteCode insns;
    //int padding;
    ItemGroup<Try> tries;
    EncodedCatchHandlerList handlers;

    @Override
    public void init(DexFile dex, File f) throws Exception
    {
    	int registers_size = f.readUshort();
    	int ins_size = f.readUshort();
    	int outs_size = f.readUshort();
    	int tries_size = f.readUshort();
    	int debug_info_off = f.readInt();
    	int insns_size = f.readInt();
    	insns = new ByteCode(f, insns_size);
    	if(tries_size != 0)
        {
            //padding;
            if((insns_size & 1) == 1)
            {
                f.readUshort();
            }

            tries = ItemHelper.news(Try.class, dex, f, tries_size);
            handlers = EncodedCatchHandlerList.class.newInstance();
            handlers.init(dex, f);
    	}
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

    private class Try implements ItemInterface
    {
    	int start_addr;
    	int insn_count;
    	EncodedCatchHandler handler_off;

        @Override
        public void init(DexFile dex, File f) throws Exception
        {
            start_addr = f.readInt();
            insn_count = f.readUshort();

            f.readUshort();
            //f.push(f.readUshort());
            //handler_off = new EncodedCatchHandler(f);
            //f.pop();
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

    private class EncodedCatchHandlerList implements ItemInterface
    {
    	int size;
        ItemGroup<EncodedCatchHandler> list;

        @Override
        public void init(DexFile dex, File f) throws Exception
        {
            size = f.readUleb128();
            list = ItemHelper.news(EncodedCatchHandler.class, dex, f, size);
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

    private class EncodedCatchHandler implements ItemInterface
    {
    	int size;
        ItemGroup<EncodedTypeAddrPair> handlers;
    	int catch_all_addr;

        @Override
        public void init(DexFile dex, File f) throws Exception
        {
            size = f.readSleb128();
            ItemHelper.news(EncodedTypeAddrPair.class, dex, f, size < 0 ? -size : size);

            if(size <= 0)
            {
                catch_all_addr = f.readUleb128();
            }
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

    private class EncodedTypeAddrPair implements ItemInterface
    {
    	int type_idx;
    	int addr;

        @Override
        public void init(DexFile dex, File f) throws Exception
        {
            type_idx = f.readUleb128();
            addr = f.readUleb128();
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
}
