package com.pbpu.domain.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbpu.entity.Buku;
import com.pbpu.jackson.JacksonMapper;

import lombok.SneakyThrows;

public class DatabaseDomain implements IDatabaseDomain {

    private ObjectMapper mapper;

    private final String path = "/src/main/java/com/pbpu";

    public DatabaseDomain() {
        mapper = JacksonMapper.INSTANCE.getObjectMapper();
    }

    @SneakyThrows
    @Override
    public void create(Buku buku) {
        var currentDirectory = new File(System.getProperty("user.dir") + path);

        var dataDirectory = new File(currentDirectory, "data");

        var dataFile = new File(dataDirectory, "data.json");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
            this.mapper.writeValue(dataFile, new ArrayList<>());
        }

        var dataList = this.mapper.readValue(dataFile, new TypeReference<List<Buku>>() {
        });
        if (dataList.size() == 0) {
            buku.setId(1);
        } else {
            var latestBuku = dataList.get(dataList.size() - 1);
            buku.setId(latestBuku.getId() + 1);
        }
        dataList.add(buku);
        this.mapper.writeValue(dataFile, dataList);
        System.out.printf("Sukses menambahkan data buku: %s\n", this.mapper.writeValueAsString(buku));
    }

    @Override
    public Buku get(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @SneakyThrows
    @Override
    public void ubah(int id, Buku buku) {
        var currentDirectory = new File(System.getProperty("user.dir") + path);

        var dataDirectory = new File(currentDirectory, "data");

        var dataFile = new File(dataDirectory, "data.json");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
            this.mapper.writeValue(dataFile, new ArrayList<>());
        }

        var dataList = this.mapper.readValue(dataFile, new TypeReference<List<Buku>>() {
        });

        var targetBook = dataList.get(id - 1);
        buku.setId(id);
        if (targetBook == null) {
            System.out.println("Buku tidak ditemukan");
            return;
        }

        dataList.set(id - 1, buku);

        this.mapper.writeValue(dataFile, dataList);

        System.out.printf("Sukses mengubah data buku: %s\n", this.mapper.writeValueAsString(buku));
    }

    @Override
    public void hapus(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hapus'");
    }

}
