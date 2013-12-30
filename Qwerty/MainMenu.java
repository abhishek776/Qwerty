package Qwerty;

import javax.swing.*;


/**
 * The MainMenu class is essential to the beginning of the game, as it asks the
 * user for his/her name, and the difficult/order they want to play in. It is
 * the starting menu for the game, Qwerty.
 * 
 * @author Abhishek, Jeffrey, Aneesh
 * 
 */
public class MainMenu extends JFrame
{

    private static final long serialVersionUID = 1L;


    /**
     * Constructs the MainMenu object by calling its method initComponents.
     */
    public MainMenu()
    {

        initComponents();

    }


    /**
     * This method constructs the JFrame, and how it looks like to the user, and
     * constructs the action listeners to the buttons in MainMenu.
     */

    private void initComponents()
    {

        this.setLocation( 300, 300 );
        this.setTitle( "Welcome To Qwerty!" );
        jLabel1 = new JLabel();

        jSeparator1 = new JSeparator();

        jTextField1 = new JTextField();

        jLabel2 = new JLabel();

        jSeparator2 = new JSeparator();

        jLabel3 = new JLabel();

        jLabel4 = new JLabel();

        jSeparator3 = new JSeparator();

        jScrollPane1 = new JScrollPane();

        jList1 = new JList();

        jScrollPane2 = new JScrollPane();

        jList2 = new JList();

        jButton1 = new JButton();

        jButton2 = new JButton();

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        jLabel1.setFont( new java.awt.Font( "Lucida Grande", 0, 24 ) ); // NOI18N

        jLabel1.setText( "Welcome To QWERTY!" );

        jTextField1.setFont( new java.awt.Font( "Lucida Grande", 2, 13 ) ); // NOI18N

        jTextField1.setText( "Player" );

        jLabel2.setText( "Enter Your Name:" );

        jLabel3.setText( "Select Your Difficulty" );

        jLabel4.setText( "Select Playing Order" );

        jSeparator3.setOrientation( SwingConstants.VERTICAL );

        jList1.setModel( new AbstractListModel()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            String[] strings = { "Easy", "Medium", "Hard", "Godly" };


            public int getSize()
            {
                return strings.length;
            }


            public Object getElementAt( int i )
            {
                return strings[i];
            }

        } );

        jScrollPane1.setViewportView( jList1 );

        jList2.setModel( new AbstractListModel()
        {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            String[] strings = { "First", "Second" };


            public int getSize()
            {
                return strings.length;
            }


            public Object getElementAt( int i )
            {
                return strings[i];
            }

        } );

        jScrollPane2.setViewportView( jList2 );

        jButton1.setText( "Start" );

        jButton1.addActionListener( new java.awt.event.ActionListener()
        {

            public void actionPerformed( java.awt.event.ActionEvent evt )
            {

                jButton1ActionPerformed( evt );

            }

        } );

        jButton2.setText( "Quit" );

        jButton2.addActionListener( new java.awt.event.ActionListener()
        {

            public void actionPerformed( java.awt.event.ActionEvent evt )
            {

                jButton2ActionPerformed( evt );

            }

        } );

        GroupLayout layout = new GroupLayout( getContentPane() );

        getContentPane().setLayout( layout );

        layout.setHorizontalGroup(

        layout.createParallelGroup( GroupLayout.Alignment.LEADING )

            .addGroup( layout.createSequentialGroup()

                .addContainerGap()

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                    .addGroup( layout.createSequentialGroup()

                        .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                            .addComponent( jLabel1 )

                            .addGroup( layout.createSequentialGroup()

                                .addComponent( jLabel2 )

                                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,
                                    40,
                                    Short.MAX_VALUE )

                                .addComponent( jTextField1,
                                    GroupLayout.PREFERRED_SIZE,
                                    241,
                                    GroupLayout.PREFERRED_SIZE ) )

                            .addComponent( jSeparator2,
                                GroupLayout.DEFAULT_SIZE,
                                390,
                                Short.MAX_VALUE )

                            .addComponent( jSeparator1,
                                GroupLayout.Alignment.TRAILING,
                                GroupLayout.DEFAULT_SIZE,
                                390,
                                Short.MAX_VALUE ) )

                        .addContainerGap() )

                    .addGroup( layout.createSequentialGroup()

                        .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING,
                            false )

                            .addComponent( jScrollPane1 )

                            .addComponent( jLabel3,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE ) )

                        .addGap( 38, 38, 38 )

                        .addComponent( jSeparator3,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE )

                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                        .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                            .addGroup( layout.createSequentialGroup()

                            .addComponent( jLabel4 )

                            .addContainerGap() )

                            .addGroup( GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()

                                    .addGroup( layout.createParallelGroup( GroupLayout.Alignment.TRAILING )

                                        .addGroup( layout.createSequentialGroup()

                                            .addComponent( jButton1 )

                                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                                            .addComponent( jButton2 ) )

                                        .addComponent( jScrollPane2,
                                            GroupLayout.DEFAULT_SIZE,
                                            156,
                                            Short.MAX_VALUE ) )

                                    .addGap( 59, 59, 59 ) ) ) ) ) )

        );

        layout.setVerticalGroup(

        layout.createParallelGroup( GroupLayout.Alignment.LEADING )

            .addGroup( layout.createSequentialGroup()

                .addContainerGap()

                .addComponent( jLabel1 )

                .addGap( 18, 18, 18 )

                .addComponent( jSeparator1,
                    GroupLayout.PREFERRED_SIZE,
                    10,
                    GroupLayout.PREFERRED_SIZE )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )

                    .addComponent( jTextField1,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE )

                    .addComponent( jLabel2 ) )

                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )

                .addComponent( jSeparator2,
                    GroupLayout.PREFERRED_SIZE,
                    10,
                    GroupLayout.PREFERRED_SIZE )

                .addGap( 18, 18, 18 )

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.TRAILING )

                    .addGroup( GroupLayout.Alignment.LEADING,
                        layout.createSequentialGroup()

                            .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                                .addComponent( jLabel3 )

                                .addComponent( jLabel4,
                                    GroupLayout.PREFERRED_SIZE,
                                    16,
                                    GroupLayout.PREFERRED_SIZE ) )

                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                            .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                                .addComponent( jScrollPane1,
                                    GroupLayout.Alignment.TRAILING,
                                    GroupLayout.PREFERRED_SIZE,
                                    72,
                                    GroupLayout.PREFERRED_SIZE )

                                .addComponent( jScrollPane2,
                                    GroupLayout.Alignment.TRAILING,
                                    GroupLayout.PREFERRED_SIZE,
                                    72,
                                    GroupLayout.PREFERRED_SIZE ) ) )

                    .addComponent( jSeparator3,
                        GroupLayout.Alignment.LEADING,
                        GroupLayout.PREFERRED_SIZE,
                        96,
                        GroupLayout.PREFERRED_SIZE ) )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,
                    28,
                    Short.MAX_VALUE )

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )

                    .addComponent( jButton2 )

                    .addComponent( jButton1 ) )

                .addContainerGap() )

        );

        pack();

    }


    /**
     * Performs the action of jButton1, Start, which takes in the difficulty,
     * order, and name of the use, and instantiates a ScrabbleBoard object to
     * begin the game. If no order or difficulty is selected, the game defaults
     * to Easy and Firt. Similarly, if no name is typed, the name is defaulted
     * to Player.
     * 
     * @param evt
     */
    private void jButton1ActionPerformed( java.awt.event.ActionEvent evt )
    {

        String name = jTextField1.getText();
        int a = jList1.getSelectedIndex();
        int b = jList2.getSelectedIndex();
        this.setVisible( false );
        new ScrabbleBoard( a, b, name );

    }


    /**
     * Performs the action on the second button whch just quits the game and
     * program.
     * 
     * @param evt
     */
    private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
    {

        System.exit( 0 );

    }

    private JButton jButton1;

    private JButton jButton2;

    private JLabel jLabel1;

    private JLabel jLabel2;

    private JLabel jLabel3;

    private JLabel jLabel4;

    private JList jList1;

    private JList jList2;

    private JScrollPane jScrollPane1;

    private JScrollPane jScrollPane2;

    private JSeparator jSeparator1;

    private JSeparator jSeparator2;

    private JSeparator jSeparator3;

    private JTextField jTextField1;

}
