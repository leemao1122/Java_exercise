import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.FileDialog;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class ScheduleApp extends JFrame
{
    Schedule schedule;
    JTextArea textArea;

    public ScheduleApp()
    {
        super();
        setTitle("Schedule Of Trains");
        setSize(600, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        //Creat Menu
        Font font = new Font("TimesRoman", Font.PLAIN, 12);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        menuFile.setFont(font);
        JMenuItem menuItemFileOpen = new JMenuItem("Open");
        menuItemFileOpen.setFont(font);
        menuItemFileOpen.addMouseListener(new MouseListener(){
                public void mousePressed(MouseEvent e) {}

                public void mouseReleased(MouseEvent e) {
                    fileOpen();
                }

                public void mouseEntered(MouseEvent e) {}

                public void mouseExited(MouseEvent e) { }

                public void mouseClicked(MouseEvent e) { }
            });
        menuFile.add(menuItemFileOpen);
        menuFile.addSeparator();
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.setFont(font);
        menuItemExit.addMouseListener(new MouseListener(){
                public void mousePressed(MouseEvent e) {}

                public void mouseReleased(MouseEvent e) {
                    System.exit(1);
                }

                public void mouseEntered(MouseEvent e) {}

                public void mouseExited(MouseEvent e) { }

                public void mouseClicked(MouseEvent e) { }
            });
        menuFile.add(menuItemExit);        
        menuBar.add(menuFile);

        setJMenuBar(menuBar);
            
        textArea = new JTextArea();
        textArea.setColumns(40);
        textArea.setBounds(0, 0, getWidth(),getHeight()-20);
        textArea.setBackground(Color.white);
        textArea.setVisible(true);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        font = new Font("TimesRoman", Font.PLAIN, 12);
        textArea.setFont(font);
        panel.add(textArea);
        
        //Add JScrollPane 
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setBounds(10, 0, getWidth()-14,getHeight()-20);
        areaScrollPane.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(areaScrollPane);
            
        this.validate();
        this.setVisible(true);
        this.toFront();
    }

    protected void fileOpen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "txt files", "txt", "all files", "*"));
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            String fileName = fileChooser.getSelectedFile().getPath();
            try
            {
                schedule = new Schedule(fileName);
                schedule.sortDeparture();
                textArea.append(schedule.toString());
                textArea.append("The fastest train:\n");
                textArea.append(schedule.fastestTrain().toString() + "\n");
            }
            catch(IOException ex)
            {
                System.out.println(ex);
            }
        }
    }
    
    public static void main(String[] args)throws IOException{
       ScheduleApp myWindow = new ScheduleApp();
   }
}
