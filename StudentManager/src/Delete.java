import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Delete extends JPanel implements ActionListener
{ 
  Hashtable 基本信息表=null;                           
  JTextField 学号,姓名,所学专业,家庭住址,出生日期;                 
  JRadioButton 男,女;
  JButton 删除;
  ButtonGroup group=null;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  FileOutputStream outOne=null;
  ObjectOutputStream outTwo=null;
  File file=null;                                           
  public Delete(File file)
  {
   this.file=file;
   学号=new JTextField(10);
   删除=new JButton("删除");
   学号.addActionListener(this);
   删除.addActionListener(this);
   姓名=new JTextField(10);
   姓名.setEditable(false);
   所学专业=new JTextField(10);
   所学专业.setEditable(false);
   家庭住址=new JTextField(10);
   家庭住址.setEditable(false);
   出生日期=new JTextField(10);
   出生日期.setEditable(false);
   男=new JRadioButton("男",false);
   女=new JRadioButton("女",false);
   group=new ButtonGroup();
   group.add(男);
   group.add(女);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("输入要删除的学号:",JLabel.CENTER));
   box1.add(学号);
   box1.add(删除);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("姓名:",JLabel.CENTER));
   box2.add(姓名);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("性别:",JLabel.CENTER));
   box3.add(男);
   box3.add(女);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("所学专业:",JLabel.CENTER));
   box4.add(所学专业);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("家庭住址:",JLabel.CENTER));
   box5.add(家庭住址);
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("出生日期:",JLabel.CENTER));
   box6.add(出生日期);
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
   validate();
  }
 public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==删除||e.getSource()==学号)
      {
        String number="";
        number=学号.getText();
        
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    基本信息表=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(基本信息表.containsKey(number))          
                 {
                   Student stu=(Student)基本信息表.get(number);
                   姓名.setText(stu.getName());
                   所学专业.setText(stu.getDisciping());
                   家庭住址.setText(stu.getGrade());
                   出生日期.setText(stu.getBorth()); 
                   if(stu.getSex().equals("男"))
                      {
                        男.setSelected(true);
                      }
                    else
                      {
                        女.setSelected(true);
                      }
                  String m="确定要删除该学号及全部信息吗?";
                  int ok=JOptionPane.showConfirmDialog(this,m,"确认",JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.QUESTION_MESSAGE);
                  if(ok==JOptionPane.YES_OPTION)
                     {
                       基本信息表.remove(number);
                       try
                        {
                          outOne=new FileOutputStream(file);
                          outTwo=new ObjectOutputStream(outOne);
                          outTwo.writeObject(基本信息表);
                          outTwo.close();
                          outOne.close();
                          学号.setText(null);
                          姓名.setText(null);                                
                          所学专业.setText(null);
                          家庭住址.setText(null);
                          出生日期.setText(null);
                        }
                       catch(Exception ee)
                        { 
                         System.out.println(ee);
                        }
                     }
                   else if(ok==JOptionPane.NO_OPTION)
                     {
                        学号.setText(null);
                        姓名.setText(null);
                        所学专业.setText(null);
                        家庭住址.setText(null);
                        出生日期.setText(null);
                     }
                 }
              else
                 { 
                  String warning="该学号不存在!";
                  JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
                 }
            }
        else
            { 
              String warning="必须要输入学号!";
              JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
            }
      } 
  }
}
