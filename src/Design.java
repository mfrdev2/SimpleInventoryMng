import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Design extends JFrame {
    JPanel center;
    JPanel bottom;
    JLabel lProductName;
    JLabel lAvailableQuantity;
    JLabel lAvgPurchasePrice;
    JLabel lPrice;
    JLabel lAvgText;
    JLabel lQuantity;
    JLabel lQuantityText;
    JLabel lDate;
    JComboBox comboBox;

    JTextField textPrice;
    JTextField textQuantity;
    JTextField textDate;
    JButton p;
    JButton s;
    JButton c;

    public Design() {
        intComponents();
        ProductController.loadCombo(comboBox);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedIndex()<0){
                    ProductController.savePname(comboBox.getSelectedItem().toString());
                }
                boolean bool = ProductController.savePurchase(comboBox.getSelectedItem().toString(),
                        textPrice.getText(),
                        textDate.getText(),
                        textQuantity.getText());
                if(!bool){
                    JOptionPane.showMessageDialog(rootPane,"Purchase is Saved");
                    ProductController.loadCombo(comboBox);
                    clear();
                }else {
                    JOptionPane.showMessageDialog(rootPane,"Purchase is not Saved");
                }

            }
        });

        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bool = ProductController.saveSale(comboBox.getSelectedItem().toString(),
                        textPrice.getText(),
                        textDate.getText(),
                        textQuantity.getText());
                if(!bool){
                    JOptionPane.showMessageDialog(rootPane,"Sale is Saved");
                    ProductController.loadCombo(comboBox);
                    clear();
                }else {
                    JOptionPane.showMessageDialog(rootPane,"Sale is not Saved");
                }
            }
        });

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedIndex()>0){
                    lQuantityText.setText(ProductController.loadQty(comboBox));
                    lAvgText.setText(ProductController.loadAvg(comboBox));
                }else {
                    lQuantityText.setText("");
                    lAvgText.setText("");
                }
            }
        });



        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        this.pack();
        this.setVisible(true);
    }

    private void intComponents() {

        center = new JPanel();
        JPanel borderPane = new JPanel();
        borderPane.setPreferredSize(new Dimension(400, 200));
        bottom = new JPanel();
        center.setLayout(new GridLayout(6, 2));
        lProductName = new JLabel("Product Name:");
        lAvailableQuantity = new JLabel("Available Quantity:");
        lAvgPurchasePrice = new JLabel("AVG Purchase Price:");
        lPrice = new JLabel("Price:");
        lQuantity = new JLabel("Quantity:");
        lDate = new JLabel("Date:");
        comboBox = new JComboBox();
        comboBox.setEditable(true);

        lQuantityText = new JLabel();
        lAvgText = new JLabel();
        textPrice = new JTextField( 15);
        textQuantity = new JTextField(15);
        textDate = new JTextField(15);


        p = new JButton("Purchase");

        s = new JButton("Sale");

        c = new JButton("clear");

        center.add(lProductName);
        center.add(comboBox);
        center.add(lAvailableQuantity);
        center.add(lQuantityText);
        center.add(lAvgPurchasePrice);
        center.add(lAvgText);
        center.add(lPrice);
        center.add(textPrice);
        center.add(lQuantity);
        center.add(textQuantity);
        center.add(lDate);
        center.add(textDate);


        bottom.add(p);
        bottom.add(s);
        bottom.add(c);
        borderPane.add(center);
        this.add(borderPane, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

    }
    private void clear(){
        comboBox.setSelectedIndex(0);
        lQuantityText.setText("");
        lAvgText.setText("");
        textPrice.setText("");
        textQuantity.setText("");
        textDate.setText("");
    }
}
