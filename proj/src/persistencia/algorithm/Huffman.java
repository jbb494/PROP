package com.company.algorithm;

import com.company.utils.*;

class Huffman { //falta comprovar

    boolean auto;
    long[] auto_codes;
    BinTree<Byte> tree;


    void set_auto_codes() {
        auto_codes = new long[256];

        auto_codes[0x00] = size_code(4, 0b1010);
        auto_codes[0x01] = size_code(2, 0b00);
        auto_codes[0x02] = size_code(2, 0b01);
        auto_codes[0x03] = size_code(3, 0b100);
        auto_codes[0x04] = size_code(4, 0b1011);
        auto_codes[0x05] = size_code(5, 0b11010);
        auto_codes[0x06] = size_code(7, 0b1111000);
        auto_codes[0x07] = size_code(8, 0b11111000);
        auto_codes[0x08] = size_code(10, 0b1111110110);

        auto_codes[0x11] = size_code(4, 0b1100);
        auto_codes[0x12] = size_code(5, 0b11011);
        auto_codes[0x13] = size_code(7, 0b1111001);
        auto_codes[0x14] = size_code(9, 0b111110110);
        auto_codes[0x15] = size_code(11,0b11111110110);

        auto_codes[0x21] = size_code(5, 0b11100);
        auto_codes[0x22] = size_code(8, 0b11111001);
        auto_codes[0x23] = size_code(10, 0b1111110111);
        auto_codes[0x24] = size_code(12, 0b111111110100);

        auto_codes[0x31] = size_code(6, 0b111010);
        auto_codes[0x32] = size_code(9, 0b111110111);
        auto_codes[0x33] = size_code(12, 0b111111110101);

        ///

        auto_codes[0x41] = size_code(6, 0b111011);
        auto_codes[0x42] = size_code(10, 0b1111111000);

        auto_codes[0x51] = size_code(7, 0b1111010);
        auto_codes[0x52] = size_code(11, 0b11111110111);

        auto_codes[0x61] = size_code(7, 0b1111011);
        auto_codes[0x62] = size_code(12, 0xFF6);

        auto_codes[0x71] = size_code(8, 0xFA);
        auto_codes[0x72] = size_code(12, 0xFF7);

        ///

        auto_codes[0x81] = size_code(9, 0x1F8);
        auto_codes[0x82] = size_code(15, 0x7FC0);

        auto_codes[0x91] = size_code(9, 0x1F9);
        auto_codes[0xA1] = size_code(9, 0x1FA);
        auto_codes[0xB1] = size_code(10, 0x3F9);
        ///
        auto_codes[0xC1] = size_code(10, 0x3FA);
        auto_codes[0xD1] = size_code(11, 0x7F8);

        auto_codes[0xF0] = size_code(11, 0x7F9);

        

        int[] cod16 = {9, 6, 5, 4,
                       3, 3, 3, 3,
                       3, 2, 2, 2,
                       2, 2, 1, 1};
        
        int x = 0b1111111110000010;
        for (int i = 0; i < 16; ++i) {
            for (int j = cod16[i]; j <= 10; ++j) {
                auto_codes[(i<<4) + j] = size_code(16, x);
                x++;
            }
        }

        //dummy data
        for (int i = 1; i <= 14; ++i) {
            auto_codes[i<<4] = size_code(-1, -1);
        }
        for (int i = 0; i < 16; ++i) {
            for (int j = 11; j < 16; ++j) {
                auto_codes[(i<<4) + j] = size_code(-1, -1);
            }
        }
    }

    private void set_auto_tree() {
        for(int i = 0; i < auto_codes.length; ++i) {
            long cd = auto_codes[i];
            if(size(cd) != -1) {
                //setToTree(cd, 0, i);
            }
        }
    }

    public Huffman(boolean automatic) {
        auto_codes = new long[0];
        auto = automatic;
        tree = new BinTree();
        if(auto) {
            set_auto_codes();
            set_auto_tree();
        }
    }

    private static long size_code(int size, int code) {
        return ((long)size << 32) + code;
    }
    
    private static int size(long x) {
        return (int)(x >> 32);
    }

    private static int code(long x) {
        return (int)x;
    }

    public static int bit_from_code(int n, long cd) {
        return (code(cd) >> (size(cd) - n)) % 2;
    }

}