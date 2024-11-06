import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// classe publica frutamanager gui
public class FrutaManagerGUI {
	
    // no caso aqui e um arraylist para amarmazenar as frutas 
    private ArrayList<String> frutas;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    // vai construir a classe frutaManagergui 
    public FrutaManagerGUI() {
    	
        // inicia o arraylist 
        frutas = new ArrayList<>();
        listModel = new DefaultListModel<>();
        // vai estar criando uma parte titulo de gerenciador de frutas
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());
        // vai crianr uma parte com botoes para se interagir com as frutas
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        // uma parte para se escrever os nomes das frutas
        JTextField frutaField = new JTextField(15);
        panel.add(frutaField);
        
        // um botao para colocar novas frutas
        JButton addButton = new JButton("Adicionar"); 
        panel.add(addButton);
        // um botao para modificar uma fruta que ja tem
        JButton modifyButton = new JButton("Modificar");
        
        modifyButton.setEnabled(false);
        panel.add(modifyButton);
        
        // um botao para remover a fruta
        JButton removeButton = new JButton("Remover");
        removeButton.setEnabled(false);
        panel.add(removeButton);
        // vai colocar um painel na parte superior
        frame.add(panel, BorderLayout.NORTH);
        // vai estar mostrando as frutas ja colocadas e dando para selecionar  uma fruta por vez 
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);
        // uma botao para entrar na lista de frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH);
        // colocar uma fruta ao clicar em adicionar
        addButton.addActionListener(new ActionListener() {
            
        	@Override
            public void actionPerformed(ActionEvent e) {
                String novaFruta = frutaField.getText();
                
                // vai estar vendo se nao esta vazio 
                if (!novaFruta.isEmpty()) { 
                	// vai estar colocando a fruta no arraylist
                    frutas.add(novaFruta); 
                 	// vai estar colocando a fruta na lista visual
                    listModel.addElement(novaFruta); 
                 	// vai estar limapando a parte de texto
                    frutaField.setText(""); 
                 	// vai mostrar a mensagem de confirmaçao
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); 
                }
            }
        });
        // vai estar moficicando a frutando quando clicar no botao modificar
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                // vai ver se algum item foi selecionado
                if (selectedIndex != -1) { 
                    String frutaSelecionada = listModel.getElementAt(selectedIndex);
                    // vai abrir uma parte onde o usuario vai clicar e escrever o nome da fruta
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
                    // vai estar vendo se foi digitado algo
                    if (novaFruta != null && !novaFruta.isEmpty()) {
                        frutas.set(selectedIndex, novaFruta); 
                        listModel.set(selectedIndex, novaFruta); 
                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta + ".");
                    }
                } else {
                	// vai mostrar uma mensagem se nenhuma fruta foi clicada
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); 
                }
            }
        });
        // vai estar removendo a fruta quando selecionada e clicando no remover
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); 
                if (selectedIndex != -1) {
                	 // vai estar Rremovendo a fruta do arraylist
                    String frutaRemovida = frutas.remove(selectedIndex);
                    // vai estar removendo a fruta da lista visual
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
                } else {
                	// vai mostrar uma mensagem caso nenhuma fruta for escolhida 
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); 
                }
            }
        });
        // vai estar mostrando as frutas colocadas quando clicar na parte de listar frutas
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) {
                	// vai mandar essa mensagem quando a lista nao tiver frutas
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); 
                } else {
                	// vai mostrar todas as frutas com arraylist
                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas); 
                }
            }
        });
        // vai estar removendo ou modificando depente da escolha do usuario
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty();
            removeButton.setEnabled(selectionExists);
            modifyButton.setEnabled(selectionExists);
        });
        frame.setVisible(true);
    }
    // esse e o metodo para iniciar a aplicaçao
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrutaManagerGUI());
    }
}