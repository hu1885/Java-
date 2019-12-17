import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class ModifySituation extends JPanel implements ActionListener
{ 
  Hashtable ������Ϣ��=null;                           
  JTextField ѧ��,����,��ѧרҵ,��ͥסַ,��������;                 
  JRadioButton ��,Ů;
  ButtonGroup group=null;
  JButton ��ʼ�޸�,¼���޸�,����;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  FileOutputStream outOne=null;
  ObjectOutputStream outTwo=null;
  File file=null;                                           
  public ModifySituation(File file)
  {
   this.file=file;
   ѧ��=new JTextField(10);
   ����=new JTextField(10);                                
   ��ѧרҵ=new JTextField(10);
   ��ͥסַ=new JTextField(10);
   ��������=new JTextField(10);
   group=new ButtonGroup();
   ��=new JRadioButton("��",true);
   Ů=new JRadioButton("Ů",false);
   group.add(��);
   group.add(Ů);
   ��ʼ�޸�=new JButton("��ʼ�޸�");
   ¼���޸�=new JButton("¼���޸�");
   ¼���޸�.setEnabled(false);
   ����=new JButton("����");
   ѧ��.addActionListener(this);
   ��ʼ�޸�.addActionListener(this);
   ¼���޸�.addActionListener(this);
   ����.addActionListener(this);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("����Ҫ�޸���Ϣ��ѧ��:",JLabel.CENTER));
   box1.add(ѧ��);
   box1.add(��ʼ�޸�);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("(��)����:",JLabel.CENTER));
   box2.add(����);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("(��)�Ա�:",JLabel.CENTER));
   box3.add(��);
   box3.add(Ů);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("(��)��ѧרҵ:",JLabel.CENTER));
   box4.add(��ѧרҵ);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("(��)��ͥסַ:",JLabel.CENTER));
   box5.add(��ͥסַ);
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("(��)��������:",JLabel.CENTER));
   box6.add(��������);
   Box boxH=Box.createVerticalBox();              
   boxH.add(box1);
   boxH.add(box2);
   boxH.add(box3);
   boxH.add(box4);
   boxH.add(box5);
   boxH.add(box6);
   boxH.add(Box.createVerticalGlue());           
   JPanel pCenter=new JPanel();
   pCenter.add(boxH);
   setLayout(new BorderLayout());
   add(pCenter,BorderLayout.CENTER);
   JPanel pSouth=new JPanel();
   pSouth.add(¼���޸�);
   pSouth.add(����);
   add(pSouth,BorderLayout.SOUTH);
   validate();
  }
 public void actionPerformed(ActionEvent e)
 { 
   if(e.getSource()==��ʼ�޸�||e.getSource()==ѧ��)
      {
         String number="";
        number=ѧ��.getText();
        
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    ������Ϣ��=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(������Ϣ��.containsKey(number))          
                 {
                   ¼���޸�.setEnabled(true);
                   Student stu=(Student)������Ϣ��.get(number);
                   ����.setText(stu.getName());
                   ��ѧרҵ.setText(stu.getDisciping());
                   ��ͥסַ.setText(stu.getGrade());
                   ��������.setText(stu.getBorth()); 
                   if(stu.getSex().equals("��"))
                      {
                        ��.setSelected(true);
                      }
                    else
                      {
                        Ů.setSelected(true);
                      }
                 } 
              else
                 { 
                  ¼���޸�.setEnabled(false);
                  
                  String warning="��ѧ�Ų�����!";
                  JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
                  ѧ��.setText(null);
                  ����.setText(null);                                
                  ��ѧרҵ.setText(null);
                  ��ͥסַ.setText(null);
                  ��������.setText(null);

                 }
            }
        else
            { 
              ¼���޸�.setEnabled(false); 
              
              String warning="����Ҫ����ѧ��!";
              JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
              ѧ��.setText(null);
              ����.setText(null);                                
              ��ѧרҵ.setText(null);
              ��ͥסַ.setText(null);
              ��������.setText(null);
            }
      } 
  else if(e.getSource()==¼���޸�)
      { 
        String number="";
        number=ѧ��.getText();
        if(number.length()>0)
           {
            try {
                 inOne=new FileInputStream(file);
                 inTwo=new ObjectInputStream(inOne);
                 ������Ϣ��=(Hashtable)inTwo.readObject();
                 inOne.close();
                 inTwo.close(); 
                }
            catch(Exception ee)
                {
                }
            if(������Ϣ��.containsKey(number))          
                {
                  String question="����������Ϣ�Ѵ���,�����޸���(��)�Ļ�����Ϣ��?";
                  
                  JOptionPane.showMessageDialog(this,question,"����",JOptionPane.QUESTION_MESSAGE);
                   
                  String m="������Ϣ�����޸�!";
                  int ok=JOptionPane.showConfirmDialog(this,m,"ȷ��",JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.INFORMATION_MESSAGE);
                  if(ok==JOptionPane.YES_OPTION)
                    {
                      String name=����.getText();
                      String discipling=��ѧרҵ.getText();
                      String grade=��ͥסַ.getText();
                      String borth=��������.getText();
                      String sex=null;
                        if(��.isSelected())
                           {
                             sex=��.getText();
                           }
                        else
                           {
                             sex=Ů.getText();
                           }
                     Student  ѧ��=new Student();
                     ѧ��.setNumber(number);
                     ѧ��.setName(name);
                     ѧ��.setDiscipling(discipling);
                     ѧ��.setGrade(grade);
                     ѧ��.setBorth(borth);
                     ѧ��.setSex(sex);
                     try
                      {
                       outOne=new FileOutputStream(file);
                       outTwo=new ObjectOutputStream(outOne);
                       ������Ϣ��.put(number,ѧ��);
                       outTwo.writeObject(������Ϣ��);
                       outTwo.close();
                       outOne.close();
                       ѧ��.setText(null);
                       ����.setText(null);                                
                       ��ѧרҵ.setText(null);
                       ��ͥסַ.setText(null);
                       ��������.setText(null);
                      }
                     catch(Exception ee)
                      { 
                       System.out.println(ee);
                      }
                     ¼���޸�.setEnabled(false); 
                   }
                 else if(ok==JOptionPane.NO_OPTION)
                   {
                     ¼���޸�.setEnabled(true);
                   }
               }
             else
               {
                
                 String warning="��ѧ��û�л�����Ϣ,�����޸�!";
                 JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
                 ¼���޸�.setEnabled(false); 
               }
           }
        else
           {
              String warning="����Ҫ����ѧ��!";
              JOptionPane.showMessageDialog(this,warning,"����",JOptionPane.WARNING_MESSAGE);
              ¼���޸�.setEnabled(false);
           }
       }
   if(e.getSource()==����)
      { 
        ѧ��.setText(null);
        ����.setText(null);
        ��ѧרҵ.setText(null);
        ��ͥסַ.setText(null);
        ��������.setText(null);
      }
  }
}
