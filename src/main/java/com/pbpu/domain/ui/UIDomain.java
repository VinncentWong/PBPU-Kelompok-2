package com.pbpu.domain.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.pbpu.domain.database.IDatabaseDomain;
import com.pbpu.entity.Buku;
import com.pbpu.utils.LineUtils;

import lombok.SneakyThrows;

public class UIDomain {

    private LineUtils utils;
    
    private IDatabaseDomain databaseDomain;
    
    public UIDomain(IDatabaseDomain databaseDomain){
        this.utils = LineUtils.INSTANCE;
        this.databaseDomain = databaseDomain;
    }

    @SneakyThrows
    public void showCommandLine(){
        try(final var input = new Scanner(System.in)){
            boolean continueLooping = true;
            do{
                this.utils.printLine();
                System.out.println("SELAMAT DATANG DI SISTEM INFORMASI CRUD BUKU");
                this.utils.printLine();
                System.out.print("""
                Pilih operasi yang akan dilakukan: 
                1.Buat Buku
                2.Dapatkan Data Buku
                3.Update Buku
                4.Hapus Buku
                5.Selesai
                Pilihan Anda: """);
                int operationNumber = input.nextInt();
                input.nextLine();
                switch (operationNumber) {
                    case 1 -> {
                        this.utils.printLine();
                        System.out.print("Masukkan nama buku: ");
                        var bookName = input.nextLine();
                        System.out.print("Masukkan harga buku: ");
                        var bookPrice = input.nextInt();
                        input.nextLine();
                        System.out.print("Masukkan nama penulis: ");
                        var authorName = input.nextLine();
                        System.out.print("Masukkan tanggal publish buku: ");
                        var publishDateStr = input.nextLine();
                        var publishDate = LocalDate.parse(publishDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        var buku = Buku.builder()
                        .authorName(authorName)
                        .bookName(bookName)
                        .price(bookPrice)
                        .publishedAt(publishDate)
                        .build();
                        this.databaseDomain.create(buku);
                        this.utils.printLine();
                    }
                    case 2 -> {

                    } 
                    case 3 -> {

                    } 
                    case 4 -> {
                        this.utils.printLine();
                        System.out.print("Masukkan id buku yang ingin dihapus: ");
                        int id = input.nextInt();
                        input.nextLine();
                        this.databaseDomain.hapus(id);
                        this.utils.printLine();
                    }
                    case 5 -> {
                        continueLooping = false;
                    }
                }
            } while(continueLooping);
             this.utils.printLine();
        }
    }
}
