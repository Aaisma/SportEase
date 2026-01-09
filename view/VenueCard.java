package view;

import model.VenueModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class VenueCard extends javax.swing.JPanel {
    private final VenueModel venue;

    public VenueCard(VenueModel venue) {
        this.venue = venue;
        initComponents();

   
        // Set venue info
        nameLabel.setText(venue.getName());
        addressLabel.setText(venue.getAddress());
        categoryLabel.setText(venue.getCategory()); 
        groundSizeLabel.setText(venue.getGroundSize());
        priceLabel.setText("Rs. " + venue.getPrice());

        // Format open/close times
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        openLabel.setText(venue.getOpenTime() != null ? sdf.format(venue.getOpenTime()) : "N/A");
        closeLabel.setText(venue.getCloseTime() != null ? sdf.format(venue.getCloseTime()) : "N/A");

        // Load and scale image
        if (venue.getImagePath() != null && !venue.getImagePath().isEmpty()) {
            ImageIcon icon = new ImageIcon(venue.getImagePath());

            // Use label size or fallback
            int width = imageLabel.getWidth() > 0 ? imageLabel.getWidth() : 120;
            int height = imageLabel.getHeight() > 0 ? imageLabel.getHeight() : 80;

            Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
            imageLabel.setText(""); // Clear fallback text
        } else {
            imageLabel.setIcon(new ImageIcon("assets/no_image.png")); // ✅ fallback image
            imageLabel.setText(""); // Optional: remove "No image" text
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        addressLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        groundSizeLabel = new javax.swing.JLabel();
        openLabel = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        bookButton = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(357, 380));
        setMinimumSize(new java.awt.Dimension(357, 380));
        setPreferredSize(new java.awt.Dimension(357, 380));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setMaximumSize(new java.awt.Dimension(357, 387));
        jPanel3.setMinimumSize(new java.awt.Dimension(357, 387));

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageLabel.setMaximumSize(new java.awt.Dimension(357, 210));
        imageLabel.setMinimumSize(new java.awt.Dimension(357, 210));
        imageLabel.setPreferredSize(new java.awt.Dimension(357, 210));

        jPanel4.setBackground(new java.awt.Color(102, 0, 0));

        addressLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        addressLabel.setText("xxx, xxxx city");
        addressLabel.setAlignmentX(10.0F);
        addressLabel.setAlignmentY(10.0F);

        nameLabel.setFont(new java.awt.Font("Algerian", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("XYZ FUTSAL GROUND");

        categoryLabel.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        categoryLabel.setForeground(new java.awt.Color(255, 204, 204));
        categoryLabel.setText("Futsal");

        groundSizeLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        groundSizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        groundSizeLabel.setText("Ground Size:");

        openLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        openLabel.setForeground(new java.awt.Color(255, 255, 255));
        openLabel.setText("Open:");

        closeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        closeLabel.setForeground(new java.awt.Color(255, 255, 255));
        closeLabel.setText("Close:");

        priceLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("Rs. 1200");

        bookButton.setBackground(new java.awt.Color(255, 204, 204));
        bookButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        bookButton.setText("BOOK");
        bookButton.setAlignmentX(1.0F);
        bookButton.setAlignmentY(1.0F);
        bookButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryLabel)
                            .addComponent(groundSizeLabel)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(openLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(priceLabel))
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(closeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groundSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(openLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeLabel))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(priceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
        new BookingForm(venue.getId(), venue.getPrice()).setVisible(true);
    }//GEN-LAST:event_bookButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton bookButton;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel groundSizeLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel openLabel;
    private javax.swing.JLabel priceLabel;
    // End of variables declaration//GEN-END:variables

}

