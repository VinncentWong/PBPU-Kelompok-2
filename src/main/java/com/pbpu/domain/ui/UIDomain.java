package com.pbpu.domain.ui;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Scanner;

import com.pbpu.domain.database.IDatabaseDomain;
import com.pbpu.entity.Buku;
import com.pbpu.jackson.JacksonMapper;
import com.pbpu.utils.LineUtils;

import lombok.SneakyThrows;

public class UIDomain {

    private LineUtils utils;

    private IDatabaseDomain databaseDomain;

    private JacksonMapper jacksonMapper;

    public UIDomain(IDatabaseDomain databaseDomain) {
        this.utils = LineUtils.INSTANCE;
        this.jacksonMapper = JacksonMapper.INSTANCE;
        this.databaseDomain = databaseDomain;
    }

    @SneakyThrows
    public void showCommandLine() {
        try (final var input = new Scanner(System.in)) {
            boolean continueLooping = true;
            do {
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
                        this.utils.printLine();
                        var datalist = this.databaseDomain.getAll();
                        if (datalist.size() == 0){
                            System.out.println("Masih belum ada buku");
                        } else {
                            System.out.println("List Buku: ");
                            for (Buku data: datalist) {
                                System.out.println(data.getId() + ". " + data.getBookName());
                            }
                            System.out.print("Pilih Buku yang ingi dilihat: ");
                            var id = input.nextInt();
                            Buku buku = this.databaseDomain.get(id);
                            this.utils.printLine();
                            if (buku == null) {
                                System.out.println("Buku Tidak Ditemukan");
                            } else {
                                var formattedPrice = NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(buku.getPrice());
                                var formattedPublisedAt = buku.getPublishedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                System.out.println("Nama Buku: " + buku.getBookName());
                                System.out.println("Penulis Buku: " + buku.getAuthorName());
                                System.out.println("Harga Buku: " + formattedPrice);
                                System.out.println("Tanggal Terbit: " + formattedPublisedAt);
                            }
                        }
                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 5 -> {
                        continueLooping = false;
                    }
                }
            } while (continueLooping);
            this.utils.printLine();
        }
    }
}
