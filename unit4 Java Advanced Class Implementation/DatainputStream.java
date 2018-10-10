    package java.io;  
     
    public class DataInputStream extends FilterInputStream implements DataInput {  
     
        // ���캯����  
        public DataInputStream(InputStream in) {  
            super(in);  
        }  
     
        private byte bytearr[] = new byte[80];  
        private char chararr[] = new char[80];  
     
        // �ӡ��������������ж�ȡһ���ֽ�  
        public final int read(byte b[]) throws IOException {  
            return in.read(b, 0, b.length);  
        }  
     
        // �ӡ��������������ж�ȡ���ݲ��洢���ֽ�����b�С�  
        // off���ֽ�����b�п�ʼ�洢Ԫ�ص���ʼλ�á�  
        // len�Ƕ�ȡ�ֽڵĸ�����  
        public final int read(byte b[], int off, int len) throws IOException {  
            return in.read(b, off, len);  
        }  
     
        // �ӡ��������������ж�ȡ���ݲ������ֽ�����b�У�û����������b��һֱ��ȡ��ֱ������λ�á�  
        // ���ֽ�����b��λ��0��ʼ�洢�����Ҷ�ȡ���ֽڸ�������b�ĳ��ȡ�  
        public final void readFully(byte b[]) throws IOException {  
            readFully(b, 0, b.length);  
        }  
     
        // �ӡ��������������ж�ȡ���ݲ��洢���ֽ�����b�У���û��ȡlen���ֽڣ�ֱ��һֱ��ȡֱ����ȡ��len���ֽ�Ϊֹ��  
        public final void readFully(byte b[], int off, int len) throws IOException {  
            if (len < 0)  
                throw new IndexOutOfBoundsException();  
            int n = 0;  
            while (n < len) {  
                int count = in.read(b, off + n, len - n);  
                if (count < 0)  
                    throw new EOFException();  
                n += count;  
            }  
        }  
     
        // ����n���ֽ�  
        public final int skipBytes(int n) throws IOException {  
            int total = 0;  
            int cur = 0;  
     
            while ((total<n) && ((cur = (int) in.skip(n-total)) > 0)) {  
                total += cur;  
            }  
     
            return total;  
        }  
     
        // �ӡ��������������ж�ȡboolean���͵�ֵ  
        public final boolean readBoolean() throws IOException {  
            int ch = in.read();  
            if (ch < 0)  
                throw new EOFException();  
            return (ch != 0);  
        }  
     
        // �ӡ��������������ж�ȡByte���͵�ֵ  
        public final byte readByte() throws IOException {  
            int ch = in.read();  
            if (ch < 0)  
                throw new EOFException();  
            return (byte)(ch);  
        }  
     
        // �ӡ��������������ж�ȡ���޷��ŵ�Byte���͡���ֵ������ȡֵΪ������byteֵ  
        public final int readUnsignedByte() throws IOException {  
            int ch = in.read();  
            if (ch < 0)  
                throw new EOFException();  
            return ch;  
        }  
     
        // �ӡ��������������ж�ȡ��short���͡���ֵ  
        public final short readShort() throws IOException {  
            int ch1 = in.read();  
            int ch2 = in.read();  
            if ((ch1 | ch2) < 0)  
                throw new EOFException();  
            return (short)((ch1 << 8) + (ch2 << 0));  
        }  
     
        // �ӡ��������������ж�ȡ���޷��ŵ�short���͡���ֵ  
        public final int readUnsignedShort() throws IOException {  
            int ch1 = in.read();  
            int ch2 = in.read();  
            if ((ch1 | ch2) < 0)  
                throw new EOFException();  
            return (ch1 << 8) + (ch2 << 0);  
        }  
     
        // �ӡ��������������ж�ȡ��char���͡���ֵ  
        public final char readChar() throws IOException {  
            int ch1 = in.read();  
            int ch2 = in.read();  
            if ((ch1 | ch2) < 0)  
                throw new EOFException();  
            return (char)((ch1 << 8) + (ch2 << 0));  
        }  
     
        // �ӡ��������������ж�ȡ��int���͡���ֵ  
        public final int readInt() throws IOException {  
            int ch1 = in.read();  
            int ch2 = in.read();  
            int ch3 = in.read();  
            int ch4 = in.read();  
            if ((ch1 | ch2 | ch3 | ch4) < 0)  
                throw new EOFException();  
            return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));  
        }  
     
        private byte readBuffer[] = new byte[8];  
     
        // �ӡ��������������ж�ȡ��long���͡���ֵ  
        public final long readLong() throws IOException {  
            readFully(readBuffer, 0, 8);  
            return (((long)readBuffer[0] << 56) +  
                    ((long)(readBuffer[1] & 255) << 48) +  
                    ((long)(readBuffer[2] & 255) << 40) +  
                    ((long)(readBuffer[3] & 255) << 32) +  
                    ((long)(readBuffer[4] & 255) << 24) +  
                    ((readBuffer[5] & 255) << 16) +  
                    ((readBuffer[6] & 255) <<  8) +  
                    ((readBuffer[7] & 255) <<  0));  
        }  
     
        // �ӡ��������������ж�ȡ��float���͡���ֵ  
        public final float readFloat() throws IOException {  
            return Float.intBitsToFloat(readInt());  
        }  
     
        // �ӡ��������������ж�ȡ��double���͡���ֵ  
        public final double readDouble() throws IOException {  
            return Double.longBitsToDouble(readLong());  
        }  
     
        private char lineBuffer[];  
     
        @Deprecated 
        public final String readLine() throws IOException {  
            char buf[] = lineBuffer;  
     
            if (buf == null) {  
                buf = lineBuffer = new char[128];  
            }  
     
            int room = buf.length;  
            int offset = 0;  
            int c;  
     
    loop:   while (true) {  
                switch (c = in.read()) {  
                  case -1:  
                  case '\n':  
                    break loop;  
     
                  case '\r':  
                    int c2 = in.read();  
                    if ((c2 != '\n') && (c2 != -1)) {  
                        if (!(in instanceof PushbackInputStream)) {  
                            this.in = new PushbackInputStream(in);  
                        }  
                        ((PushbackInputStream)in).unread(c2);  
                    }  
                    break loop;  
     
                  default:  
                    if (--room < 0) {  
                        buf = new char[offset + 128];  
                        room = buf.length - offset - 1;  
                        System.arraycopy(lineBuffer, 0, buf, 0, offset);  
                        lineBuffer = buf;  
                    }  
                    buf[offset++] = (char) c;  
                    break;  
                }  
            }  
            if ((c == -1) && (offset == 0)) {  
                return null;  
            }  
            return String.copyValueOf(buf, 0, offset);  
        }  
     
        // �ӡ��������������ж�ȡ��UTF���͡���ֵ  
        public final String readUTF() throws IOException {  
            return readUTF(this);  
        }  
     
        public final static String readUTF(DataInput in) throws IOException {  
            // �ӡ��������������ж�ȡ���޷��ŵ�short���͡���ֵ��  
            // ע�⣺UTF-8��������ǰ2���ֽ������ݵĳ���  
            int utflen = in.readUnsignedShort();  
            byte[] bytearr = null;  
            char[] chararr = null;  
     
            // ���in�����ǡ���������������  
            // �������ֽ�����bytearr = "����������"�ĳ�Աbytearr  
            //     �����ַ�����chararr = "����������"�ĳ�Աchararr  
            // ����Ļ����½�����bytearr��chararr  
            if (in instanceof DataInputStream) {  
                DataInputStream dis = (DataInputStream)in;  
                if (dis.bytearr.length < utflen){  
                    dis.bytearr = new byte[utflen*2];  
                    dis.chararr = new char[utflen*2];  
                }  
                chararr = dis.chararr;  
                bytearr = dis.bytearr;  
            } else {  
                bytearr = new byte[utflen];  
                chararr = new char[utflen];  
            }  
     
            int c, char2, char3;  
            int count = 0;  
            int chararr_count=0;  
     
            // �ӡ��������������ж�ȡ���ݲ��洢���ֽ�����bytearr�У���bytearr��λ��0��ʼ�洢���洢����Ϊutflen��  
            // ע�⣬�����Ǵ洢���ֽ����飡���Ҷ�ȡ����ȫ�������ݡ�  
            in.readFully(bytearr, 0, utflen);  
     
            // �����ֽ�����bytearr���е����� ������ ���ַ�����chararr����  
            // ע�⣺�����൱�ڡ�Ԥ������������е��ֽڵķ��š�����ΪUTF-8��1-4���ֽڿɱ�ġ�  
            while (count < utflen) {  
                // ��ÿ���ֽ�ת����intֵ  
                c = (int) bytearr[count] & 0xff;  
                // UTF-8�ĵ��ֽ����ݵ�ֵ�����ᳬ��127�����ԣ�����127�����˳���  
                if (c > 127) break;  
                count++;  
                // ��c���浽���ַ�����chararr����  
                chararr[chararr_count++]=(char)c;  
            }  
     
            // �������������е��ֽڵķ���֮�󣬽��������Ǽ�������  
            while (count < utflen) {  
                // �������ִ����2��������  
                // (01) ���ֽ��� ��byte���͡� ת���� ��int���͡���  
                //      ���磬 ��11001010�� ת����int֮���� ��00000000 00000000 00000000 11001010��  
                // (02) �� ��int���͡� ����������4λ  
                //      ���磬 ��00000000 00000000 00000000 11001010�� ����4λ֮�󣬱�� ��00000000 00000000 00000000 00001100��  
                c = (int) bytearr[count] & 0xff;  
                switch (c >> 4) {  
                    // �� UTF-8 �ǵ��ֽڣ��� bytearr[count] ��Ӧ�� ��0xxxxxxx�� ��ʽ��  
                    // �� bytearr[count] ��Ӧ��int���͵�c��ȡֵ��Χ�� 0-7��  
                    case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:  
                        /* 0xxxxxxx*/ 
                        count++;  
                        chararr[chararr_count++]=(char)c;  
                        break;  
     
                    // �� UTF-8 ��˫�ֽڣ��� bytearr[count] ��Ӧ�� ��110xxxxx  10xxxxxx�� ��ʽ�еĵ�һ��������110xxxxx��  
                    // �� bytearr[count] ��Ӧ��int���͵�c��ȡֵ��Χ�� 12-13��  
                    case 12: case 13:  
                        /* 110x xxxx   10xx xxxx*/ 
                        count += 2;  
                        if (count > utflen)  
                            throw new UTFDataFormatException(  
                                "malformed input: partial character at end");  
                        char2 = (int) bytearr[count-1];  
                        if ((char2 & 0xC0) != 0x80)  
                            throw new UTFDataFormatException(  
                                "malformed input around byte " + count);  
                        chararr[chararr_count++]=(char)(((c & 0x1F) << 6) |  
                                                        (char2 & 0x3F));  
                        break;  
     
                    // �� UTF-8 �����ֽڣ��� bytearr[count] ��Ӧ�� ��1110xxxx  10xxxxxx  10xxxxxx�� ��ʽ�еĵ�һ��������1110xxxx��  
                    // �� bytearr[count] ��Ӧ��int���͵�c��ȡֵ��14 ��  
                    case 14:  
                        /* 1110 xxxx  10xx xxxx  10xx xxxx */ 
                        count += 3;  
                        if (count > utflen)  
                            throw new UTFDataFormatException(  
                                "malformed input: partial character at end");  
                        char2 = (int) bytearr[count-2];  
                        char3 = (int) bytearr[count-1];  
                        if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))  
                            throw new UTFDataFormatException(  
                                "malformed input around byte " + (count-1));  
                        chararr[chararr_count++]=(char)(((c     & 0x0F) << 12) |  
                                                        ((char2 & 0x3F) << 6)  |  
                                                        ((char3 & 0x3F) << 0));  
                        break;  
     
                    // �� UTF-8 �����ֽڣ��� bytearr[count] ��Ӧ�� ��11110xxx 10xxxxxx  10xxxxxx  10xxxxxx�� ��ʽ�еĵ�һ��������11110xxx��  
                    // �� bytearr[count] ��Ӧ��int���͵�c��ȡֵ��15   
                    default:  
                        /* 10xx xxxx,  1111 xxxx */ 
                        throw new UTFDataFormatException(  
                            "malformed input around byte " + count);  
                }  
            }  
            // The number of chars produced may be less than utflen  
            return new String(chararr, 0, chararr_count);  
        }  
    } 