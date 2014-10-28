/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public final class GeneticOutput extends javax.swing.JFrame {
    
    GeneticCalc gc = new GeneticCalc();
    Data d = new Data();
    Schedule s;
    RoomOutput[][] sched;
    
    public GeneticOutput() {
        super("Algoritmo Genetico");
        initComponents();      
        s = gc.generateRandomSchedule(2, 3, 5, 6);
        
        sched = gc.printScheme(0, 0, s);
        for (RoomOutput[] sched1 : sched) {
            for (int m = 0; m<sched[0].length; m++) {
                add(sched1[m]);
            }
        }
        fitness.setText("" + s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()].getFitness());
        setSize(5 + 185 * sched.length, 30 + 75 * sched[0].length);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        floorList = new javax.swing.JComboBox();
        roomList = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        fitness = new javax.swing.JLabel();
        algorithmButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        crossover = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mutation = new javax.swing.JTextField();
        aboutButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        generations = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fitnessProm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Escoja un salón y un piso");

        floorList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Piso 1", "Piso 2" }));
        floorList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                floorListItemStateChanged(evt);
            }
        });

        roomList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Salón", "Sala de Informática", "Auditorio" }));
        roomList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomListItemStateChanged(evt);
            }
        });

        jLabel2.setText("Fitness actual:");

        fitness.setText("0");

        algorithmButton.setText("Ejecutar algoritmo!");
        algorithmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("% Cruce");

        crossover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossoverActionPerformed(evt);
            }
        });

        jLabel4.setText("% Mutacion");

        aboutButton.setText("Acerca de");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Generaciones");

        generations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generationsActionPerformed(evt);
            }
        });

        jLabel6.setText("Fitness promedio:");

        fitnessProm.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fitness)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(7, 7, 7)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crossover, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(floorList, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roomList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mutation, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generations, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(algorithmButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aboutButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitnessProm)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(floorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(jLabel2)
                    .addComponent(fitness)
                    .addComponent(algorithmButton)
                    .addComponent(jLabel3)
                    .addComponent(crossover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(mutation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aboutButton)
                    .addComponent(jLabel5)
                    .addComponent(generations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fitnessProm)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void roomListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomListItemStateChanged
        System.out.println("Floor " + floorList.getSelectedIndex() + " Room " + roomList.getSelectedIndex());
        gc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
        fitness.setText("" + s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()].getFitness());
    }//GEN-LAST:event_roomListItemStateChanged

    private void floorListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_floorListItemStateChanged
        gc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
        fitness.setText("" + s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()].getFitness());
    }//GEN-LAST:event_floorListItemStateChanged

    private void algorithmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmButtonActionPerformed
        try{
            double crossChance = Float.parseFloat(crossover.getText());
            double mutationChance = Float.parseFloat(mutation.getText());
            int generationsNum = Integer.parseInt(generations.getText());
            if(0.0<crossChance && 0.0<mutationChance && crossChance<1.0 && mutationChance<1.0){
                s = gc.geneticAlgorithm(s, crossChance, mutationChance, generationsNum);
                fitness.setText("" + s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()].getFitness());
                fitnessProm.setText("" + s.getFitness());
                gc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
            }
            else{
                JOptionPane.showMessageDialog(this, "Valores de cruce y mutacion invalidos!",
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Debe ingresar valores de cruce, mutacion y generaciones!",
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            crossover.setText("");
        }
    }//GEN-LAST:event_algorithmButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Este programa uiliza Algoritmos Geneticos (Genetic Algorithms),\n"
                                          + "el cual se basa en el modelo del AND, generando nuevas poblaciones\n"
                                          + "de genes mediante operaciones de cruce y mutacion.",
                        "Acerca del algoritmo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void crossoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crossoverActionPerformed

    private void generationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generationsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton algorithmButton;
    private javax.swing.JTextField crossover;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel fitness;
    private javax.swing.JLabel fitnessProm;
    private javax.swing.JComboBox floorList;
    private javax.swing.JTextField generations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField mutation;
    private javax.swing.JComboBox roomList;
    // End of variables declaration//GEN-END:variables
}
