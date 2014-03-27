package Qwerty;

import javax.swing.*;


/**
 * The UI class is a User Interface enhancer in the Qwerty game which provides
 * the user with an opportunity to check if the words they are thinking of are
 * valid in the dictionary used by the game. It is based off the JFrame class,
 * and is a essential part of the User Interface and User Friendliness of this
 * game.
 * 
 * @author Abhishek, Anish, Nikhil
 * @source NetBeans IDE
 * 
 */
public class UI extends JFrame
{

    private static final long serialVersionUID = 1L;


    /**
     * Constructs the UI object by implicitly calling the Super constructor and
     * then calling the method initComponents
     */
    public UI()
    {

        initComponents();

    }


    /**
     * This method is called from withing the constructor to construct all the
     * objects and layout of the UI (Dictionary Look-Up) Object.
     */

    private void initComponents()
    {

        jLabel1 = new JLabel();

        jSeparator1 = new JSeparator();

        jTextField1 = new JTextField();

        jLabel2 = new JLabel();

        jLabel3 = new JLabel();

        jSeparator2 = new JSeparator();

        jButton1 = new JButton();

        jButton2 = new JButton();

        jSeparator3 = new JSeparator();

        jLabel4 = new JLabel();

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        setTitle( "Dictionary Look-Up" );

        jLabel1.setText( "Enter Word To Check It's Validity" );

        jButton2.addActionListener( new java.awt.event.ActionListener()
        {

            public void actionPerformed( java.awt.event.ActionEvent evt )
            {

                jButton2ActionPerformed( evt );

            }

        } );

        jLabel2.setText( "Two Letter Words: At, On, An, No, Do, So, La, To," );

        jLabel3.setText( "Me, Pa, Up, Us, We, Be, Or, Of, My, It, By, If, In, He" );

        jButton1.setText( "Enter" );

        jButton1.addActionListener( new java.awt.event.ActionListener()
        {

            public void actionPerformed( java.awt.event.ActionEvent evt )
            {

                jButton1ActionPerformed( evt );

            }

        } );

        jButton2.setText( "Clear" );

        GroupLayout layout = new GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );

        layout.setHorizontalGroup(

        layout.createParallelGroup( GroupLayout.Alignment.LEADING )

            .addGroup( layout.createSequentialGroup()

                .addContainerGap()

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )

                    .addGroup( layout.createSequentialGroup()

                    .addComponent( jLabel1 )

                    .addContainerGap( 183, Short.MAX_VALUE ) )

                    .addGroup( GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()

                            .addComponent( jLabel4,
                                GroupLayout.PREFERRED_SIZE,
                                179,
                                GroupLayout.PREFERRED_SIZE )

                            .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE )

                            .addComponent( jButton1 )

                            .addGap( 31, 31, 31 )

                            .addComponent( jButton2 )

                            .addContainerGap() )

                    .addGroup( layout.createSequentialGroup()

                        .addComponent( jLabel3,
                            GroupLayout.DEFAULT_SIZE,
                            318,
                            Short.MAX_VALUE )

                        .addGap( 70, 70, 70 ) )

                    .addGroup( GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()

                            .addGroup( layout.createParallelGroup( GroupLayout.Alignment.TRAILING )

                                .addComponent( jSeparator3,
                                    GroupLayout.Alignment.LEADING,
                                    GroupLayout.DEFAULT_SIZE,
                                    368,
                                    Short.MAX_VALUE )

                                .addComponent( jSeparator2,
                                    GroupLayout.Alignment.LEADING,
                                    GroupLayout.DEFAULT_SIZE,
                                    368,
                                    Short.MAX_VALUE )

                                .addComponent( jSeparator1,
                                    GroupLayout.Alignment.LEADING,
                                    GroupLayout.DEFAULT_SIZE,
                                    368,
                                    Short.MAX_VALUE )

                                .addComponent( jTextField1,
                                    GroupLayout.Alignment.LEADING,
                                    GroupLayout.DEFAULT_SIZE,
                                    368,
                                    Short.MAX_VALUE )

                                .addComponent( jLabel2,
                                    GroupLayout.DEFAULT_SIZE,
                                    368,
                                    Short.MAX_VALUE ) )

                            .addContainerGap() ) ) )

        );

        layout.setVerticalGroup(

        layout.createParallelGroup( GroupLayout.Alignment.LEADING )

            .addGroup( layout.createSequentialGroup()

                .addContainerGap( 22, Short.MAX_VALUE )

                .addComponent( jLabel1 )

                .addGap( 4, 4, 4 )

                .addComponent( jSeparator1,
                    GroupLayout.PREFERRED_SIZE,
                    10,
                    GroupLayout.PREFERRED_SIZE )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addComponent( jTextField1,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )

                    .addComponent( jButton2 )

                    .addComponent( jButton1 )

                    .addComponent( jLabel4 ) )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addComponent( jSeparator2,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addComponent( jLabel2,
                    GroupLayout.PREFERRED_SIZE,
                    26,
                    GroupLayout.PREFERRED_SIZE )

                .addGap( 10, 10, 10 )

                .addComponent( jLabel3 )

                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )

                .addComponent( jSeparator3,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE )

                .addGap( 34, 34, 34 ) )

        );

        pack();

    }// </editor-fold>


    /**
     * Records the action of JButton2, and performs the action when it is
     * clicked, for the Clear button. Essentially the clear button clears the
     * validity statement and the user input line.
     * 
     * @param evt
     */
    private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
    {
        jTextField1.setText( "" );
        jLabel4.setText( "" );
    }


    /**
     * Performs the action on JButton1, which is the Check Validity button,
     * which compares the user input with the dictionary and prints out whether
     * the word is valid or invalid.
     * 
     * @param evt
     */
    private void jButton1ActionPerformed( java.awt.event.ActionEvent evt )
    {

        // Enter Button

        String s = jTextField1.getText();

        Dictionary dict = new Dictionary();

        if ( dict.isWord( s.toLowerCase().trim() ) )
        {
            jLabel4.setText( "Valid Word" );
        }
        else
        {
            jLabel4.setText( "Invalid Word" );
        }

    }

    private JButton jButton1;

    private JButton jButton2;

    private JLabel jLabel1;

    private JLabel jLabel2;

    private JLabel jLabel3;

    private JLabel jLabel4;

    private JSeparator jSeparator1;

    private JSeparator jSeparator2;

    private JSeparator jSeparator3;

    private JTextField jTextField1;

}
