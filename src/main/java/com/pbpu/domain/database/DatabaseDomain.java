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

   @SneakyThrows
    @Override
    public Buku get(int id) {
        Buku buku = null;
        var dataList = getAll();
        for (Buku data: dataList){
            if (data.getId() == id){
                buku = data;
            }
        }
        return buku;
    }

    @SneakyThrows
    @Override
    public List<Buku> getAll() {
        var currentDirectory = new File(System.getProperty("user.dir") + path);

        var dataDirectory = new File(currentDirectory, "data");

        var dataFile = new File(dataDirectory, "data.json");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
            this.mapper.writeValue(dataFile, new ArrayList<>());
        }

        var dataList = this.mapper.readValue(dataFile, new TypeReference<List<Buku>>() {
        });
        return dataList;
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

    @SneakyThrows
    @Override
    public void hapus(int id) {
        var currentDirectory = new File(System.getProperty("user.dir") + path);

        var dataDirectory = new File(currentDirectory, "data");

        var dataFile = new File(dataDirectory, "data.json");

        if(!dataFile.exists()){
            System.out.println("Tidak ada file data.json. Silahkan masukkan data terlebih dahulu");
            return;
        } 

        var dataList = this.mapper.readValue(dataFile, new TypeReference<List<Buku>>(){});

        if(dataList.isEmpty()){
            System.out.println("data.json berisi [] kosong");
            return;
        }

        boolean dataFound = false;
        for(int i = 0 ; i < dataList.size() ; i++){
            if(dataList.get(i).getId().equals(id)){
                dataList.remove(i);
                dataFound = true;
            }
        }

        this.mapper.writeValue(dataFile, dataList);

        if(!dataFound){
            System.out.printf("Tidak terdapat Buku dengan id %d dalam data.json\n", id);
            return;
        } else{
            System.out.println("Sukses menghapus buku");
        }
    }

}
