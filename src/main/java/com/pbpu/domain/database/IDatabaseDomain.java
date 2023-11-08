package com.pbpu.domain.database;

import com.pbpu.entity.Buku;

public interface IDatabaseDomain {
    void create(Buku buku);    
    Buku get(int id);
    void ubah(int id, Buku buku);
    void hapus(int id);
}
