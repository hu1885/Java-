import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Hashtable;
public class StudentManager extends JFrame implements ActionListener
{
   StudentSituation ������Ϣ¼��=null;          
   ModifySituation  ������Ϣ�޸�=null;          
   Inquest          ������Ϣ��ѯ=null; 
   Delete           ������Ϣɾ��=null; 
   JMenuBar bar;
   JMenu fileMenu;
   JMenuItem ¼��,�޸�,��ѯ,ɾ��,�˳�;      
   Container con=null;
   Hashtable ������Ϣ=null;                    
   File file=null;                             
   CardLayout card=null;                       
   JLabel label;
   JPanel pCenter;
   public StudentManager()
   {
     setTitle("��ӭ����༶����ϵͳ");
     ¼��=new JMenuItem("¼��ѧ��������Ϣ");
     �޸�=new JMenuItem("�޸�ѧ��������Ϣ");
     ��ѯ=new JMenuItem("��ѯѧ��������Ϣ");
     ɾ��=new JMenuItem("ɾ��ѧ��������Ϣ");
     �˳�=new JMenuItem("�˳���ϵͳ");
     �˳�.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
     bar=new JMenuBar();
     fileMenu=new JMenu("�˵�ѡ��");
     fileMenu.add(¼��);
     fileMenu.addSeparator();
     fileMenu.add(�޸�);
     fileMenu.addSeparator();
     fileMenu.add(��ѯ);
     fileMenu.addSeparator();
     fileMenu.add(ɾ��);
     fileMenu.addSeparator();
     fileMenu.addSeparator();
     fileMenu.add(�˳�);
     bar.add(fileMenu);
     setJMenuBar(bar);
     label=new JLabel("��ӭ����༶����ϵͳ!",label.CENTER);
     label.setFont(new Font("����",Font.BOLD,30));
     label.setForeground(Color.blue);
     ������Ϣ=new Hashtable();
     ¼��.addActionListener(this);
     �޸�.addActionListener(this);
     ��ѯ.addActionListener(this);
     ɾ��.addActionListener(this);
     
     card=new CardLayout();
     con=getContentPane();
     pCenter=new JPanel();
     pCenter.setLayout(card); 
     file=new File("ѧ��������Ϣ.txt");
     if(!file.exists())
      {
       try{
           FileOutputStream out=new FileOutputStream(file);
           ObjectOutputStream objectOut=new ObjectOutputStream(out);
           objectOut.writeObject(������Ϣ);
           objectOut.close();
           out.close();
          }
       catch(IOException e)
          {
          }
      } 
     ������Ϣ¼��=new StudentSituation(file);
     ������Ϣ�޸�=new ModifySituation(file);
     ������Ϣ��ѯ=new Inquest(this,file);
     ������Ϣɾ��=new Delete(file);
     pCenter.add("��ӭ�����",label);
     pCenter.add("¼�����",������Ϣ¼��);
     pCenter.add("�޸Ľ���",������Ϣ�޸�);    
     pCenter.add("ɾ������",������Ϣɾ��);
     con.add(pCenter,BorderLayout.CENTER);
     con.validate();
     addWindowListener(new WindowAdapter()
                    { public void windowClosing(WindowEvent e)
                       {
                          System.exit(0);
      	               }
                    });
    setVisible(true);
    setBounds(100,50,420,380);
    validate();
   }
  public void actionPerformed(ActionEvent e)
   {
     if(e.getSource()==¼��)
       {
         card.show(pCenter,"¼�����");
       }
     else if(e.getSource()==�޸�)
       {
         card.show(pCenter,"�޸Ľ���");
       }
     else if(e.getSource()==��ѯ)
      {
         ������Ϣ��ѯ.setVisible(true);
      }
     else if(e.getSource()==ɾ��)
      {
         card.show(pCenter,"ɾ������");
      }
   }
  public static void main(String args[])
   {
     new StudentManager();
   }

}
