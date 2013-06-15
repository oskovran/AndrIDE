package com.oskovran.andride;

class ByteCode extends ItemChain {
    
    ByteCode(File f, int insns_size) {
        add(new ByteCodeGrupe(f, insns_size));
    }

    private class ByteCodeGrupe extends ItemChain {

        ByteCodeGrupe(File f, int insns_size) {

            System.out.println ("BCG_______________________________"+insns_size);
            
            int PC0 = f.PC;

            for (int i = 0; ; i++) {
            	int tmp = (f.PC - PC0) / 2;//TODO 
            	if(tmp == insns_size) {
                    break;
            	}
                int instruction = f.readUshort();
                switch (instruction & 255) {
                    case 0x00:
                        Format1 ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" nop");
                        break;
                    case 0x01:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x02:
                        Format2 ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x03:
                        Format3 ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case 0x04:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-wide v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x05:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move-wide/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x06:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move-wide/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case 0x07:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-object v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x08:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" move-object/from16 v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x09:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" move-object/16 v"+ins3.CCCC()+" v"+ins3.EEEE());
                        break;
                    case 0x0a:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result v"+ins1.AA());
                        break;
                    case 0x0b:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result-wide v"+ins1.AA());
                        break;
                    case 0x0c:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-result-object v"+ins1.AA());
                        break;
                    case 0x0d:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" move-exception v"+ins1.AA());
                        break;
                    case 0x0e:
                        add(new Format1(instruction));
                        System.out.println(" return void");
                        break;
                    case 0x0f:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return v"+ins1.AA());
                        break;
                    case 0x10:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return-wide v"+ins1.AA());
                        break;
                    case 0x11:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" return-object v"+ins1.AA());
                        break;
                    case 0x12:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" const/4 v"+ins1.A()+" #+"+ins1.B());
                        break;
                    case 0x13:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const/16 v"+ins2.AA()+" #+"+ins2.CCCC());
                        break;
                    case 0x14:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const v"+ins3.AA()+" #+"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case 0x15:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const/high16 v"+ins2.AA()+" #+"+ins2.CCCC()+" 0000");
                        break;
                    case 0x16:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-wide/16 v"+ins2.AA()+" #+"+ins2.CCCC());
                        break;
                    case 0x17:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const-wide/32 v"+ins3.AA()+" #+"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case 0x18:
                        Format5 ins5 = new Format5(instruction, f.readUshort(), f.readUshort(), f.readUshort(), f.readUshort());
                        add(ins5);
                        System.out.println(" const-wide/32 v"+ins5.AA()+" #+"+ins5.CCCC()+" "+ins5.EEEE()+" #+"+ins5.IIII()+" "+ins5.JJJJ());
                        break;
                    case 0x19:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-wide/high16 v"+ins2.AA()+" #+"+ins2.CCCC()+" 0000");
                        break;
                    case 0x1a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-string v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x1b:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" const-string/jumbo v"+ins3.AA()+" @"+ins3.CCCC()+" "+ins3.EEEE());
                        break;
                    case 0x1c:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" const-class v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x1d:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" monitor-enter v"+ins1.AA());
                        break;
                    case 0x1e:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" monitor-exit v"+ins1.AA());
                        break;
                    case 0x1f:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" check-cast v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x20:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" instance-of v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x21:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" array-length v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x22:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" new-instance v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x23:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" new-array v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x26:
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
                    case 0x27:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" throw v"+ins1.AA());
                        break;
                    case 0x28:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" goto +"+(byte)ins1.AA());
                        break;
                    case 0x29:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" goto/16 +"+(short)ins2.CCCC());
                        break;
                    case 0x2b:
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
                    case 0x2c:
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
                    case 0x2d:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpl-float (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x2e:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpg-float (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x2f:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpl-double (lt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x30:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmpg-double (gt bias) v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x31:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" cmp-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x32:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-eq v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x33:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ne v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x34:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-lt v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x35:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ge v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x36:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gt v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x37:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-le v"+ins2.A()+" v"+ins2.B()+" +"+ins2.CCCC());
                        break;
                    case 0x38:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-eqz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x39:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-nez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x3a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-ltz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x3b:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x3c:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-gtz v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x3d:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" if-lez v"+ins2.AA()+" +"+ins2.CCCC());
                        break;
                    case 0x44:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x45:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-wide v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x46:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-object v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x47:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-boolean v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x48:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-byte v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x49:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-char v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aget-short v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4b:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4c:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-wide v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4d:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" get-object v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4e:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-boolean v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x4f:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-byte v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x50:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-char v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x51:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" aput-short v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x52:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x53:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-wide v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x54:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-object v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x55:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-boolean v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x56:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-byte v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x57:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-char v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x58:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iget-short v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x59:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-wide v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5b:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-object v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5c:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-boolean v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5d:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-byte v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5e:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-char v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x5f:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" iput-short v"+ins2.A()+" v"+ins2.B()+" @"+ins2.CCCC());
                        break;
                    case 0x60:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x61:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-wide v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x62:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-object v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x63:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-boolean v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x64:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-byte v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x65:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-char v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x66:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sget-short v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x67:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x68:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-wide v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x69:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-object v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x6a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sput-boolean v"+ins2.AA()+" @"+ins2.CCCC());
                        break;
                    case 0x6e:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-virtual B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case 0x6f:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-super B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case 0x70:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-direct B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case 0x71:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-static B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case 0x72:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-interface B="+ins3.B()+" @"+ins3.CCCC()+" ");
                        break;
                    case 0x74:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-virtual/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case 0x75:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-super/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case 0x76:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-direct/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case 0x77:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-static/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case 0x78:
                        ins3 = new Format3(instruction, f.readUshort(), f.readUshort());
                        add(ins3);
                        System.out.println(" invoke-interface/range AA="+ins3.AA()+" BBBB="+ins3.CCCC()+" CCCC="+ins3.EEEE()+" ");
                        break;
                    case 0x7b:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x7c:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" not-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x7d:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x7e:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" not-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x7f:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x80:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" neg-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x81:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x82:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x83:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x84:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x85:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x86:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" long-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x87:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x88:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x89:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" float-to-double v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8a:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-int v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8b:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-long v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8c:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" double-to-float v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8d:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-byte v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8e:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-char v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x8f:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" int-to-short v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0x90:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x91:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x92:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x93:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x94:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x95:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x96:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x97:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x98:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x99:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9a:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-int v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9b:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9c:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9d:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9e:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0x9f:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa0:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa1:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa2:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa3:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa4:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa5:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-long v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa6:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa7:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xa9:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xaa:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-float v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xab:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xac:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" sub-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xad:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xae:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xaf:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-double v"+ins2.AA()+" v"+ins2.CC()+" v"+ins2.DD());
                        break;
                    case 0xb0:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb1:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb2:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb3:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb4:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb5:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" and-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb6:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" or-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb7:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" xor-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb8:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shl-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xb9:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shr-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xba:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" ushr-int/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xbb:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xbc:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xbd:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xbe:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xbf:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc0:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" and-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc1:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" or-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc2:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" xor-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc3:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shl-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc4:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" shr-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc5:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" ushr-long/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc6:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc7:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc8:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xc9:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xca:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-float/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xcb:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" add-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xcc:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" sub-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xcd:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" mul-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xce:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" div-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xcf:
                        ins1 = new Format1(instruction);
                        add(ins1);
                        System.out.println(" rem-double/2addr v"+ins1.A()+" v"+ins1.B());
                        break;
                    case 0xd0:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd1:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rsub-int (reverse subtract) v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd2:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd3:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd4:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd5:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd6:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd7:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int/lit16 v"+ins2.A()+" v"+ins2.B()+" #+"+ins2.CCCC());
                        break;
                    case 0xd8:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" add-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xd9:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rsub-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xda:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" mul-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xdb:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" div-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xdc:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" rem-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xdd:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" and-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xde:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" or-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xdf:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" xor-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xe0:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shl-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xe1:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" shr-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    case 0xe2:
                        ins2 = new Format2(instruction, f.readUshort());
                        add(ins2);
                        System.out.println(" ushr-int/lit8 v"+ins2.AA()+" v"+ins2.CC()+" #+"+ins2.DD());
                        break;
                    default:
                        System.out.println("***i*** " + instruction);
                        System.out.println(" **o*** " + (instruction & 255));
                }
            }
        }

        private abstract class Format extends Item {

            protected void added(int index) {
            }

            protected void inserted(int index) {
            }

            protected void removed(int index) {
            }

            protected void write(File f) {
            }
        }

        private class Format1 extends Format {

            final int ins1;

            Format1(int ins1) {
                this.ins1 = ins1;
            }

            final int A() {
                return (ins1 >> 8) & 15;
            }

            final int B() {
                return (ins1 >> 12) & 15;
            }

            final int AA() {
                return ins1 >> 8;
            }
        }

        private class Format2 extends Format1 {

            final int ins2;

            Format2(int ins1, int ins2) {
                super(ins1);
                this.ins2 = ins2;
            }

            final int CC() {
                return ins2 & 255;
            }

            final int DD() {
                return ins2 >> 8;
            }

            final int CCCC() {
                return ins2;
            }
        }

        private class Format3 extends Format2 {

            final int ins3;

            Format3(int ins1, int ins2, int ins3) {
                super(ins1, ins2);
                this.ins3 = ins3;
            }

            final int E() {
                return ins3 & 15;
            }

            final int F() {
                return (ins3 >> 4) & 15;
            }

            final int G() {
                return (ins3 >> 8) & 15;
            }

            final int H() {
                return (ins3 >> 12) & 15;
            }

            final int EEEE() {
                return ins3;
            }
        }

        private class Format5 extends Format3 {

            final int ins4;
            final int ins5;

            Format5(int ins1, int ins2, int ins3, int ins4, int ins5) {
                super(ins1, ins2, ins3);
                this.ins4 = ins4;
                this.ins5 = ins5;
            }

            final int IIII() {
                return ins4;
            }

            final int JJJJ() {
                return ins5;
            }
        }
        
        @Override
        protected void added(int index) {
            
        }
    }
}