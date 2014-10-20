/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;
/**
 *
 * @author Alex
 */
public final class Output extends javax.swing.JFrame {
    
    DataCalc dc = new DataCalc();
    Data d = new Data();
    Schedule s;
    RoomOutput[][] sched;
    int floors, roomsPerFloor;
    
    public Output(int floors, int roomsPerFloor) {
        initComponents();
        this.floors = floors;
        this.roomsPerFloor = roomsPerFloor;        
        s = new Schedule(new RoomScheme[6][5]);
        
        for(int k=0; k<6; k++){
            for(int m=0; m<5; m++){
                s.schedule[k][m] = dc.generateRandomScheme(floors, roomsPerFloor, d.subject);
            }
        }
        
        sched = dc.printScheme(0, 0, s);
        for (RoomOutput[] sched1 : sched) {
            for (int m = 0; m<sched[0].length; m++) {
                add(sched1[m]);
            }
        }
        fitness.setText("" + dc.getRoomFitness(s.schedule[classTimeList.getSelectedIndex()][dayList.getSelectedIndex()]));
        
        dc.MetropolisAlgorithm(s);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dayList = new javax.swing.JComboBox();
        classTimeList = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        fitness = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Escoja un día y una franja");

        dayList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }));
        dayList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dayListItemStateChanged(evt);
            }
        });

        classTimeList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7 - 9", "9 - 11", "11 - 13", "14 - 16", "16 - 18", "18 - 20" }));
        classTimeList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                classTimeListItemStateChanged(evt);
            }
        });

        jLabel2.setText("Fitness actual:");

        fitness.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fitness)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classTimeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classTimeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jLabel2)
                    .addComponent(fitness))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        Input i = new Input();
        i.setLocationRelativeTo(null);
        i.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        this.setSize(sched.length * 185 + 5, sched[0].length * 55 + 145);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formComponentResized

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dayListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dayListItemStateChanged
        dc.changeText(s, sched, dayList.getSelectedIndex(), classTimeList.getSelectedIndex());
        fitness.setText("" + dc.getRoomFitness(s.schedule[classTimeList.getSelectedIndex()][dayList.getSelectedIndex()]));
    }//GEN-LAST:event_dayListItemStateChanged

    private void classTimeListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_classTimeListItemStateChanged
        dc.changeText(s, sched, dayList.getSelectedIndex(), classTimeList.getSelectedIndex());
        fitness.setText("" + dc.getRoomFitness(s.schedule[classTimeList.getSelectedIndex()][dayList.getSelectedIndex()]));
    }//GEN-LAST:event_classTimeListItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox classTimeList;
    private javax.swing.JComboBox dayList;
    private javax.swing.JLabel fitness;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
