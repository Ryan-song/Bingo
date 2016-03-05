package com.ryan.bingo.modle.reading;

import android.media.Rating;

import java.io.Serializable;

/**
 * Created by air on 16/3/5.
 */

/**
 * 暂时未用到 不知道为什么这样定义对象
 */
public class BookBean implements Serializable {
    private int count;
    private int start;
    private int total;

    private class Book {
        android.media.Rating rating;
        String subtitle;
        String[] author;
        String pubdate;
        Tags[] tages;
        String origin_title;
        String image;
        String binding;
        String[] translator;
        String catalog;
        String ebook_url;
        int pages;
        Images images;
        String alt;
        int id;
        String publisher;
        int isbn10;
        int isbn13;
        String title;
        String url;
        String alt_title;
        String aurhor_intro;
        String summary;
        double ebook_price;
        Series series;
        double price;
    }

    private class Rating implements Serializable {
        int max;
        int numRaters;
        double average;
        int min;
    }

    private class Tags implements Serializable {
        int count;
        String name;
        String title;
    }

    private class Images implements Serializable {
        String small;
        String large;
        String medium;
    }

    private class Series implements Serializable {
        int id;
        double price;
    }
}
