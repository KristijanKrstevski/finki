//package com.example.airbnb.model.domains;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Available {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String date_to;
//    private String date_from;
//    private Integer price;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Accommodation accommodation;
//
//    public Available() {
//    }
//
//    public Available(String date_to, String date_from, Integer price, Accommodation accommodation) {
//        this.date_to = date_to;
//        this.date_from = date_from;
//        this.price = price;
//        this.accommodation = accommodation;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getDate_to() {
//        return date_to;
//    }
//
//    public String getDate_from() {
//        return date_from;
//    }
//
//    public Integer getPrice() {
//        return price;
//    }
//
//    public Accommodation getAccommodation() {
//        return accommodation;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setDate_to(String date_to) {
//        this.date_to = date_to;
//    }
//
//    public void setDate_from(String date_from) {
//        this.date_from = date_from;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
//
//    public void setAccommodation(Accommodation accommodation) {
//        this.accommodation = accommodation;
//    }
//}
