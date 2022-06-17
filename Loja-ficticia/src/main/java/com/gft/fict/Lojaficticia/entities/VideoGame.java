package com.gft.fict.Lojaficticia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoGame {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    protected double preco;
    private int qtd;
    private String marca;
    private String modelo;
    private boolean isUsado;



}
