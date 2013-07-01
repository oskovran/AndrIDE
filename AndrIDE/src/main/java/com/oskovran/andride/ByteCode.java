package com.oskovran.andride;

class ByteCode extends ItemChain implements dalvik.bytecode.Opcodes
{
    ByteCode(File f, int insns_size)
    {
        add(new ByteCodeGrupe(f, insns_size));
    }

    private class ByteCodeGrupe extends ItemChain
    {
        ByteCodeGrupe(File f, int insns_size)
        {
            System.out.println ("BCG_______________________________"+insns_size);
            
            int PC0 = f.PC;

            for (int i = 0; ; i++)
            {
            	int tmp = (f.PC - PC0) / 2;//TODO 

            	if(tmp == insns_size)
                {
                    break;
            	}

                int instruction = f.readUshort();

                switch (instruction & 255) {
                    case OP_NOP:
                        Format1 ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" nop");
                        break;
                    case OP_MOVE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MOVE_FROM16:
                        Format2 ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_MOVE_16:
                        Format3 ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case OP_MOVE_WIDE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-wide v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MOVE_WIDE_FROM16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move-wide/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_MOVE_WIDE_16:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move-wide/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case OP_MOVE_OBJECT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-object v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MOVE_OBJECT_FROM16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move-object/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_MOVE_OBJECT_16:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move-object/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case OP_MOVE_RESULT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result v"+ins1.AA());
                        break;
                    case OP_MOVE_RESULT_WIDE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result-wide v"+ins1.AA());
                        break;
                    case OP_MOVE_RESULT_OBJECT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result-object v"+ins1.AA());
                        break;
                    case OP_MOVE_EXCEPTION:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-exception v"+ins1.AA());
                        break;
                    case OP_RETURN_VOID:
                        add(new Format1(instruction));
                        System.out.println(" return void");
                        break;
                    case OP_RETURN:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return v"+ins1.AA());
                        break;
                    case OP_RETURN_WIDE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return-wide v"+ins1.AA());
                        break;
                    case OP_RETURN_OBJECT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return-object v"+ins1.AA());
                        break;
                    case OP_CONST_4:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" const/4 v"+ins1.A()+" #+"+ins1.B());
                        break;
                    case OP_CONST_16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const/16 v"+ins2.AA()+" #+"+ins2.CCCC());
                        break;
                    case OP_CONST:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const v"+ins3.AA()+" #+"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case OP_CONST_HIGH16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const/high16 v"+ins2.AA()+" #+"+ins2.CCCC()+" 0000");
                        break;
                    case OP_CONST_WIDE_16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-wide/16 v"+ins2.AA()+" #+"+ins2.CCCC());
                        break;
                    case OP_CONST_WIDE_32:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const-wide/32 v"+ins3.AA()+" #+"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case OP_CONST_WIDE:
                        Format5 ins5 = new Format5(instruction, f.readUshort(), f.readUshort(), f.readUshort(), f.readUshort());
                        add(ins5);
                        System.out.println(" const-wide v"+ins5.AA()+" #+"+ins5.CCCC()+" "+ins5.EEEE()+" #+"+ins5.IIII()+" "+ins5.JJJJ());
                        break;
                    case OP_CONST_WIDE_HIGH16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-wide/high16 v"+ins2.AA()+" #+"+ins2.CCCC()+" 0000");
                        break;
                    case OP_CONST_STRING:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-string v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_CONST_STRING_JUMBO:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const-string/jumbo v"+ins3.AA()+" @"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case OP_CONST_CLASS:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-class v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_MONITOR_ENTER:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" monitor-enter v"+ins1.AA());
                        break;
                    case OP_MONITOR_EXIT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" monitor-exit v"+ins1.AA());
                        break;
                    case OP_CHECK_CAST:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" check-cast v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_INSTANCE_OF:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" instance-of v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_ARRAY_LENGTH:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" array-length v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NEW_INSTANCE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" new-instance v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_NEW_ARRAY:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" new-array v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_FILLED_NEW_ARRAY:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" fill-array-data v"+ins3.AA()+" @"+(ins3.CCCC() |  (ins3.EEEE() << 16 )));
                        f.push(f.PC+(ins3.CCCC() |  (ins3.EEEE() << 16 ))*2-6);
                        System.out.println("  ident="+f.readUshort());
                        int element_width = f.readUshort();
                        System.out.println("  element_width="+element_width);
                        int size = f.readInt();
                        System.out.println("  size="+size);
                        f.pop();
                        insns_size -= (size * element_width + 1) / 2 + 4;
                        break;
                    case OP_FILLED_NEW_ARRAY_RANGE:
                        throw new RuntimeException("OP_FILLED_NEW_ARRAY_RANGE");
                    case OP_FILL_ARRAY_DATA:
                        throw new RuntimeException("OP_FILL_ARRAY_DATA");
                    case OP_THROW:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" throw v"+ins1.AA());
                        break;
                    case OP_GOTO:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" goto +"+(byte)ins1.AA());
                        break;
                    case OP_GOTO_16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" goto/16 +"+(short)ins2.CCCC());
                        break;
                    case OP_GOTO_32:
                        throw new RuntimeException("OP_GOTO_32");
                    case OP_PACKED_SWITCH:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" packed-switch v"+ins3.AA()+" @"+(ins3.CCCC() |  (ins3.EEEE() << 16 )));
                        f.push(f.PC+(ins3.CCCC() | (ins3.EEEE() << 16 ))*2-6);
                        System.out.println("  ident="+f.readUshort());
                        size = f.readUshort();
                        System.out.println("  size="+size);
                        System.out.println("  first_key="+f.readInt());
                        for(int ii = 0; ii < size; ii++) {
                            System.out.println("  target("+ii+")="+f.readInt());
                        }
                        f.pop();
                        insns_size -= (size * 2) + 4;
                        break;
                    case OP_SPARSE_SWITCH:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" sparse-switch v"+ins3.AA()+" @"+(ins3.CCCC() |  (ins3.EEEE() << 16 )));
                        f.push(f.PC+(ins3.CCCC() |  (ins3.EEEE() << 16 ))*2-6);
                        System.out.println("  ident="+f.readUshort());
                        size = f.readUshort();
                        System.out.println("  size="+size);
                        for(int ii = 0; ii < size; ii++) {
                        	System.out.println("  key("+ii+")="+f.readInt());
                        }
                        for(int ii = 0; ii < size; ii++) {
                        	System.out.println("  target("+ii+")="+f.readInt());
                        }
                        f.pop();
                        insns_size -= (size * 4) + 2;
                        break;
                    case OP_CMPL_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpl-float (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_CMPG_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpg-float (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_CMPL_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpl-double (lt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_CMPG_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpg-double (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_CMP_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmp-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_IF_EQ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-eq v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_NE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ne v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_LT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-lt v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_GE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ge v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_GT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gt v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_LE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-le v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_EQZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-eqz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_NEZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-nez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_LTZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ltz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_GEZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_GTZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gtz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_IF_LEZ:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-lez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case OP_AGET:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-wide v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-object v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-boolean v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_BYTE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-byte v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_CHAR:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-char v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AGET_SHORT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-short v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-wide v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" get-object v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-boolean v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_BYTE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-byte v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_CHAR:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-char v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_APUT_SHORT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-short v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_IGET:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-wide v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-object v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-boolean v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_BYTE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-byte v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_CHAR:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-char v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IGET_SHORT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-short v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-wide v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-object v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-boolean v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_BYTE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-byte v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_CHAR:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-char v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_IPUT_SHORT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-short v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-wide v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-object v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-boolean v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_BYTE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-byte v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_CHAR:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-char v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SGET_SHORT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-short v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SPUT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SPUT_WIDE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-wide v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SPUT_OBJECT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-object v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SPUT_BOOLEAN:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-boolean v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case OP_SPUT_BYTE:
                        throw new RuntimeException("OP_SPUT_BYTE");
                    case OP_SPUT_CHAR:
                        throw new RuntimeException("OP_SPUT_CHAR");
                    case OP_SPUT_SHORT:
                        throw new RuntimeException("OP_SPUT_SHORT");
                    case OP_INVOKE_VIRTUAL:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-virtual B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case OP_INVOKE_SUPER:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-super B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case OP_INVOKE_DIRECT:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-direct B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case OP_INVOKE_STATIC:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-static B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case OP_INVOKE_INTERFACE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-interface B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case OP_INVOKE_VIRTUAL_RANGE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-virtual/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case OP_INVOKE_SUPER_RANGE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-super/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case OP_INVOKE_DIRECT_RANGE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-direct/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case OP_INVOKE_STATIC_RANGE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-static/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case OP_INVOKE_INTERFACE_RANGE:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-interface/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case OP_NEG_INT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NOT_INT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" not-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NEG_LONG:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NOT_LONG:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" not-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NEG_FLOAT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_NEG_DOUBLE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_LONG:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_FLOAT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_DOUBLE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_LONG_TO_INT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_LONG_TO_FLOAT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_LONG_TO_DOUBLE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_FLOAT_TO_INT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_FLOAT_TO_LONG:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_FLOAT_TO_DOUBLE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DOUBLE_TO_INT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DOUBLE_TO_LONG:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DOUBLE_TO_FLOAT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_BYTE:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-byte v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_CHAR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-char v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_INT_TO_SHORT:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-short v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_ADD_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SUB_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_MUL_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_DIV_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_REM_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AND_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_OR_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_XOR_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SHL_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SHR_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_USHR_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_ADD_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SUB_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_MUL_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_DIV_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_REM_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_AND_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_OR_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_XOR_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SHL_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SHR_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_USHR_LONG:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_ADD_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SUB_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_MUL_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_DIV_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_REM_FLOAT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_ADD_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_SUB_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_MUL_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_DIV_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_REM_DOUBLE:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case OP_ADD_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SUB_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MUL_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DIV_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_REM_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_AND_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" and-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_OR_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" or-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_XOR_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" xor-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SHL_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shl-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SHR_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shr-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_USHR_INT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" ushr-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_ADD_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SUB_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MUL_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DIV_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_REM_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_AND_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" and-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_OR_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" or-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_XOR_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" xor-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SHL_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shl-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SHR_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shr-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_USHR_LONG_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" ushr-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_ADD_FLOAT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SUB_FLOAT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MUL_FLOAT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DIV_FLOAT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_REM_FLOAT_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_ADD_DOUBLE_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_SUB_DOUBLE_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_MUL_DOUBLE_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_DIV_DOUBLE_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_REM_DOUBLE_2ADDR:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case OP_ADD_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_RSUB_INT:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rsub-int (reverse subtract) v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_MUL_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_DIV_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_REM_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_AND_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_OR_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_XOR_INT_LIT16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case OP_ADD_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_RSUB_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rsub-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_MUL_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_DIV_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_REM_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_AND_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_OR_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_XOR_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_SHL_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_SHR_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_USHR_INT_LIT8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case OP_IGET_WIDE_VOLATILE:
                    case OP_IPUT_WIDE_VOLATILE:
                    case OP_SGET_WIDE_VOLATILE:
                    case OP_SPUT_WIDE_VOLATILE:
                    case OP_BREAKPOINT:
                    case OP_EXECUTE_INLINE:
                    case OP_EXECUTE_INLINE_RANGE:
                    case OP_INVOKE_DIRECT_EMPTY:
                    case OP_IGET_QUICK:
                    case OP_IGET_WIDE_QUICK:
                    case OP_IGET_OBJECT_QUICK:
                    case OP_IPUT_QUICK:
                    case OP_IPUT_WIDE_QUICK:
                    case OP_IPUT_OBJECT_QUICK:
                    case OP_INVOKE_VIRTUAL_QUICK:
                    case OP_INVOKE_VIRTUAL_QUICK_RANGE:
                    case OP_INVOKE_SUPER_QUICK:
                    case OP_INVOKE_SUPER_QUICK_RANGE:
                        throw new RuntimeException(instruction + " This constant was deprecated in API level 11.");
                    default:
                        System.out.println("***i*** " + instruction);
                        System.out.println(" **o*** " + (instruction & 255));
                }
            }
        }

        private abstract class Format extends Item
        {
            protected void added(int index)
            {
            }

            protected void inserted(int index)
            {
            }

            protected void removed(int index)
            {
            }

            protected void write(File f)
            {
            }
        }

        private class Format1 extends Format
        {
            final int ins1;

            Format1(int ins1)
            {
                this.ins1 = ins1;
            }

            final int A()
            {
                return (ins1 >> 8) & 15;
            }

            final int B()
            {
                return (ins1 >> 12) & 15;
            }

            final int AA()
            {
                return ins1 >> 8;
            }
        }

        private class Format2 extends Format1
        {
            final int ins2;

            Format2(int ins1, int ins2)
            {
                super(ins1);
                this.ins2 = ins2;
            }

            final int CC()
            {
                return ins2 & 255;
            }

            final int DD()
            {
                return ins2 >> 8;
            }

            final int CCCC()
            {
                return ins2;
            }
        }

        private class Format3 extends Format2
        {
            final int ins3;

            Format3(int ins1, int ins2, int ins3)
            {
                super(ins1, ins2);
                this.ins3 = ins3;
            }

            final int E()
            {
                return ins3 & 15;
            }

            final int F()
            {
                return (ins3 >> 4) & 15;
            }

            final int G()
            {
                return (ins3 >> 8) & 15;
            }

            final int H()
            {
                return (ins3 >> 12) & 15;
            }

            final int EEEE()
            {
                return ins3;
            }
        }

        private class Format5 extends Format3
        {
            final int ins4;
            final int ins5;

            Format5(int ins1, int ins2, int ins3, int ins4, int ins5)
            {
                super(ins1, ins2, ins3);
                this.ins4 = ins4;
                this.ins5 = ins5;
            }

            final int IIII()
            {
                return ins4;
            }

            final int JJJJ()
            {
                return ins5;
            }
        }
        
        @Override
        protected void added(int index)
        {
            
        }
    }
}