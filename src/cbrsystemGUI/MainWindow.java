package cbrsystemGUI;

import cbrsystem.*;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


//Filter file diaglog for XML files
class MyCustomFilter extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".xml");
    }
    @Override
    public String getDescription() {
        return "XML (*.xml)";
    }
}

//Format list for displaying query attributes
class MyListCell extends JLabel implements ListCellRenderer {
    
    Case query;
    Object val;
    
    public MyListCell(Case query) {
        setOpaque(true);
        this.query = query;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        val = value;
        if (query.contains(value.toString()) && !query.find(value.toString()).getValue().equals("")){
            setForeground(Color.BLUE);
        }
        else{
            setForeground(Color.RED);
        }
        if (isSelected) {
            setBackground(Color.GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }
        return this;
    }
    
    @Override
    public String toString(){
        return val.toString();
    }
}

//Format list for displaying knowledge base cases
class MyListCell2 extends JLabel implements ListCellRenderer {
    
    Object val;
    
    public MyListCell2() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        val = value;
        if (index == 0){
            setForeground(Color.BLUE);
        }
        else{
            setForeground(Color.BLACK);
        }
        if (isSelected) {
            setBackground(Color.GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }
        return this;
    }
    
    @Override
    public String toString(){
        return val.toString();
    }
}

//Format list for comparing attributes between query case and knowledge base case
class MyListCell3 extends JLabel implements ListCellRenderer {
    
    Object val;
    Case query;
    Case facts;
    
    public MyListCell3(Case query, Case facts) {
        setOpaque(true);
        this.query = query;
        this.facts = facts;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        val = value;
        String valString = value.toString();
        if (query.contains(valString) && facts.contains(valString)){
            if(query.find(valString).getValue().equals(facts.find(valString).getValue())){
                setForeground(Color.BLUE);
            }
            else{
                setForeground(Color.ORANGE);
            }
        }
        else{
            setForeground(Color.RED);
        }
        if (isSelected) {
            setBackground(Color.GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }
        return this;
    }
    
    @Override
    public String toString(){
        return val.toString();
    }
}

//General format for other lists
class MyListCell4 extends JLabel implements ListCellRenderer {
    
    Object val;
    
    public MyListCell4() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        val = value;
        if (isSelected) {
            setBackground(Color.GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }
        return this;
    }
    
    @Override
    public String toString(){
        return val.toString();
    }
}

public class MainWindow extends javax.swing.JFrame {
    
    private CaseBase knowledgeBase;
    private CaseBase queryCase; //case base containing query
    private Case query; //actual case to be queried
    private FeatureList featureDefinitions;
    private String actionLog; //Report problems and successes to user
    private String outputAttribute; //attribute name for output
    private Feature output;
    private DefaultListModel listModel; //UI components
    private DefaultListModel listModel2;
    private DefaultListModel listModel3;
    private DefaultListModel listModel4;
    private DefaultListModel listModel5;
    private FeatureList validAttributes; //feature definitions without output definition
    private String currentAdjust1; //Used to keep track of weights to be changed/displayed
    private String currentAdjust2; //Used to keep track of weights to be changed/displayed
    private Map<String,String> missingValues; //Used in imputation of missing values
    
    public MainWindow() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList8 = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        jList9 = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList10 = new javax.swing.JList();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        fileChooser = new javax.swing.JFileChooser();
        caseBaseLocation = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        factsLocation = new javax.swing.JTextField();
        fileChooser2 = new javax.swing.JFileChooser();
        factsPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        factsAttributeList = new javax.swing.JList();
        jLabel41 = new javax.swing.JLabel();
        factValue = new javax.swing.JComboBox();
        updateCase = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        infoGainField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        casesList = new javax.swing.JList();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        featuresList = new javax.swing.JList();
        jLabel37 = new javax.swing.JLabel();
        caseFeatureValue = new javax.swing.JTextField();
        outputLabel = new javax.swing.JLabel();
        outputField = new javax.swing.JTextField();
        outputLabel1 = new javax.swing.JLabel();
        similarityField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        attType = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        attPropList = new javax.swing.JList();
        jLabel34 = new javax.swing.JLabel();
        attWeight = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        attList = new javax.swing.JList();
        attributeField1 = new javax.swing.JComboBox();
        adjust1 = new javax.swing.JTextField();
        attributeField2 = new javax.swing.JComboBox();
        adjust2 = new javax.swing.JTextField();
        adjustButton = new javax.swing.JButton();
        wNone = new javax.swing.JRadioButton();
        wBoth = new javax.swing.JRadioButton();
        wOne = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FactsFileText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        KBFileText = new javax.swing.JTextArea();
        OpenKBButton = new javax.swing.JButton();
        OpenFactsButton = new javax.swing.JButton();
        ActionLog = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        OpenCBFile = new javax.swing.JMenuItem();
        OpenFactsFile = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane8.setViewportView(jList8);

        jScrollPane9.setViewportView(jList9);

        jLabel19.setText("Case:");

        jLabel20.setText("Feature:");

        jLabel21.setText("Name:");

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel22.setText("Type:");

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel23.setText("Property:");

        jScrollPane10.setViewportView(jList10);

        jLabel24.setText("Property Name:");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel25.setText("Property Value:");

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jLabel26.setText("Weight:");

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel21)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField11))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel23)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel22)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField12))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane10)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jLabel24)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jLabel25)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jLabel26)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel19)
                    .add(jLabel20)
                    .add(jLabel21)
                    .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel22)
                            .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel23)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 226, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel24)
                            .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel25)
                            .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel26)
                            .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane8)
                    .add(jScrollPane9))
                .add(38, 38, 38))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CBR System");
        setBackground(new java.awt.Color(255, 255, 255));

        fileChooser.setDialogTitle("This is my open dialog");
        fileChooser.setFileFilter(new MyCustomFilter());

        caseBaseLocation.setEditable(false);
        caseBaseLocation.setBackground(new java.awt.Color(204, 204, 204));
        caseBaseLocation.setText("None Selected");
        caseBaseLocation.setFocusable(false);
        caseBaseLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseBaseLocationActionPerformed(evt);
            }
        });

        jLabel1.setText("Knowledge Base File");

        jLabel2.setText("Facts File:");

        factsLocation.setEditable(false);
        factsLocation.setBackground(new java.awt.Color(204, 204, 204));
        factsLocation.setText("None Selected");
        factsLocation.setFocusable(false);
        factsLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factsLocationActionPerformed(evt);
            }
        });

        fileChooser2.setDialogTitle("Selects facts");
        fileChooser2.setFileFilter(new MyCustomFilter());

        factsPane.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel40.setText("Attribute:");

        factsAttributeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                factsAttributeListValueChanged(evt);
            }
        });
        jScrollPane18.setViewportView(factsAttributeList);

        jLabel41.setText("Value:");

        factValue.setEditable(true);

        updateCase.setText("Update");
        updateCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCaseActionPerformed(evt);
            }
        });

        jLabel42.setText("Information Gain:");

        infoGainField.setEditable(false);
        infoGainField.setBackground(new java.awt.Color(204, 204, 204));
        infoGainField.setFocusable(false);
        infoGainField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoGainFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel40)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jScrollPane18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 506, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(infoGainField)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel42)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jLabel41)
                                .add(factValue, 0, 441, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, updateCase, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel40)
                    .add(jLabel41))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(factValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(updateCase)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel42)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(infoGainField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 404, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        factsPane.addTab("Facts", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        casesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                casesListValueChanged(evt);
            }
        });
        jScrollPane14.setViewportView(casesList);

        jLabel35.setText("Case Name:");

        jLabel36.setText("Feature:");

        featuresList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                featuresListValueChanged(evt);
            }
        });
        jScrollPane15.setViewportView(featuresList);

        jLabel37.setText("Value:");

        caseFeatureValue.setEditable(false);
        caseFeatureValue.setBackground(new java.awt.Color(204, 204, 204));
        caseFeatureValue.setFocusable(false);
        caseFeatureValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseFeatureValueActionPerformed(evt);
            }
        });

        outputLabel.setText("Output:");

        outputField.setEditable(false);
        outputField.setBackground(new java.awt.Color(204, 204, 204));
        outputField.setFocusable(false);
        outputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFieldActionPerformed(evt);
            }
        });

        outputLabel1.setText("Similarity:");

        similarityField.setEditable(false);
        similarityField.setBackground(new java.awt.Color(204, 204, 204));
        similarityField.setFocusable(false);
        similarityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                similarityFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel35)
                        .add(0, 566, Short.MAX_VALUE))
                    .add(jScrollPane14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel36)
                        .add(jScrollPane15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel37)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(caseFeatureValue))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(outputLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(outputField))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(outputLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(similarityField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel35)
                    .add(jLabel36))
                .add(11, 11, 11)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScrollPane15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 276, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel37)
                            .add(caseFeatureValue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(outputLabel)
                            .add(outputField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(outputLabel1)
                            .add(similarityField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 379, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        factsPane.addTab("Knowledge Base", jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel30.setText("Type:");

        attType.setEditable(false);
        attType.setBackground(new java.awt.Color(204, 204, 204));
        attType.setFocusable(false);
        attType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attTypeActionPerformed(evt);
            }
        });

        jLabel31.setText("Property:");

        attPropList.setFocusable(false);
        attPropList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                attPropListValueChanged(evt);
            }
        });
        jScrollPane13.setViewportView(attPropList);

        jLabel34.setText("Weight:");

        attWeight.setEditable(false);
        attWeight.setBackground(new java.awt.Color(204, 204, 204));
        attWeight.setFocusable(false);
        attWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attWeightActionPerformed(evt);
            }
        });

        jLabel38.setText("Attribute List:");

        attList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                attListValueChanged(evt);
            }
        });
        jScrollPane16.setViewportView(attList);

        attributeField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributeField1ActionPerformed(evt);
            }
        });

        attributeField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributeField2ActionPerformed(evt);
            }
        });

        adjustButton.setText("Adjust Weight");
        adjustButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjustButtonActionPerformed(evt);
            }
        });

        wNone.setSelected(true);
        wNone.setText("None");
        wNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wNoneActionPerformed(evt);
            }
        });

        wBoth.setText("Both");
        wBoth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wBothActionPerformed(evt);
            }
        });

        wOne.setText("One");
        wOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wOneActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel38)
                            .add(jScrollPane16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 417, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(attributeField1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjust1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 217, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)))
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jLabel34)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(attWeight))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel31)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                                .add(jLabel30)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(attType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 298, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(attributeField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(adjust2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 172, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(adjustButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(wNone)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(wOne)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(wBoth, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(jScrollPane13))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(attributeField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(adjust1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(attributeField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(adjust2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(adjustButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel30)
                        .add(attType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel38))
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(wBoth)
                        .add(wOne)
                        .add(wNone)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel31)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel34)
                            .add(attWeight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 362, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(22, 22, 22))
        );

        factsPane.addTab("Attributes", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel43.setText("Knowledge Base File:");

        jLabel44.setText("Facts File:");

        FactsFileText.setColumns(20);
        FactsFileText.setRows(5);
        jScrollPane1.setViewportView(FactsFileText);

        KBFileText.setColumns(20);
        KBFileText.setRows(5);
        jScrollPane2.setViewportView(KBFileText);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel43)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 466, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel44)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel43)
                    .add(jLabel44))
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jScrollPane2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        factsPane.addTab("Input Files", jPanel5);

        OpenKBButton.setText("Choose");
        OpenKBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenKBButtonActionPerformed(evt);
            }
        });

        OpenFactsButton.setText("Choose");
        OpenFactsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenFactsButtonActionPerformed(evt);
            }
        });

        ActionLog.setForeground(new java.awt.Color(0, 0, 255));
        ActionLog.setText("Action Log");

        jMenu1.setText("File");

        OpenCBFile.setText("Open Knowledge Base File");
        OpenCBFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenCBFileActionPerformed(evt);
            }
        });
        jMenu1.add(OpenCBFile);

        OpenFactsFile.setText("Open Facts File");
        OpenFactsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenFactsFileActionPerformed(evt);
            }
        });
        jMenu1.add(OpenFactsFile);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ActionLog, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(factsPane)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(caseBaseLocation))
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(factsLocation)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(OpenKBButton)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, OpenFactsButton))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(caseBaseLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(OpenKBButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(factsLocation)
                        .add(OpenFactsButton))
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ActionLog)
                .add(13, 13, 13)
                .add(factsPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 504, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void OpenCBFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenCBFileActionPerformed
        openKnowledgeBase();
    }//GEN-LAST:event_OpenCBFileActionPerformed
    
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed
    
    private void caseBaseLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseBaseLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseBaseLocationActionPerformed
    
    private void factsLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factsLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_factsLocationActionPerformed
    
    private void OpenFactsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFactsFileActionPerformed
        openFacts();
    }//GEN-LAST:event_OpenFactsFileActionPerformed
    
    private void OpenKBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenKBButtonActionPerformed
        openKnowledgeBase();
    }//GEN-LAST:event_OpenKBButtonActionPerformed
    
    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed
    
    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed
    
    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed
    
    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed
    
    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed
    
    private void attWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attWeightActionPerformed
            
    private void attTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attTypeActionPerformed
        
    private void caseFeatureValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseFeatureValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseFeatureValueActionPerformed
    
    private void OpenFactsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFactsButtonActionPerformed
        openFacts();
    }//GEN-LAST:event_OpenFactsButtonActionPerformed
    
    private void infoGainFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoGainFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infoGainFieldActionPerformed
    
    //Make selection from fact attribute list
    private void factsAttributeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_factsAttributeListValueChanged
        //Redraw fact value combobox for new selected attribute, redraw information gain text field
        if(factsAttributeList.getSelectedValue() != null){
            factValue.removeAllItems();
            String attribute = factsAttributeList.getSelectedValue().toString();
            Feature currentFeature = featureDefinitions.find(attribute);
            List<Property> properties = currentFeature.getProperties();
            List<Fact> queryFacts = query.getFactList();
            String valueFact = "";
            if(!currentFeature.getType().equals("numeric")){
                factValue.setEditable(false);
                factValue.addItem("");
            }
            else{
                factValue.setEditable(true);
            }
            for(Fact f: queryFacts){
                if (f.getAttribute().equals(attribute)){
                    valueFact = f.getValue();
                }
            }
            for(Property p: properties){
                if(!currentFeature.getType().equals("numeric")){
                    factValue.addItem(p.getValue());
                }
            }
            factValue.setSelectedItem(valueFact);
            String infoGain = "" + currentFeature.getInformationGain();
            infoGainField.setText(infoGain);
        }
    }//GEN-LAST:event_factsAttributeListValueChanged
    
    //Update query case based on new attribute value
    private void updateCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCaseActionPerformed
        if(factsAttributeList.getSelectedValue() != null){
            String newVal = factValue.getSelectedItem().toString();
            Fact queryFact;
            if(!query.contains(factsAttributeList.getSelectedValue().toString())){
                queryFact = new Fact();
                queryFact.setAttribute(factsAttributeList.getSelectedValue().toString());
                query.add(queryFact);
            }
            if(newVal.equals("")){
                query.remove(query.find(factsAttributeList.getSelectedValue().toString()));
            }
            else{
                query.find(factsAttributeList.getSelectedValue().toString()).setValue(newVal);
            }
            update();
        }
    }//GEN-LAST:event_updateCaseActionPerformed

    
    private void outputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputFieldActionPerformed

    //Redraw elements of knowledge base panel
    private void casesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_casesListValueChanged
        if(casesList.getSelectedValue() != null){
            Case caseName = knowledgeBase.find(casesList.getSelectedValue().toString());
            outputLabel.setText(caseName.getOutputCopy().getAttribute()+":");
            outputField.setText(caseName.getOutputCopy().getValue());
            similarityField.setText(Double.toString(caseName.getSimilarity()));
            List<Fact> facts = caseName.getFactListCopy();
            listModel3 = new DefaultListModel();
            featuresList.setModel(listModel3);
            featuresList.setCellRenderer(new MyListCell3(query,caseName));
            listModel3.clear();
            for(Fact f: facts){
                listModel3.addElement(f.getAttribute());
            }
        }
    }//GEN-LAST:event_casesListValueChanged

    private void similarityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_similarityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_similarityFieldActionPerformed

    //Update display of attribute value for case
    private void featuresListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_featuresListValueChanged
        if((casesList.getSelectedValue() != null) && (featuresList.getSelectedValue() != null)){
            Case caseName = knowledgeBase.find(casesList.getSelectedValue().toString());
            List<Fact> facts = caseName.getFactListCopy();
            String value = caseName.find(featuresList.getSelectedValue().toString()).getValue();
            caseFeatureValue.setText(value);
        }
    }//GEN-LAST:event_featuresListValueChanged

    //Update feature weights on button press
    private void adjustButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjustButtonActionPerformed
        updateWeights(wNone.isSelected(),wOne.isSelected(),wBoth.isSelected());
        actionLog = "Weights updated! ";
        update();
    }//GEN-LAST:event_adjustButtonActionPerformed

    //Populate first weight field
    private void attributeField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributeField1ActionPerformed
        if(attributeField1.getItemCount() != 0){
            String feature = attributeField1.getSelectedItem().toString();
            double w = featureDefinitions.find(feature).getWeight();
            String weight = w + "";
            adjust1.setText(weight);
        }
    }//GEN-LAST:event_attributeField1ActionPerformed

    //Populate second weight field
    private void attributeField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributeField2ActionPerformed
        if(attributeField2.getItemCount() != 0){
            String feature = attributeField2.getSelectedItem().toString();
            double w = featureDefinitions.find(feature).getWeight();
            String weight = w + "";
            adjust2.setText(weight);
        }
    }//GEN-LAST:event_attributeField2ActionPerformed

    //'None' radio button selected
    private void wNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wNoneActionPerformed
        wNone.setSelected(true);
        wOne.setSelected(false);
        wBoth.setSelected(false);
    }//GEN-LAST:event_wNoneActionPerformed

    //'Both' radio button selected
    private void wBothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wBothActionPerformed
        wNone.setSelected(false);
        wOne.setSelected(false);
        wBoth.setSelected(true);
    }//GEN-LAST:event_wBothActionPerformed

    //'One' radio button selected
    private void wOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wOneActionPerformed
        wNone.setSelected(false);
        wOne.setSelected(true);
        wBoth.setSelected(false);
    }//GEN-LAST:event_wOneActionPerformed

    //Redraw property list after attribute selection
    private void attListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_attListValueChanged
        if(attList.getSelectedValue() != null){
            Feature feature = featureDefinitions.find(attList.getSelectedValue().toString());
            attType.setText(feature.getType());
            attWeight.setText(Double.toString(feature.getWeight()));
            Property[] properties = feature.getPropertiesCopy().toArray(new Property[0]);
            
            listModel5 = new DefaultListModel();
            attPropList.setModel(listModel5);
            attPropList.setCellRenderer(new MyListCell4());
            for(int j = 0; j < properties.length; j++){
                listModel5.addElement(properties[j].getName() + ": " + properties[j].getValue());
            }
        }
    }//GEN-LAST:event_attListValueChanged

    private void attPropListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_attPropListValueChanged
    }//GEN-LAST:event_attPropListValueChanged
    
    //Load knowledge base
    public void openKnowledgeBase(){
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filepath = file.getAbsolutePath();
            caseBaseLocation.setText(filepath);
            try{
                ParseXML parser = new ParseXML(filepath);
                knowledgeBase = parser.getCaseBase();
                featureDefinitions = parser.getFeatureDefinitions();
                if(knowledgeBase != null){
                    ActionLog.setForeground(Color.blue);
                    actionLog = "Knowledge base file parsed successfully! ";
                    KBFileText.read(new FileReader(file), file);
                    if(featureDefinitions == null){
                        ActionLog.setForeground(Color.red);
                        actionLog = "No feature definitions found in knowledge base. ";
                    }
                    ActionLog.setText(actionLog);
                }
                else{
                    ActionLog.setForeground(Color.red);
                    actionLog = "Knowledge base failed to parse successfully! ";
                    ActionLog.setText(actionLog);
                }
                update();
            }catch(ParserConfigurationException e){
            }catch(SAXException e){
            }catch(IOException e){
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    
    //Load query case
    public void openFacts(){
        int returnVal = fileChooser2.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser2.getSelectedFile();
            String filepath = file.getAbsolutePath();
            factsLocation.setText(filepath);
            try{
                ParseXML parser = new ParseXML(filepath);
                queryCase = parser.getCaseBase();
                if(queryCase != null){
                    query = queryCase.getCases().get(0);
                    FactsFileText.read(new FileReader(file), file);
                    ActionLog.setForeground(Color.blue);
                    actionLog = "Facts file parsed successfully! ";
                    if(queryCase.getSize() > 1){
                        ActionLog.setForeground(Color.yellow);
                        actionLog += "More than one query case found. Selecting first case as query case. ";
                    }
                    ActionLog.setText(actionLog);
                }
                else{
                    ActionLog.setForeground(Color.red);
                    actionLog = "Facts failed to parse successfully! ";
                    ActionLog.setText(actionLog);
                }
                update();
            }catch(ParserConfigurationException e){
            }catch(SAXException e){
            }catch(IOException e){
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    
    //Update information gain, similarity, and weights, redraw some form elements
    public void update(){
        if((knowledgeBase != null) && (queryCase != null) && (featureDefinitions != null)){
            calculateInformationGain(knowledgeBase, featureDefinitions);
            missingAttributes();
            calculateSimilarity(knowledgeBase, query);
            ActionLog.setForeground(Color.blue);
            actionLog += "Updated successfully! ";
            ActionLog.setText(actionLog);
            actionLog = "";
            updateWeights(true,false,false);
            drawAttList();
        }
        else{
            ActionLog.setForeground(Color.red);
            actionLog += "Failed to execute update due to invalid files. ";
            ActionLog.setText(actionLog);
            actionLog = "";
        }
    }
    
    //Calculate similarity metric
    public void calculateSimilarity(CaseBase knowledge, Case query){
        List<Case> caseList = knowledge.getCases();
        for(Case currentCase: caseList){
            List<Fact> factList = currentCase.getFactListCopy();
            Map<String,Feature> featureDefs = featureDefinitions.getFeatureListCopy();
            double similarity = 0;
            for(Fact fact: factList){
                String attribute = fact.getAttribute();
                String baseValue = fact.getValue();
                String type = featureDefs.get(attribute).getType();
                double weight = featureDefs.get(attribute).getWeight();
                if(query.contains(attribute)){
                    String queryValue = query.find(attribute).getValue();
                    //numeric attribute
                    if(type.equals("numeric")){
                        List<Property> propertyList = featureDefs.get(attribute).getPropertiesCopy();
                        double maxValue = 0;
                        double minValue = 0;
                        double bValue = Double.parseDouble(baseValue);
                        double qValue = Double.parseDouble(queryValue);
                        for(Property prop: propertyList){
                            if(prop.getName().equals("max")){
                                maxValue = Double.parseDouble(prop.getValue());
                            }
                            else if(prop.getName().equals("min")){
                                minValue = Double.parseDouble(prop.getValue());
                            }
                        }
                        // 1 - [(A - B) / (max - min)] used as metric, if A = B --> 1, if A is max & B is min --> 0
                        similarity += weight * (1 - (Math.abs(bValue - qValue) / (maxValue - minValue)));
                    }
                    //symbolic attribute
                    else{
                        if(queryValue.equals(baseValue)){
                            similarity += weight * 1; //Add one for same value, zero for different value
                        }
                    }
                }
                //Missing values are assigned to most common value for symbolic and mean for numeric if in query case and not in kb case
                else{
                   String queryValue = missingValues.get(attribute);
                   if(type.equals("numeric")){
                        List<Property> propertyList = featureDefs.get(attribute).getPropertiesCopy();
                        double maxValue = 0;
                        double minValue = 0;
                        double bValue = Double.parseDouble(baseValue);
                        double qValue = Double.parseDouble(queryValue);
                        for(Property prop: propertyList){
                            if(prop.getName().equals("max")){
                                maxValue = Double.parseDouble(prop.getValue());
                            }
                            else if(prop.getName().equals("min")){
                                minValue = Double.parseDouble(prop.getValue());
                            }
                        }
                        // 1 - [(A - B) / (max - min)] used as metric, if A = B --> 1, if A is max & B is min --> 0
                        similarity += weight * (1 - (Math.abs(bValue - qValue) / (maxValue - minValue)));
                    }
                    //symbolic attribute
                    else{
                        if(queryValue.equals(baseValue)){
                            similarity += weight * 1; //Add one for same value, zero for different value
                        }
                    } 
                }
            }
            //Missing values are assigned a similarity of zero if they appear in kb case and not query case
            currentCase.setSimilarity(similarity);
        }
        Case[] caseResults = caseList.toArray(new Case[0]);
        //Sort cases
        Arrays.sort(caseResults);
        listModel2 = new DefaultListModel();
        casesList.setModel(listModel2);
        casesList.setCellRenderer(new MyListCell2());
        for(int i = 0; i < caseResults.length; i++){
            String caseName = caseResults[i].getName();
            listModel2.addElement((caseResults[i].getName()));
        }
    }
    
    //Calculate information gain for an attribute
    public void calculateInformationGain(CaseBase knowledge, FeatureList features){
        outputAttribute = knowledge.getCases().get(0).getOutput().getAttribute();
        output = new Feature(features.find(outputAttribute));
        //remove output
        FeatureList featuresNoOutput = new FeatureList(features);
        featuresNoOutput.remove(outputAttribute);
        List<Property> outputProperties = output.getPropertiesCopy();
        Map<String,Integer> outputCount = new HashMap<String,Integer>(); //Count number of instances of each output value
        for(Property p: outputProperties){
            outputCount.put(p.getValue(),0);
        }
        List<Case> cases = knowledge.getCasesCopy();
        for(Case c: cases){
            String property = c.getOutput().getValue();
            int count = outputCount.get(property).intValue() + 1;
            outputCount.remove(property);
            outputCount.put(property, count);
        }
        Integer[] outputResults;
        outputResults = outputCount.values().toArray(new Integer[0]);
        double total = 0; //Get total number of instances
        for(int i = 0; i < outputResults.length; i++){
            total += outputResults[i].intValue();
        }
        double information = 0; //calculate information
        for(int i = 0; i < outputResults.length; i++){
            double num = outputResults[i].intValue();
            information -= (num / total) * log2(num / total);
        }
        String[] outputResults2;
        outputResults2 = outputCount.keySet().toArray(new String[0]);
        Map<String,Integer> outputIndex = new HashMap<String,Integer>(); //Stores index for keeping track of an instance's output
        for(int i = 0; i < outputResults2.length; i++){
            outputIndex.put(outputResults2[i],i);
        }
        String[] featuresNoOutputResults;
        featuresNoOutputResults = featuresNoOutput.getFeatureList().keySet().toArray(new String[0]);
        Map<String,Integer> propertyIndex = new HashMap<String,Integer>(); //Stores index for keeping track of a property
        for(int i = 0; i < featuresNoOutput.getSize(); i++){
            if(featuresNoOutput.find(featuresNoOutputResults[i]).getType().equals("symbolic")){
                List<Property> properties = featuresNoOutput.find(featuresNoOutputResults[i]).getPropertiesCopy();
                for(int j = 0; j < properties.size(); j++){
                    if(featuresNoOutput.find(featuresNoOutputResults[i]).getType().equals("symbolic")){
                        propertyIndex.put(properties.get(j).getValue(),j);
                    }
                }
                double[][] propertyCounts= new double[propertyIndex.size()][outputIndex.size()]; //Table of property x output
                for(int j = 0; j < propertyIndex.size(); j++){
                    for(int k = 0; k < outputIndex.size(); k++){
                        propertyCounts[j][k] = 0;
                    }
                }
                //Fill in table
                for(Case c: cases){
                    String outputValue = c.getOutput().getValue();
                    List<Fact> factList = c.getFactListCopy();
                    for(Fact f: factList){
                        if(f.getAttribute().equals(featuresNoOutputResults[i])){
                            String value = f.getValue();
                            propertyCounts[propertyIndex.get(value).intValue()][outputIndex.get(outputValue).intValue()]++;
                        }
                    }
                }
                double remainder = 0; //Calculate remainder
                for(int j = 0; j < propertyIndex.size(); j++){
                    double propTotal = 0;
                    for(int k = 0; k < outputIndex.size(); k++){
                        propTotal += propertyCounts[j][k];
                    }
                    double appliesTo = propTotal/total;
                    for(int k = 0; k < outputIndex.size(); k++){
                        if(propertyCounts[j][k] != 0){
                            remainder += appliesTo * -1 * (propertyCounts[j][k]/propTotal) * log2(propertyCounts[j][k]/propTotal);
                        }
                    }
                }
                double informationGain = information - remainder; //Calculate information gain
                featuresNoOutput.find(featuresNoOutputResults[i]).setInformationGain(informationGain);
                propertyIndex.clear();
            }
        }
        Feature[] featuresNoOutputResults2 = featuresNoOutput.getFeatureList().values().toArray(new Feature[0]);
        //Sort attributes by information gain
        Arrays.sort(featuresNoOutputResults2);
        listModel = new DefaultListModel();
        factsAttributeList.setModel(listModel);
        factsAttributeList.setCellRenderer(new MyListCell(query));
        for(int j = 0; j < featuresNoOutputResults2.length; j++){
            listModel.addElement((featuresNoOutputResults2[j].getName()));
        }
    }
    
    //Calculate the log base 2 of a number
    public double log2(double num){
        return Math.log(num) / Math.log(2);
    }
    
    //Update weights
    public void updateWeights(Boolean none, Boolean one, Boolean both){
        validAttributes = new FeatureList(featureDefinitions);
        validAttributes.remove(outputAttribute);
        Feature[] vaList = validAttributes.getFeatureListCopy().values().toArray(new Feature[0]);
        if(attributeField1.getItemCount() == 0){
            currentAdjust1 = vaList[0].getName();
            currentAdjust2 = vaList[1].getName();
        }
        else{
            currentAdjust1 = attributeField1.getSelectedItem().toString();
            currentAdjust2 = attributeField2.getSelectedItem().toString();
        }
        
        double w1;
        double w2;
        double w3;
                
        if(none){
            w1 = featureDefinitions.find(currentAdjust1).getWeight();
            w2 = featureDefinitions.find(currentAdjust2).getWeight();
            w3 = 0;
        }
        else if(one){
            w1 = Double.parseDouble(adjust1.getText());
            w2 = (1 - w1) / (vaList.length - 1);
            w3 = w2;
        }
        else{
            w1 = Double.parseDouble(adjust1.getText());
            w2 = Double.parseDouble(adjust2.getText());
            w3 = (1 - (w1 + w2)) / (vaList.length - 2);   
        }
        
        if(one || both){
            for(int i = 0; i < vaList.length; i++){
                if(vaList[i].getName().equals(currentAdjust1)){
                    featureDefinitions.find(currentAdjust1).setWeight(w1);
                }
                else if(vaList[i].getName().equals(currentAdjust2)){
                    featureDefinitions.find(currentAdjust2).setWeight(w2);
                }
                else{
                    String feature = vaList[i].getName();
                    featureDefinitions.find(feature).setWeight(w3);
                }
            }
        }
        
        w1 = featureDefinitions.find(currentAdjust1).getWeight();
        String weight = "" + w1;
        adjust1.setText(weight);
        attributeField1.removeAll();
        for(int i = 0; i < vaList.length; i++){
            attributeField1.addItem(vaList[i].getName());
        }
        attributeField1.setSelectedItem(currentAdjust1);

        w2 = featureDefinitions.find(currentAdjust2).getWeight();
        weight = "" + w2;
        adjust2.setText(weight);
        attributeField2.removeAll();
        for(int i = 0; i < vaList.length; i++){
            attributeField2.addItem(vaList[i].getName());
        }
        attributeField2.setSelectedItem(currentAdjust2);
    }
    
    //Draw the attribute list
    public void drawAttList(){
        Feature[] features = featureDefinitions.getFeatureList().values().toArray(new Feature[0]);
        listModel4 = new DefaultListModel();
        attList.setModel(listModel4);
        attList.setCellRenderer(new MyListCell4());
        for(int j = 0; j < features.length; j++){
            listModel4.addElement((features[j].getName()));
        }
    }
    
    //Find the values to use when attributes are missing
    public void missingAttributes(){
        Feature[] features = featureDefinitions.getFeatureList().values().toArray(new Feature[0]);
        Map<String,Integer> counter = new LinkedHashMap<String,Integer>(); //Use map to store counts
        missingValues = new HashMap<String,String>(); //Use map to keep track of missing value substitutions
        for(int i = 0; i < features.length; i++){
            counter.clear();
            Feature feature = features[i]; //Select feature
            Boolean isSymbolic = feature.getType().equals("symbolic");
            List<Property> properties = feature.getPropertiesCopy();
            double total = 0;
            double numInstances = 0;
            for(Property p: properties){
                if(isSymbolic){
                    counter.put(p.getValue(),0);
                }
            }
            List<Case> cases = knowledgeBase.getCasesCopy();
            //Compare feature with every case
            for(Case c: cases){
                List<Fact> facts = c.getFactListCopy();
                for(Fact f: facts){
                    if(f.getAttribute().equals(feature.getName())){
                        if(isSymbolic){
                            int num = counter.get(f.getValue()).intValue();
                            num++;
                            counter.remove(f.getValue());
                            counter.put(f.getValue(), num);
                        }
                        if(!isSymbolic){
                            total += Double.parseDouble(f.getValue());
                            numInstances++;
                        }
                    }
                }
            }
            Integer[] values = counter.values().toArray(new Integer[0]);
            int max = 0;
            int element = 0;
            //Find most common attribute values
            for(int j = 0; j < values.length; j++){
                if(values[j].intValue() > max){
                    max = values[j];
                    element = j;
                }
            }
            String[] keys = counter.keySet().toArray(new String[0]);
            //Store replacement values
            if(!isSymbolic){
                double average = total / numInstances;
                missingValues.put(feature.getName(),Double.toString(average));
            }
            else{
                missingValues.put(feature.getName(),keys[element]);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActionLog;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JTextArea FactsFileText;
    private javax.swing.JTextArea KBFileText;
    private javax.swing.JMenuItem OpenCBFile;
    private javax.swing.JButton OpenFactsButton;
    private javax.swing.JMenuItem OpenFactsFile;
    private javax.swing.JButton OpenKBButton;
    private javax.swing.JTextField adjust1;
    private javax.swing.JTextField adjust2;
    private javax.swing.JButton adjustButton;
    private javax.swing.JList attList;
    private javax.swing.JList attPropList;
    private javax.swing.JTextField attType;
    private javax.swing.JTextField attWeight;
    private javax.swing.JComboBox attributeField1;
    private javax.swing.JComboBox attributeField2;
    private javax.swing.JTextField caseBaseLocation;
    private javax.swing.JTextField caseFeatureValue;
    private javax.swing.JList casesList;
    private javax.swing.JComboBox factValue;
    private javax.swing.JList factsAttributeList;
    private javax.swing.JTextField factsLocation;
    private javax.swing.JTabbedPane factsPane;
    private javax.swing.JList featuresList;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFileChooser fileChooser2;
    private javax.swing.JTextField infoGainField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JList jList10;
    private javax.swing.JList jList8;
    private javax.swing.JList jList9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField outputField;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JLabel outputLabel1;
    private javax.swing.JTextField similarityField;
    private javax.swing.JButton updateCase;
    private javax.swing.JRadioButton wBoth;
    private javax.swing.JRadioButton wNone;
    private javax.swing.JRadioButton wOne;
    // End of variables declaration//GEN-END:variables
}
