/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {

    private BufferedWriter buffered;
    private String ruta;

    public Log(String ruta) throws IOException {
        this.ruta = ruta;
        this.open(true);

    }

    public Log(String ruta, boolean reset) throws IOException {
        this.ruta = ruta;
        this.open(!reset);
    }

    private void open(boolean append) throws IOException {
        this.buffered = new BufferedWriter(new FileWriter(this.ruta, append));
    }

    public void addLine(String linea) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        String formatoFecha = sdf.format(new Date());
        this.open(true);
        this.buffered.write("[" + formatoFecha + "] " + linea + "\n");
        this.close();
    }

    public String[] getLinea() throws FileNotFoundException, IOException {
        ArrayList<String> lineaFile = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(this.ruta));

        String line;
        while ((line = br.readLine()) != null) {
            lineaFile.add(line);
        }
        br.close();
        String[] lines = new String[lineaFile.size()];
        for (int i = 0; i < lineaFile.size(); i++) {
            lines[i] = lineaFile.get(i);
        }
        return lines;
    }

    public void resetLog() throws IOException {
        this.open(false);
        this.close();
    }

    public void close() throws IOException {
        this.buffered.close();
    }
}
