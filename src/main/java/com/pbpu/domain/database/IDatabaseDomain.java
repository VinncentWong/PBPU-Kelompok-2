package com.pbpu.domain.database;

import com.pbpu.entity.Buku;

import java.util.List;

public interface IDatabaseDomain {
    void create(Buku buku);    
    Buku get(int id);
    List<Buku> getAll();
    void ubah(int id, Buku buku);
    void hapus(int id);
}
