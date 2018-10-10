package hartech.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * <p>Title: Windows Examples Show</p>
 *
 * <p>Description: JTL's Java</p>
 *
 * <p>Copyright: Copyright (c) 2006 hartech.cn</p>
 *
 * <p>Website: www.hartech.cn</p>
 *
 * @author JTL.zheng@gmail.com
 * @version 1.0
 */

public class WindowsEX {
  private JFrame frame,jFrame;
  private JDialog dialog;
  private JWindow window;
  private JDesktopPane desktopPane;
  private JInternalFrame internalFrame;

  public void creatFrame() {
    // frame
    frame = new JFrame("Windows Examples Show --- www.hartech.cn");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // panel
    JPanel panel_up = new JPanel();
    panel_up.setBorder(BorderFactory.createTitledBorder("各种窗口对比："));
    JPanel panel_down = new JPanel();
    panel_down.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // label in frame north
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setText(
        "\nJFrame： 带标题栏和边框，可带菜单栏，一个GUI程序至少得拥有一个 JFrame 或 JWindow，不会挡住主JFrame");
    textArea.append("\n\nJWindow： 可单独使用，也可从属于 JFrame ，可当成一JFrame使用，仅是无标题栏，无窗口按钮，会挡住主JFrame");
    textArea.append(
        "\n\nJDialog： 可当成一 JFrame 使用，但必须从属于 JFrame （区别于关闭窗口时），可选屏蔽其它窗口，会挡住主JFrame");
    textArea.append(
        "\n\nJOptionPane： 提供各种常见的对话框（消息、确认、输入。。）模版 modal，基于 JDialog，" +
        "自动屏蔽其它窗口");
    textArea.append(
        "\n\nJDesktopPane： 仅是一个容器，必须置于一JFrame内，用于容纳子窗口 JInternalFrame");
    textArea.append("\n\nJInternalFrame： 可当成一 JFrame 使用，仅是必须 add 到 JDesktopPane内");
    textArea.append("\n\n－－－以上除 JDesktopPane 无使用布局外都是使用边缘布局（BorderLayout）\n");

    // five buttons
    JButton b_Frame,b_Dialog, b_Window, b_OptionPane, b_DesktopPane, b_InternalFrame;
    b_Frame = new JButton("creat a Frame");
    b_Frame.addActionListener(new buttonAction(0));
    b_Dialog = new JButton("creat a Dialog");
    b_Dialog.addActionListener(new buttonAction(1));
    b_Window = new JButton("creat a Window");
    b_Window.addActionListener(new buttonAction(2));
    b_OptionPane = new JButton("creat a OptionPane");
    b_OptionPane.addActionListener(new buttonAction(3));
    b_DesktopPane = new JButton("creat a DesktopPane");
    b_DesktopPane.addActionListener(new buttonAction(4));
    b_InternalFrame = new JButton("creat a InternalFrame");
    b_InternalFrame.addActionListener(new buttonAction(5));

    // panel add
    panel_up.add(textArea);
    panel_down.add(b_Frame);
    panel_down.add(b_Window);
    panel_down.add(b_Dialog);
    panel_down.add(b_OptionPane);
    panel_down.add(b_DesktopPane);
    panel_down.add(b_InternalFrame);

    // frame add
    frame.add(panel_up, BorderLayout.NORTH);
    frame.add(panel_down, BorderLayout.SOUTH);
    frame.pack();
    J.goCenter(frame);
    frame.setVisible(true);
  }

  public void createJFrame(){
    jFrame=new JFrame("This is a JFrame");

    // panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Panel title in a JDialog"));
    JLabel label = new JLabel(
        "<html>you can put <font color=red>any thing</font> here , just like a frame</html>");
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label);

    jFrame.add(panel, BorderLayout.CENTER);
    //jFrame.pack();
    jFrame.setSize(400, 200);
    J.goCenter(jFrame);
    jFrame.setVisible(true);
  }


  public void createDialog() {
    dialog = new JDialog(frame, "dialog title", false);

    // use just like a JFrame
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Panel title in a JDialog"));
    JLabel label = new JLabel(
        "<html>you can put <font color=red>any thing</font> here , just like a frame</html>");
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label);

    dialog.add(panel, BorderLayout.CENTER);
    //dialog.pack();
    dialog.setSize(400, 200);
    J.goCenter(dialog);
    dialog.setVisible(true);
  }

  public void createWindow() {
    // can choose to belong to a frame
    window = new JWindow(frame);

    // or stand alone
    //window = new JWindow();

    // use just like a JFrame
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Panel title in a JWindow"));
    JLabel label = new JLabel(
        "<html>you can put <font color=red>any thing</font> here , just like a frame</html>");
    label.setHorizontalAlignment(JLabel.CENTER);
    JButton button = new JButton("Close this window");
    button.addActionListener(new buttonAction(6));
    panel.add(label, BorderLayout.CENTER);
    panel.add(button, BorderLayout.SOUTH);

    window.add(panel, BorderLayout.CENTER);
    window.setSize(600, 300);
    J.goCenter(window);
    window.setVisible(true);
  }

  public void createOptionPane() {
    // showMessageDialog,showInputDialog,showConfirmDialog,showOptionDialog
    // If your dialog should be an internal frame, then add Internal after show
    //   eg. showMessageDialog -> showInternalMessageDialog
    // If you need to instantiate JOptionPane then should add it to a JDialog instance.
    //   Then invoke setVisible(true) on the JDialog to make it appear.

    // Message Dialog: only one unchangeable button "OK"
    // not return
    JOptionPane.showMessageDialog(frame, "message \nbody", "message title",
                                  JOptionPane.INFORMATION_MESSAGE);
    // Option Dialog:
    // can change number of buttons(up to 3),can change text on buttons
    // return -1 if close the dialog
    Object[] options = {
        "Yes, please", "No, thanks", "No eggs, no ham!"};
    int n1 = JOptionPane.showOptionDialog(frame,
                                          "Would you like some green eggs \nto go with that ham?",
                                          "Option Dialog title",
                                          JOptionPane.YES_NO_CANCEL_OPTION,
                                          JOptionPane.QUESTION_MESSAGE,
                                          null, options, options[2]);
    // Confirm Dialog:
    // can change the number of buttons(up to 3),can't change the text on button
    // return -1 if close the dialog
    int n2 = JOptionPane.showConfirmDialog(frame,
                                           "Would you like green eggs and ham?",
                                           "Confirm Dialog title",
                                           JOptionPane.YES_NO_OPTION,
                                           JOptionPane.INFORMATION_MESSAGE);
    // Input Dialog 1: given chosen
    // return a string which you chosen
    // return null if you close the dialog or chick "Canel"
    // or you can give a array of sometype objects,it will return the specific object
    Object[] options2 = {
        "Apple", "Pear", "Peach"};
    String s1 = (String) JOptionPane.showInputDialog(frame, "Choose a fruit:",
        "Input Dialog 1 title",
        JOptionPane.QUESTION_MESSAGE,
        null, options2, "Pear");

    // Input Dialog 2: input string
    // return a string input
    // return "" if not chars and chick "OK"
    // return null if you close the dialog or chick "Canel"
    String s2 = (String) JOptionPane.showInputDialog(frame, "Input a fruit:",
        "default string");

    // an example to check input
    while (s2 != null && !hartech.JMath.isNumeric(s2)) {
      s2 = (String) JOptionPane.showInputDialog(frame, "You input a number:",
                                                s2);
    }
  }

  public void createDesktopPane() {
    openFrameCount = 0;
    // only a container, must put inside a JFrame
    // define a JFrame
    JFrame frame_desktop = new JFrame("This is a JDesktopPane's JFrame");
    //Make the JFrame be indented 50 pixels from each edge of the screen.
    int inset = 50;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame_desktop.setBounds(inset, inset, screenSize.width - inset * 2,
                            screenSize.height - inset * 2);

    // define a JDesktopPane
    desktopPane = new JDesktopPane();
    // add it to JFrame
    frame_desktop.setContentPane(desktopPane);

    //Make dragging InternalFrame faster
    desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

    //HFrame.goCenter(frame_desktop);
    frame_desktop.setVisible(true);
    frame.setVisible(true);
  }

  // some variables for createInternalFrame()
  static int openFrameCount = 0;
  static final int xOffset = 30, yOffset = 30;

  public void createInternalFrame() {
    if (desktopPane == null) {
      JOptionPane.showMessageDialog(frame,
                                    "You must create a DesktopPane first.",
                                    "createInternalFrame()",
                                    JOptionPane.WARNING_MESSAGE);
      return;
    }
    internalFrame = new JInternalFrame("Internal Frame No." + (++openFrameCount),
                                       true, //resizable
                                       true, //closable
                                       true, //maximizable
                                       true); //iconifiable

    // add your panel or componets here
    // use it just like a frame

    internalFrame.setSize(300, 300);
    //internalFrame.pack();
    // set the internalFrame's location.
    internalFrame.setLocation(xOffset * openFrameCount,
                              yOffset * openFrameCount);
    internalFrame.setVisible(true);
    //internalFrame.setSelected(true);

    // must added to a JDesktopPane
    desktopPane.add(internalFrame);
  }

  class buttonAction
      implements ActionListener {
    int type;
    public buttonAction(int i) {
      type = i;
    }

    public void actionPerformed(ActionEvent e) {
      switch (type) {
        case 0:
          createJFrame();
          break;
        case 1:
          createDialog();
          break;
        case 2:
          createWindow();
          break;
        case 3:
          createOptionPane();
          break;
        case 4:
          createDesktopPane();
          break;
        case 5:
          createInternalFrame();
          break;
        case 6:
          window.dispose();
          break;
      }
    }
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        J.setLookAndFeel("Metal");
        new WindowsEX().creatFrame();
      }
    });
  }
}
