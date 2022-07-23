package kukukube;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class KukuKube implements MouseListener, ActionListener {

    //declaraciones de todas las componentes que se van a utilizar
    private JLabel jl1;
    private static JLabel jl5,jl4,jl3,jl2;
    private JPanel panel1, panel2, panel3, panel4, panelEstadisiticas, panelArriba, cartasPanel, panelGrande, panelAbajo;

    private JButton nueva, salir2;

    private JMenuBar menu;
    private JMenu general;
    private JMenuItem nuevo, salir;

    private static JFrame frame;
    private static JPanel panel;

    private JTextField niveles, dificultad;

    private static KukuKube app;
    private static GameManager manager;
    private static JButton[][] cubos;

    private CardLayout cl;

    private static boolean enCurso;
    private int puntuacion = 0;
    private static int numNiveles;

    //Método main que va ya ejecutar nuestro porgrama
    public static void main(String[] args) {
        (new KukuKube()).initContents();

    }

    /**
     * Crea la la ventana, con todos sus componentes.
     */
    private void initContents() {

        //al iniciar ponemos el boolean de enCurso en false para decir al programa que aun no ha empezado el juego
        enCurso = false;
        //llamo al GameManager para poder manejar todos los 
        manager = new GameManager();
        //creo el JFrame principal
        frame = new JFrame("Programacion II - 2021-2022");

        //pongo el tamaño del grid con un getter
        int gridDimension = manager.getGridDimension();

        //croe el JPanel "panel" con un gridlayout que me va ha hacer la estructura matrizial del juego
        panel = new JPanel(new GridLayout(gridDimension, gridDimension));
        //creo el JPanel "panelGrande" donde voy a meter todas las componentes 
        panelGrande = new JPanel(new BorderLayout());

        //añado todas las componentes necesarias al JPanel botones, paneles, menus...
        añadirComponentes();

        //inicializo la aplicación
        app = new KukuKube();

        frame.setContentPane(panelGrande);
        //Añado todos los panales necesarios
        panelGrande.add(panelArriba, BorderLayout.NORTH);
        panelGrande.add(cartasPanel, BorderLayout.CENTER);
        panelGrande.add(panelAbajo, BorderLayout.SOUTH);
        //pongo el tamaño de la ventana que va ha salir
        frame.setSize(830, 920);
        //no dejo que la ventana pueda ser redimensionada
        frame.setResizable(false);
        //al ejecutar la aplicación digo que salga en medio de la pantaña
        frame.setLocationRelativeTo(null);
        //pongo el frame visible
        frame.setVisible(true);
        //pongo que el frame al cerrarse que para la aplicación
        frame.setDefaultCloseOperation(3);

    }

    /**
     * Crea todas las componentes y las añade a sus paneles pertinentes.
     */
    private void añadirComponentes() {

        //creo el menu
        menu = new JMenuBar();
        nuevo = new JMenuItem("NUEVA PARTIDA");
        //añado un actionlistener al boton
        nuevo.addActionListener((ActionListener) this);
        //pongo un nombre al comnado que va ha salir si se pulsa el boton
        nuevo.setActionCommand("nuevaP");

        salir = new JMenuItem("SALIR");
        general = new JMenu("MENU");
        //añado un actionlistener al boton
        salir.addActionListener((ActionListener) this);
        //pongo un nombre al comnado que va ha salir si se pulsa el boton
        salir.setActionCommand("salir");

        general.add(nuevo);
        general.add(salir);
        menu.add(general);

        //declaro el panel de arriba
        panelArriba = new JPanel();
        panelArriba.setLayout(new BorderLayout());

        //declaro el panel de estadisiticas
        panelEstadisiticas = new JPanel();
        panelEstadisiticas.setLayout(new GridLayout(2, 2, 200, 10));
        panelEstadisiticas.setBackground(Color.black);

        //declaro los paneles
        panel1 = new JPanel();
        panel1.setBackground(Color.orange);

        panel2 = new JPanel();
        panel2.setBackground(Color.orange);

        panel3 = new JPanel();
        panel3.setBackground(Color.orange);

        panel4 = new JPanel();
        panel4.setBackground(Color.orange);

        //añado los paneles al paneld e estadisiticas
        panelEstadisiticas.add(panel1);
        panelEstadisiticas.add(panel2);
        panelEstadisiticas.add(panel3);
        panelEstadisiticas.add(panel4);

        //declaro los labels que me van a representar la información que quiero ver
        jl1 = new JLabel("NIVELES PARTIDA:  ");
        jl1.setFont(new Font("arial", 0, 20));

        jl2 = new JLabel("000");
        jl2.setFont(new Font("arial", 0, 20));
        //pongo que los numeros esten en rojo
        jl2.setForeground(Color.red);

        //añado los dos labels al panel 
        panel1.add(jl1);
        panel1.add(jl2);

        jl1 = new JLabel("NIVELES RESTANTES:  ");
        jl1.setFont(new Font("arial", 0, 20));

        jl3 = new JLabel("000");
        jl3.setFont(new Font("arial", 0, 20));
        //pongo que los numeros esten en rojo
        jl3.setForeground(Color.red);
        //añado los dos labels al panel 
        panel2.add(jl1);
        panel2.add(jl3);

        jl1 = new JLabel("NIVEL ACTUAL:       ");
        jl1.setFont(new Font("arial", 0, 20));

        jl4 = new JLabel("000");
        jl4.setFont(new Font("arial", 0, 20));
        //pongo que los numeros esten en rojo
        jl4.setForeground(Color.red);
        //añado los dos labels al panel 
        panel3.add(jl1);
        panel3.add(jl4);

        jl1 = new JLabel("PUNTUACION:               ");
        jl1.setFont(new Font("arial", 0, 20));

        jl5 = new JLabel("000");
        jl5.setFont(new Font("arial", 0, 20));
        //pongo que los numeros esten en rojo
        jl5.setForeground(Color.red);
        //añado los dos labels al panel 
        panel4.add(jl1);
        panel4.add(jl5);

        //añado el menu y el panel de estadisticas al panel que los contendrá. 
        panelArriba.add(menu, BorderLayout.NORTH);
        panelArriba.add(panelEstadisiticas, BorderLayout.SOUTH);

        //declaro el JPanel de abajo que va ha tener los botones
        panelAbajo = new JPanel();
        panelAbajo.setLayout(new GridLayout(1, 2));
        panelAbajo.setBackground(Color.pink);

        //boton nueva dentro del panel de abajo
        nueva = new JButton("NUEVA PARTIDA");
        nueva.setBackground(Color.black);
        nueva.setForeground(Color.WHITE);
        nueva.setFont(new Font("arial", 0, 20));
        //añado un actionlistener al boton
        nueva.addActionListener((ActionListener) this);
        //pongo un nombre al comnado que va ha salir si se pulsa el boton
        nueva.setActionCommand("nuevaP");

        //boton de salir
        salir2 = new JButton("SALIR");
        salir2.setBackground(Color.black);
        salir2.setForeground(Color.WHITE);
        salir2.setFont(new Font("arial", 0, 20));
        //pongo un nombre al comnado que va ha salir si se pulsa el boton
        salir2.setActionCommand("salir");
        //añado un actionlistener al boton
        salir2.addActionListener((ActionListener) this);
        //añado los dos botones al panel 
        panelAbajo.add(nueva);
        panelAbajo.add(salir2);

        //Hago un cardLayout para representar el gif
        JPanel card1 = new JPanel();
        JLabel label = new JLabel();

        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kukukube/uib.gif")));
        card1.add(label);

        cl = new CardLayout();
        cartasPanel = new JPanel(cl);
        cartasPanel.add(card1);
        cartasPanel.add(panel);

    }

    /**
     * actionPerformed que mira que boton se ha pulsado.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {

        if ("salir".equals(evt.getActionCommand())) {
            salirActionPerformed(evt);

        } else if ("nuevaP".equals(evt.getActionCommand())) {
            nuevaPartidaActionPerformed(evt);

        }
    }

    /**
     * ActionEvent que se ejecuta si se ha pulado el boton de "SALIR".
     */
    private void salirActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * ActionEvent que se ejecuta si se ha pulado el boton de "NUEVA PARTIDA".
     */
    private void nuevaPartidaActionPerformed(ActionEvent evt) {

        //Si enCurso==true, significa que el juego ya ha empezado y no se puede iniciar una nueva
        if (enCurso == false) {
            //llamo al cuadroOpciones para sacar los niveles y la dificultad.
            cuadroOpciones();
            //paso a la siguiente carta
            cl.next(cartasPanel);
            //contruyo el juego
            cubos = construcionUI();
            //pongo enCurso a true para poner que el juego ya ha empezado
            enCurso = true;

        } else {//si enCurso==true se enseñará un mensaje de que no se puede iniciar una nueva partida
            JOptionPane.showMessageDialog(frame, "NO SE PUEDE EMPEZAR UNA PARTIDA SI YA HAY UNA EN CURSO");
        }
    }

    /**
     * Pregunta los niveles y la dificultad mediante un JOptionPane.
     */
    private void cuadroOpciones() {
        //declaro los TextFields
        niveles = new JTextField(5);
        dificultad = new JTextField(5);

        //declaro un JPanel "myPanel" donde meteremos todos los componentes. 
        JPanel myPanel = new JPanel(new GridLayout(2, 1));
        //metemos el label de los niveles
        myPanel.add(new JLabel("Nº Niveles:"));
        myPanel.add(niveles);

        //y el label de la dificultad
        myPanel.add(new JLabel("Dificultad:"));
        myPanel.add(dificultad);

        //con el JOptionPane haremos una pestaña donde el usuario nos va ha meter los datos
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Introduzca los datos", JOptionPane.OK_CANCEL_OPTION);
        //si el boton ok ha sido pulsado
        if (result == JOptionPane.OK_OPTION) {

            //validamos las entradas de los textfields
            if ((Integer.parseInt(niveles.getText())) > 10
                    || ((Integer.parseInt(niveles.getText())) < 1)
                    || ((Integer.parseInt(dificultad.getText())) > 10)
                    || ((Integer.parseInt(dificultad.getText())) < 1)) {

                //sacamos un mensaje de error
                JOptionPane.showMessageDialog(frame, "ALGUN DATO HA SIDO INTRODUCIDO ERRONEAMENTE");
                //llamamos otra vez al metodo para sacar los valores
                cuadroOpciones();
            }
            //hemos validado la entrada y ahora setteamos todos los campos a parametros en el GameManager.
            GameManager.MAX_GRID_SIZE = Integer.parseInt(niveles.getText());
            numNiveles=Integer.parseInt(niveles.getText());
            manager.setDificultad(Integer.parseInt(dificultad.getText()));
        
        //niveles partida
        jl2.setText(String.valueOf(GameManager.MAX_GRID_SIZE));
        //niveles restantes
        jl3.setText(String.valueOf(numNiveles));
        //nivel actual
        jl4.setText(String.valueOf(manager.getNiveles()));
        //puntuacion
        jl5.setText(String.valueOf(puntuacion));
        }
    }

    /**
     * Quita todos los cubos del panel.
     */
    //metodo que borra toda la pantalla
    private static void clearBoard(JButton[][] cubes) {
        //getter del grid actual
        int currentGridDimension = manager.getGridDimension();
        //medante dos fors voy quitando cubos
        for (int i = 0; i < currentGridDimension; i++) {
            for (int j = 0; j < currentGridDimension; j++) {
                panel.remove(cubes[i][j]);
                cubes[i][j] = null;
            }
        }
    }

    /**
     * Añade los cubos a panel y mete los MouseListeners a cada cubo.
     */
    private static void añadirCubos(JButton[][] cubes) {
        //getter del grid actual
        int currentGridDimension = manager.getGridDimension();
        //mediante dos fors voy metiendo botones con sus MouseListeners asociados a ellos
        for (int i = 0; i < currentGridDimension; i++) {
            for (int j = 0; j < currentGridDimension; j++) {
                cubes[i][j] = new JButton();//i + "," + j);
                cubes[i][j].addMouseListener(app);
                cubes[i][j].setBackground(manager.getNormalColor());
                panel.add(cubes[i][j]);
            }
        }
    }

    /**
     * Getter del cubo correcto.
     */
    private static JButton getTargetCube(JButton[][] cubes) {
        int[] targetCubeCoordinates = manager.getTargetCubeCoordinates();
        return cubes[targetCubeCoordinates[0]][targetCubeCoordinates[1]];
    }

    /**
     * Inicializa el UI con los botones, los MouseListeners y los colores
     * correctos.
     */
    private static JButton[][] construcionUI() {

        int currentGridDimension = manager.getGridDimension();

        JButton[][] cubes = new JButton[currentGridDimension][currentGridDimension];

        // Create a new GridLayout to place cubes in.
        panel.setLayout(new GridLayout(currentGridDimension, currentGridDimension));

        manager.asignarColorNuevo();

        añadirCubos(cubes);

        // Elige un cubo correcto.
        manager.shuffleTargetCubeCoordinates();

        // Cambia el color del cubo correcto para que difiera un poco de los otros.
        getTargetCube(cubes).setBackground(manager.getVariantColor());

        // Forzamos la UI a refrescar por si se han puesto mal los cubos.
        SwingUtilities.updateComponentTreeUI(frame);
        return cubes;
    }

    /**
     * Actuliza el panel/tabla con colores nuevos, si el GameManager indica que
     * el juego ya se ha acabado se resetea todo si no actualiza la tabla con
     * colores nuevos.
     */
    private void updateBoard() {
        numNiveles=numNiveles-1;
        jl2.setText(String.valueOf(GameManager.MAX_GRID_SIZE));
        jl3.setText(String.valueOf(numNiveles));
        jl4.setText(String.valueOf(nivelLabel));
        jl5.setText(String.valueOf(puntuacion));

        //limpiamos el panel
        clearBoard(cubos);

        //miramos si el juego se ha acabado
        if (manager.isGameOver()) {

            
            //reseteamos todo
            manager.setGridDimension(2);
            enCurso = false;
            manager.setNiveles(1);
            GameManager.MAX_GRID_SIZE=0;

            //creo un JPanel
            JPanel myPanel = new JPanel(new GridLayout(2, 1));
            //le añado un label
            myPanel.add(new JLabel("LA PARTIDA HA TERMINADO CON UN TOTAL DE " + puntuacion + " PUNTOS."));

            //Represento una ventanita con los puntos
            int opcion = JOptionPane.showConfirmDialog(null, myPanel, "Fin partida", JOptionPane.YES_NO_OPTION);

            //si se ha pulsado un boton se resetea el Frame
            if (opcion == JOptionPane.OK_OPTION || opcion == JOptionPane.NO_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
                frame.setVisible(false);
                frame.dispose();
                initContents();
            }

            //reseteamos la puntuacion a 0.
            puntuacion = 0;
            return;
        }

        //Si no se ha acabado el juego actualizamos el tamaño de la tabla/panel.
        manager.updateBoardSize();
        
        //construimos otra vez el panel
        cubos = construcionUI();
        
    }
    
   
 int nivelLabel;
    //metodo de MouseEvent que se ejecuta cuando se ha clickado el raton
    @Override
    public void mouseClicked(MouseEvent e) {
        //cojo el cubo correcto
        JButton targetCube = getTargetCube(cubos);
        if (e.getSource() == targetCube) {//Si el cubo clickado es igual al cubo correcto
             nivelLabel=manager.getNiveles();
              
            puntuacion += (nivelLabel + 1);
            updateBoard();

        } else {//Si el cubo no es correcto
            //asigno el evento a un boton
            JButton b = (JButton) e.getSource();
            //cojo el boton y pinto sus bordes de rojo
            b.setBorder(new CompoundBorder(new EtchedBorder(), new LineBorder(Color.red)));
            //cojo el boton correcto y pinto sus bordes a verde
            targetCube.setBorder(new CompoundBorder(new EtchedBorder(), new LineBorder(Color.green)));
            //meto un mensaje de que el cuadrado clickado ha sido incorrecto
            JOptionPane.showMessageDialog(frame, "Cuadrado incorrecto");
            //Actualizo el panel
            updateBoard();

        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
