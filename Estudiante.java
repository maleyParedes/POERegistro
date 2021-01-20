/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

/**
 *
 * @author aleym
 */
public class Estudiante {

    private String nombreApellido;
    private String genero;
    private int id;
    private String documento;
    private double nota1, nota2, nota3;
    private float definitiva;

    public Estudiante() {
    }

    public Estudiante(String documento, int id, String genero, String nombreApellido, double nota1, double nota2, double nota3) {
        this.nombreApellido = nombreApellido;
        this.genero = genero;
        this.id = id;
        this.documento = documento;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        sacarDefinitiva();
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public float getDefinitiva() {
        return definitiva;
    }

    public void setDefinitiva(float definitiva) {
        this.definitiva = definitiva;
    }

    public void sacarDefinitiva() {
        this.definitiva = (float)((this.nota1 + this.nota2 + this.nota3)/3);
    }

}
