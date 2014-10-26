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
public final class Output extends javax.swing.JFrame {
    
    DataCalc dc = new DataCalc();
    Data d = new Data();
    Schedule s;
    RoomOutput[][] sched;
    
    public Output() {
        initComponents();      
        s = new Schedule(new RoomScheme[2][3]);
        
        for(int k=0; k<2; k++){
            for(int m=0; m<3; m++){
                s.schedule[k][m] = dc.generateRandomScheme(5, 6, d.subject);
            }
        }
        
        sched = dc.printScheme(0, 0, s);
        for (RoomOutput[] sched1 : sched) {
            for (int m = 0; m<sched[0].length; m++) {
                add(sched1[m]);
            }
        }
        fitness.setText("" + dc.getRoomFitness(s.schedule[roomList.getSelectedIndex()][floorList.getSelectedIndex()]));
        setSize(5 + 185 * sched.length, 40 + 70 * sched[0].length);
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
        classCount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        classCount.setText("Conteo de clases");
        classCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classCountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(floorList, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitness)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(algorithmButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton))
                    .addComponent(roomList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(jLabel2)
                    .addComponent(fitness)
                    .addComponent(algorithmButton)
                    .addComponent(classCount))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void roomListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomListItemStateChanged
        dc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
        fitness.setText("" + dc.getRoomFitness(s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()]));
    }//GEN-LAST:event_roomListItemStateChanged

    private void floorListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_floorListItemStateChanged
        dc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
        fitness.setText("" + dc.getRoomFitness(s.schedule[floorList.getSelectedIndex()][roomList.getSelectedIndex()]));
    }//GEN-LAST:event_floorListItemStateChanged

    private void algorithmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmButtonActionPerformed
        dc.MetropolisAlgorithm(s);
        fitness.setText("" + dc.getRoomFitness(s.schedule[roomList.getSelectedIndex()][floorList.getSelectedIndex()]));
        dc.changeText(s, sched, floorList.getSelectedIndex(), roomList.getSelectedIndex());
    }//GEN-LAST:event_algorithmButtonActionPerformed

    private void classCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classCountActionPerformed
        JOptionPane.showMessageDialog(this, "Intente con un valor diferente!",
                        "Conteo de clases", JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_classCountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton algorithmButton;
    private javax.swing.JButton classCount;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel fitness;
    private javax.swing.JComboBox floorList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox roomList;
    // End of variables declaration//GEN-END:variables
}
