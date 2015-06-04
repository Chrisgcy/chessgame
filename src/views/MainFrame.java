package views;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import mnk.MNKController;
import mnk.MNKPos;

/**
 * game UI
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * game UI constructor
     */
    public MainFrame() {
        initComponents();
        addActions();
        
        this.controller = new MNKController(this);
    }

   /**
    * init all the components
    */
    private void initComponents() {
    	this.setTitle("MNK Application");
    	this.setLocationRelativeTo(null);
    	
        setPanel = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        chessPanel = new javax.swing.JPanel();
        playerRadioButton = new javax.swing.JRadioButton();
        computerRadioButton = new javax.swing.JRadioButton();
        easyRadioButton = new javax.swing.JRadioButton();
        normalRadioButton = new javax.swing.JRadioButton();
        hardRadioButton = new javax.swing.JRadioButton();
        firstLabel = new javax.swing.JLabel();
        modeLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        meesageField = new javax.swing.JTextField();
        jMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        startMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu(); 
        welcomeMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setName("main_frame"); // NOI18N

        setPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Set"));

        firstLabel.setText("Go First:");

        playerRadioButton.setText("Player");
        playerRadioButton.setSelected(true);             
        computerRadioButton.setText("Computer");
 
        modeLabel.setText("Mode:");

        easyRadioButton.setText("Easy");
        easyRadioButton.setSelected(true);     
        
        normalRadioButton.setText("Normal");     
        
        hardRadioButton.setText("Hard");        
        
        startButton.setText("Start");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(setPanel);
        setPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modeLabel)
                    .addComponent(firstLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerRadioButton)
                    .addComponent(easyRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                    	.addComponent(normalRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(hardRadioButton)
                        .addGap(80, 80, 80)
                        .addComponent(startButton))                  
                        .addComponent(computerRadioButton))
                        .addContainerGap(290, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstLabel)
                    .addComponent(playerRadioButton)
                    .addComponent(computerRadioButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeLabel)
                    .addComponent(easyRadioButton)
                    .addComponent(normalRadioButton)
                    .addComponent(hardRadioButton)
                    .addComponent(startButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        chessButtonGroup = new javax.swing.JButton[4][5];
        for(int i = 0;i < 4; i++) {
        	 for(int j = 0;j < 5; j++) {
        		 chessButtonGroup[i][j] = new javax.swing.JButton();
        		 chessButtonGroup[i][j].setBackground(new Color(255,255,255));
        		 chessButtonGroup[i][j].setEnabled(false); 
        	 }
        }

        chessPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        chessPanel.setLayout(new GridLayout(4,5,0,0));
        for(int i = 0;i < 4; i++) {
        	for(int j = 0;j < 5; j++) {
        		chessPanel.add(chessButtonGroup[i][j]);
       	 	}
        }
        
        gamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Game"));
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
            		.addContainerGap()
                     .addComponent(chessPanel, 500, 500, 500)
                     .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
            		 .addContainerGap()
                     .addComponent(chessPanel, 400, 400, 400)
                     .addContainerGap(200, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        startMenuItem.setText("Start");
        fileMenu.add(startMenuItem);

        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        jMenuBar.add(fileMenu);

        helpMenu.setText("Help");
        welcomeMenuItem.setText("Welcome");
        helpMenu.add(welcomeMenuItem);
        jMenuBar.add(helpMenu);

        setJMenuBar(jMenuBar);
        
        meesageField.setEditable(false);
        meesageField.setText("Messages: game is not started");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meesageField, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(setPanel, 600,600,600)
                    .addComponent(gamePanel, 600,600,600))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setPanel,  100,100,100)
                .addGap(18, 18, 18)
                .addComponent(meesageField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gamePanel, 500,500,500)
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * add all the components action
     */
    public void addActions () {
    	//game start action
    	startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	controller.start();
            }
        });
    	
    	//all the chess button action
    	for(int i = 0;i < 4; i++) {
    		for(int j = 0;j < 5; j++) {
    			chessButtonGroup[i][j].addActionListener(new java.awt.event.ActionListener() {
    				public void actionPerformed(java.awt.event.ActionEvent evt) {
    					if(!controller.isPlayerTurn()) {
    						return; //if it's not Player's turn, Player cannot click
    					}
    					
    					//Player click once
    					javax.swing.JButton btn = (javax.swing.JButton)evt.getSource();
    					btn.setEnabled(false);
    					btn.setBackground(new Color(255,255,255));
    					btn.setIcon(new ImageIcon(getClass().getResource("/views/O.png")));
    					int ret = controller.playerClick(btn);  //after Player clicked, judge whether the game is over				
    					if(ret != 0) {
    						switch(ret) {
    						case -1:
    							JOptionPane.showMessageDialog(null, "Game Over! No one wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						case 1:
    							JOptionPane.showMessageDialog(null, "Game Over! Player wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						case 2:
    							JOptionPane.showMessageDialog(null, "Game Over! Computer wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						}
    						controller.end();
    						return;
    					}
    					
    					//then it's time for Computer to click
    					ret = controller.computerClick();
    					if(ret != 0) {
    						switch(ret) {
    						case -1:
    							JOptionPane.showMessageDialog(null, "Game Over! No wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						case 1:
    							JOptionPane.showMessageDialog(null, "Game Over! Player wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						case 2:
    							JOptionPane.showMessageDialog(null, "Game Over! Computer wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
    							break;
    						}
    						controller.end();
    						return;	
    					}
    				}
    			});
    		}
         }
    	 
         playerRadioButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	playerRadioButton.setSelected(true);
             	computerRadioButton.setSelected(false);
             }
         });
         
         computerRadioButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	playerRadioButton.setSelected(false);
             	computerRadioButton.setSelected(true);
             }
         });

         easyRadioButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	easyRadioButton.setSelected(true);
             	normalRadioButton.setSelected(false);
             	hardRadioButton.setSelected(false);
             }
         });
         
         normalRadioButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	easyRadioButton.setSelected(false);
             	normalRadioButton.setSelected(true);
             	hardRadioButton.setSelected(false);
             }
         });
         
         hardRadioButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	easyRadioButton.setSelected(false);
             	normalRadioButton.setSelected(false);
             	hardRadioButton.setSelected(true);
             }
         });
         
         startMenuItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	controller.start();
             }
         });
         
         exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	System.exit(0);
             }
         });
         
         welcomeMenuItem.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 JOptionPane.showMessageDialog(null, "Welcome", "MNK", JOptionPane.PLAIN_MESSAGE);
             }
         });
    	 
    }
    

    public javax.swing.JButton startButton;
    public javax.swing.JLabel firstLabel;
    public javax.swing.JLabel modeLabel;
    public javax.swing.JMenu fileMenu;
    public javax.swing.JMenu helpMenu;
    public javax.swing.JMenuBar jMenuBar;
    public javax.swing.JMenuItem startMenuItem;
    public javax.swing.JMenuItem exitMenuItem;
    public javax.swing.JMenuItem welcomeMenuItem;
    public javax.swing.JPanel setPanel;
    public javax.swing.JPanel gamePanel;
    public javax.swing.JRadioButton playerRadioButton;
    public javax.swing.JRadioButton computerRadioButton;
    public javax.swing.JRadioButton easyRadioButton;
    public javax.swing.JRadioButton normalRadioButton;
    public javax.swing.JRadioButton hardRadioButton;
    public javax.swing.JTextField meesageField;
    
    public javax.swing.JPanel chessPanel;
    public javax.swing.JButton [][]chessButtonGroup;
    
    private MNKController controller;
}