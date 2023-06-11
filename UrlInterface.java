import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.EventListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;    
import java.awt.event.*;    
import java.io.*;  

public class UrlInterface implements ActionListener{
    private static JTextField txtIdNo, txtLName, txtFName, txtContact, txtLocation;
    private static JButton btnAdd, btnSubmit, btnCancel, btnDispAll, btnExit, btnSearch;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static JLabel lblTitle;

    public static void main(String[] args){
        frame=new JFrame();
        frame.setSize(600, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel lblTitle=new JLabel("URL/FILE TEXT EXTRACTOR and FILE CONVERTER");
        lblTitle.setFont(new Font("ARIAL",Font.BOLD,16));
        lblTitle.setBounds(50, 5, 450, 20);
        panel.add(lblTitle);

        JLabel urlInput=new JLabel("Enter URL");
        urlInput.setBounds(30, 30, 320, 25);
        panel.add(urlInput);

        JTextField url1 = new JTextField();
        url1.setBounds(120, 30, 315, 25);
        panel.add(url1);

        JTextArea data = new JTextArea();
        data.setBounds(20, 90, 530, 450);
        data.setEditable(false);
        panel.add(data);

        JScrollPane scrollPane = new JScrollPane(data);
        scrollPane.setBounds(20, 90, 530, 450);
        panel.add(scrollPane);

        JButton btnAdd=new JButton("Load");
        btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UrlConnection uc = new UrlConnection();
                    data.setText(uc.displayData(url1.getText()));
                }
            });

        btnAdd.setBounds(450, 30, 100, 25);
        panel.add(btnAdd);
        frame.setVisible(true);

        JButton btnBrowse=new JButton("Browse");
        btnBrowse.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==btnBrowse){    
                        JFileChooser fc=new JFileChooser();    
                        int i=fc.showOpenDialog(frame);    
                        if(i==JFileChooser.APPROVE_OPTION){    
                            File f=fc.getSelectedFile();    
                            String filepath=f.getPath();    
                            try{  
                                BufferedWriter output = new BufferedWriter(new FileWriter("data.txt"));
                                BufferedReader br=new BufferedReader(new FileReader(filepath));    
                                String s1="",s2="";                         
                                while((s1=br.readLine())!=null){    
                                    s2+=s1+"\n";    
                                }    
                                output.write(s2);
                                data.setText(s2);
                                output.close();
                                br.close();    
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }                 
                        }    
                    }    
                }
            });

        btnBrowse.setBounds(450, 60, 100, 25);
        panel.add(btnBrowse);
        frame.setVisible(true);

        JButton btnClear=new JButton("Clear");
        btnClear.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    UrlConnection u = new UrlConnection();
                    u.clearTextFile();
                    data.setText("");
                    url1.setText("");
                }
            });
        btnClear.setBounds(335, 60, 100, 25);
        panel.add(btnClear);
        frame.setVisible(true);

        JButton conCsv=new JButton("Convert to CSV File");
        conCsv.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == conCsv) {
                        UrlConnection u = new UrlConnection();
                        String text = data.getText();
                        u.convertToCSV(text);
                        JOptionPane.showMessageDialog(frame, "Converted to CSV file Successfully");
                    }
                }
            });

        conCsv.setBounds(350, 550, 200, 25);
        panel.add(conCsv);
        frame.setVisible(true);

        
        JButton conXml=new JButton("Convert to XML File");
        conXml.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == conXml) {
                        UrlConnection u = new UrlConnection();
                        String text = data.getText();
                        u.convertToXML(text);
                        JOptionPane.showMessageDialog(frame, "Converted to XML file Successfully");
                    }
                }
            });

        conXml.setBounds(350, 580, 200, 25);
        panel.add(conXml);
        frame.setVisible(true);

        
        JButton exit=new JButton("Exit");
        exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
        exit.setBounds(20, 565, 150, 25);
        panel.add(exit);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){

    }
}