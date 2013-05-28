package com.oskovran.andride;

/**
 *
 * @author Ondra
 */
class Code extends Item {

    //private int registers_size;
    //private int ins_size;
    //private int outs_size;
    //private int tries_size;
    //private int debug_info_off;
    //private int insns_size;
    ByteCode insns;
    //int padding;
    Chain<Try> tries;
    EncodedCatchHandlerList handlers;
    
    Code(File f) throws Exception {
    	int registers_size = f.readUshort();
    	int ins_size = f.readUshort();
    	int outs_size = f.readUshort();
    	int tries_size = f.readUshort();
    	int debug_info_off = f.readInt();
    	int insns_size = f.readInt();
    	insns = new ByteCode(f, insns_size);
    	if(tries_size != 0) {
            if((insns_size & 1) == 1) {//padding;
                f.readUshort();
            }
            tries = new Chain<Try>();
            for(int i = 0; i < tries_size; i++) {
                tries.add(new Try(f));
            }
            handlers = new EncodedCatchHandlerList(f);
    	}
    }

    protected void write(File f) {
        //f.writeInt(string_data_off);
    }

    private class Try extends Item {
    	
    	int start_addr;
    	int insn_count;
    	EncodedCatchHandler handler_off;

        private Try(File f) throws Exception {
            start_addr = f.readInt();
            insn_count = f.readUshort();

            f.readUshort();
            //f.push(f.readUshort());
            //handler_off = new EncodedCatchHandler(f);
            //f.pop();
        }

        protected void write(File f) {

        }
    }

    private class EncodedCatchHandlerList extends Item {
    	
    	int size;
    	Chain<EncodedCatchHandler> list;

        private EncodedCatchHandlerList(File f) throws Exception {
            size = f.readUleb128();

            list = new Chain<EncodedCatchHandler>();
            for(int i = 0; i < size; i++) {
                list.add(new EncodedCatchHandler(f));
            }
        }

        protected void write(File f) {

        }
    }

    private class EncodedCatchHandler extends Item {
    	
    	int size;
    	Chain<EncodedTypeAddrPair> handlers;
    	int catch_all_addr;

        private EncodedCatchHandler(File f) throws Exception {
            size = f.readSleb128();

            handlers = new Chain<EncodedTypeAddrPair>();
            for(int i = 0; i < (size < 0 ? -size : size); i++) {
                handlers.add(new EncodedTypeAddrPair(f));
            }
            if(size <= 0) {
                catch_all_addr = f.readUleb128();
            }
        }

        protected void write(File f) {

        }
    }

    private class EncodedTypeAddrPair extends Item {
    	
    	int type_idx;
    	int addr;

        private EncodedTypeAddrPair(File f) throws Exception {
            type_idx = f.readUleb128();
            addr = f.readUleb128();
        }

        protected void write(File f) {

        }
    }
}
