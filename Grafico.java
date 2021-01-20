/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleym
 */
public class Grafico extends JFrame implements ActionListener {

    private static int numeroEst = 0;
    private String txtMessage;
    private JTabbedPane tarjet;
    private JTable registro, busqEstudiante, estAprob, estReprob;
    private JPanel tarjRegis, tarjEstud, busqueda, p1, p2, pBusca, pEst, p2Aprob, p2Reprob, LogExc;
    private JTextField nombreApellido, id, nota1, nota2, nota3, buscarEst;
    private JTextArea errores;
    private JComboBox genero, doc, docB;
    private JButton nuevoEst, ingresarEst, buscarEs, reiniciarLog, reiniciarSist;
    private JLabel etiqueta;
    private ImageIcon imagen;
    private GridBagConstraints c = new GridBagConstraints(), s = new GridBagConstraints(), z = new GridBagConstraints(), fEst = new GridBagConstraints();
    private DefaultTableModel dtm, modelo, mod, model;
    private Border line = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
    private DecimalFormat formato = new DecimalFormat("#.##");
    private ArrayList<Estudiante> estud = new ArrayList<Estudiante>();
    private ArrayList<String> excMessage = new ArrayList<String>();
    private String[] columnNames = {"Tipo", "Número ID", "Genero", "Nombres y Apellidos", "Nota 1", "Nota 2", "Nota 3", "Definitiva"};
    private Font fo = new Font("Times New Roman", Font.BOLD, 16);
    private Log excLog;

    Grafico() throws IOException {

        setTitle("Registro Estudiantes - Aley Miguel");
        setSize(1000, 700);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setResizable(false);
        //setIconImage(new ImageIcon(getClass().getResource("src/main/java/Registro/registroEstudiante.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            excLog = new Log("src/main/java/Registro/Excepciones.txt");

        } catch (Exception e) {
            System.out.println("Ruta no encontrada");
        }

        tabed();
        paneles();
        botones();
        etiqueta();
        cajasText();
        areaText();
        combos();
        tabla();
        iniciarLog();

        pBusca.add(buscarEs);
        pBusca.add(docB);
        pBusca.add(buscarEst);

        c.insets = new Insets(5, 5, 8, 8);//Darle espacio a los componentes por arriba abajo izquierda y derecha
        tarjRegis.add(p1, BorderLayout.NORTH);
        tarjRegis.add(p2, BorderLayout.CENTER);
        tarjRegis.add(reiniciarSist, BorderLayout.SOUTH);

        tarjEstud.add(p2Aprob);
        tarjEstud.add(p2Reprob);

        busqueda.add(pBusca, BorderLayout.NORTH);
        busqueda.add(pEst, BorderLayout.CENTER);

        LogExc.add(errores, BorderLayout.CENTER);
        LogExc.add(reiniciarLog, BorderLayout.SOUTH);

        tarjet.add("Registro", tarjRegis);
        tarjet.add("Estudiantes", tarjEstud);
        tarjet.add("Buscar Estudiantes", busqueda);
        tarjet.add("Excepciones", LogExc);

        add(tarjet);

        setVisible(true);
    }

    private void tabed() {
        tarjet = new JTabbedPane();
    }

    private void paneles() {
        tarjRegis = new JPanel(new BorderLayout());
        tarjRegis.setBackground(Color.WHITE);

        tarjEstud = new JPanel(new GridLayout(2, 1));
        tarjEstud.setBackground(Color.WHITE);

        busqueda = new JPanel(new BorderLayout());
        busqueda.setBackground(Color.WHITE);

        p1 = new JPanel(new GridBagLayout());
        p1.setBackground(Color.WHITE);
        p1.setBorder(BorderFactory.createTitledBorder(line, "Registro Estudiantes", 2, 0, fo, Color.BLACK));

        p2 = new JPanel(new GridBagLayout());
        p2.setBackground(Color.WHITE);
        p2.setBorder(line);

        pBusca = new JPanel(new FlowLayout());
        pBusca.setBackground(Color.WHITE);
        pBusca.setBorder(BorderFactory.createTitledBorder(line, "Busqueda de estudiante", 2, 0, fo, Color.BLACK));

        pEst = new JPanel(new GridBagLayout());
        pEst.setBackground(Color.WHITE);
        pEst.setBorder(line);

        p2Aprob = new JPanel(new GridBagLayout());
        p2Aprob.setBackground(Color.BLACK);
        p2Aprob.setBorder(BorderFactory.createTitledBorder(line, "Aprobados", 2, 0, fo, Color.WHITE));

        p2Reprob = new JPanel(new GridBagLayout());
        p2Reprob.setBackground(Color.BLACK);
        p2Reprob.setBorder(BorderFactory.createTitledBorder(line, "Reprobados", 2, 0, fo, Color.WHITE));

        LogExc = new JPanel(new BorderLayout());
        LogExc.setBackground(Color.BLACK);
        LogExc.setBorder(BorderFactory.createTitledBorder(line, "Excepciones", 2, 0, fo, Color.WHITE));

    }

    private void etiqueta() throws IOException {
        etiqueta = new JLabel();
        try {
            imagen = new ImageIcon("src/main/java/Registro/registroEstudiante.png");
            etiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 3;
            c.gridwidth = 2;
            p1.add(etiqueta, c);
        } catch (Exception e) {
            excLog.addLine(e.toString());
        }
    }

    private void cajasText() {
        nombreApellido = new JTextField("", 60);
        nombreApellido.setBorder(BorderFactory.createTitledBorder("Nombres y apellidos"));
        nombreApellido.setEnabled(false);

        c.gridx = 4;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 0;
        p1.add(nombreApellido, c);

        id = new JTextField("", 60);
        id.setBorder(BorderFactory.createTitledBorder("Identificación"));
        id.setEnabled(false);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        p1.add(id, c);

        nota1 = new JTextField();
        nota1.setBorder(BorderFactory.createTitledBorder("Nota 1"));
        nota1.setEnabled(false);

        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 1;

        p1.add(nota1, c);

        nota2 = new JTextField();
        nota2.setBorder(BorderFactory.createTitledBorder("Nota 2"));
        nota2.setEnabled(false);

        c.gridx = 3;
        c.gridy = 2;
        p1.add(nota2, c);

        nota3 = new JTextField("", 60);
        nota3.setBorder(BorderFactory.createTitledBorder("Nota 3"));
        nota3.setEnabled(false);

        c.gridx = 4;
        c.gridy = 2;
        c.weightx = 1;
        p1.add(nota3, c);

        Dimension dim = new Dimension(180, 40);
        buscarEst = new JTextField();
        buscarEst.setBorder(BorderFactory.createTitledBorder("Identificacion del estudiante"));
        buscarEst.setPreferredSize(dim);
        buscarEst.setEnabled(false);

    }

    private void areaText() {
        errores = new JTextArea();
        errores.setBackground(Color.GRAY);
        errores.setForeground(Color.WHITE);
        errores.setFont(fo);
        errores.setEditable(false);
    }

    private void tabla() {

        registro = new JTable();
        dtm = (DefaultTableModel) registro.getModel();
        dtm.addColumn(columnNames[0]);
        dtm.addColumn(columnNames[1]);
        dtm.addColumn(columnNames[2]);
        dtm.addColumn(columnNames[3]);
        dtm.addColumn(columnNames[4]);
        dtm.addColumn(columnNames[5]);
        dtm.addColumn(columnNames[6]);
        dtm.addColumn(columnNames[7]);
        registro.setCellSelectionEnabled(false);
        registro.setFocusable(false);
        registro.setRowSelectionAllowed(false);
        registro.setColumnSelectionAllowed(false);
        registro.getTableHeader().setPreferredSize(new Dimension(120, 30));
        s.gridx = 0;
        s.gridy = 1;
        s.weightx = 1;
        s.weighty = 1;
        s.gridwidth = 3;
        s.gridheight = 2;
        s.fill = GridBagConstraints.BOTH;
        p2.add(new JScrollPane(registro), s);

        busqEstudiante = new JTable();
        mod = (DefaultTableModel) busqEstudiante.getModel();
        mod.addColumn(columnNames[0]);
        mod.addColumn(columnNames[1]);
        mod.addColumn(columnNames[2]);
        mod.addColumn(columnNames[3]);
        mod.addColumn(columnNames[4]);
        mod.addColumn(columnNames[5]);
        mod.addColumn(columnNames[6]);
        mod.addColumn(columnNames[7]);
        busqEstudiante.setEnabled(false);
        z.fill = GridBagConstraints.HORIZONTAL;
        z.gridx = 0;
        z.gridy = 2;
        z.weightx = 1;
        z.weighty = 1;
        busqEstudiante.setCellSelectionEnabled(false);
        busqEstudiante.setFocusable(false);
        busqEstudiante.setRowSelectionAllowed(false);
        busqEstudiante.setColumnSelectionAllowed(false);
        busqEstudiante.getTableHeader().setPreferredSize(new Dimension(120, 30));
        pEst.add(new JScrollPane(busqEstudiante), z);

        estAprob = new JTable();
        modelo = (DefaultTableModel) estAprob.getModel();
        modelo.addColumn(columnNames[0]);
        modelo.addColumn(columnNames[1]);
        modelo.addColumn(columnNames[2]);
        modelo.addColumn(columnNames[3]);
        modelo.addColumn(columnNames[4]);
        modelo.addColumn(columnNames[5]);
        modelo.addColumn(columnNames[6]);
        modelo.addColumn(columnNames[7]);
        fEst.fill = GridBagConstraints.BOTH;
        fEst.gridx = 0;
        fEst.gridy = 0;
        fEst.weightx = 1;
        fEst.weighty = 1;
        estAprob.setCellSelectionEnabled(false);
        estAprob.setFocusable(false);
        estAprob.setRowSelectionAllowed(false);
        estAprob.setColumnSelectionAllowed(false);
        estAprob.getTableHeader().setPreferredSize(new Dimension(120, 30));
        p2Aprob.add(new JScrollPane(estAprob), fEst);

        estReprob = new JTable();
        model = (DefaultTableModel) estReprob.getModel();
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        model.addColumn(columnNames[3]);
        model.addColumn(columnNames[4]);
        model.addColumn(columnNames[5]);
        model.addColumn(columnNames[6]);
        model.addColumn(columnNames[7]);
        estReprob.setCellSelectionEnabled(false);
        estReprob.setFocusable(false);
        estReprob.setRowSelectionAllowed(false);
        estReprob.setColumnSelectionAllowed(false);
        estReprob.getTableHeader().setPreferredSize(new Dimension(120, 30));
        p2Reprob.add(new JScrollPane(estReprob), fEst);

    }

    private void botones() {
        nuevoEst = new JButton("Nuevo Estudiante");
        nuevoEst.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        p1.add(nuevoEst, c);

        ingresarEst = new JButton("Registrar Estudiante");
        ingresarEst.setEnabled(false);
        ingresarEst.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        p1.add(ingresarEst, c);

        buscarEs = new JButton("Buscar");
        buscarEs.addActionListener(this);
        buscarEs.setEnabled(false);

        reiniciarLog = new JButton("Reiniciar Log");
        reiniciarLog.addActionListener(this);
        reiniciarLog.setPreferredSize(new Dimension(LogExc.getWidth(), 60));

        reiniciarSist = new JButton("Reiniciar sistema");
        reiniciarSist.addActionListener(this);
        reiniciarSist.setPreferredSize(new Dimension(50, 40));

    }

    private void combos() {
        Dimension dm = new Dimension(120, 50);
        String[] gen = {"--Seleccionar--", "Indeterminado", "Masculino", "Femenino"};
        genero = new JComboBox(gen);
        genero.setBorder(BorderFactory.createTitledBorder("Genero"));
        genero.setEnabled(false);
        genero.setPreferredSize(dm);

        c.gridx = 3;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        p1.add(genero, c);

        String[] docum = {"--Seleccionar--", "Cedula", "T. Identidad", "C. extranjeria"};
        doc = new JComboBox(docum);
        doc.setBorder(BorderFactory.createTitledBorder("Documento"));
        doc.setEnabled(false);
        doc.setPreferredSize(dm);

        c.gridx = 3;
        c.gridy = 0;
        p1.add(doc, c);

        docB = new JComboBox(docum);
        docB.setBorder(BorderFactory.createTitledBorder("Documento"));
        docB.setPreferredSize(dm);
        docB.setEnabled(false);

    }

    protected void onOffComp(int i) {
        if (i == 0) {
            doc.setEnabled(false);
            genero.setEnabled(false);
            id.setEnabled(false);
            nombreApellido.setEnabled(false);
            ingresarEst.setEnabled(false);
            nota1.setEnabled(false);
            nota2.setEnabled(false);
            nota3.setEnabled(false);

            doc.setSelectedIndex(0);
            genero.setSelectedIndex(0);
            id.setText("");
            nombreApellido.setText("");
            nota1.setText("");
            nota2.setText("");
            nota3.setText("");

        } else {
            doc.setEnabled(true);
            genero.setEnabled(true);
            id.setEnabled(true);
            nombreApellido.setEnabled(true);
            ingresarEst.setEnabled(true);
            nota1.setEnabled(true);
            nota2.setEnabled(true);
            nota3.setEnabled(true);
        }
        if (numeroEst != 0) {
            buscarEs.setEnabled(true);
            buscarEst.setEnabled(true);
            busqEstudiante.setEnabled(true);
            docB.setEnabled(true);
        } else {
            buscarEs.setEnabled(false);
            buscarEst.setEnabled(false);
            buscarEst.setText("");
            busqEstudiante.setEnabled(false);
            docB.setEnabled(false);
            docB.setSelectedIndex(0);
        }
    }

    protected void ingresarEst() {
        p2.removeAll();
        estud.add(new Estudiante(doc.getSelectedItem() + "", Integer.parseInt(id.getText()), genero.getSelectedItem() + "", nombreApellido.getText(), Double.parseDouble(nota1.getText()), Double.parseDouble(nota2.getText()), Double.parseDouble(nota3.getText())));
        Object row[] = {estud.get(numeroEst).getDocumento(), estud.get(numeroEst).getId(), estud.get(numeroEst).getGenero(), estud.get(numeroEst).getNombreApellido(), estud.get(numeroEst).getNota1(), estud.get(numeroEst).getNota2(), estud.get(numeroEst).getNota3(), formato.format(estud.get(numeroEst).getDefinitiva())};
        ((DefaultTableModel) registro.getModel()).addRow(row);
        p2.add(new JScrollPane(registro), s);

        if (estud.get(numeroEst).getDefinitiva() >= 3) {
            Object aprob[] = {estud.get(numeroEst).getDocumento(), estud.get(numeroEst).getId(), estud.get(numeroEst).getGenero(), estud.get(numeroEst).getNombreApellido(), estud.get(numeroEst).getNota1(), estud.get(numeroEst).getNota2(), estud.get(numeroEst).getNota3(), formato.format(estud.get(numeroEst).getDefinitiva())};
            ((DefaultTableModel) estAprob.getModel()).addRow(aprob);
        } else {
            Object reprob[] = {estud.get(numeroEst).getDocumento(), estud.get(numeroEst).getId(), estud.get(numeroEst).getGenero(), estud.get(numeroEst).getNombreApellido(), estud.get(numeroEst).getNota1(), estud.get(numeroEst).getNota2(), estud.get(numeroEst).getNota3(), formato.format(estud.get(numeroEst).getDefinitiva())};
            ((DefaultTableModel) estReprob.getModel()).addRow(reprob);
        }

        numeroEst++;
    }

    protected void estudianteEncontrado(Estudiante e) {
        if (busqEstudiante.getRowCount() >= 0) {
            for (int i = busqEstudiante.getRowCount() - 1; i >= 0; i--) {
                mod.removeRow(i);
            }
        }
        Object encont[] = {e.getDocumento(), e.getId(), e.getGenero(), e.getNombreApellido(), e.getNota1(), e.getNota2(), e.getNota3(), formato.format(e.getDefinitiva())};
        ((DefaultTableModel) busqEstudiante.getModel()).addRow(encont);
    }

    private void iniciarLog() throws IOException {
        String[] line = excLog.getLinea();
        for (int i = 0; i < line.length; i++) {
            errores.append(line[i] + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevoEst) {
            onOffComp(1);
        } else if (e.getSource() == ingresarEst) {
            txtMessage = "";
            boolean bandera = true, bandera1 = true, bandera2 = true, bandera3 = true, bandera4 = true;
            try {
                Integer.parseInt(id.getText());
            } catch (Exception ex) {
                excMessage.add(ex.toString());
                txtMessage += "El campo de cedula solo recibe valores numericos\n";
                bandera = false;
                id.setText("");
            }
            //Valida si el texto está escrito con letras+
            if (!nombreApellido.getText().matches("^[A-Za-z ]*$") || nombreApellido.getText().equals("")) {
                bandera4 = false;
                nombreApellido.setText("");
                txtMessage += "Nombre invalido\n";
                excMessage.add("Excepcion formato nombre invalido");
            }

            try {
                Double.parseDouble(nota1.getText());
            } catch (Exception ex) {
                excMessage.add(ex.toString());
                txtMessage += "El campo de nota1 solo recibe valores numericos\n";
                bandera1 = false;
                nota1.setText("");
            }

            try {
                Double.parseDouble(nota2.getText());
            } catch (Exception ex) {
                excMessage.add(ex.toString());
                txtMessage += "El campo de nota2 solo recibe valores numericos\n";
                bandera2 = false;
                nota2.setText("");
            }

            try {
                Double.parseDouble(nota3.getText());
            } catch (Exception ex) {
                excMessage.add(ex.toString());
                txtMessage += "El campo de nota3 solo recibe valores numericos\n";
                bandera3 = false;
                nota3.setText("");
            }
            if (bandera && bandera1 && bandera2 && bandera3 && bandera4) {
                if ((Double.parseDouble(nota3.getText()) >= 0 && Double.parseDouble(nota3.getText()) <= 5) && (Double.parseDouble(nota2.getText()) >= 0 && Double.parseDouble(nota2.getText()) <= 5) && (Double.parseDouble(nota1.getText()) >= 0 && Double.parseDouble(nota1.getText()) <= 5)) {
                    if ((doc.getSelectedIndex() != 0)) {
                        if ((genero.getSelectedIndex() != 0)) {
                            ingresarEst();
                            p2.updateUI();
                            onOffComp(0);
                            LogExc.updateUI();
                        } else {
                            JOptionPane.showMessageDialog(null, "Genero invalido");
                            excMessage.add("Genero invalido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Documento invalido");
                        excMessage.add("Documento invalido");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Las notas van desde 0.0 hasta 5.0");
                    excMessage.add("Las notas van desde 0.0 hasta 5.0");
                }

            } else {
                JOptionPane.showMessageDialog(null, txtMessage, "Advertencia", JOptionPane.ERROR_MESSAGE);
                for (int i = 0; i < excMessage.size(); i++) {
                    try {
                        excLog.addLine(excMessage.get(i));
                    } catch (IOException ex) {
                        Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        errores.append(excMessage.get(i) + "\n");
                    }
                }

            }

        }

        if (e.getSource() == buscarEs) {
            txtMessage = "";
            Boolean bandera = false, banderaExc = true;
            try {
                Integer.parseInt(buscarEst.getText());
            } catch (Exception ex) {
                excMessage.add(ex.toString());
                txtMessage = "El campo de identificación solo recibe valores numericos";
                banderaExc = false;
                buscarEst.setText("");
            }
            if (banderaExc) {
                for (int i = 0; i < estud.size(); i++) {
                    if (estud.get(i).getDocumento().equals(docB.getSelectedItem() + "") && estud.get(i).getId() == Integer.parseInt(buscarEst.getText())) {
                        estudianteEncontrado(estud.get(i));
                        pEst.updateUI();
                        bandera = true;
                    }
                }
                if (!bandera) {
                    JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, txtMessage, "Advertencia", JOptionPane.ERROR_MESSAGE);

                try {
                    excLog.addLine(excMessage.get(excMessage.size() - 1));
                } catch (IOException ex) {
                    Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
                }
                errores.append(excMessage.get(excMessage.size() - 1) + "\n");

            }
        }

        if (e.getSource() == reiniciarLog) {
            try {
                excLog.resetLog();
                errores.setText("");
                excMessage.clear();
            } catch (IOException ex) {
                Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == reiniciarSist) {

            for (int i = registro.getRowCount() - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }
            if (estAprob.getRowCount() >= 0) {
                for (int i = estAprob.getRowCount() - 1; i >= 0; i--) {
                    modelo.removeRow(i);
                }
            }
            if (estReprob.getRowCount() >= 0) {
                for (int i = estReprob.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
            }
            if (busqEstudiante.getRowCount() >= 0) {
                for (int i = busqEstudiante.getRowCount() - 1; i >= 0; i--) {
                    mod.removeRow(i);
                }
            }

            estud.clear();
            try {
                excLog.resetLog();
                errores.setText("");
                excMessage.clear();
            } catch (IOException ex) {
                Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
            }
            numeroEst = 0;
            onOffComp(0);

            p2.updateUI();
            pEst.updateUI();
            LogExc.updateUI();
        }
    }
}
